package com.meng.sleeve.core.money;

import java.awt.image.ImageConsumer;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class HalfUpRound implements IMoneyDiscount {
    @Override
    public BigDecimal disCount(BigDecimal original, BigDecimal discount) {
        BigDecimal actualMoney=original.multiply(discount);
        return actualMoney.setScale(2, RoundingMode.HALF_UP);
    }
}
