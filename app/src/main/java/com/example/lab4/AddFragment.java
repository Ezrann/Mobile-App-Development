package com.example.lab4;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import androidx.fragment.app.Fragment;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class AddFragment extends Fragment {


    private EditText amountInput, remarkInput, createDateInput;
    private RadioGroup currencyRadioGroup;
    private Spinner categorySpinner;
    private Button addExpenseButton;
    private Calendar calendar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_add_fragment, container, false);


        // Initialize UI elements
        amountInput = view.findViewById(R.id.editTextText);
        remarkInput = view.findViewById(R.id.remark);
        createDateInput = view.findViewById(R.id.date);
        currencyRadioGroup = view.findViewById(R.id.currency_radio_group);
        categorySpinner = view.findViewById(R.id.category_spinner);
        addExpenseButton = view.findViewById(R.id.button);


        // Initialize Calendar instance
        calendar = Calendar.getInstance();


        // Set click listener on create_date_input to show DatePickerDialog
        createDateInput.setOnClickListener(v -> showDatePickerDialog());


        // Set button click listener
        addExpenseButton.setOnClickListener(v -> {
            // Get input values
            String amountStr = amountInput.getText().toString();
            String remark = remarkInput.getText().toString();
            String createDateStr = createDateInput.getText().toString();
            String category = categorySpinner.getSelectedItem().toString();


            // Get selected currency
            int selectedRadioId = currencyRadioGroup.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = view.findViewById(selectedRadioId);
            String currency = selectedRadioButton != null ? selectedRadioButton.getText().toString() : "KHR"; // Default to KHR if none selected


            // Parse amount and date
            double amount;
            try {
                amount = Double.parseDouble(amountStr);
            } catch (NumberFormatException e) {
                amount = 0.0; // Default value if parsing fails
            }


            Date createDate;
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                createDate = dateFormat.parse(createDateStr);
            } catch (Exception e) {
                createDate = new Date(); // Use current date if parsing fails
            }


            // Create new Expense object
            Expense newExpense = new Expense(remark, amount, currency, category, createDate);


            // Add to ExpenseData
            ExpenseData.addExpense(newExpense);


            // Optional: Clear inputs after adding
            amountInput.setText("");
            remarkInput.setText("");
            createDateInput.setText("");
            currencyRadioGroup.check(R.id.khr_radio); // Reset to default
            categorySpinner.setSelection(0); // Reset to first item
        });


        return view;
    }


    private void showDatePickerDialog() {
        // Get current date
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);


        // Create and show DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Format the selected date as "yyyy-MM-dd"
                    String selectedDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay);
                    createDateInput.setText(selectedDate); // Update the EditText with the selected date
                },
                year, month, day
        );


        datePickerDialog.show();
    }
}
