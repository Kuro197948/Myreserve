package com.example.app.service;

public interface MailService {

    void sendPasswordSetupMail(String to, String setupUrl);
}