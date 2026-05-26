package com.example.todoapp;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    // Dot colors that cycle through tasks
    private static final int[] DOT_COLORS = {
            Color.parseColor("#4F46E5"),  // indigo
            Color.parseColor("#10B981"),  // green
            Color.parseColor("#F59E0B"),  // amber
            Color.parseColor("#EF4444"),  // red
            Color.parseColor("#8B5CF6"),  // purple
            Color.parseColor("#06B6D4"),  // cyan
    };

    private List<String> taskList;
    private OnRemoveClickListener listener;

    public interface OnRemoveClickListener {
        void onRemoveClick(int position);
    }

    public TaskAdapter(List<String> taskList, OnRemoveClickListener listener) {
        this.taskList = taskList;
        this.listener = listener;
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView tvTask;
        LinearLayout btnRemove;
        View taskDot;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTask     = itemView.findViewById(R.id.tvTask);
            btnRemove  = itemView.findViewById(R.id.btnRemove);
            taskDot    = itemView.findViewById(R.id.taskDot);
        }
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.tvTask.setText(taskList.get(position));

        // Cycle through dot colors
        int color = DOT_COLORS[position % DOT_COLORS.length];
        holder.taskDot.setBackgroundColor(color);

        holder.btnRemove.setOnClickListener(v ->
                listener.onRemoveClick(holder.getAdapterPosition())
        );
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}