package com.example.jobcnx;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.example.jobcnx.DatabaseHandler;
import com.example.jobcnx.ToDoModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Calendar;

public class AddNewTask extends BottomSheetDialogFragment {

    public static final String TAG = "ActionBottomDialog";

    private String newTaskText;
    private EditText newTaskText2;
    private EditText editTextDate;
    private Button newTaskSaveButton;
    private DatabaseHandler db;

    public static AddNewTask newInstance(){
        return new AddNewTask();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.widget_layout, container, false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        EditText editText = getView().findViewById(R.id.todoTextView);
        editText.setText(MainActivity.salary, TextView.BufferType.EDITABLE);
        EditText editText2 = getView().findViewById(R.id.todoTextView2);
        editText2.setText(MainActivity.field, TextView.BufferType.EDITABLE);

        db = new DatabaseHandler(getActivity());
        db.openDatabase();
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        editTextDate.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

            DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    month = month + 1;
                    String date = day + "/" + month + "/" + year;
                    editTextDate.setText(date);

                }
            }, year, month, day
            );


            }
        });



        final boolean finalIsUpdate = true;
        final Bundle bundle = getArguments();
         {
                editText = getView().findViewById(R.id.todoTextView);
                editText.setText(MainActivity.salary, TextView.BufferType.EDITABLE);
                editText2 = getView().findViewById(R.id.todoTextView2);
                editText2.setText(MainActivity.field, TextView.BufferType.EDITABLE);
              Log.d("saved note()", String.valueOf(MainActivity.salary.length()));
              Log.d("saved note()",MainActivity.field);

              if(MainActivity.salary.length() != 0){
              if(finalIsUpdate){
                  db.updateTask(bundle.getInt("id"), MainActivity.salary);
                  db.updateDescription(bundle.getInt("id"), MainActivity.field);
              }
              else{
                  ToDoModel task = new ToDoModel();
                  task.setTask(MainActivity.salary);
                  task.setDescription(MainActivity.field);
//                  task.setStatus(0);
                  db.insertTask(task);
                  Log.d("SAVED", task.getTask());
                  Log.d("SAVED", MainActivity.field);
              }
              }


              dismiss();
            }
        };


    @Override
    public void onDismiss(DialogInterface dialog){
        Activity activity = getActivity();
        if(activity instanceof DialogCloseListener){
            ((DialogCloseListener)activity).handleDialogClose(dialog);
        }
    }
}
