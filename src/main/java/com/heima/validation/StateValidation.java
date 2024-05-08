package com.heima.validation;

import com.heima.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

//<给哪个注解提供校验规则，校验的数据类型>
public class StateValidation implements ConstraintValidator<State,String> {

    /**
     *
     * @param value 将来要校验的数据
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
//        提供校验规则
        if(value == null){
            return false;
        }
        if(value.equals("已发布")||value.equals("保存")){
            return true;
        }
        return false;
    }
}