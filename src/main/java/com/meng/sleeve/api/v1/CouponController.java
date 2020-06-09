package com.meng.sleeve.api.v1;

import com.meng.sleeve.model.Coupon;
import com.meng.sleeve.service.CouponService;
import com.meng.sleeve.vo.CouponPureVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

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

}
