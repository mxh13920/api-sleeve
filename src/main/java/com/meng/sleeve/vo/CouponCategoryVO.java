package com.meng.sleeve.vo;

import com.meng.sleeve.model.Category;
import com.meng.sleeve.model.Coupon;

import java.util.ArrayList;
import java.util.List;

public class CouponCategoryVO  extends CouponPureVO {
    private List<CategoryPureVO> categories = new ArrayList<>();

    public CouponCategoryVO(Coupon coupon) {
        super(coupon);
        List<Category> categories = coupon.getCategoryList();
        categories.forEach(category -> {
            CategoryPureVO vo = new CategoryPureVO(category);
            this.categories.add(vo);
        });
    }
}
