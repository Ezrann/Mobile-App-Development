
package com.example.lab4;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.lab4.ExpenseAdapter;


import java.util.List;


public class ViewFragment extends Fragment {
    private RecyclerView recyclerView;
    private ExpenseAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_view_fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        // Load updated dummy data
        List<Expense> expenses = ExpenseData.getDummyExpenses();
        adapter = new ExpenseAdapter(expenses, getContext());
        recyclerView.setAdapter(adapter);


        return view;
    }
}
