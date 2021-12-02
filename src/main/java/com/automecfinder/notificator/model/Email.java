package com.automecfinder.notificator.model;

import com.automecfinder.notificator.enums.EmailStatus;
import com.automecfinder.notificator.enums.EmailType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@Document(collection = "email")
public class Email {

    @Id
    private String id;

    private String subject;

    private String body;

    private EmailType type;

    private EmailStatus status;

}
