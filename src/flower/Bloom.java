package demo.flower;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Bloom {
    private final int radius;
    private boolean isFinished;
    private static final Random rand = new Random();
    private final Color color;
    private final ArrayList<Petal> petals = new ArrayList<>();

    public Bloom(Transformer trans, int radius, Color color, Graphics g) {
        this.radius = radius;
        this.color = color;
        int petalCount = getPetalCount();
        double angle = 360.0 / petalCount;
        double sAngle = Math.random() * 90.0D;

        for(int i = 0; i < petalCount; ++i) {
            this.petals.add(new Petal(trans, getPetalStretch(), getPetalStretch(), sAngle +  i * angle, angle, getGrowFactor(), this, g));
        }

    }

    public double getRadius() {
        return this.radius;
    }

    public void draw() {
        boolean done = true;

        for (Petal value : this.petals) {
            value.render();
            done = done && value.isFinished();
        }

        if (done) {
            this.isFinished = true;
        }

    }

    public Color getColor() {
        return this.color;
    }

    public boolean isFinished() {
        return this.isFinished;
    }

    public static int getPetalCount() {
        return rand.nextInt(8) + 8;
    }

    public static double getPetalStretch() {
        return rand.nextDouble() * 2.9D + 0.1D;
    }

    private static double getGrowFactor() {
        return rand.nextDouble() * 0.9D + 0.1D;
    }
}
