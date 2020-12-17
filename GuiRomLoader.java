import javax.lang.model.util.ElementScanner14;
import javax.swing.*;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class GuiRomLoader extends RomLoader implements ActionListener {
    // this frame is shared by the RomLoader and GuiDisplay
    private JFrame frame;
    private JPanel panel;
    private JButton openButton;
    private boolean ready = false;
    private JFileChooser fc;

    public void turnOn() {
        frame = new JFrame();
<<<<<<< HEAD
       
=======
        frame.setVisible(true);
>>>>>>> fdb73b90a68e2090926e0b685425e8d10ddeae2c
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        openButton = new JButton("Open Rom");
        openButton.setSize(100,30);
        openButton.setFocusable(false);
        openButton.addActionListener(this);
        openButton.setLocation(30,0);
        openButton.setLayout(null);

        fc = new JFileChooser();

<<<<<<< HEAD
        boolean setGuiDisplay=true;
=======
        boolean setGuiDisplay=false;
>>>>>>> fdb73b90a68e2090926e0b685425e8d10ddeae2c
        final GuiDisplay guiDisplay = new GuiDisplay(frame);
        ConsoleDisplay consoleDisplay=null;
        if(!setGuiDisplay){
            consoleDisplay=new ConsoleDisplay();
            consoleDisplay.config();
<<<<<<< HEAD
            frame.setSize(150,100);
=======
>>>>>>> fdb73b90a68e2090926e0b685425e8d10ddeae2c
        }
        final GuiJoypad guiJoypad = new GuiJoypad();



        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if(setGuiDisplay)
                guiDisplay.paint(g);
            }
        }; 
          
        panel.setLayout(null);
        panel.add(openButton);
<<<<<<< HEAD
        frame.add(panel);
        frame.setVisible(true);
=======

        frame.add(panel);

>>>>>>> fdb73b90a68e2090926e0b685425e8d10ddeae2c
       
        new Thread(new Runnable() {
            public void run() { 
                if(setGuiDisplay)   
                guiDisplay.config();
                guiJoypad.config();
                frame.addKeyListener(guiJoypad);
                ready = true;
            }
        }).start();

        bus.joypad = guiJoypad;
        
        bus.display =setGuiDisplay?guiDisplay:consoleDisplay;
        bus.configBus();
        while (!ready)
            try {
                Thread.sleep(100);
            } catch (Exception e) {
               e.printStackTrace();
            }
        bus.turnOn();

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == openButton) {
            bus.setPaused(true);
            int returnVal;
            try {
                 returnVal = fc.showOpenDialog(frame);
            } catch (HeadlessException ex) {
                ex.printStackTrace();
                bus.setPaused(false);
                return;
            }
           
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                bus.restartBus();
                bus.loadRom(file);
            } 
            bus.setPaused(false);
        }

    }

}
