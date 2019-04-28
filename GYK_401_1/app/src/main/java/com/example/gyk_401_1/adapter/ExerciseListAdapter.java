package com.example.gyk_401_1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gyk_401_1.R;
import com.example.gyk_401_1.models.Exercise;

import java.util.List;

public class ExerciseListAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    private List<Exercise> exerciseList;

    public ExerciseListAdapter(LayoutInflater layoutInflater, List<Exercise> exerciseList){
        this.layoutInflater = layoutInflater;
        this.exerciseList = exerciseList;
    }

    @Override
    public int getCount() {
        return exerciseList.size();
    }

    @Override
    public Object getItem(int i) {
        return exerciseList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
       View exerciseView = layoutInflater.inflate(R.layout.exercise_fragment, null);
        ImageView exercisePicture = (ImageView) exerciseView.findViewById(R.id.exercisePicture);
        TextView exerciseName = (TextView) exerciseView.findViewById(R.id.exerciseName);
        TextView exerciseDuration = (TextView) exerciseView.findViewById(R.id.exerciseDuration);
        Exercise exercise = exerciseList.get(i);
        exercisePicture.setImageResource(exercise.getExercisePicture());
        exerciseName.setText(exercise.getExerciseName());
        exerciseDuration.setText(exercise.getExerciseDuration());
        return exerciseView;
    }
}
