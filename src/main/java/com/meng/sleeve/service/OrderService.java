package com.meng.sleeve.service;

import com.meng.sleeve.core.LocalUser;
import com.meng.sleeve.core.enumeration.OrderStatus;
import com.meng.sleeve.core.money.IMoneyDiscount;
import com.meng.sleeve.dto.OrderDTO;
import com.meng.sleeve.dto.SkuInfoDTO;
import com.meng.sleeve.exception.http.ForbiddenException;
import com.meng.sleeve.exception.http.NotFoundException;
import com.meng.sleeve.exception.http.ParameterException;
import com.meng.sleeve.logic.CouponChecker;
import com.meng.sleeve.logic.OrderChecker;
import com.meng.sleeve.model.*;
import com.meng.sleeve.repository.CouponRepository;
import com.meng.sleeve.repository.OrderRepository;
import com.meng.sleeve.repository.SkuRepository;
import com.meng.sleeve.repository.UserCouponRepository;
import com.meng.sleeve.utils.CommonUtils;
import com.meng.sleeve.utils.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private SkuService skuService;

    @Autowired
    private SkuRepository skuRepository;

    @Autowired
    private UserCouponRepository userCouponRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private IMoneyDiscount iMoneyDiscount;

    @Value("${sleeve.order.max-sku-limit}")
    private Integer maxSkuLimit;
    @Value("${sleeve.order.pay-time-limit}")
    private Integer payTimeLimit;

    @Transactional
    public Long placeOrder(Long uid, OrderDTO orderDTO, OrderChecker orderChecker) {
        String orderNo = OrderUtil.madeOrderNo();

        Calendar now = Calendar.getInstance();
        Calendar now1 = (Calendar) now.clone();
        Date expired = CommonUtils.addSomeSecond(now, this.payTimeLimit).getTime();

        Order order = Order.builder()
                .orderNo(orderNo)
                .totalPrice(orderDTO.getTotalPrice())
                .finalTotalPrice(orderDTO.getFinalTotalPrice())
                .userId(uid)
                .totalCount(orderChecker.getTotalCount().longValue())
                .snapImg(orderChecker.getLeaderImg())
                .snapTitle(orderChecker.getLeaderTitle())
                .status(OrderStatus.UNPAID.value())
                .expiredTime(expired)
                .placedTime(now1.getTime())
                .build();

        order.setSnapAddress(orderDTO.getAddress());
        order.setSnapItems(orderChecker.getOrderSkuList());
        this.orderRepository.save(order);

        //reduceStock
        this.reduceStock(orderChecker);
        //核销优惠券
        if (orderDTO.getCouponId() != null) {
            this.writeOffCoupon(orderDTO.getCouponId(), order.getId(), uid);
        }
        //加入到延迟消息队列
        return order.getId();
    }


    public OrderChecker isOk(Long uid, OrderDTO orderDTO) {
        if (orderDTO.getFinalTotalPrice().compareTo(new BigDecimal("0")) <= 0) {
            throw new ParameterException(50011);
        }

        List<Long> skuIdList = orderDTO.getSkuInfoList()
                .stream()
                .map(SkuInfoDTO::getId)
                .collect(Collectors.toList());

        List<Sku> skuList = skuService.getSkuListByIds(skuIdList);

        Long couponId = orderDTO.getCouponId();
        CouponChecker couponChecker = null;
        if (couponId != null) {
            Coupon coupon = this.couponRepository.findById(couponId)
                    .orElseThrow(() -> new NotFoundException(40004));
            UserCoupon userCoupon = this.userCouponRepository.findFirstByUserIdAndCouponIdAndStatus(uid, couponId, 1)
                    .orElseThrow(() -> new NotFoundException(50006));
            couponChecker = new CouponChecker(coupon, iMoneyDiscount);
        }
        OrderChecker orderChecker = new OrderChecker(
                orderDTO, skuList, couponChecker, this.maxSkuLimit
        );
        orderChecker.isOK();
        return orderChecker;
    }

    public Page<Order> getUnpaid(Integer page, Integer size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by("createTime").descending());

        Date now = new Date();
        Long id = LocalUser.getUser().getId();
        return this.orderRepository.findByExpiredTimeGreaterThanAndStatusAndAndUserId(now, OrderStatus.UNPAID.value(), id, pageable);
    }

    public Page<Order> getByStatus(int status, Integer page, Integer size) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by("createTime").descending());
        Long uid = LocalUser.getUser().getId();
        if (status == OrderStatus.All.value()) {
            return this.orderRepository.findByUserId(uid, pageable);
        }
        return this.orderRepository.findByStatusAndUserId(status, uid, pageable);
    }

    public Optional<Order> getOrderDetail(Long oid) {
        Long uid = LocalUser.getUser().getId();
        return this.orderRepository.findByUserIdAndId(uid, oid);
    }

    private void writeOffCoupon(Long couponId, Long oid, Long uid) {
        int result = this.userCouponRepository.writeOff(couponId, oid, uid);
        if (result != 1) {
            throw new ForbiddenException(40012);
        }
    }


    private void reduceStock(OrderChecker orderChecker) {
        List<OrderSku> orderSkuList = orderChecker.getOrderSkuList();
        for (OrderSku orderSku : orderSkuList) {
            int result = this.skuRepository.reduceStock(orderSku.getId(), orderSku.getCount().longValue());
            if (result != 1) {
                throw new ParameterException(50003);
            }
        }
    }


}
