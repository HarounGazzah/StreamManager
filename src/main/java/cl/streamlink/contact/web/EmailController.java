package cl.streamlink.contact.web;


import cl.streamlink.contact.domain.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.inject.Inject;

@RestController
@RequestMapping("/ws/mail")
public class EmailController {

    @Resource
    public JavaMailSender javaMailSender;

    @PostMapping
    public String sendEmail(@RequestBody Email email) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getTo());
        message.setSubject(email.getMessageSubject());
        message.setText(email.getMessageBody());

        javaMailSender.send(message);

        return "Successfully sent Email";
    }




}
