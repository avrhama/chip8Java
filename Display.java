public abstract class Display {

    public enum Color {
        Black((byte) 0), White((byte) 1);

        byte value;

        private Color(byte value) {
            this.value = value;
        }
    }

    byte height = 32;
    byte width = 64;
    Color[][] board= new Color[width][height];;

    abstract Color getPixel(byte x, byte y);

    abstract void setPixel(byte x, byte y, Color c);
    
    public void clearScreen() {
        for (byte x = 0; x < width; x++) {
            for (byte y = 0; y < height; y++) {
                board[x][y] = Color.Black;
            }
        }
    }
}
