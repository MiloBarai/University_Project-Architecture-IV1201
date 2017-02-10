/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.security.MessageDigest;
import java.util.Base64;

/**
 *
 * @author MediaMarkt
 */
public class GeneralUtils {
    public static String encryptPass(String toEncrypt) throws Exception{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(toEncrypt.getBytes());
        byte[] hash = md.digest();
         String encrypted = Base64.getEncoder().encodeToString( hash );
         return encrypted;
        
        
    }
    
}
