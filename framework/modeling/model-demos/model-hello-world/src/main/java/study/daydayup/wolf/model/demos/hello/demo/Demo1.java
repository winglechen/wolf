package study.daydayup.wolf.model.demos.hello.demo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import study.daydayup.wolf.model.demos.hello.annotation.CannotContainSpaces;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Set;

/**
 * study.daydayup.wolf.model.demos.hello.demo
 *
 * @author Wingle
 * @since 2019/10/16 9:49 下午
 **/
@Data
public class Demo1 {
    @Length(min=2,max=30)
    @CannotContainSpaces
    private String name;
    @Max(100) @Min(0)
    private int age;

    public static void main(String[] args) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Demo1 demo = new Demo1();
        demo.setName("wingle chen");
        demo.setAge(18);

        Set< ConstraintViolation<Demo1> > constraintViolations = validator.validate(demo);
        for (ConstraintViolation<Demo1> constraintViolation : constraintViolations) {
            System.out.println("对象属性：" + constraintViolation.getPropertyPath());
            System.out.println("国际化key: " + constraintViolation.getMessageTemplate());
            System.out.println("错误信息：" + constraintViolation.getMessage());
        }

    }
}
