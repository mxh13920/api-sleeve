package com.meng.sleeve.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
public class CouponCategory {
    @Id
    private int id;
    private int categoryId;
    private int couponId;

}
