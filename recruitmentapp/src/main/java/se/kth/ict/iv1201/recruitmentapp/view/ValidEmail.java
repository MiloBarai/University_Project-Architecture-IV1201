package se.kth.ict.iv1201.recruitmentapp.view;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

/**
 * The annotated target is checked to be valid.
 */
@Constraint(validatedBy = ValidEmail.EmailValidator.class)
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEmail {

    String message() default "{se.kth.ict.iv1201.beanvalidation.view.ValidEmail.invalidZipCode}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class EmailValidator implements ConstraintValidator<ValidEmail, String> {

        @Override
        public void initialize(ValidEmail constraintAnnotation) {
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (isEmpty(value, context)) {
                return false;
            }
            try {
                return value.length() == 5;
            } catch (NumberFormatException nfe) {
                return false;
            }
        }

        private boolean isEmpty(String value, ConstraintValidatorContext context) {
            if (value.length() == 0) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("{se.kth.ict.iv1201.beanvalidation.view.ValidZipCode.noZip}").addConstraintViolation();
                return true;
            }
            return false;
        }
    }
}
