package com.bits.claimregbackend.documentScanner;

public class InternetBillScanResult {
    private DocField subscriptionStartDate;
    private DocField subscriptionEndDate;
    private DocField invoiceNumber;
    private DocField invoiceDate;
    private DocField amount;
    private DocField subscriberName;

    public DocField getSubscriptionStartDate() {
        return subscriptionStartDate;
    }

    public void setSubscriptionStartDate(DocField subscriptionStartDate) {
        this.subscriptionStartDate = subscriptionStartDate;
    }

    public DocField getSubscriptionEndDate() {
        return subscriptionEndDate;
    }

    public void setSubscriptionEndDate(DocField subscriptionEndDate) {
        this.subscriptionEndDate = subscriptionEndDate;
    }

    public DocField getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(DocField invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public DocField getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(DocField invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public DocField getAmount() {
        return amount;
    }

    public void setAmount(DocField amount) {
        this.amount = amount;
    }

    public DocField getSubscriberName() {
        return subscriberName;
    }

    public void setSubscriberName(DocField subscriberName) {
        this.subscriberName = subscriberName;
    }
}
