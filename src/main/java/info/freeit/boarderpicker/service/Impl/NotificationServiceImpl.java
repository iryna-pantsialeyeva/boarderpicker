package info.freeit.boarderpicker.service.Impl;

import info.freeit.boarderpicker.entity.User;
import info.freeit.boarderpicker.service.NotificationService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final JavaMailSender javaMailSender;
    private final String from = "boarderpicker.noreply@gmail.com";
    private final String subject = "New sale is available";
    private final String text = "Dear %s, game from your subscription list is on sale now!";

    public NotificationServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendNotification(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject(subject);
        mailMessage.setText(String.format(text, user.getUserName()));
        javaMailSender.send(mailMessage);
    }
}
