package demo.anim;

import java.awt.Font;
import java.io.InputStream;

public class CustomFont {
    public CustomFont() {
    }

    public Font getFont(String name, float size) {
        Font font = null;

        try {
            InputStream is = this.getClass().getResourceAsStream("/demo/resources/" + name + ".ttf");
            font = Font.createFont(0, is);
            font = font.deriveFont(size);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return font;
    }
}
