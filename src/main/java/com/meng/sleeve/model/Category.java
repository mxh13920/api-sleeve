package com.meng.sleeve.model;

import com.sun.imageio.plugins.common.LZWCompressor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Category extends BaseEntity{
    @Id
    private Long id;

    private String name;

    private String description;

    private Boolean isRoot;

    private String img;

    private Long parentId;

    private Long index;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "coupon")
//    private List<Coupon> c
}
