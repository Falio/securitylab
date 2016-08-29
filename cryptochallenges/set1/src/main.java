import java.util.Base64;

/**
 * Created by Fabian on 29-8-2016.
 */
public class main {

    static String string = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d";

    public static void main(String[] args) {
        String bytesEncoded = Base64.getEncoder().encodeToString(string.getBytes());
        System.out.println("ecncoded value is " + new String(bytesEncoded ));
    }
}
