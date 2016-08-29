import javax.xml.bind.DatatypeConverter;

/**
 * Created by Fabian on 29-8-2016.
 */
public class challenge3 {

    public static void main(String[] args) {

        byte[] c3Ciphertext = toByteArray("1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736");

        for (int i = 0; i < 255; i++)
        {
            byte[] decoded = single(c3Ciphertext, (byte) i);
            double score = stringMetric(decoded);
            if (score > 0.90) // at least 95% score
                System.out.println(toNormalStr(decoded) + " - Score: " + score + " Key: " + (char) i);
        }

    }

    private static byte[] toByteArray(String s) {
        return DatatypeConverter.parseHexBinary(s);
    }

    public static byte[] single(byte[] arr, byte key)
    {
        byte[] ret = new byte[arr.length];
        for(int i=0; i<arr.length; i++)
        {
            ret[i] = (byte) (arr[i] ^ key);
        }
        return ret;
    }

    public static double stringMetric(byte[] arr)
    {
        int count = 0;
        for (byte b : arr)
        {
            // stuff is weighted how I felt like it
            if ((b >= 'a' && b <= 'z') || b == ' ')
                count += 4;
            if ((b >= 'A' && b <= 'Z') || b == '\'' || b == '.' || b == '!' || b == '?')
                count += 2;
            if ((b >= '0' && b <= '9') || b == '\n' || b == '\t' || b == '\r')
                count++;
        }
        return (double)count / (arr.length * 4);
    }

    public static String toNormalStr(byte[] arr)
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
