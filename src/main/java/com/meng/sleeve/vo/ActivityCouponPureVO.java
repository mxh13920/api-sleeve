package com.meng.sleeve.vo;

import com.meng.sleeve.model.Activity;
import com.meng.sleeve.model.ActivityCoupon;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ActivityCouponPureVO extends ActivityPureVo {
    private List<CouponPureVO> coupons;

    public ActivityCouponPureVO(Activity activity) {
        super(activity);
        coupons=activity.getCouponList()
                .stream().map(CouponPureVO::new)
                .collect(Collectors.toList());
    }
}
