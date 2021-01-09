package libgofer;

import java.util.Random;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;

public class Sound {

    public static float SAMPLE_RATE = 8000f;

    public static void tone(int hz, int msecs) {
        tone(hz, msecs, 1.0);
    }

    public static void sound() {
        Sound.tone(400, 500, 0.9);
    }

    public static void tone(int hz, int msecs, double vol) {
        try {
            byte[] buf = new byte[1];
            AudioFormat af
                    = new AudioFormat(
                            SAMPLE_RATE, // sampleRate
                            8, // sampleSizeInBits
                            1, // channels
                            true, // signed
                            false);      // bigEndian
            SourceDataLine sdl;
            sdl = AudioSystem.getSourceDataLine(af);
            sdl.open(af);
            sdl.start();
            for (int i = 0; i < msecs * 8; i++) {
                double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
                buf[0] = (byte) (Math.sin(angle) * 127.0 * vol);
                sdl.write(buf, 0, 1);
            }
            sdl.drain();
            sdl.stop();
            sdl.close();
        } catch (LineUnavailableException ex) {
            //
        }
    }

    public static void laser(int repeat) {
        try{
            AudioFormat af
                    = new AudioFormat(
                            SAMPLE_RATE, // sampleRate
                            8, // sampleSizeInBits
                            1, // channels
                            true, // signed
                            false);      // bigEndian
            SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
            sdl.open(af);
            sdl.start();

            byte[] buf = new byte[1];
            int step;

            for (int j = 0; j < repeat; j++) {
                step = 10;
                for (int i = 0; i < 2000; i++) {
                    buf[0] = ((i % step > 0) ? 32 : (byte) 0);

                    if (i % 250 == 0) {
                        step += 2;
                    }
                    sdl.write(buf, 0, 1);
                }
                Thread.sleep(200);
            }
            sdl.drain();
            sdl.stop();
            sdl.close();
        } catch (LineUnavailableException | InterruptedException ex) {
            //
        }
    }

    public static void warp(int repeat) {
        try{
            AudioFormat af
                    = new AudioFormat(
                            SAMPLE_RATE, // sampleRate
                            8, // sampleSizeInBits
                            1, // channels
                            true, // signed
                            false);      // bigEndian
            SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
            sdl.open(af);
            sdl.start();

            byte[] buf = new byte[1];
            int step;

            for (int j = 0; j < repeat; j++) {
                step = 25;
                for (int i = 0; i < 2000; i++) {
                    if (i < 500) {
                        buf[0] = ((i % step > 0) ? 32 : (byte) 0);
                        if (i % 25 == 0) {
                            step--;
                        }
                    } else {
                        buf[0] = ((i % step > 0) ? 16 : (byte) 0);
                        if (i % 50 == 0) {
                            step++;
                        }
                    }
                    sdl.write(buf, 0, 1);
                }
            }
            sdl.drain();
            sdl.stop();
            sdl.close();
        } catch (Exception ex) {
            //
        }
    }

    public static void bang() {
        try {
            AudioFormat af
                    = new AudioFormat(
                            SAMPLE_RATE, // sampleRate
                            8, // sampleSizeInBits
                            1, // channels
                            true, // signed
                            false);      // bigEndian
            SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
            sdl.open(af);
            sdl.start();

            byte[] buf = new byte[1];
            Random r = new Random();
            boolean silence = true;
            for (int i = 0; i < 8000; i++) {
                while (r.nextInt() % 10 != 0) {
                    buf[0]
                            = silence ? 0
                            : (byte) Math.abs(r.nextInt()
                                    % (int) (1. + 63. * (1. + Math.cos(((double) i)
                                            * Math.PI / 8000.))));
                    i++;
                    sdl.write(buf, 0, 1);
                }
                silence = !silence;
            }
            sdl.drain();
            sdl.stop();
            sdl.close();
        } catch (Exception ex) {
            //
        }
    }

    public static void main(String[] args) throws InterruptedException, LineUnavailableException {
        Sound.tone(1000, 100);
        Thread.sleep(1000);
        Sound.tone(100, 1000);
        Thread.sleep(1000);
        Sound.tone(5000, 100);
        Thread.sleep(1000);
        Sound.tone(800, 500, 0.9);
        Thread.sleep(1000);
        Sound.tone(400, 500, 0.9);
        Sound.laser(1);
    }
}
