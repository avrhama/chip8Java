import javax.swing.*;
import java.awt.Graphics;

public class GuiDisplay extends Display {
    private java.awt.Color[] monochrome = { java.awt.Color.BLACK, java.awt.Color.WHITE };
    private int planeXoffset = 0;
    private int planeYoffset = 32;
    private int sizeScale = 10;
    private JFrame frame;
    private boolean boardChanged=false;
    public void setPixel(int x, int y, Display.Color c) {
        boardChanged=c!= board[x][y];
        board[x][y] = c;
       
    }
    public Display.Color getPixel(int x, int y) {
        return board[x][y];
    }

    public GuiDisplay(JFrame frame) {
        this.frame = frame;
    }

    public void config() {
        frame.setSize((width * sizeScale) + 20, (height * sizeScale) + 90);
    }

    public void paint(Graphics g) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                g.setColor(monochrome[board[x][y].value]);
                g.fillRect(planeXoffset + x * sizeScale, planeYoffset + y * sizeScale, sizeScale, sizeScale);
            }
        }
    }

    public void draw() {
        if(!boardChanged)
        return;
        frame.repaint();
        boardChanged=false;
    }
    public void clearScreen(){
        earseBaord();
    }

}
