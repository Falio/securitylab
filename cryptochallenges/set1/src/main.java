import java.util.Base64;

/**
 * Created by Fabian on 29-8-2016.
 */
public class main {

    static String hex = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d";

    public static void main(String[] args) {

        String bytesEncoded = Base64.getEncoder().encodeToString(hexStringToByteArray(hex));
        System.out.println("encoded value is " + bytesEncoded);
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}
