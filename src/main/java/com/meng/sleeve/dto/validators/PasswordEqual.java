package com.meng.sleeve.dto.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface PasswordEqual {
     String message() default "password is different ";

//     必须加入
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
