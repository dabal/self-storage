package pl.dabal.selfstorage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import pl.dabal.selfstorage.service.EmailComposerService;

import java.util.Locale;

@Service
public class EmailComposerServiceImpl implements EmailComposerService {
    @Autowired
    private MessageSource messageSource;

    @Override
    public String getSubject(String properties, Object[] args, Locale locale) {
        return messageSource.getMessage(properties, args, locale);
    }

    @Override
    public String getMessage(String properties, Object[] args, Locale locale) {
        return messageSource.getMessage(properties, args, locale);
    }
}
