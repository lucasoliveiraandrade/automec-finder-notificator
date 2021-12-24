package com.automecfinder.notificator.usecase;

import com.automecfinder.notificator.enums.EmailStatus;
import com.automecfinder.notificator.enums.EmailType;
import com.automecfinder.notificator.model.User;
import com.automecfinder.notificator.repository.EmailRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@Service
@Slf4j
@AllArgsConstructor
public class UserUseCase {

    private EmailUseCase emailUseCase;
    private EmailRepository emailRepository;

    public void notifyPreNewUser(User user) {

        ofNullable(emailRepository.findByTypeAndStatus(EmailType.PRE_NEW_USER, EmailStatus.ACTIVE))
                .map(email -> emailUseCase.sendMail(user.getEmail(), email.getSubject(), email.getBody()))
                .orElseGet(() -> {
                    log.info("Pre New User email not found in database to be sent");
                    return false;
                });
    }
}
