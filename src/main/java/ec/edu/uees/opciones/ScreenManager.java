package ec.edu.uees.opciones;

public class ScreenManager {
    private static ScreenManager instance;
    private boolean screenLocked = false; 
    
    private ScreenManager(){}
    
    public static ScreenManager getInstance(){
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }
    
    public boolean isScreenLocked() {
        return screenLocked;
    }

    public void setScreenLock(boolean lock) {
        screenLocked = lock;
    }
}
