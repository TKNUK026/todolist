package com.nuk.todolist.modelview;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nuk.todolist.db.TodoItemDB;
import com.nuk.todolist.model.TodoListModel;

import java.util.List;

public class ItemListViewModel extends AndroidViewModel {

    private final LiveData<List<TodoListModel>> mTodoListModel;

    private TodoItemDB todoItemDB;

    public ItemListViewModel(Application application) {
        super(application);
        todoItemDB = TodoItemDB.getDatabase(this.getApplication());
        mTodoListModel = todoItemDB.itemTodoModel().getAllTodoListItems();
    }

    public LiveData<List<TodoListModel>> getItemTodoList() {
        return mTodoListModel;
    }

    public LiveData<List<TodoListModel>> getSearchedItemList(String itemSearch) {
        return todoItemDB.itemTodoModel().getSearchedItemById(itemSearch);
    }

    public void addItem(TodoListModel todoListModel) {
        new addAsyncTask(todoItemDB).execute(todoListModel);
    }

    private static class addAsyncTask extends AsyncTask<TodoListModel, Void, Void> {

        private TodoItemDB db;

        addAsyncTask(TodoItemDB todoItemDB) {
            db = todoItemDB;
        }

        @Override
        protected Void doInBackground(final TodoListModel... params) {
            db.itemTodoModel().addTodoItem(params[0]);
            return null;
        }
    }
}
