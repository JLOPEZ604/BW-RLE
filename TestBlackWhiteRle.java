import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Do not modify this class.
 */
public class TestBlackWhiteRle {

    /**
     * Main entry point of the application.
     */
    public static void main(String[] args) throws IOException {

        // Write the B-W image out to a byte array (RLE)
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (BlackWhiteRleOutputStream os = populatePicture(baos)) {
            System.out.println("Raw Size: " + os.getRawCount());
            System.out.println("Compressed Size: " + baos.size());
        }

        // Read the B-W image (RLE) and draw it to the screen
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        try (BlackWhiteRleInputStream is = new BlackWhiteRleInputStream(bais)) {
            for (int h = 0; h < is.getHeight(); h++) {
                for (int w = 0; w < is.getWidth(); w++) {
                    int read = is.read();
                    System.out.print((char) read);
                }
                System.out.println();
            }
        }
    }

    /**
     * Build the mystery image.
     */
    public static BlackWhiteRleOutputStream populatePicture(OutputStream dest) throws IOException {
        final int height = 14;
        final int width = 23;
        BlackWhiteRleOutputStream out = new BlackWhiteRleOutputStream(dest, height, width);

        // Row 1
        out.writeBlack(23);

        // Row 2
        out.writeBlack(23);

        // Row 3
        out.writeBlack(4);
        out.writeWhite(4);
        out.writeBlack(7);
        out.writeWhite(4);
        out.writeBlack(4);

        // Row 4
        out.writeBlack(2);
        out.writeWhite(8);
        out.writeBlack(3);
        out.writeWhite(8);
        out.writeBlack(2);

        // Row 5
        out.writeBlack();
        out.writeWhite(10);
        out.writeBlack();
        out.writeWhite(10);
        out.writeBlack();

        // Row 6
        out.writeBlack();
        out.writeWhite(21);
        out.writeBlack();

        // Row 7
        out.writeBlack(2);
        out.writeWhite(19);
        out.writeBlack(2);

        // Row 8
        out.writeBlack(3);
        out.writeWhite(17);
        out.writeBlack(3);

        // Row 9
        out.writeBlack(4);
        out.writeWhite(15);
        out.writeBlack(4);

        // Row 10
        out.writeBlack(7);
        out.writeWhite(9);
        out.writeBlack(7);

        // Row 11
        out.writeBlack(10);
        out.writeWhite(3);
        out.writeBlack(10);

        // Row 12
        out.writeBlack(11);
        out.writeWhite();
        out.writeBlack(11);

        // Row 13
        out.writeBlack(23);

        // Row 14
        out.writeBlack(23);
        
        out.flush();

        return out;
    }

}
