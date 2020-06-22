package com.meng.sleeve.core.money;

import java.math.BigDecimal;

public interface IMoneyDiscount {

     BigDecimal disCount(BigDecimal original,BigDecimal discount);
}
