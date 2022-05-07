package com.pick.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailTO {
    private String receiveMail;
    private String senderMail;
    private String senderName;
    private String subject;
    private String message;
}
