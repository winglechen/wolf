package study.daydayup.wolf.model.demos.hello.annotation;

import study.daydayup.wolf.model.demos.hello.validator.CannotContainSpacesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * study.daydayup.wolf.model.demos.hello.annotation
 *
 * @author Wingle
 * @since 2019/10/16 11:27 下午
 **/
@Constraint(validatedBy = CannotContainSpacesValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CannotContainSpaces {
    String message() default "can not contain spaces";
    int length() default 5;

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
