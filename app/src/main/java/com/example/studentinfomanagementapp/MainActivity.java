package com.example.studentinfomanagementapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DataEntryFragment.OnDataSubmitListener {

    private DisplayFragment displayFragment;
    private List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentList = new ArrayList<>();

        if (savedInstanceState == null) {
            // Add Data Entry Fragment
            DataEntryFragment dataEntryFragment = new DataEntryFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.data_entry_container, dataEntryFragment)
                    .commit();

            // Add Display Fragment
            displayFragment = new DisplayFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.display_container, displayFragment)
                    .commit();
        }
    }

    @Override
    public void onDataSubmit(String name, int age, int grade, String major) {
        // Add new student to the list
        Student newStudent = new Student(name, age, grade, major);
        studentList.add(newStudent);

        // Update Display Fragment with new data
        displayFragment.updateStudentList(studentList);
    }
}