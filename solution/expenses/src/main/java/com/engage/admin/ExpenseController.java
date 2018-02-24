package com.engage.admin;

import com.engage.admin.domain.CurrencyConverter;
import com.engage.admin.domain.Expense;
import com.engage.admin.domain.ExpenseRepository;
import com.engage.dto.ExpenseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import static com.engage.dto.ExpenseDto.DATE_FORMAT;

@RestController
public class ExpenseController {

    private static final Float VAT_RATE = 0.2f;

    @Autowired
    ExpenseRepository expenseRepository;

    @RequestMapping(value = "/expenses" ,method = RequestMethod.GET)
    public List<ExpenseDto> getExpenses() {

        List<ExpenseDto> dtoList = new ArrayList<>(1);
        ArrayList<Expense> expenseList = (ArrayList<Expense>) expenseRepository.findAll();

        for (Expense expense : expenseList) {
            dtoList.add( new ExpenseDto(expense.getReason(),expense.getAmount().toString(), fromDate(expense.getExpenseDate(),DATE_FORMAT), expense.getVatAmount()));
        }

        return dtoList;
    }

    @RequestMapping(value = "/expenses" , method = RequestMethod.POST)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public @ResponseBody ExpenseDto addExpense(@RequestBody ExpenseDto expenseDto ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ExpenseDto.DATE_FORMAT);
        LocalDate localDate = LocalDate.parse(expenseDto.getDate(),formatter);


        Float totalAmount = getAmount(expenseDto.getAmount());

        Float vatAmount = totalAmount * VAT_RATE;
        Expense expense = new Expense(expenseDto.getReason(), totalAmount,localDate, vatAmount );

        expense= expenseRepository.save(expense);

        return new ExpenseDto(expense.getReason(),expense.getAmount().toString(),fromDate(expense.getExpenseDate(),DATE_FORMAT), vatAmount);
    }

    private Float  getAmount(String amount) {

        if (amount.contains("EUR")) {
            try {
                Float rawAmount = new Float(amount.replace("EUR",""));
                Float theAmount = CurrencyConverter.convertCurrency(rawAmount,"EUR", "GBP");
                // round off
                theAmount = theAmount * 100;
                Integer intAmount = theAmount.intValue();
                Float roundedAmount =  new Float(intAmount) /100;
                return roundedAmount;

            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        } else {
            return new Float(amount);
        }
    }


    private String fromDate(LocalDate localDate , String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDate.format(formatter);
    }
}
