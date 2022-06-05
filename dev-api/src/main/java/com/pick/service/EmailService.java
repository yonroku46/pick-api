package com.pick.service;

import com.pick.model.EmailTO;

public interface EmailService {
    public Boolean sendMail(EmailTO mail);
}
