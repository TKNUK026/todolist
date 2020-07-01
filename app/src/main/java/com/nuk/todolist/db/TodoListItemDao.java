package com.nuk.todolist.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.nuk.todolist.model.TodoListModel;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface TodoListItemDao {

    @Query("select * from TodoListModel")
    LiveData<List<TodoListModel>> getAllTodoListItems();

    @Query("select * from TodoListModel where id = :id")
    TodoListModel getItemById(String id);

    @Query("select * from TodoListModel where " +
            "tittle GLOB '*' || :itemSearch|| '*' OR description GLOB '*' || :itemSearch|| '*'" )
    LiveData<List<TodoListModel>> getSearchedItemById(String itemSearch);

    @Insert(onConflict = REPLACE)
    void addTodoItem(TodoListModel todoListModel);

    @Delete
    void deleteTodoItem(TodoListModel todoListModel);
}
