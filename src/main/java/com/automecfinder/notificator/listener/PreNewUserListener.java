package com.automecfinder.notificator.listener;

import com.automecfinder.notificator.usecase.UserUseCase;
import com.automecfinder.notificator.util.ParserUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PreNewUserListener {

    private ParserUtil parserUtil;
    private UserUseCase userUseCase;

    @KafkaListener(topics = "${spring.kafka.consumer.topic}", groupId = "automec-finder")
    public void dequeueMessage(String payload) {

        log.info("Payload consumed: {}", payload);

        parserUtil.deserialize(payload)
                .ifPresent(userUseCase::notifyPreNewUser);
    }
}
