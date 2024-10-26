package com.example.studentinfomanagementapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class DataEntryFragment extends Fragment {

    private EditText editName, editAge, editGrade, editMajor;
    private Button undoButton;
    private OnDataSubmitListener dataSubmitListener;
    private String lastName, lastMajor;
    private int lastAge, lastGrade;

    public interface OnDataSubmitListener {
        void onDataSubmit(String name, int age, int grade, String major);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_entry, container, false);

        editName = view.findViewById(R.id.edit_name);
        editAge = view.findViewById(R.id.edit_age);
        editGrade = view.findViewById(R.id.edit_grade);
        editMajor = view.findViewById(R.id.edit_major);
        Button submitButton = view.findViewById(R.id.button_submit);
        Button clearButton = view.findViewById(R.id.button_clear);
        undoButton = view.findViewById(R.id.button_undo);

        submitButton.setOnClickListener(v -> submitData());
        clearButton.setOnClickListener(v -> clearFields());
        undoButton.setOnClickListener(v -> undoClear());

        return view;
    }

    private void submitData() {
        String name = editName.getText().toString().trim();
        String ageStr = editAge.getText().toString().trim();
        String gradeStr = editGrade.getText().toString().trim();
        String major = editMajor.getText().toString().trim();

        if (name.isEmpty() || ageStr.isEmpty() || gradeStr.isEmpty()) {
            Toast.makeText(getContext(), "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int age, grade;
        try {
            age = Integer.parseInt(ageStr);
            grade = Integer.parseInt(gradeStr);
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Invalid age or grade", Toast.LENGTH_SHORT).show();
            return;
        }

        if (age <= 0 || age > 120) {
            Toast.makeText(getContext(), "Invalid age", Toast.LENGTH_SHORT).show();
            return;
        }

        if (grade < 0 || grade > 100) {
            Toast.makeText(getContext(), "Invalid grade (0-100)", Toast.LENGTH_SHORT).show();
            return;
        }

        if (dataSubmitListener != null) {
            dataSubmitListener.onDataSubmit(name, age, grade, major);
        }

        clearFields();
        Toast.makeText(getContext(), "Student added successfully", Toast.LENGTH_SHORT).show();
    }

    private void clearFields() {
        saveLastInput(editName.getText().toString().trim(),
                editAge.getText().toString().trim(),
                editGrade.getText().toString().trim(),
                editMajor.getText().toString().trim());
        editName.setText("");
        editAge.setText("");
        editGrade.setText("");
        editMajor.setText("");
        undoButton.setEnabled(true);
    }

    private void saveLastInput(String name, String age, String grade, String major) {
        lastName = name;
        lastAge = age.isEmpty() ? 0 : Integer.parseInt(age);
        lastGrade = grade.isEmpty() ? 0 : Integer.parseInt(grade);
        lastMajor = major;
    }

    private void undoClear() {
        editName.setText(lastName);
        editAge.setText(lastAge != 0 ? String.valueOf(lastAge) : "");
        editGrade.setText(lastGrade != 0 ? String.valueOf(lastGrade) : "");
        editMajor.setText(lastMajor);
        undoButton.setEnabled(false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnDataSubmitListener) {
            dataSubmitListener = (OnDataSubmitListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnDataSubmitListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dataSubmitListener = null;
    }
}