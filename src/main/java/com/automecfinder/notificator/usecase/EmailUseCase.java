package com.automecfinder.notificator.usecase;

import com.automecfinder.notificator.config.properties.EmailProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Service
@Slf4j
@AllArgsConstructor
public class EmailUseCase {

    private EmailProperties emailProperties;

    public void sendMail(String to, String subject, String body) throws MessagingException, javax.mail.MessagingException {

        Properties properties = new Properties();
        properties.put("mail.smtp.host", emailProperties.getSmtp().getHost());
        properties.put("mail.smtp.socketFactory.port", emailProperties.getSmtp().getSocketFactory().getPort());
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", emailProperties.getSmtp().getAuth());
        properties.put("mail.smtp.port", emailProperties.getSmtp().getPort());

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailProperties.getCredential().getUser(), emailProperties.getCredential().getPassword());
            }
        });

        session.setDebug(true);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(body, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress());
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setContent(multipart);

        Transport.send(message);

        log.info("email enviado!!!");
    }

//    public void sendMail(List<String> tos, String subject, String body) throws MessagingException, javax.mail.MessagingException {
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
//        mimeMessageHelper.setTo(tos.toArray(new String[tos.size()]));
//        send(mimeMessage, mimeMessageHelper, subject, body);
//    }
//
//    private void send(MimeMessage message, MimeMessageHelper mimeMessageHelper, String subject, String body) throws MessagingException {
//        mimeMessageHelper.setFrom(templateEmail.getEmailFrom());
//        mimeMessageHelper.setSubject(subject);
//        mimeMessageHelper.setText(templateEmail.getHtmlTemplate(body), true);
//        javaMailSender.send(message);
//    }
}
