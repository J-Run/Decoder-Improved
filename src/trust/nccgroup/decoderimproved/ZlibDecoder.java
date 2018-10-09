package trust.nccgroup.decoderimproved;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.InflaterInputStream;

/**
 * Created by j on 12/7/16.
 */
public class ZLIBDecoder extends ByteModifier {
    public ZLIBDecoder() {
        super("ZLIB");
    }

    // GZIP Encode the bytes
    public byte[] modifyBytes(byte[] input) throws ModificationException{
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(input);
            InflaterInputStream zlis = new InflaterInputStream(bais);

            byte[] buffer = new byte[input.length*2];
            int bytesRead;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            while ((bytesRead = zlis.read(buffer)) != -1) {
                // I need to change this to accept arbitrary values
                if (bytesRead < input.length*2) {
                    output.write(buffer, 0, bytesRead);
                } else {
                    throw new ModificationException("Cannot Decompress, input too long.");
                }
            }


            return output.toByteArray();
        } catch (IOException e) {
            throw new ModificationException("Invalid ZLIB Input or something else goes wrong");
        }
    }
}
