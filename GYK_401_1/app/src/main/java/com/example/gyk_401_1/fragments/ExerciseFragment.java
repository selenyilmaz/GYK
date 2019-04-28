package com.example.gyk_401_1.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.gyk_401_1.R;
import com.example.gyk_401_1.adapter.ExerciseListAdapter;
import com.example.gyk_401_1.models.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseFragment extends Fragment {
    final ArrayList<Exercise> exerciseList = new ArrayList<Exercise>();
    private OnFragmentInteractionListener mListener;

    public ExerciseFragment(){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,null);
        exerciseList.add(new Exercise(R.drawable.exercise1, "Jumping Jacks", "20 reps"));
        exerciseList.add(new Exercise(R.drawable.exercise2, "Push Ups", "15 reps"));
        exerciseList.add(new Exercise(R.drawable.exercise3, "Sit Ups", "15 reps"));
        exerciseList.add(new Exercise(R.drawable.exercise4, "Running", "30 minutes"));

        ListView myExerciseList = (ListView) view.findViewById(R.id.fragmentListView);
        ExerciseListAdapter exerciseListAdapter = new ExerciseListAdapter(getLayoutInflater(), exerciseList);
        myExerciseList.setAdapter(exerciseListAdapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction (Uri uri);
    }
}
