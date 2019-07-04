package com.example.todo_android_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView itemListView;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> arrList;
    String todoText;
    int position_edited_todo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemListView = (ListView) findViewById(R.id.TodoAppListId);
        arrList = new ArrayList<>();
        //arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrList);
        CustomAdapter adapter = new CustomAdapter(arrList, this);
        itemListView.setAdapter(adapter);
        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,EditTodoActivity.class);
                intent.putExtra(Intent_Constants.EDIT_ITEM_TEXT,arrList.get(position).toString());
                intent.putExtra(Intent_Constants.POSITION_ITEM_TO_EDIT,position);
                startActivityForResult(intent,Intent_Constants.INTENT_EDIT_CODE);
            }
        });
    }

    public void addTodoItem(View v){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,AddTodoActivity.class);
        startActivityForResult(intent,Intent_Constants.INTENT_CODE);
    }

    @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(Intent_Constants.INTENT_CODE == resultCode){
            final RelativeLayout rl = (RelativeLayout)findViewById(R.id.TodoRootLayoutId);
            final Button btn = new Button(this);
            rl.addView(btn);
            btn.setText("hello");
            btn.setWidth(320);
            btn.setHeight(40);
            todoText = data.getStringExtra(Intent_Constants.ADD_ITEM_TEXT);
            arrList.add(todoText);
            arrayAdapter.notifyDataSetChanged();
        }else if(Intent_Constants.INTENT_EDIT_CODE == resultCode){
            todoText = data.getStringExtra(Intent_Constants.EDIT_ITEM_TEXT);
            position_edited_todo = data.getIntExtra(Intent_Constants.POSITION_ITEM_TO_EDIT,-1);
            arrList.remove(position_edited_todo);
            arrList.add(position_edited_todo,todoText);
            arrayAdapter.notifyDataSetChanged();
        }
    }
}
