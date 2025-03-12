package ec.edu.uees.opciones;

public class AnimationManager {
    private static AnimationManager instance;
    private boolean animationsEnabled = true; 

    private AnimationManager() {} 

    public static AnimationManager getInstance() {
        if (instance == null) {
            instance = new AnimationManager();
        }
        return instance;
    }

    public boolean areAnimationsEnabled() {
        return animationsEnabled;
    }

    public void setAnimationsEnabled(boolean enabled) {
        animationsEnabled = enabled;
    }
}
