package com.meng.sleeve.repository;

import com.meng.sleeve.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    @Query("select c from Coupon c\n" +
            "join c.categoryList ca\n" +
            "join Activity a on a.id=c.activityId\n" +
            "where ca.id=:cid\n" +
            "and a.startTime<:now\n" +
            "and a.endTime>:now")
    List<Coupon> findByCategory(Long cid, Date now);

    @Query("select c from Coupon c\n" +
            "join Activity a on c.activityId=a.id\n" +
            "where c.wholeStore=:isWholeStore\n" +
            "and a.startTime<:now\n" +
            "and a.endTime>:now")
    List<Coupon> findByWholeStore(Boolean isWholeStore, Date now);

    //    user coupon user_coupon
    @Query("select c from Coupon c\n" +
            "join UserCoupon uc\n" +
            "on c.id=uc.couponId\n" +
            "join User u\n" +
            "on u.id=uc.userId\n" +
            "where uc.status=1\n" +
            "and u.id=:uid\n" +
            "and c.startTime<:now\n" +
            "and c.endTime>:now\n" +
            "and uc.orderId is null")
    List<Coupon> findMyAvailable(Long uid, Date now);

    @Query("select c from Coupon c\n" +
            "join UserCoupon uc\n" +
            "on c.id=uc.couponId\n" +
            "join User u\n" +
            "on u.id=uc.userId\n" +
            "where uc.status=2\n" +
            "and u.id=:uid\n" +
            "and c.startTime<:now\n" +
            "and c.endTime>:now\n" +
            "and uc.orderId is null")
    List<Coupon> findMyUsed(Long uid, Date now);

    @Query("select c from Coupon c\n" +
            "join UserCoupon uc\n" +
            "on c.id=uc.couponId\n" +
            "join User u\n" +
            "on u.id=uc.userId\n" +
            "where uc.status<>2\n" +
            "and u.id=:uid\n" +
            "and c.endTime<:now\n" +
            "and uc.orderId is null")
    List<Coupon> findMyExpired(Long uid, Date now);
}
