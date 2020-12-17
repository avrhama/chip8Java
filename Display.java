public abstract class Display {

    public enum Color {
        Black((int) 0), White((int) 1);

        int value;

        private Color(int value) {
            this.value = value;
        }
    }
  //public for opcodes and guiDisplay.
   public int height = 32;
   public int width = 64;
   public Color[][] board= new Color[width][height];;

    abstract Color getPixel(int x, int y);
    abstract void setPixel(int x, int y, Color c);
    abstract void draw();

   public Display(){
       earseBaord();
   }
   abstract void clearScreen();
    public void earseBaord(){
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                board[x][y] = Color.Black;
            }
        }
    }
    
}
