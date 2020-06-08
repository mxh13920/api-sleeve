package com.meng.sleeve.dto.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.xml.bind.Element;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.TYPE, ElementType.FIELD})
@Constraint(validatedBy = TokenPasswordValidator.class)
public @interface TokenPassword {
    String message() default "字段不符合要求";

    int min() default 6;
    int max() default 32;

    //     必须加入
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
