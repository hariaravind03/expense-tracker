package com.hari.Expense_Tracker.controller;

import com.hari.Expense_Tracker.DTO.AddExpenseRequest;
import com.hari.Expense_Tracker.entity.Expense;
import com.hari.Expense_Tracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    private final ExpenseService expenseService;


    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getExpense(@AuthenticationPrincipal String email){
        return expenseService.getExpense(email);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addExpense(@Valid @RequestBody AddExpenseRequest request,
                                             @AuthenticationPrincipal String email){
        return expenseService.addExpense(request,email);
    }

}
