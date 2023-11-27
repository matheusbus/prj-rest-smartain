package br.udesc.smartain.restsmartainproject.domain.glo.UserComponent;

import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

public class HashingPassword {

    public static String hashing(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                hexString.append(String.format("%02x", hashByte & 0xff));
            }

            String hashedPassword = hexString.toString();
            return hashedPassword;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
