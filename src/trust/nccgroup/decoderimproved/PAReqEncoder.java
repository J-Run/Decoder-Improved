package trust.nccgroup.decoderimproved;

import java.io.ByteArrayInputStream;

/**
 * Created by j on 12/7/16.
 */
public class PAReqEncoder extends ByteModifier {
    public PAReqEncoder() {
        super("PAReq");
    }

    // GZIP Encode the bytes
    public byte[] modifyBytes(byte[] input) throws ModificationException{
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(input);
            URLSpecialCharEncoder uenc = new URLSpecialCharEncoder();
            Base64Encoder b64enc = new Base64Encoder();
            ZLIBEncoder zlenc = new ZLIBEncoder();

            byte[] zlibbytes = zlenc.modifyBytes(input);
            byte[] b64bytes = b64enc.modifyBytes(zlibbytes);
            byte[] output = uenc.modifyBytes(b64bytes);



            return output;
        } catch (Exception e) {
            throw new ModificationException("Something went wrong");
        }
    }
}
