package com.engage.admin.domain;

public class SomeStuff {
    private String reason;
    private String amount;
    private String date;

    public SomeStuff(String a, String b, String date ) {
        this.reason = a;
        this.amount = b;
        this.date = date;
    }

    public SomeStuff() {
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
