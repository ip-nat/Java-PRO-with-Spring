package org.ippnat.model.exception;

import lombok.Getter;

@Getter
public class IntegrationException extends RuntimeException {

    private final String externalSystemCode;

    public IntegrationException(String code, String message) {
        super(message);
        this.externalSystemCode = code;
    }

}
