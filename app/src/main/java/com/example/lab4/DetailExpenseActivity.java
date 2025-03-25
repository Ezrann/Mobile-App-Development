package com.example.lab4;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class DetailExpenseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_expense);


        String expenseId = getIntent().getStringExtra("expenseId");
        Expense expense = ExpenseData.getExpenseById(expenseId);


        if (expense != null) {
            TextView amount = findViewById(R.id.amount);
            TextView currency = findViewById(R.id.currency);
            TextView createDate = findViewById(R.id.create_date);
            TextView category = findViewById(R.id.category);
            TextView remark = findViewById(R.id.remark);


            amount.setText(String.valueOf(expense.getAmount()));
            currency.setText(expense.getCurrency());
            createDate.setText(expense.getFormattedDueDate());
            category.setText(expense.getCategory());
            remark.setText(expense.getRemark());
        }
    }
}
