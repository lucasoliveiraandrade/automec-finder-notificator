package com.automecfinder.notificator.usecase;

import com.automecfinder.notificator.config.properties.EmailProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private static final String TEXT_HTML_CHARSET_UTF_8 = "text/html; charset=utf-8";

    private EmailProperties emailProperties;

    public Boolean sendMail(String to, String subject, String body) {

        log.info("Sending email to {}", to);

        boolean emailSent = false;

        try {
            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(body, TEXT_HTML_CHARSET_UTF_8);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            Message message = new MimeMessage(getSession());
            message.setFrom(new InternetAddress());
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setContent(multipart);

            Transport.send(message);

            log.info("Email sent successfully");
            emailSent = true;

        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            return emailSent;
        }
    }

    private Session getSession() {
        Properties properties = getProperties();

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailProperties.getCredential().getUser(), emailProperties.getCredential().getPassword());
            }
        });

        session.setDebug(true);
        return session;
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", emailProperties.getSmtp().getHost());
        properties.put("mail.smtp.socketFactory.port", emailProperties.getSmtp().getSocketFactory().getPort());
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", emailProperties.getSmtp().getAuth());
        properties.put("mail.smtp.port", emailProperties.getSmtp().getPort());
        return properties;
    }
}
