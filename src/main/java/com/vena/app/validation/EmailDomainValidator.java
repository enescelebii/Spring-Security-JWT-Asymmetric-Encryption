package com.vena.app.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EmailDomainValidator implements ConstraintValidator<NonDisposableEmail, String> {

    // upload blocked domains from the properties file
    private final Set<String> blocked;

    public EmailDomainValidator(
            @Value("${app.security.disposable-email}")
            final List<String> domainsToBlock
    ) {
        this.blocked = domainsToBlock.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(final String email,final ConstraintValidatorContext context) {
        if (email == null || !email.contains("@")){
            return false;
        }

        final int atIndex = email.indexOf("@") + 1 ;
        final int dotIndex = email.lastIndexOf(".");
        final String domain = email.substring(atIndex, dotIndex);
        return !this.blocked.contains(domain);
    }
}
