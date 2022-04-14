package light;

public class Light {

    private int radius, diameter, color;
    private int[] lightMap;

    public Light(int radius, int color) {
        this.radius = radius;
        this.diameter = this.radius*2;
        this.color = color;

        for (int y = 0; y < diameter; y++) {
            for (int x = 0; x < diameter; x++) {
                double distance = Math.sqrt(
                        (x-radius)*(x-radius)+(y-radius)*(y-radius));
                if (distance < radius) {
                    double power = 1 - (distance/radius);
//                    this.lightMap[x + y * diameter] = ((int) (((color >> 16) & 0xff) * power) << 16 | (int)(((color >> 8) & 0xff) * power) << 8 | ((int) (color & 0xff) * power));
                }
                else {
                    this.lightMap[x + y * diameter] = 0;
                }
            }
        }
    }
}
