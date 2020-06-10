package com.meng.sleeve.api.v1;

import com.meng.sleeve.core.LocalUser;
import com.meng.sleeve.core.UnifyResponse;
import com.meng.sleeve.core.annotations.ScopeLevel;
import com.meng.sleeve.core.enumeration.CouponStatus;
import com.meng.sleeve.exception.http.ParameterException;
import com.meng.sleeve.model.Coupon;
import com.meng.sleeve.model.User;
import com.meng.sleeve.service.CouponService;
import com.meng.sleeve.vo.CouponCategoryVO;
import com.meng.sleeve.vo.CouponPureVO;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.rmi.server.UID;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @GetMapping("/by/category/{cid}")
    public List<CouponPureVO> getCouponByCategory(@PathVariable Long cid){
        List<Coupon> couponList = couponService.getByCategory(cid);
        if (couponList.isEmpty()) {
            return Collections.emptyList();
        }
        return CouponPureVO.getList(couponList);
    }

    @GetMapping("/whole_store")
    public List<CouponPureVO> getWholeStoreCouponList(){
        List<Coupon> wholeStore = couponService.getWholeStore(true);
        if (wholeStore.isEmpty()){
            return Collections.emptyList();
        }
        return CouponPureVO.getList(wholeStore);
    }

    @ScopeLevel()
    @PostMapping("/collect/{id}")
    public void collectCoupon(@PathVariable Long id) {
        Long uid = LocalUser.getUser().getId();
        couponService.collectOneCoupon(uid, id);
        UnifyResponse.createSuccess(0);
    }

    @ScopeLevel()
    @PostMapping("/myself/by/status/{status}")
    public List<CouponPureVO> getMyCouponByStatus(@PathVariable Integer status){
        Long id = LocalUser.getUser().getId();
        List<Coupon> couponList;
        switch (CouponStatus.toType(status)){
            case AVAILABLE:
                couponList=couponService.getMyAvailable(id);
                break;
            case USED:
                couponList=couponService.getMyUsed(id);
                break;
            case EXPIRED:
                couponList=couponService.getMyExpired(id);
                break;
            default:
                throw new ParameterException(40001);
        }
        return CouponPureVO.getList(couponList);
    }

    @ScopeLevel()
    @GetMapping("/myself/available/with_category")
    public List<CouponCategoryVO> getUserCouponWithCategory() {
        User user = LocalUser.getUser();
        List<Coupon> coupons = couponService.getMyAvailable(user.getId());
        if (coupons.isEmpty()) {
            return Collections.emptyList();
        }
        return coupons.stream().map(coupon -> {
            CouponCategoryVO vo = new CouponCategoryVO(coupon);
            return vo;
        }).collect(Collectors.toList());
    }
}
