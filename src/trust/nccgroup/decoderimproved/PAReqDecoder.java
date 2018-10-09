package trust.nccgroup.decoderimproved;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;

/**
 * Created by j on 12/7/16.
 */
public class PAReqDecoder extends ByteModifier {
    public PAReqDecoder() {
        super("PAReq");
    }

    // GZIP Encode the bytes
    public byte[] modifyBytes(byte[] input) throws ModificationException{
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(input);
            URLDecoder udec = new URLDecoder();
            Base64Decoder b64dec = new Base64Decoder();
            ZLIBDecoder zldec = new ZLIBDecoder();
            byte[] b64bytes = udec.modifyBytes(input);
            byte[] zlibbytes = b64dec.modifyBytes(b64bytes);
            byte[] output = zldec.modifyBytes(zlibbytes);


            return output;
        } catch (Exception e) {
            throw new ModificationException("Something went wrong");
        }
    }
}
