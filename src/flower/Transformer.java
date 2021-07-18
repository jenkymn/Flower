package demo.flower;

public class Transformer {
    private double x;
    private double y;

    public Transformer(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Transformer rotate(double b) {
        double a = this.x;
        double c = this.y;
        this.x = Math.cos(b) * a - Math.sin(b) * c;
        this.y = Math.sin(b) * a + Math.cos(b) * c;
        return this;
    }

    public Transformer mult(double a) {
        this.x *= a;
        this.y *= a;
        return this;
    }

    public Transformer clone() {
        return new Transformer(this.x, this.y);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }
}
