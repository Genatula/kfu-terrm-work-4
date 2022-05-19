package ru.kpfu.itis.genatulin.termwork.validation;

import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.List;

public class OneFilledFieldValidator implements ConstraintValidator<OneFilledField, Object> {
    @SneakyThrows
    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Class<?> entityClass = o.getClass();
        List<Field> fields = List.of(entityClass.getFields());
        for (Field field : fields) {
            if (field.get(o) != null || !field.get(o).equals("")) {
                return true;
            }
        }
        return false;
    }
}
