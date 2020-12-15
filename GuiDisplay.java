import javax.swing.*;
import javax.swing.JPanel;
import java.awt.Graphics;

public class GuiDisplay extends Display {
    JFrame frame;

    public void setPixel(byte x, byte y, Display.Color c) {
        board[x][y] = c;

    }

    public Display.Color getPixel(byte x, byte y) {
        return board[x][y];
    }

    public GuiDisplay() {
        frame = new JFrame();
        /*
         * {
         * 
         * @Override public void paint(Graphics g) { super.paint(g); //g.drawRect(50,
         * 50, 105, 105); //g.fillRect(0, 0, 10, 10); } };
         */
    }

    public void config() {
        clearScreen();
        int sizeScale = 10;
        frame.setSize(width * sizeScale, height * sizeScale);
        // frame.setLayout(null);
        java.awt.Color [] monochrome={java.awt.Color.BLACK,java.awt.Color.WHITE};
        frame.add(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (byte x = 0; x < width; x++) {
                    for (byte y = 0; y < height; y++) {
                        g.setColor(monochrome[board[x][y].value]);
                        g.fillRect(x*sizeScale, y*sizeScale, sizeScale, sizeScale);
                    }
                }
            System.out.println("drawing");              
            }
        }, java.awt.FlowLayout.LEFT);
        //frame.repaint();
        frame.setVisible(true);
    }

}
