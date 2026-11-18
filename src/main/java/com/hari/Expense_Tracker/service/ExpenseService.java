package com.hari.Expense_Tracker.service;

import com.hari.Expense_Tracker.DTO.AddExpenseRequest;
import com.hari.Expense_Tracker.entity.Expense;
import com.hari.Expense_Tracker.entity.User;
import com.hari.Expense_Tracker.repository.ExpenseRepository;
import com.hari.Expense_Tracker.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseService {

    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

        public ResponseEntity<List<Expense>> getExpense(String email) {
            List<Expense> expense=expenseRepository.findByUser_EmailId(email);

            return ResponseEntity.ok(expense);
        }

        public ResponseEntity<String> addExpense(@Valid AddExpenseRequest request,String email) {

            User user= userRepository.findByEmailId(email);

            Expense expense=new Expense(request.getTitle(),
                    request.getAmount(),
                    request.getCategory(),
                    request.getDescription(),
                    request.getExpenseDate(),
                    user);

            expenseRepository.save(expense);

            return ResponseEntity.ok("Successfully inserted a expense for user: " + email);
        }

}
