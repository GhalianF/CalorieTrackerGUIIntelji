package GymTrackerApp;

import java.io.Serializable;

public class QuickAddFoods implements Serializable {
    String name;
    String calories;
    String protein;

    public QuickAddFoods(String name, String calories, String protein) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
    }

    @Override
    public String toString() {
        return name + " : " + calories  + " c " + protein + " p";
    }
}
