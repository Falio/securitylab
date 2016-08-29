import java.math.BigInteger;

/**
 * Created by Fabian on 29-8-2016.
 */
public class main {

    static String string = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d";

    public static void main(String[] args) {
        byte[] b = new BigInteger(string,16).toByteArray();
        System.out.println("string value is " + new String(b ));
    }
}
