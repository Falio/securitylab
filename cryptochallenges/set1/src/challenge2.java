import javax.xml.bind.DatatypeConverter;
import java.util.Base64;

/**
 * Created by Fabian on 29-8-2016.
 */
public class challenge2 {

    static private String hex = "1c0111001f010100061a024b53535009181c";
    static private String hex2 = "686974207468652062756c6c277320657965";


    public static void main(String[] args) {

        byte[] array_1 = toByteArray(hex);
        byte[] array_2 = toByteArray(hex2);

        System.out.println("We got:    " + toHexStr(xOr(array_1, array_2, 17)));

    }

    private static byte[] toByteArray(String s) {
        return DatatypeConverter.parseHexBinary(s);
    }

    private static byte[] xOr(byte[] b1, byte[] b2, int len){

        if(len > b1.length || len > b2.length)
            throw new RuntimeException("Cant do fixed xor longer than buffers");
        byte[] ret = new byte[len];
        for(int i=0; i<len; i++)
        {
            ret[i] = (byte) (b1[i] ^ b2[i]);
        }
        return ret;

    }

    private static String toHexStr(byte[] arr)
    {
        String hexKey = "0123456789abcdef";

        String s = "";
        for (byte b : arr)
        {
            s += hexKey.charAt((b >> 4) & 0x0F);
            s += hexKey.charAt((b >> 0) & 0x0F);
        }
        return s;
    }


}
