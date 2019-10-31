package util;

/**
 * @author Huilton
 * @version 1.1
 */
public class Numeric {

    public static boolean isNumeric(String nro) {
        if (nro == null) {
            return false;
        }
        try {
            float num = Float.parseFloat(nro);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
