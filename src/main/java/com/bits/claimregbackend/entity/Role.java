package com.bits.claimregbackend.entity;

public enum Role {
    APPROVER("Approver"),
    SUBMITTER("Submitter");
    public final String value;

    private Role(String value) {
        this.value = value;
    }

    public static Role valueOfFieldName(String value) {
        for (Role e : values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        return null;
    }
}
