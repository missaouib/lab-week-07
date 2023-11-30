package com.minh.labweek07.backend.service;

import com.minh.labweek07.backend.models.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    public void sendMail(Email email){
        try{
            org.springframework.mail.SimpleMailMessage msg = new org.springframework.mail.SimpleMailMessage();
            msg.setTo(email.getTo());
            msg.setSubject(email.getSubject());
            msg.setText(email.getBody());
            javaMailSender.send(msg);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
