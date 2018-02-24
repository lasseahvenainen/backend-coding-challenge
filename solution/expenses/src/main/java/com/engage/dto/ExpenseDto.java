package com.engage.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * A class for passing data between backend and frontend
 */
public class ExpenseDto {

    private String reason;
    private Float amount;
    //  A String representation of a date , the format used is in the String DATE_FORMAT
    private String date;
    private Float vat;

    public static final String DATE_FORMAT= "dd/MM/yyyy";

    public ExpenseDto() {
    }

    public ExpenseDto(String reason, Float amount, String  date, Float vat) {
        this.reason = reason;
        this.amount = amount;
        this.vat = vat;
        this.date = date;
    }





    public Float getVat() {
        return vat;
    }

    public void setVat(Float vat) {
        this.vat = vat;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private Date toDate(String dateString, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String dateAsString(LocalDate date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(date);
    }
}
