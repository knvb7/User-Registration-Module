package com.trading.alphatrading.utils;

import com.trading.alphatrading.exception.AlgoTradingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MailSenderUtil {

    private final Logger logger = LogManager.getLogger(MailSenderUtil.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.password}")
    private String password;

    public void sendEmail(String fromEmail, List<String> toEmailIds, String subject, String body) throws AlgoTradingException {
        int maxRetries = 3;

        int attempt = 0;
        boolean emailSent = false;

        logger.info("fromEmail : {}, toEmailIds : {}, subject : {}, body : {}", fromEmail, toEmailIds, subject, body);

        while (attempt < maxRetries && !emailSent) {
            attempt++;
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(fromEmail);
                message.setTo(toEmailIds.toArray(new String[0]));
                message.setSubject(subject);
                message.setText(body);
                logger.info("Seewwerw + "+password);

                mailSender.send(message);
                logger.info("Email sent successfully on attempt {}", attempt);
                emailSent = true;
            } catch (Exception e) {
                logger.error("Error while sending email (Attempt {}): {}", attempt, e.getMessage(), e);
                if (attempt < maxRetries) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        logger.error("Retry interrupted: {}", ie.getMessage(), ie);
                    }
                }
            }
        }

        if (!emailSent) {
            String errorMessage = "Failed to send email for the payload: "
                    + "fromEmail: " + fromEmail
                    + ", toEmailIds: " + toEmailIds
                    + ", subject: " + subject
                    + ", body: " + body;
            logger.error(errorMessage);
            throw new AlgoTradingException(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR, logger);
        }
    }
}