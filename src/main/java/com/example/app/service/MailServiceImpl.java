package com.example.app.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendPasswordSetupMail(String to, String setupUrl) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("【MyReserve】パスワード設定のご案内");
        message.setText(
                "会員登録ありがとうございます。\n\n"
              + "以下のURLからパスワードを設定してください。\n"
              + setupUrl + "\n\n"
              + "※このURLの有効期限は30分です。");

        mailSender.send(message);
    }
}