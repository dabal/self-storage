package pl.dabal.selfstorage.service;

public interface EmailSenderService {

    void sendMail(String to, String subject, String message);


}
