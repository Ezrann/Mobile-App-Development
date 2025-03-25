package com.example.lab4;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.lab4.DetailExpenseActivity;
import com.example.lab4.Expense;
import com.example.lab4.R;


import java.util.List;


public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {
    private List<Expense> expenses;
    private Context context;


    public ExpenseAdapter(List<Expense> expenses, Context context) {
        this.expenses = expenses;
        this.context = context;
    }


    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new ExpenseViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        Expense expense = expenses.get(position);
        holder.amount.setText(String.valueOf(expense.getAmount()));
        holder.currency.setText(expense.getCurrency());
        holder.createDate.setText(expense.getFormattedDueDate());
        holder.category.setText(expense.getCategory());
        holder.remark.setText(expense.getRemark());


        // Navigate to DetailExpenseActivity
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailExpenseActivity.class);
            intent.putExtra("expenseId", expense.getId());
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return expenses.size();
    }


    public static class ExpenseViewHolder extends RecyclerView.ViewHolder {
        TextView amount, currency, createDate, category, remark;


        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            amount = itemView.findViewById(R.id.amount);
            currency = itemView.findViewById(R.id.currency);
            createDate = itemView.findViewById(R.id.create_date);
            category = itemView.findViewById(R.id.category);
            remark = itemView.findViewById(R.id.remark);
        }
    }
}
