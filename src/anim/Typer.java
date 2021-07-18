package demo.anim;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Typer {
    private static final String CONTENT = "段落1：正文字符串1用小写的Ll来控制同段落换行，小写的Ll不会输出\n段落2：正文字符串2\n段落3：正文字符串3\n";
    private static final int x_StartOffset = 50;
    private static int x_Offset = 50;
    private static int y_Offset = 120;

    public Typer() {
    }

    public static void typeText(Graphics g) {
        Font font = new Font("宋体", Font.PLAIN, 24);
        Graphics2D g2D = (Graphics2D)g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setFont(font);

        for(int i = 0; i < CONTENT.length(); ++i) {
            if (CONTENT.substring(i, i + 1).equals("\n")) {
                y_Offset += 42;
                x_Offset = x_StartOffset;
            } else if (CONTENT.substring(i, i + 1).equals("l")) {
                y_Offset += 42;
                x_Offset = x_StartOffset + 72;
            } else {
                x_Offset += 24;
                g2D.drawString(CONTENT.substring(i, i + 1), x_Offset, y_Offset);
            }

            Utilities.pause(200);
        }

        g2D.drawString("结尾字符串", 600, 420);
        Utilities.pause(4000);
    }
}
