package com.engage.admin;

import com.engage.admin.domain.Expense;
import com.engage.admin.domain.ExpenseRepository;
import com.engage.dto.ExpenseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
            dtoList.add( new ExpenseDto(expense.getReason(),expense.getAmount(), fromDate(expense.getExpenseDate(),DATE_FORMAT), expense.getVatAmount()));
        }

        return dtoList;
    }

    @RequestMapping(value = "/expenses" , method = RequestMethod.POST)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public @ResponseBody ExpenseDto addExpense(@RequestBody ExpenseDto expenseDto ) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ExpenseDto.DATE_FORMAT);
        LocalDate localDate = LocalDate.parse(expenseDto.getDate(),formatter);
        Float vatAmount = expenseDto.getAmount() * VAT_RATE;
        Expense expense = new Expense(expenseDto.getReason(), expenseDto.getAmount(),localDate, vatAmount );

        expense= expenseRepository.save(expense);

        return new ExpenseDto(expense.getReason(),expense.getAmount(),fromDate(expense.getExpenseDate(),DATE_FORMAT), vatAmount);
    }


    @RequestMapping(value = "/exp" , method = RequestMethod.POST)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public @ResponseBody String  addExpe(@RequestBody ExpenseDto theStuff ) {

        return theStuff.getReason()+' '+theStuff.getAmount()+ " " + theStuff.getDate();
    }

    private Date stringToDate(String dateAsString, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(dateAsString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String fromDate(LocalDate localDate , String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDate.format(formatter);
    }
}
