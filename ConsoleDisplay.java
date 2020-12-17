
public class ConsoleDisplay extends Display {
    private boolean boardChanged = false;

    public void setPixel(int x, int y, Display.Color c) {
        boardChanged = c != board[x][y];
        board[x][y] = c;

    }

    public Display.Color getPixel(int x, int y) {
        return board[x][y];
    }

    // private static Runnable clearCmd;
    private String [] cmd;

    public void config() {
        String os = System.getProperty("os.name");
        if (os.contains("Windows")) {
            cmd = new String[] {"cmd.exe", "/c", "cls"};
        }else{
            cmd = new String[] {"clear"};
        }
    }

    public void draw() {
        if (!boardChanged)
            return;
        clearConsole();
        String strBoard = "";
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                if (board[x][y].value == 1) {
                    strBoard += ".";
                } else {
                    strBoard += " ";
                }
                strBoard += " ";
            }
            strBoard += "\n";
        }
        System.out.println(strBoard);
        boardChanged = false;
    }

    public void clearScreen() {
       earseBaord();
       clearConsole();
    }
    public void clearConsole(){
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
