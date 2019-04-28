package com.example.gyk_401_1.models;

public class Exercise {
    int exercisePicture;
    String exerciseName;
    String exerciseDuration;

    public Exercise(int exercisePicture, String exerciseName, String exerciseDuration) {
        this.exercisePicture = exercisePicture;
        this.exerciseName = exerciseName;
        this.exerciseDuration = exerciseDuration;
    }

    public int getExercisePicture() {
        return exercisePicture;
    }

    public void setExercisePicture(int exercisePicture) {
        this.exercisePicture = exercisePicture;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseDuration() {
        return exerciseDuration;
    }

    public void setExerciseDuration(String exerciseDuration) {
        this.exerciseDuration = exerciseDuration;
    }
}
