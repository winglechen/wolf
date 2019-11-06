package study.daydayup.wolf.model.demos.hello.validator;

import study.daydayup.wolf.model.demos.hello.annotation.CannotContainSpaces;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Path;
import javax.validation.metadata.ConstraintDescriptor;

/**
 * study.daydayup.wolf.model.demos.hello.validator
 *
 * @author Wingle
 * @since 2019/10/16 11:30 下午
 **/
public class CannotContainSpacesValidator implements ConstraintValidator<CannotContainSpaces, String> {
    private int len;

    @Override
    public void initialize(CannotContainSpaces arg) {
        this.len = arg.length();
    }

    @Override
    public boolean isValid(String str, ConstraintValidatorContext context) {
        if ( str == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("字符串不能为空")
                    .addConstraintViolation();
            return false;
        }

        return str.indexOf(" ") < 0;
    }

}
