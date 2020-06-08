package com.meng.sleeve.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
public class BannerItem extends BaseEntity{
    @Id
    private long id;
    private String img;
    private String keyword;
    private short type;
    private long bannerId;
    private String name;

}
