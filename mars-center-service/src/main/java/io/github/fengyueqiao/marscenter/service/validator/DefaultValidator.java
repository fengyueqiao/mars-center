package io.github.fengyueqiao.marscenter.service.validator;

import io.github.fengyueqiao.marscenter.common.exception.BizException;
import org.apache.commons.lang3.ClassUtils;
import org.hibernate.validator.HibernateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Set;

public class DefaultValidator {

    private static Logger logger = LoggerFactory.getLogger(DefaultValidator.class);

    private ValidatorFactory factory = Validation.byProvider(HibernateValidator.class).configure().failFast(true)
            .messageInterpolator(new MessageInterpolator()).buildValidatorFactory();

    /**
     * Recursively do validation to the target if the field is not primitive or wrapped.
     *
     * @param target
     */
    public void validate(Object target){
        if (target == null) {
            return;
        }
        doValidation(target);
        Field[] fields = target.getClass().getDeclaredFields();
        for (Field field : fields){
            if(isChildField(field.getType())){
                continue;
            }
            Object fieldValue = null;
            try {
                field.setAccessible(true);
                fieldValue = field.get(target);
            } catch (IllegalAccessException e) {
                continue;
            }

            //Recursively validate
            validate(fieldValue);
        }
    }

    private boolean isChildField(Class type){
        return type.equals(String.class) || ClassUtils.isPrimitiveOrWrapper(type)
                || type.equals(InputStream.class)
                || type.equals(OutputStream.class)
                || type.isEnum();
    }

    public void doValidation(Object target) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(target);
        constraintViolations.forEach(violation -> {
            logger.debug("Field: "+violation.getPropertyPath() + " Message: " + violation.getMessage());
            throw new BizException("[" + violation.getPropertyPath() + "] " + violation.getMessage());
        });
    }
}