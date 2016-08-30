import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.BitSet;

/**
 * Created by Fabian on 30-8-2016.
 */
public class set2 {

    public static void main(String[] args) throws IOException {
        System.out.println(toNormalStr(createPadding("YELLOW SUBMARINE".getBytes(), 20)));

        System.out.println(decryptEcb("YELLOW SUBMARINE", "", readFile("7.txt",StandardCharsets.UTF_8)));

    }

    private static byte[] createPadding(byte[] inputBlock, int blockSize) throws UnsupportedEncodingException {
        int paddingSize = blockSize - inputBlock.length;
        byte[] output = new byte[blockSize];
        byte padding = 0x4;
        for (int i = 0; i < blockSize; i++) {
            if(i >= inputBlock.length){
                output[i] = padding;
            }else{
                output[i] = inputBlock[i];
            }
        }
        System.out.println(new String(output, "UTF-8"));
        return null;
    }

    static String readFile(String path, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public static String decryptEcb(String key, String initVector, String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);

            byte [] original = cipher.doFinal(Base64.getDecoder().decode(encrypted.getBytes()));

            return toNormalStr(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String decryptCbc(String key, String initVector, String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte [] original = cipher.doFinal(Base64.getDecoder().decode(encrypted.getBytes()));

            return toNormalStr(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    private static String toNormalStr(byte[] arr)
    {
        try
        {
            return new String(arr, "UTF-8");
        } catch (Exception e)
        {
            return "";
        }
    }


}
