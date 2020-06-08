package com.meng.sleeve.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
public class ActivityCoupon {
    @Id
    private Long id;
    private Long couponId;
    private Long activityId;


}
