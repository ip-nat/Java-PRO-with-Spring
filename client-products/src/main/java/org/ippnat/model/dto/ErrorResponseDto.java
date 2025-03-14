package org.ippnat.model.dto;

public record ErrorResponseDto(ErrorCodeEnum code, String message) {
}
