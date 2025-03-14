package org.ippnat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ippnat.model.exception.ErrorResponseDto;
import org.ippnat.model.exception.IntegrationException;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().is4xxClientError()
                || response.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        if (response.getStatusCode().is4xxClientError()) {
            ObjectMapper objectMapper = new ObjectMapper();
            ErrorResponseDto errorResponseDto = objectMapper.readValue(response.getBody(), ErrorResponseDto.class);
            throw new IntegrationException(errorResponseDto.code(),
                    "Ошибка при обращении к внешней API: " + errorResponseDto.message() + ". " + response.getStatusText());
        } else if (response.getStatusCode().is5xxServerError()) {
            throw new IntegrationException("SERVER_ERROR", "Внешняя система недоступна: " + response.getStatusText());
        }
    }
}
