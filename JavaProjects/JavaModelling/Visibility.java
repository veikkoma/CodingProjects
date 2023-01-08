package veikkola;

public abstract class Visibility {
    private boolean inBackground = false;

    public void changeVisibility() {
        this.inBackground = !this.inBackground;
    }

    public boolean isVisible() {
        return this.inBackground;
    }
}
