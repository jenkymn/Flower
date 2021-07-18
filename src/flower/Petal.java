package demo.flower;

import demo.anim.Utilities;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.CubicCurve2D.Double;

public class Petal {
    private final double startAngle;
    private final double angle;
    private final double stretchA;
    private final double stretchB;
    private final double growFactor;
    private double radius = 1.0D;
    private boolean isFinished;
    private final Bloom bloom;
    private final Graphics g;
    private final Transformer trans;

    public Petal(Transformer trans, double stretchA, double stretchB, double startAngle, double angle, double factor, Bloom bloom, Graphics g) {
        this.trans = trans;
        this.stretchA = stretchA;
        this.stretchB = stretchB;
        this.startAngle = startAngle;
        this.angle = angle;
        this.bloom = bloom;
        this.growFactor = factor;
        this.g = g;
    }

    private void draw() {
        Graphics2D g2D = (Graphics2D)this.g;
        Transformer sartPonit = (new Transformer(0.0D, this.radius)).rotate(Utilities.degToRad(this.startAngle));
        Transformer endPoint = sartPonit.clone().rotate(Utilities.degToRad(this.angle));
        Transformer ctrlPoint1 = sartPonit.clone().mult(this.stretchA);
        Transformer ctrlPoint2 = endPoint.clone().mult(this.stretchB);
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setColor(this.bloom.getColor());
        g2D.setStroke(new BasicStroke(1.0F));
        CubicCurve2D ccd = new Double(sartPonit.getX() + this.trans.getX(), sartPonit.getY() + this.trans.getY(), ctrlPoint1.getX() + this.trans.getX(), ctrlPoint1.getY() + this.trans.getY(), ctrlPoint2.getX() + this.trans.getX(), ctrlPoint2.getY() + this.trans.getY(), endPoint.getX() + this.trans.getX(), endPoint.getY() + this.trans.getY());
        g2D.draw(ccd);
    }

    public void render() {
        if (this.radius <= this.bloom.getRadius()) {
            this.radius += this.growFactor;
            this.draw();
        } else {
            this.isFinished = true;
        }

    }

    public boolean isFinished() {
        return this.isFinished;
    }
}
