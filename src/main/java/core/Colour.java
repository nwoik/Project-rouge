package core;

//helper class for simplifying the level loader. Stores RGB values in separate attributes, unlike pixel class
public class Colour {
    private int red;
    private int green;
    private int blue;

    public int getBlue() {
        return blue;
    }
    public int getGreen() {
        return green;
    }
    public int getRed() {
        return red;
    }
    public void setBlue(int blue) {
        this.blue = blue;
    }
    public void setGreen(int green) {
        this.green = green;
    }
    public void setRed(int red) {
        this.red = red;
    }
}
