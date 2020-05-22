package study.daydayup.wolf.middleware.notice.biz.email;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * study.daydayup.wolf.middleware.notice.biz.email
 *
 * @author Wingle
 * @since 2020/5/22 3:25 下午
 **/
@Component
public class SmtpSender {

    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private JavaMailSender mailSender;

    public void send(@NonNull String to, @NonNull String subject, @NonNull String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        mailSender.send(message);
    }

}
