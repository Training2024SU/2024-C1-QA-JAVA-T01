package com.davidbonelo.models;

public enum UserRole {
    ADMINISTRATOR("ADMINISTRATOR"), EMPLOYEE("EMPLOYEE"), READER("READER");

    private final String value;

    UserRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
