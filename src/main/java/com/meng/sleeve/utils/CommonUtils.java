package com.meng.sleeve.utils;

import com.meng.sleeve.bo.PageCount;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class CommonUtils {

    public static PageCount convertToPageParameter(Integer star,Integer count){
        int pageCount=star/count;
        PageCount build = PageCount.builder()
                .page(pageCount)
                .count(count)
                .build();
        return build;
    }

    public static Boolean isInTimeLine(Date date, Date start, Date end) {
        Long time = date.getTime();
        Long startTime = start.getTime();
        Long endTime = end.getTime();
        if (time > startTime && time < endTime) {
            return true;
        }
        return false;
    }
}
