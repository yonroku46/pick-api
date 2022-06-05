package com.pick.service.impl;

import com.pick.model.EmailTO;
import com.pick.service.EmailService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Boolean sendMail(EmailTO mail) {
        try {
            MimeMessage msg = mailSender.createMimeMessage();

            mail.setSenderMail("teambepo@gmail.com");
            mail.setSenderName("pick");

            // 받는 사람을 설정 (수신자, 받는사람의 이메일 주소 객체를 생성해서 수신자 이메일주소를 담음)
            msg.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(mail.getReceiveMail()));

            // 보내는 사람 (이메일주소+이름) (발신자, 보내는 사람의 이메일 주소와 이름을 담음)
            msg.addFrom(new InternetAddress[] { new InternetAddress(mail.getSenderMail(), mail.getSenderName()) });

            // 이메일 제목
            msg.setSubject(mail.getSubject(), "utf-8");
            // 이메일 본문
            msg.setText(mail.getMessage(), "utf-8");

//            html로 보낼 경우
//            MimeMessage message = mailSender.createMimeMessage();
//            MimeMessageHelper helper
//            = new MimeMessageHelper(message, true);
//            helper.setTo("test@host.com");
//            helper.setText("<html><body><img src='cid:identifier1234'></body></html>", true);

            mailSender.send(msg);
            return Boolean.TRUE;
        } catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }
}
