package com.meng.sleeve.service;

import com.meng.sleeve.core.enumeration.CouponStatus;
import com.meng.sleeve.exception.http.NotFoundException;
import com.meng.sleeve.exception.http.ParameterException;
import com.meng.sleeve.model.Activity;
import com.meng.sleeve.model.Coupon;
import com.meng.sleeve.model.UserCoupon;
import com.meng.sleeve.repository.ActivityRepository;
import com.meng.sleeve.repository.CouponRepository;
import com.meng.sleeve.repository.UserCouponRepository;
import com.meng.sleeve.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private UserCouponRepository userCouponRepository;

    public List<Coupon> getByCategory(Long cid) {
        Date now = new Date();
        return couponRepository.findByCategory(cid, now);
    }

    public List<Coupon> getWholeStore(Boolean isWholeStore) {
        Date now = new Date();
        return couponRepository.findByWholeStore(isWholeStore, now);
    }

    public void collectOneCoupon(Long uid, Long couponId) {
        this.couponRepository
                .findById(couponId)
                .orElseThrow(() -> new NotFoundException(40003));

        Activity activity = this.activityRepository
                .findByCouponListId(couponId)
                .orElseThrow(() -> new NotFoundException(40010));

        Date now = new Date();
        Boolean isIn = CommonUtils.isInTimeLine(now, activity.getStartTime(), activity.getEndTime());
        if(!isIn){
            throw new ParameterException(40005);
        }

        this.userCouponRepository
                .findFirstByUserIdAndCouponId(uid, couponId)
                .ifPresent((uc) -> {throw new ParameterException(40006);});

        UserCoupon userCouponNew = UserCoupon.builder()
                .userId(uid)
                .couponId(couponId)
                .status(CouponStatus.AVAILABLE.getValue())
                .createTime(now)
                .build();
        userCouponRepository.save(userCouponNew);
    }

    public List<Coupon> getMyAvailable(Long uid){
        Date now = new Date();
        return couponRepository.findMyAvailable(uid,now);
    }

    public List<Coupon> getMyUsed(Long uid){
        Date now = new Date();
        return couponRepository.findMyUsed(uid,now);
    }

    public List<Coupon> getMyExpired(Long uid){
        Date now = new Date();
        return couponRepository.findMyExpired(uid,now);
    }

}

