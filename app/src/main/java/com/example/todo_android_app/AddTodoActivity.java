package com.example.todo_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddTodoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_todo_layout);
    }

    public void saveTodoItem(View v){
        String newItemText = ((EditText) findViewById(R.id.newTodoEditTextId)).getText().toString();
        if(newItemText.equals("")){

        }else{
            Intent intent = new Intent();
            intent.putExtra(Intent_Constants.ADD_ITEM_TEXT,newItemText);
            setResult(Intent_Constants.RESULT_CODE,intent);
            finish();
        }
    }
}
