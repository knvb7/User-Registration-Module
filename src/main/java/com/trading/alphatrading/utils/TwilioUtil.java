package com.trading.alphatrading.utils;

import com.trading.alphatrading.exception.AlgoTradingException;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class TwilioUtil {
    private final Logger logger = LogManager.getLogger(TwilioUtil.class);

    @Value("${twilio.accountSid}")
    private String twilioAccountSid;

    @Value("${twilio.authToken}")
    private String twilioAuthToken;

    @Value("${twilio.verifyServiceSid}")
    private String verifyServiceSid;

    public void sendOtpTwilio(String phoneNumber) throws AlgoTradingException {
        Twilio.init(twilioAccountSid, twilioAuthToken);
        int retryCount = 3;
        int attempt = 0;
        boolean success = false;

        while (attempt < retryCount && !success) {
            try {
                Verification verification = Verification.creator(
                                verifyServiceSid,
                                phoneNumber,
                                Constants.SMSCHANNEL)
                        .create();
                logger.info("OTP sent successfully for phone number: {}", phoneNumber);
                logger.info("Verification variable is : {}", verification);
                success = true;
            } catch (Exception e) {
                attempt++;
                logger.error("Error occurred while calling the Twilio API (attempt {}): {}", attempt, phoneNumber, e);

                if (attempt < retryCount) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        logger.error("Retry attempt interrupted", ie);
                    }
                } else {
                    logger.error("All retry attempts failed for phone number: {}", phoneNumber);
                }
            }
        }

        if (!success) {
            String errorMessage = "Failed to send OTP after " + retryCount + " attempts for phone number: " + phoneNumber;
            logger.error(errorMessage);
            throw new AlgoTradingException(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR, logger);
        }
    }

    public Boolean verifyOtpTwilio(String phoneNumber, String otpEntered) {
        VerificationCheck verificationCheck = VerificationCheck.creator(
                        verifyServiceSid
                ).setTo(phoneNumber)
                .setCode(otpEntered)
                .create();

        logger.info("VerificationCheck is : {}", verificationCheck);

        if (Constants.APPROVED.equals(verificationCheck.getStatus()))
            logger.info("Phone number verified successfully.");
        else {
            logger.error("Failed to verify phone number. Status: {}", verificationCheck.getStatus());
            return false;
        }
        return true;
    }

}
