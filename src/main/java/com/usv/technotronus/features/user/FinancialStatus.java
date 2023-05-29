package com.usv.technotronus.features.user;

public enum FinancialStatus {
    BUDGET,
    TAX;

    public static FinancialStatus getByStatusName(String statusName) {
        for (FinancialStatus status : FinancialStatus.values()) {
            if (status.name().equalsIgnoreCase(statusName)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid financial status name: " + statusName);
    }
}