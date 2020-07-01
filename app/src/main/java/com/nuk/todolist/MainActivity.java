package com.nuk.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nuk.todolist.model.TodoListModel;
import com.nuk.todolist.modelview.ItemListViewModel;
import com.nuk.todolist.ui.AddItemActivity;
import com.nuk.todolist.ui.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button mAddNewItemBtn;

    private SearchView mSearchView;

    private RecyclerViewAdapter mRecyclerViewAdapter;

    private ItemListViewModel mItemListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setupListeners();
    }

    private void init() {
        mAddNewItemBtn = findViewById(R.id.add_item);
        mSearchView = findViewById(R.id.search_item);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        mRecyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<TodoListModel>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(mRecyclerViewAdapter);

        mItemListViewModel = ViewModelProviders.of(this).get(ItemListViewModel.class);
        mItemListViewModel.getItemTodoList().observe(MainActivity.this, new Observer<List<TodoListModel>>() {
            @Override
            public void onChanged(@Nullable List<TodoListModel> todoListModelList) {
                mRecyclerViewAdapter.addItems(todoListModelList);
            }
        });
    }

    private void setupListeners(){
        mAddNewItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddItemActivity.class));
            }
        });

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getItemsFromDb(query);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                getItemsFromDb(newText);
                return true;
            }

            private void getItemsFromDb(String searchText) {
                mItemListViewModel.getSearchedItemList(searchText)
                        .observe(MainActivity.this, new Observer<List<TodoListModel>>() {
                            @Override
                            public void onChanged(@Nullable List<TodoListModel> todoListModelList) {
                                if (todoListModelList == null) {
                                    return;
                                }
                                mRecyclerViewAdapter.addItems(todoListModelList);
                            }
                        });
            }
        });
    }
}
