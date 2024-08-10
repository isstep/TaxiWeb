package com.example.taxiwebapp.EntityRepositories;

import com.example.taxiwebapp.EntityClasses.MessageEntity;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<MessageEntity,Long> {
}
