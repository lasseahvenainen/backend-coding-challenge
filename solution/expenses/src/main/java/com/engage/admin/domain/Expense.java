package com.engage.admin.domain;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table( name="EXPENSES")

public class Expense {

    public Expense() {
    }

    public Expense(String reason, Float amount, LocalDate expenseDate, Float vatAmount) {
        this.reason = reason;
        this.amount = amount;
        this.expenseDate = expenseDate;
        this.vatAmount = vatAmount;
    }


    @Id
    @Column( name ="ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="REASON")
    private String reason;

    @Column(name="AMOUNT")
    private Float amount;

    @Column(name="EXPENSE_DATE")
    private LocalDate expenseDate;

    @Column(name="VAT_AMOUNT")
    private Float vatAmount;

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

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }

    public Float getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(Float vatAmount) {
        this.vatAmount = vatAmount;
    }
}
