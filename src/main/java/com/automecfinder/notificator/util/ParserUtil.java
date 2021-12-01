package com.automecfinder.notificator.util;

import com.automecfinder.notificator.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

@Slf4j
@Service
@AllArgsConstructor
public class ParserUtil {

    private final ObjectMapper objectMapper;

//    public Optional<String> serialize(Object obj) {
//        try {
//            return ofNullable(objectMapper.writeValueAsString(obj));
//        } catch (Exception e) {
//            log.error("Object could not be serialized as a json string. Error: \n{}", e.getMessage());
//        }
//        return empty();
//    }

    public Optional<User> deserialize(String userSerialized) {
        try {
            return ofNullable(objectMapper.readValue(userSerialized, User.class));
        } catch (Exception e) {
            log.error("User could not be deserialized as an object. Error: \n{}", e.getMessage());
        }
        return empty();
    }
}
