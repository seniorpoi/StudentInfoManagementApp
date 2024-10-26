package com.example.studentinfomanagementapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> studentList;

    public StudentAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.textName.setText("Name: " + student.getName());
        holder.textAge.setText("Age: " + student.getAge());
        holder.textGrade.setText("Grade: " + student.getGrade());
        holder.textMajor.setText("Major: " + (student.getMajor().isEmpty() ? "N/A" : student.getMajor()));
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textAge, textGrade, textMajor;

        StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.text_name);

            textAge = itemView.findViewById(R.id.text_age);
            textGrade = itemView.findViewById(R.id.text_grade);
            textMajor = itemView.findViewById(R.id.text_major);
        }
    }
}