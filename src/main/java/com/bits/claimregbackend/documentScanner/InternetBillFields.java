package com.bits.claimregbackend.documentScanner;

public enum InternetBillFields {
    SUBSCRIPTION_START_DATE("SubPeriodStart"),
    SUBSCRIPTION_END_DATE("SubPeriodEnd"),
    INVOICE_NUMBER("InvoiceNumber"),
    INVOICE_DATE("InvoiceDate"),
    AMOUNT("AmountPayable"),
    NAME("Name");

    public final String fieldName;

    private InternetBillFields(String fieldName) {
        this.fieldName = fieldName;
    }

    public static InternetBillFields valueOfFieldName(String fieldName) {
        for (InternetBillFields e : values()) {
            if (e.fieldName.equals(fieldName)) {
                return e;
            }
        }
        return null;
    }
}
