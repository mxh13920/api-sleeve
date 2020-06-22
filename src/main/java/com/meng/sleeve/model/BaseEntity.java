package com.meng.sleeve.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
/**
 *
 为什么不设置entity？
 因为一个entity对应一个数据库表
 应该设施为mappedSuperclass（映射资源类）
 *
 */
@MappedSuperclass
public abstract class BaseEntity {

//    @JsonIgnore 不显示该属性
    @JsonIgnore
    @Column(insertable=false, updatable=false)
    private Date createTime;

    @JsonIgnore
    @Column(insertable=false, updatable=false)
    private Date updateTime;

    @Column(insertable=false, updatable=false)
    @JsonIgnore
    private Date deleteTime;
}
