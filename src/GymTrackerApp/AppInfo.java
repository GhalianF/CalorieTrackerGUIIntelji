package GymTrackerApp;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class AppInfo implements Serializable {
    ArrayList<QuickAddFoods> quickAddFoods = new ArrayList<>();

    Color[][] colorSchemes = {{Color.black,new Color(32,32,32),new Color(0,128,255)}
                            ,{new Color(21, 35, 108),new Color(108, 94, 21),Color.white}
                            ,{new Color(219, 26, 94),new Color(94, 219, 26),Color.white}
                            ,{new Color(49, 24, 214),new Color(209, 33, 33),Color.white}};
    Color[] colorScheme;

    int colorSchemeIndex = 0;

    public AppInfo() {
        quickAddFoods.add(new QuickAddFoods("Eggs","78","6"));
        colorScheme = colorSchemes[colorSchemeIndex];
    }

    public int getColorSchemeIndex() {
        return colorSchemeIndex;
    }

    public void setColorSchemeIndex(int colorSchemeIndex) {
        this.colorSchemeIndex = colorSchemeIndex;
    }
}
