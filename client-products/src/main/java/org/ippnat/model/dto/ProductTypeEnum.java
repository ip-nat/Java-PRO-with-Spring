package org.ippnat.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductTypeEnum {

    ACCOUNT("Счет"),
    CARD("Карта");

    private final String name;

}

