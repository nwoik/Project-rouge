package debug;

public class DebugSettings {
    private Boolean debugMode;
    public DebugSettings(Boolean debugMode) {
        this.debugMode = debugMode;
    }

    public Boolean isDebugMode() {
        return this.debugMode;
    }

    public void changeDebugMode() {
        this.debugMode = !this.debugMode;
    }
}
