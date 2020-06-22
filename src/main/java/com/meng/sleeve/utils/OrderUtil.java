package com.meng.sleeve.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Calendar;

@Component
public class OrderUtil {
    // B3230651812529
    private static String[] yearCodes;

    @Value("${sleeve.year-codes}")
    public void setYearCodes(String yearCodes) {
        String[] chars = yearCodes.split(",");
        OrderUtil.yearCodes = chars;
    }

    public static String madeOrderNo() {
        StringBuffer joiner = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        String mills = String.valueOf(calendar.getTimeInMillis());
        String micro = LocalDate.now().toString();
        String random = String.valueOf(Math.random() * 1000).substring(0, 2);
        joiner.append(OrderUtil.yearCodes[calendar.get(Calendar.YEAR) - 2020])
                .append(Integer.toHexString(calendar.get(Calendar.MONTH) + 1).toUpperCase())
                .append(mills.substring(mills.length() - 5, mills.length()))
                .append(micro.substring(micro.length()-3, micro.length()))
                .append(random);
        return joiner.toString();
    }
}
