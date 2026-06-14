package com.spitoring.domain.spitto.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SpittoType {

    SP500("스피또500", "500"),
    SP1000("스피또1000", "1000"),
    SP2000("스피또2000", "2000");

    private final String displayName;
    private final String numberCode;

    public static SpittoType from(String code) {
        for (SpittoType type : values()) {
            if (type.name().equalsIgnoreCase(code) || type.numberCode.equals(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("알 수 없는 스피또 종류 코드: " + code);
    }
}
