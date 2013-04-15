package com.asccode.siteswatch.support;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 * User: Trabalho
 * Date: 10/04/13
 * Time: 13:33
 * To change this template use File | Settings | File Templates.
 */
public class Security {


    private static byte[] generateMD5(Object from) {

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(from.toString().getBytes());

            return md.digest();

        } catch (NoSuchAlgorithmException e) {

            return null;

        }

    }

    public static String md5(Object from) {

        byte[] bytes = Security.generateMD5(from);

        StringBuilder s = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {

            int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
            int parteBaixa = bytes[i] & 0xf;
            if (parteAlta == 0) s.append('0');
            s.append(Integer.toHexString(parteAlta | parteBaixa));

        }

        return s.toString();
    }

}
