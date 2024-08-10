package com.example.taxiwebapp.EntityClasses;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Table("message")
public class MessageEntity {
    @Id
    private Long id;
    private Long messageRecipientId;
    private Long messageSenderId;
    private String textMessage;
    private Timestamp messageDate;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMessageRecipientId() {
        return messageRecipientId;
    }

    public void setMessageRecipientId(Long messageRecipientId) {
        this.messageRecipientId = messageRecipientId;
    }

    public Long getMessageSenderId() {
        return messageSenderId;
    }

    public void setMessageSenderId(Long messageSenderId) {
        this.messageSenderId = messageSenderId;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public Timestamp getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Timestamp messageDate) {
        this.messageDate = messageDate;
    }


}
