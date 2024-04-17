package com.davidbonelo.models;

public enum BorrowingStatus {
    REQUESTED("REQUESTED"), BORROWED("BORROWED"), FINALIZED("FINALIZED");

    private final String value;

    BorrowingStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
