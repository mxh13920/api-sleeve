package com.meng.sleeve.core.money;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class UpRound implements IMoneyDiscount{
    @Override
    public BigDecimal disCount(BigDecimal original, BigDecimal discount) {
        BigDecimal multiply = original.multiply(discount);
        return multiply.setScale(2, RoundingMode.UP);
    }
}
