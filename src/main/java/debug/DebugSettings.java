package debug;

public class DebugSettings {
    private final Boolean debugMode;
    public DebugSettings(Boolean debugMode) {
        this.debugMode = debugMode;
    }

    public Boolean isDebugMode() {
        return this.debugMode;
    }
}
