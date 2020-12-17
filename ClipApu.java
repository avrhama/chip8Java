
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class ClipApu implements Apu {

    private final int BUFFER_SIZE = 128000;
    //question: 22KB is it too much to be hold in the memory?, should I read the wav file for each play call? 
   private byte[] data;
    public  void config(String filename) {
        InputStream stream = ClipApu.class.getResourceAsStream(filename);
        byte buffer[] = new byte[BUFFER_SIZE];
        int b, i = 0;
        try {
            while ((b = stream.read()) != -1) {

                buffer[i++] = (byte) b;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
       data = new byte[i + 1];
        while (i >= 0) {
            data[i] = buffer[i];
            i--;
        }
    }

    public void play() {

        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new ByteArrayInputStream(data));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.setFramePosition(0);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
}