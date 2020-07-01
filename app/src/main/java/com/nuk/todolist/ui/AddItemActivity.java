package com.nuk.todolist.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.nuk.todolist.R;
import com.nuk.todolist.model.TodoListModel;
import com.nuk.todolist.modelview.ItemListViewModel;

public class AddItemActivity extends AppCompatActivity {

    private EditText tittleET;
    private EditText descriptionET;

    private ItemListViewModel mItemListViewModel;

    private Button mCancelBtn;
    private Button mDoneBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_view);

        tittleET = findViewById(R.id.tittle_et);
        descriptionET = findViewById(R.id.description_et);

        mDoneBtn = findViewById(R.id.done_btn);
        mCancelBtn = findViewById(R.id.cancel_btn);

        mItemListViewModel = ViewModelProviders.of(this).get(ItemListViewModel.class);

        setupListeners();
    }

    private void setupListeners() {
        mDoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tittleET.getText().toString().trim().isEmpty() ||
                        descriptionET.getText().toString().trim().isEmpty()) {
                    Toast.makeText(AddItemActivity.this, "Missing fields", Toast.LENGTH_SHORT).show();
                } else {
                    mItemListViewModel.addItem(new TodoListModel(
                            tittleET.getText().toString(),
                            descriptionET.getText().toString()
                    ));
                    finish();
                }
            }
        });
        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}