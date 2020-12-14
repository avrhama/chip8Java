public interface Display {
    public class Pixel {
        byte r, g, b;
    }

    public enum Color {
        Black((byte) 0), White((byte) 1);

        byte value;

        private Color(byte value) {
            this.value = value;
        }
    }

    byte height = 64;
    byte width = 32;

    Color getPixel(byte x, byte y);

    void setPixel(byte x, byte y, Color c);
}
