
package demo.anim;

import demo.flower.Garden;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JWindow;

public class ScrawlWindow extends JWindow {
    private final int width = 800;
    private final int height = 500;
    private int ctrlCode = 0;
    private float alpha;

    public ScrawlWindow() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int start_x = (d.width - width) / 2;
        int start_y = (d.height - height) / 2;
        this.setBounds(start_x, start_y, width, height);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        switch (this.ctrlCode) {
            case 1:
                this.drawgGadient(g);
                break;
            case 2:
                Garden.buildGarden(g);
                break;
            case 3:
                Typer.typeText(g);
        }

    }

    private void drawgGadient(Graphics g) {
        BufferedImage img = new BufferedImage(width, height, 2);
        Graphics2D g2D = img.createGraphics();
        g2D.setComposite(AlphaComposite.getInstance(3, this.alpha));
        g2D.setColor(new Color(0xFFE5DF));
        g2D.fillRect(0, 0, width, height);
        g.drawImage(img, 0, 0, this);
    }

    public void startAnimation() {
        this.ctrlCode = 1;
        while (this.alpha < 0.4F) {
            this.alpha = (float) ((double) this.alpha + 0.01D);
            this.repaint();
            Utilities.pause(50);
        }
        this.ctrlCode = 2;
        this.repaint();
        while (!Garden.isFinished()) {
            this.ctrlCode = 3;
            this.repaint();
        }

    }
}
