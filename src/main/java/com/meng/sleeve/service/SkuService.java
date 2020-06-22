package com.meng.sleeve.service;

import com.meng.sleeve.model.Sku;
import com.meng.sleeve.repository.SkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkuService {

    @Autowired
    private SkuRepository skuRepository;

    public List<Sku> getSkuListByIds(List<Long> ids){
        return skuRepository.findAllByIdIn(ids);
    }


}
