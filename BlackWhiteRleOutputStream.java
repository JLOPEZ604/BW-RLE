import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BlackWhiteRleOutputStream extends FilterOutputStream {

    private int rawCount = 0;
    private final int height;
    private final int width;

    public BlackWhiteRleOutputStream(OutputStream out, int height, int width) throws IOException {
        super(out);
        this.height = height;
        this.width = width;
        out.write(height);
        out.write(width);
    }

    public void writeWhite() throws IOException {
        super.out.write('W');
        super.out.write(1);
        rawCount++;



    }

    public void writeBlack() throws IOException {
        super.out.write('B');
        super.out.write(1);
        rawCount++;
    }

    public void writeWhite(final int count) throws IOException {
        super.out.write('W');
        super.out.write(count);
        rawCount += count;
    }

    public void writeBlack(final int count) throws IOException {
        super.out.write('B');
        super.out.write(count);
        rawCount += count;
    }

    @Override
    public synchronized void flush() throws IOException {
        super.out.flush();
    }
    
    public int getRawCount() {
        return rawCount;
    }

    @Override
    public void close() throws IOException {
        if (rawCount != height * width) {
            System.out.println("Image has not finished populating.");
        }
        super.out.close();
    }

}
