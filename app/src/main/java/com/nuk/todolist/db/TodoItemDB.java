package com.nuk.todolist.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nuk.todolist.model.TodoListModel;

@Database(entities = {TodoListModel.class}, version = 1)
public abstract class TodoItemDB extends RoomDatabase {

    private static TodoItemDB INSTANCE;

    public static TodoItemDB getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), TodoItemDB.class, "todo_db")
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract TodoListItemDao itemTodoModel();
}
