/**
 * @author
 *
 * IV1201 Design of Global Applications: Group 8
 * Arvid Persson Moosavi <amoosavi at kth.se>
 * Arvin Behshad <arvinb at kth.se>
 * Milad Barai <barai at kth.se>
 * Massar Almosawi <massar at kth.se>
 *
 */
package se.kth.ict.iv1201.recruitmentapp.utils;

import java.security.MessageDigest;
import java.util.Base64;

/*
 * Javadoc placeholder
 */
public class GeneralUtils {

    public static String encryptPass(String toEncrypt) throws Exception {

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(toEncrypt.getBytes());
        byte[] hash = md.digest();
        String encrypted = Base64.getEncoder().encodeToString(hash);

        return encrypted;
    }
        public static Throwable getRootCause(Throwable throwable) {
        if (throwable.getCause() != null) {
            return getRootCause(throwable.getCause());
        }
        return throwable;
    }
}
