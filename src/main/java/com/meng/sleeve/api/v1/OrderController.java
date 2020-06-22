package com.meng.sleeve.api.v1;

import com.meng.sleeve.bo.PageCount;
import com.meng.sleeve.core.LocalUser;
import com.meng.sleeve.core.annotations.ScopeLevel;
import com.meng.sleeve.core.enumeration.OrderStatus;
import com.meng.sleeve.dto.OrderDTO;
import com.meng.sleeve.exception.http.NotFoundException;
import com.meng.sleeve.logic.OrderChecker;
import com.meng.sleeve.model.Order;
import com.meng.sleeve.service.OrderService;
import com.meng.sleeve.utils.CommonUtils;
import com.meng.sleeve.vo.OrderIdVO;
import com.meng.sleeve.vo.OrderPureVO;
import com.meng.sleeve.vo.OrderSimplifyVO;
import com.meng.sleeve.vo.PagingDozer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.print.PageableDoc;

import java.util.Optional;

@RestController
@RequestMapping("/order")
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Value("${sleeve.order.pay-time-limit}")
    private Long payTimeLimit;

    @PostMapping("")
    @ScopeLevel()
    public OrderIdVO placeOrder(@RequestBody OrderDTO orderDTO) {
        Long uid = LocalUser.getUser().getId();
        OrderChecker orderChecker = this.orderService.isOk(uid, orderDTO);
        Long oid = this.orderService.placeOrder(uid, orderDTO, orderChecker);
        return new OrderIdVO(oid);
    }

    @ScopeLevel
    @GetMapping("/status/unpaid")
    @SuppressWarnings("unchecked")
    public PagingDozer getUnpaid(@RequestParam(defaultValue = "0")
                                 Integer start,
                                 @RequestParam(defaultValue = "10")
                                 Integer count){
        PageCount page = CommonUtils.convertToPageParameter(start, count);
        Page<Order> orderPage = this.orderService.getUnpaid(page.getPage(), page.getCount());
        PagingDozer pagingDozer = new PagingDozer<>(orderPage, OrderSimplifyVO.class);
        pagingDozer.getItems().forEach((o) -> ((OrderSimplifyVO) o).setPeriod(this.payTimeLimit));
        return pagingDozer;
    }


    @ScopeLevel
    @GetMapping("/by/Status/{status}")
    @SuppressWarnings("unchecked")
    public PagingDozer getByStatus(@PathVariable int status,
                                   @RequestParam(defaultValue = "0") Integer start,
                                   @RequestParam(defaultValue = "10") Integer count){
        PageCount pageCount = CommonUtils.convertToPageParameter(start, count);
        Page<Order> orders = this.orderService.getByStatus(status, pageCount.getPage(), pageCount.getCount());
        PagingDozer pagingDozer = new PagingDozer<>(orders, OrderSimplifyVO.class);
        pagingDozer.getItems().forEach((o) -> ((OrderSimplifyVO) o).setPeriod(this.payTimeLimit));
        return pagingDozer;
    }

    @ScopeLevel
    @GetMapping("/detail/{id}")
    public OrderPureVO getOrderDetail(@PathVariable(name = "id") Long oid){
        Optional<Order> orderDetail = this.orderService.getOrderDetail(oid);
        return orderDetail.map(o->new OrderPureVO(o,this.payTimeLimit))
                .orElseThrow(()->new NotFoundException(50009));
    }

}
