package com.meng.sleeve.bo;

import com.meng.sleeve.dto.SkuInfoDTO;
import com.meng.sleeve.model.Sku;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class SkuOrderBO {
    private BigDecimal actualPrice;
    private Integer count;
    private Long categoryId;

    public SkuOrderBO(Sku sku, SkuInfoDTO skuInfoDTO) {
        this.actualPrice=sku.getActualPrice();
        this.count=skuInfoDTO.getCount();
        this.categoryId=sku.getCategoryId();
    }

    public BigDecimal getTotalPrice(){
        return this.getActualPrice().multiply(new BigDecimal(this.count));
    }
}
