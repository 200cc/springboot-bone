package me.cc200.base.utils;

import lombok.SneakyThrows;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.util.Base64;

public abstract class AESUtil {

    public static final String AES = "AES";

    public static final String AES_ALG = "AES/ECB/PKCS5Padding";

    public static final Charset UTF8 = Charset.forName("utf8");

    public static String encrypt(String data, String key) {
        return Base64.getEncoder().encodeToString(encrypt(data.getBytes(UTF8), key.getBytes(UTF8)));
    }

    public static String decrypt(String data, String key) {
        return new String(decrypt(Base64.getDecoder().decode(data), key.getBytes(UTF8)), UTF8);
    }

    @SneakyThrows
    private static byte[] encrypt(byte[] data, byte[] key) {
        SecretKeySpec secretKeySpec = getSecretKeySpec(key);
        Cipher cipher = Cipher.getInstance(AES_ALG);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        return cipher.doFinal(data);
    }

    @SneakyThrows
    private static byte[] decrypt(byte[] data, byte[] key) {
        SecretKeySpec secretKeySpec = getSecretKeySpec(key);
        Cipher cipher = Cipher.getInstance(AES_ALG);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        return cipher.doFinal(data);
    }

    private static SecretKeySpec getSecretKeySpec(byte[] key) {
        byte[] b = new byte[16];
        System.arraycopy(key, 0, b, 0, key.length < b.length ? key.length : b.length);
        return new SecretKeySpec(b, AES);
    }

    public static void main(String[] args) {
        String b = "你好";

        String a = "hello world";
        System.out.println(encrypt(a, b));
        System.out.println(decrypt(encrypt(a, b), b));

        a = "你好世界！";
        System.out.println(encrypt(a, b));
        System.out.println(decrypt(encrypt(a, b), b));
    }
}
