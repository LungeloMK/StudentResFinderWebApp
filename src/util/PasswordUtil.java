package util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public final class PasswordUtil {
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 256;

    private PasswordUtil() {
    }

    public static String generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String hashPassword(String password, String salt) {
        try {
            byte[] saltBytes = Base64.getDecoder().decode(salt);
            KeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            return Base64.getEncoder().encodeToString(factory.generateSecret(spec).getEncoded());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw new IllegalStateException("Password hashing is not available", ex);
        }
    }

    public static boolean verifyPassword(String password, String expectedHash, String salt) {
        if (password == null || expectedHash == null || salt == null) {
            return false;
        }
        String actualHash = hashPassword(password, salt);
        return constantTimeEquals(actualHash, expectedHash);
    }

    private static boolean constantTimeEquals(String first, String second) {
        byte[] firstBytes = first.getBytes();
        byte[] secondBytes = second.getBytes();
        int diff = firstBytes.length ^ secondBytes.length;
        int length = Math.min(firstBytes.length, secondBytes.length);
        for (int i = 0; i < length; i++) {
            diff |= firstBytes[i] ^ secondBytes[i];
        }
        return diff == 0;
    }
}
