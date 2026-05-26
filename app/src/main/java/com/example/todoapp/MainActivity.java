package com.example.todoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etTask;
    private Button btnAdd;
    private RecyclerView recyclerView;
    private TextView tvTaskCount, tvEmpty;

    private List<String> taskList;
    private TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask      = findViewById(R.id.etTask);
        btnAdd      = findViewById(R.id.btnAdd);
        recyclerView = findViewById(R.id.recyclerView);
        tvTaskCount = findViewById(R.id.tvTaskCount);
        tvEmpty     = findViewById(R.id.tvEmpty);

        taskList = new ArrayList<>();

        adapter = new TaskAdapter(taskList, position -> {
            taskList.remove(position);
            adapter.notifyItemRemoved(position);
            adapter.notifyItemRangeChanged(position, taskList.size());
            updateCounter();
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        updateCounter();

        btnAdd.setOnClickListener(v -> {
            String task = etTask.getText().toString().trim();
            if (task.isEmpty()) {
                Toast.makeText(this,
                        "Please enter a task first!", Toast.LENGTH_SHORT).show();
            } else {
                taskList.add(task);
                adapter.notifyItemInserted(taskList.size() - 1);
                recyclerView.scrollToPosition(taskList.size() - 1);
                etTask.getText().clear();
                updateCounter();
            }
        });
    }

    // Updates the badge counter in the header
    private void updateCounter() {
        int count = taskList.size();
        tvTaskCount.setText(count + (count == 1 ? " task" : " tasks"));
        tvEmpty.setVisibility(count == 0 ? View.VISIBLE : View.GONE);
    }
}