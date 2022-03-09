import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class BlackWhiteRleInputStream extends FilterInputStream {

    // static integers that keep the height and width of the current input stream created.
    public final int height;
    public final int width;
    public int[] length = null;
    public int index = 0;
    public final InputStream is;

    public BlackWhiteRleInputStream(InputStream in) throws IOException {
        super(in);
        this.is = in;
        height = in.read();
        width = in.read();
    }

    public int getHeight() throws IOException {
        return height;
    }

    public int getWidth() throws IOException {
        return width;
    }

    @Override
    public synchronized int read() throws IOException {

        if (length == null || length.length == index) {
            int run = super.read(); // collect whether 'B' or 'W'

            if(run == -1) {
                return -1;
            }

            int uncomp_length = super.read();
            length = new int[uncomp_length];
            Arrays.fill(length,run);
            index = 0;
        }
        return this.length[index++];

    }

}
