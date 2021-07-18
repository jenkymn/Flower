package demo.flower;

import demo.anim.Utilities;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Garden {
    private final int width;
    private final int height;
    private static boolean isFinished = false;
    private static final Random rand = new Random();
    private final List<Bloom> blooms = new ArrayList<>();
    private final Graphics g;
    private List<Position> p = new ArrayList<>();

    public Garden(Graphics g) {
        this.g = g;
        this.width = g.getClipBounds().width;
        this.height = g.getClipBounds().height;
    }

    public void createRandomBloom(int point_X, int point_Y) {
        this.addBloom(new Bloom(new Transformer(point_X, point_Y), getBloomRadius(), getColor(), this.g));
    }

    public static Color getColor() {
        int red = rand.nextInt(128) + 128;
        int green = rand.nextInt(127) + 1;
        int blue = rand.nextInt(127) + 1;
        int alpha = 40;
        return new Color(red, green, blue, alpha);
    }

    public static int getBloomRadius() {
        return rand.nextInt(3) + 8;
    }

    public void render(int millSeconds) {
        while(!isFinished) {
            Utilities.pause(millSeconds);
            this.grow();
        }

    }

    private void grow() {
        for(int i = 0; i < this.blooms.size(); ++i) {
            this.blooms.get(i).draw();
            if (i == this.blooms.size() - 1 && this.blooms.get(i).isFinished()) {
                isFinished = true;
            }
        }

    }

    private void addBloom(Bloom b) {
        this.blooms.add(b);
    }

    //绘制心形图案的坐标,将其加入List<Position> p即可，用于替换默认的矩形队列
    private Position getHeartPoint(double angle) {
        double t = angle / Math.PI;
        double x = 19.5D * 16.0D * Math.pow(Math.sin(t), 3.0D);
        double y = -20.0D * (13.0D * Math.cos(t) - 5.0D * Math.cos(2.0D * t) - 2.0D * Math.cos(3.0D * t) - Math.cos(4.0D * t));
        return new Position((this.width / 2) + x, (this.height / 2 - 55) + y);
    }

    //绘制矩形花框
    private void prepareQueue(final int millSeconds) {
        Thread t = new Thread(() -> {
            int i;
            for(i = Garden.this.width / 2; i >= 40; i -= 20) {
                Utilities.pause(millSeconds);
                Garden.this.createRandomBloom(i, 40);
            }

            for(i = 60; i <= Garden.this.height - 40; i += 20) {
                Utilities.pause(millSeconds);
                Garden.this.createRandomBloom(40, i);
            }

            for(i = 60; i <= Garden.this.width - 40; i += 20) {
                Utilities.pause(millSeconds);
                Garden.this.createRandomBloom(i, 460);
            }

            for(i = Garden.this.height - 40; i >= 40; i -= 20) {
                Utilities.pause(millSeconds);
                Garden.this.createRandomBloom(760, i);
            }

            for(i = Garden.this.width - 60; i >= 420; i -= 20) {
                Utilities.pause(millSeconds);
                Garden.this.createRandomBloom(i, 40);
            }

        });
        t.start();
    }

    public static void buildGarden(Graphics g) {
        Garden garden = new Garden(g);
        garden.prepareQueue(80);
        garden.render(30);
    }

    public static boolean isFinished() {
        return isFinished;
    }
}
