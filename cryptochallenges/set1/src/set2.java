import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import java.util.BitSet;
import java.util.Random;

/**
 * Created by Fabian on 30-8-2016.
 */
public class set2 {
    public static void main(String[] args) throws IOException {
        //System.out.println(toNormalStr(createPadding("YELLOW SUBMARINE".getBytes(), 20)));

        //System.out.println(decryptEcb("YELLOW SUBMARINE", "", readFile("7.txt",StandardCharsets.UTF_8)));
        String[] test = {"SSBoYXZlIG1ldCB0aGVtIGF0IGNsb3NlIG9mIGRheQ==",
                "Q29taW5nIHdpdGggdml2aWQgZmFjZXM=",
                "RnJvbSBjb3VudGVyIG9yIGRlc2sgYW1vbmcgZ3JleQ==",
                "RWlnaHRlZW50aC1jZW50dXJ5IGhvdXNlcy4=",
                "SSBoYXZlIHBhc3NlZCB3aXRoIGEgbm9kIG9mIHRoZSBoZWFk",
                "T3IgcG9saXRlIG1lYW5pbmdsZXNzIHdvcmRzLA==",
                "T3IgaGF2ZSBsaW5nZXJlZCBhd2hpbGUgYW5kIHNhaWQ=",
                "UG9saXRlIG1lYW5pbmdsZXNzIHdvcmRzLA==",
                "QW5kIHRob3VnaHQgYmVmb3JlIEkgaGFkIGRvbmU=",
                "T2YgYSBtb2NraW5nIHRhbGUgb3IgYSBnaWJl",
                "VG8gcGxlYXNlIGEgY29tcGFuaW9u",
                "QXJvdW5kIHRoZSBmaXJlIGF0IHRoZSBjbHViLA==",
                "QmVpbmcgY2VydGFpbiB0aGF0IHRoZXkgYW5kIEk=",
                "QnV0IGxpdmVkIHdoZXJlIG1vdGxleSBpcyB3b3JuOg==",
                "QWxsIGNoYW5nZWQsIGNoYW5nZWQgdXR0ZXJseTo=",
                "QSB0ZXJyaWJsZSBiZWF1dHkgaXMgYm9ybi4=",
                "VGhhdCB3b21hbidzIGRheXMgd2VyZSBzcGVudA==",
                "SW4gaWdub3JhbnQgZ29vZCB3aWxsLA==",
                "SGVyIG5pZ2h0cyBpbiBhcmd1bWVudA==",
                "VW50aWwgaGVyIHZvaWNlIGdyZXcgc2hyaWxsLg==",
                "V2hhdCB2b2ljZSBtb3JlIHN3ZWV0IHRoYW4gaGVycw==",
                "V2hlbiB5b3VuZyBhbmQgYmVhdXRpZnVsLA==",
                "U2hlIHJvZGUgdG8gaGFycmllcnM/",
                "VGhpcyBtYW4gaGFkIGtlcHQgYSBzY2hvb2w=",
                "QW5kIHJvZGUgb3VyIHdpbmdlZCBob3JzZS4=",
                "VGhpcyBvdGhlciBoaXMgaGVscGVyIGFuZCBmcmllbmQ=",
                "V2FzIGNvbWluZyBpbnRvIGhpcyBmb3JjZTs=",
                "SGUgbWlnaHQgaGF2ZSB3b24gZmFtZSBpbiB0aGUgZW5kLA==",
                "U28gc2Vuc2l0aXZlIGhpcyBuYXR1cmUgc2VlbWVkLA==",
                "U28gZGFyaW5nIGFuZCBzd2VldCBoaXMgdGhvdWdodC4=",
                "VGhpcyBvdGhlciBtYW4gSSBoYWQgZHJlYW1lZA==",
                "QSBkcnVua2VuLCB2YWluLWdsb3Jpb3VzIGxvdXQu",
                "SGUgaGFkIGRvbmUgbW9zdCBiaXR0ZXIgd3Jvbmc=",
                "VG8gc29tZSB3aG8gYXJlIG5lYXIgbXkgaGVhcnQs",
                "WWV0IEkgbnVtYmVyIGhpbSBpbiB0aGUgc29uZzs=",
                "SGUsIHRvbywgaGFzIHJlc2lnbmVkIGhpcyBwYXJ0",
                "SW4gdGhlIGNhc3VhbCBjb21lZHk7",
                "SGUsIHRvbywgaGFzIGJlZW4gY2hhbmdlZCBpbiBoaXMgdHVybiw=",
                "VHJhbnNmb3JtZWQgdXR0ZXJseTo=",
                "QSB0ZXJyaWJsZSBiZWF1dHkgaXMgYm9ybi4="};
        for (int i = 0; i < test.length; i++) {
            System.out.println((toNormalStr(encryptCtr("0", test[i]))));
            System.out.println(toNormalStr(test[i].getBytes()));
            System.out.println(xOr(encryptCtr("0", test[i]), test[i].getBytes(), 40));

        }

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



    private static SecretKeySpec generateRandomAES() throws Exception {
        byte[] aes = new byte[16];
        new Random().nextBytes(aes);
        return new SecretKeySpec(aes, "AES");
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

    public static byte[] encryptCbc(String initVector, String plainText) {
        try {

            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            SecretKey secretKey = keyGen.generateKey();
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(secretKey.toString().getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            return cipher.doFinal(plainText.getBytes());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static byte[] encryptCtr(String initVector, String plainText) {
        try {

            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            SecretKey secretKey = keyGen.generateKey();
            SecretKeySpec skeySpec = new SecretKeySpec("YELLOW SUBMARINE".getBytes(), "AES");

            IvParameterSpec iv = new IvParameterSpec(new byte[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0});

            Cipher cipher = Cipher.getInstance("AES/CTR/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, generateRandomAES(), iv);

            return cipher.doFinal(plainText.getBytes());
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

    private static int detectBlockSize(byte[] ciphertext){
        // detect block size
        byte[] plaintext = { 0x20, 0x20 };
        byte[] block1 = copy(ciphertext, 0, ciphertext.length / 2);
        byte[] block2 = copy(ciphertext, ciphertext.length / 2, ciphertext.length - 1);
        int blocksize = 1;
        while (!Arrays.equals(block1, block2))
        {
            blocksize++;
            plaintext = fill((byte)0x20, blocksize * 2);
            ciphertext = encryptCbc("2", toNormalStr(plaintext));
            block1 = copy(ciphertext, 0, blocksize);
            block2 = copy(ciphertext, blocksize, 2 * blocksize);
            if (blocksize > 128)
            {
                blocksize = -1;
                break;
            }
        }

        return blocksize;
    }



    public static byte[] copy(byte[] input, int start, int end)
    {
        byte[] ret = new byte[end - start];
        for(int i=0; i<ret.length; i++)
        {
            ret[i] = input[start + i];
        }
        return ret;
    }

    public static byte[] fill(byte b, int count)
    {
        byte[] ret = new byte[count];
        for(int i=0; i<count; i++)
        {
            ret[i] = b;
        }
        return ret;
    }

}
