package com.example.todo_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditTodoActivity extends AppCompatActivity {
    String newTodo;
    int todoItemPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_todo_layout);
        Intent intent = getIntent();
        newTodo = intent.getStringExtra(Intent_Constants.EDIT_ITEM_TEXT);
        todoItemPosition = intent.getIntExtra(Intent_Constants.POSITION_ITEM_TO_EDIT,-1);
        EditText todoToEdit = (EditText) findViewById(R.id.newTodoEditTextId);
        todoToEdit.setText(newTodo);
    }

    public void saveTodoItem(View v){
        String itemChanged = ((EditText) findViewById(R.id.newTodoEditTextId)).getText().toString();
        Intent intent = new Intent();
        intent.putExtra(Intent_Constants.CHANGED_TODO_ITEM,itemChanged);
        intent.putExtra(Intent_Constants.POSITION_ITEM_TO_EDIT,todoItemPosition);
        setResult(Intent_Constants.INTENT_EDIT_RESULT_CODE,intent);
        finish();
    }
}
