package com.example.jobcnx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.core.motion.utils.Utils;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.example.jobcnx.DatabaseHandler;
import com.example.jobcnx.ToDoAdapter;
import com.example.jobcnx.ToDoModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements DialogCloseListener{

    private RecyclerView tasksRecyclerView;
    private ToDoAdapter tasksAdapter;
    private FloatingActionButton fab;
    private List<ToDoModel> taskList;
    private DatabaseHandler db;

    public static String field;
    public static String salary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Objects.requireNonNull(getSupportActionBar()).hide();

        db = new DatabaseHandler(this);
        db.openDatabase();

        taskList = new ArrayList<>();

        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksAdapter = new ToDoAdapter(db, this);
        tasksRecyclerView.setAdapter(tasksAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper((new RecyclerItemTouchHelper(tasksAdapter)));
        itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);

        String path = "/Users/adig/Desktop/NYC_Jobs.csv";
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (Integer.parseInt(values[2]) > salaryActivity.salaryInt) {
                    if (values[3].contains(fieldActivity.field)) {
                        // System.out.println(values[0]);
                        field = values[0];
                        salary = values[2];
                        AddNewTask.newInstance().show(getSupportFragmentManager(),AddNewTask.TAG);
                    } else if (values[3].contains((fieldActivity.field).substring(0, (fieldActivity.field).length()-1))) {
                        // System.out.println(values[0]);
                        field = values[0];
                        salary = values[2];
                        AddNewTask.newInstance().show(getSupportFragmentManager(),AddNewTask.TAG);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNewTask.newInstance().show(getSupportFragmentManager(),AddNewTask.TAG);
            }
        });
    }

    @Override
    public void handleDialogClose(DialogInterface dialog){
        taskList = db.getAllTasks();
        Log.d("MAINACT()", taskList.toString());
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);
        tasksAdapter.notifyDataSetChanged();
    }
}