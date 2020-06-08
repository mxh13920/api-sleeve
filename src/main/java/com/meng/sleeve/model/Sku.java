package com.meng.sleeve.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.meng.sleeve.utils.GenericAndJson;
import com.meng.sleeve.utils.ListAndJson;
import com.meng.sleeve.utils.MapAndJson;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Entity
@Setter
@Getter
public class Sku extends BaseEntity{
    @Id
    private Long id;
    private BigDecimal price;
    private BigDecimal discountPrice;
    private Boolean online;
    private String img;
    private String title;
    private Long spuId;
    private String code;
    private Long stock;
    private Long categoryId;
    private Long rootCategoryId;

    private String specs;

    public List<Spec> getSpecs() {
        if (this.specs == null) {
            return Collections.emptyList();
        }
        return GenericAndJson.jsonToObject(this.specs, new TypeReference<List<Spec>>(){});
    }

    public void setSpecs(List<Spec> specs) {
        if(specs.isEmpty()){
            return;
        }
        this.specs = GenericAndJson.objectToJson(specs);
    }
//    @Convert(converter = ListAndJson.class)
//    private List<Object> specs;

//    @Convert(converter = MapAndJson.class)
//    private Map<String,Objects> test;
}
