package pl.dabal.selfstorage.service;

import java.util.Locale;

public interface EmailComposerService {
    String getSubject(String properties, Object[] args, Locale locale);

    String getMessage(String properties, Object[] args, Locale locale);
}
