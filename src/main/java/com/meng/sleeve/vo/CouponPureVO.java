package com.meng.sleeve.vo;

import com.meng.sleeve.model.Coupon;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CouponPureVO {
    private Long id;
    private String title;
    private Date startTime;
    private Date endTime;
    private String description;
    private BigDecimal fullMoney;
    private BigDecimal minus;
    private BigDecimal rate;
    private Integer type;
    private String remark;
    private Boolean wholeStore;

    public CouponPureVO(Object[] objects){
        Coupon coupon = (Coupon) objects[0];
        BeanUtils.copyProperties(coupon, this);
    }

    public CouponPureVO(Coupon coupon){
        BeanUtils.copyProperties(coupon, this);
    }

    public static List<CouponPureVO> getList(List<Coupon> couponList) {
        return couponList.stream()
                .map(CouponPureVO::new)
                .collect(Collectors.toList());
    }
}
