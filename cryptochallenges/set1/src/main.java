import javax.xml.bind.DatatypeConverter;
import java.lang.reflect.Array;
import java.math.BigInteger;

/**
 * Created by Fabian on 29-8-2016.
 */
public class main {

    // 1

//    static String string = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d";
//
//    public static void main(String[] args) {
//        byte[] b = new BigInteger(string,16).toByteArray();
//        System.out.println("string value is " + new String(b ));
//    }

    // 2

//    static String input_1 = "1c0111001f010100061a024b53535009181c";
//    static String input_2 = "686974207468652062756c6c277320657965";
//
//    public static void main(String[] args) {
//        byte[] bytes_1 = DatatypeConverter.parseHexBinary(input_1);
//        byte[] bytes_2 = DatatypeConverter.parseHexBinary(input_2);
//
//        byte[] bytes_combined = new byte[18];
//
//        int i = 0;
//        for (byte b : bytes_1)
//            bytes_combined[i] = (byte) (b ^ bytes_2[i++]);
//
//        System.out.println("string value is " + String.format("%x", new BigInteger(1, bytes_combined)));
//    }

    // 3

//    static String input_1 = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736";
//    static String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
//
//    public static void main(String[] args) {
//        byte[] bytes_1 = DatatypeConverter.parseHexBinary(input_1);
//
//        for (String letter : alphabet)
//        {
//            byte[] bytes_combined = new byte[18];
//
//            int i = 0;
//            for (byte b : bytes_1) {
//                bytes_combined[i] = (byte) (b ^ DatatypeConverter.parseHexBinary(letter)[0] );
//            }
//
//            System.out.println("string value is " + String.format("%x", new BigInteger(1, bytes_combined)));
//        }
//    }

    // 4

    static String input_1 = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736";
    static String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    public static void main(String[] args) {
        byte[] bytes_1 = DatatypeConverter.parseHexBinary(input_1);

        for (String letter : alphabet)
        {
            char character = letter.charAt(0);
            byte[] bytes_combined = new byte[18];

            int i = 0;
            for (byte b : bytes_1) {
                bytes_combined[i] = (byte) (b ^ character );
            }

            System.out.println("string value is " + new String( bytes_combined ));
//            System.out.println("string value is " + String.format("%x", new BigInteger(1, bytes_combined)));
        }
    }
}
