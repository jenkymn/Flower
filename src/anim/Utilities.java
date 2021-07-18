package demo.anim;

public class Utilities {
    private static final double CIRCLE = 2*Math.PI;

    public Utilities() {
    }

    public static void pause(int millSeconds) {
        try {
            Thread.sleep(millSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static double degToRad(double degree) {
        return (Math.PI/180) * degree;
    }
}
