package com.meng.sleeve.utils;

import com.meng.sleeve.bo.PageCount;
import org.springframework.beans.factory.annotation.Autowired;

public class CommonUtils {

    public static PageCount convertToPageParameter(Integer star,Integer count){
        int pageCount=star/count;
        PageCount build = PageCount.builder()
                .page(pageCount)
                .count(count)
                .build();
        return build;
    }
}
