package com.meng.sleeve.service;

import com.meng.sleeve.model.Coupon;
import com.meng.sleeve.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    public List<Coupon> getByCategory(Long cid){
        Date now =new Date();
        return couponRepository.findByCategory(cid, now);
    }

    public List<Coupon> getWholeStore(Boolean isWholeStore){
        Date now =new Date();
        return couponRepository.findByWholeStore(isWholeStore,now);
    }

}

