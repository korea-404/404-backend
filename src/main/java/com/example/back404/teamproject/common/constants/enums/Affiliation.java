package com.example.back404.teamproject.common.constants.enums;

import lombok.Getter;

@Getter
public enum Affiliation {
    LIBERAL_ARTS("인문계열"),
    NATURAL_SCIENCES("자연계열");

    private final String description;

    Affiliation(String description) {
        this.description = description;
    }
}