package com.meng.sleeve.vo;

import com.meng.sleeve.model.Category;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Setter
@Getter
public class CategoryPureVO {

    private Long id;

    private String name;

    private String description;

    private Boolean isRoot;

    private String img;

    private Long parentId;

    private Long index;

    public CategoryPureVO(Category category){
        BeanUtils.copyProperties(category,this);
    }

}
