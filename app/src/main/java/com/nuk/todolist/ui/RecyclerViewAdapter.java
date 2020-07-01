package com.nuk.todolist.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nuk.todolist.R;
import com.nuk.todolist.model.TodoListModel;

import java.util.List;

public class RecyclerViewAdapter
        extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private List<TodoListModel> todoListModels;

    public RecyclerViewAdapter(List<TodoListModel> todoListModels) {
        this.todoListModels = todoListModels;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_row, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        TodoListModel todoListModel = todoListModels.get(position);
        holder.tittleTV.setText(todoListModel.getTittle());
        holder.descriptionTV.setText(todoListModel.getDescription());

        holder.itemCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                Toast.makeText(v.getContext(), "Clicked on Checkbox: " + cb.getText() + " is "
                        + cb.isChecked(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return todoListModels.size();
    }

    public void addItems(List<TodoListModel> todoListModels) {
        this.todoListModels = todoListModels;
        notifyDataSetChanged();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView tittleTV;
        private TextView descriptionTV;
        private CheckBox itemCB;

        RecyclerViewHolder(View view) {
            super(view);
            tittleTV = view.findViewById(R.id.tittle_tv);
            descriptionTV = view.findViewById(R.id.description_tv);
            itemCB = view.findViewById(R.id.chk_selected);
        }
    }
}