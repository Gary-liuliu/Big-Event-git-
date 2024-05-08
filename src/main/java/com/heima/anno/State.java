package com.heima.anno;

import com.heima.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotEmpty;

import java.lang.annotation.*;


@Documented
@Constraint(
        validatedBy = {StateValidation.class}//指定提供校验规则的类
)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface State {
    String message() default "state参数的值只能是已发布或者保存";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}