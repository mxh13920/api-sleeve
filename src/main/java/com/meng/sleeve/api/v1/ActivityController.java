package com.meng.sleeve.api.v1;

import com.meng.sleeve.exception.http.NotFoundException;
import com.meng.sleeve.model.Activity;
import com.meng.sleeve.service.ActivityService;
import com.meng.sleeve.vo.ActivityCouponPureVO;
import com.meng.sleeve.vo.ActivityPureVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping("/name/{name}")
    public ActivityPureVo getHomeActivity(@PathVariable String name){
        Activity a = activityService.getByName(name);
        if (a == null) {
            throw new NotFoundException(40001);
        }
        return new ActivityPureVo(a);
    }

    @GetMapping("/by/name/{name}/with_coupon")
    public ActivityCouponPureVO getActivityCoupon(@PathVariable String name){
        Activity activity = activityService.getByName(name);
        if (activity == null) {
            throw new NotFoundException(40001);
        }
//        return activity;
        return new ActivityCouponPureVO(activity);
    }
}
