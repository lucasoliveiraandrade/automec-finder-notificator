package com.automecfinder.notificator.repository;

import com.automecfinder.notificator.enums.EmailStatus;
import com.automecfinder.notificator.enums.EmailType;
import com.automecfinder.notificator.model.Email;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends MongoRepository<Email, String> {

    Email findByTypeAndStatus(EmailType type, EmailStatus status);

}
