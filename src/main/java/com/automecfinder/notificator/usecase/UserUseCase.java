package com.automecfinder.notificator.usecase;

import com.automecfinder.notificator.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
@Slf4j
@AllArgsConstructor
public class UserUseCase {

    private EmailUseCase emailUseCase;

    public void notifyPreNewUser(User user) throws MessagingException {
        emailUseCase.sendMail(user.getEmail(),"Assunto caralho", "corpo do caralho");

    }


}
