
package demo.anim;

import javax.swing.UIManager;

public class init {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        ScrawlWindow sc = new ScrawlWindow();
        sc.startAnimation();
        sc.dispose();
    }
}
