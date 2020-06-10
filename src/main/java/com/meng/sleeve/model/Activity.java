package com.meng.sleeve.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Activity extends BaseEntity{
    @Id
    private Long id;
    private String title;
    private String name;
    private String description;
    //    private Long activityCoverId;
    private Date startTime;
    private Date endTime;
    private Boolean online;
    private String entranceImg;
    private String internalTopImg;
    private String remark;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="activityId")
    private List<Coupon> couponList;
}
