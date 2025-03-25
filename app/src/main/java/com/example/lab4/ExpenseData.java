package com.example.lab4;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class ExpenseData {
    private static final List<Expense> expenses = new ArrayList<>();


    static {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            expenses.add(new Expense("Good", 20000, "KHR", "Ice Latte", dateFormat.parse("2025-03-01")));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static List<Expense> getDummyExpenses() {
        return expenses;
    }


    public static Expense getExpenseById(String id) {
        for (Expense expense : expenses) {
            if (expense.getId().equals(id)) {
                return expense;
            }
        }
        return null;
    }


    // New method to add an expense
    public static void addExpense(Expense expense) {
        expenses.add(expense);
    }
}

