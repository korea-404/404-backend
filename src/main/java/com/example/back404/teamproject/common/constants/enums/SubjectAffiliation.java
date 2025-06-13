package com.example.back404.teamproject.common.constants.enums;

import lombok.Getter;

@Getter
public enum SubjectAffiliation {
    liberal_arts("인문계열"),
    natural_sciences("자연계열");

    private final String description;

    SubjectAffiliation(String description) {
        this.description = description;
    }
}