package trust.nccgroup.decoderimproved;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;

/**
 * Created by j on 12/6/16.
 */
public class ZLIBEncoder extends ByteModifier {
    public ZLIBEncoder() {
        super("ZLIB");
    }

    // GZIP Encode the bytes
    public byte[] modifyBytes(byte[] input) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream(input.length);
            DeflaterOutputStream zos;
            zos = new DeflaterOutputStream(bos);
            zos.write(input, 0, input.length);
            zos.finish();
            zos.flush();
            bos.flush();
            zos.close();
            bos.close();
            return bos.toByteArray();
        } catch (IOException e) {
            return input;
        }
    }
}

