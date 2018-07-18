package me.cc200.base.utils;

import lombok.SneakyThrows;

import javax.crypto.Cipher;
import java.nio.charset.Charset;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * RSA
 *
 * // javascript RSA: https://github.com/travist/jsencrypt
 * // vuejs RSA: import JsEncrypt from 'jsencrypt'
 */
public abstract class RSAUtil {

    public static final String RSA = "RSA";

    public static final String SIGN_ALG = "SHA1WithRSA";

    public static final Charset UTF8 = Charset.forName("utf8");

    /**
     * 生成密钥对
     * @return
     */
    @SneakyThrows
    public static KeyPair genKeyPair() {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
        keyPairGenerator.initialize(1024, new SecureRandom());
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * 公钥
     * @param keyPair
     * @return
     */
    public static String genPublicKey(KeyPair keyPair) {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    /**
     * 私钥
     * @param keyPair
     * @return
     */
    public static String genPrivateKey(KeyPair keyPair) {
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    /**
     * 从字符串加载公钥
     * @param str
     * @return
     */
    @SneakyThrows
    public static RSAPublicKey loadPublicKey(String str) {
        byte[] bytes = Base64.getDecoder().decode(str);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }

    /**
     * 从字符串加载私钥
     * @param str
     * @return
     */
    @SneakyThrows
    public static RSAPrivateKey loadPrivateKey(String str) {
        byte[] bytes = Base64.getDecoder().decode(str);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
    }

    /**
     * 使用公钥加密
     * @param publicKey
     * @param data
     * @return
     */
    @SneakyThrows
    private static byte[] encrypt(RSAPublicKey publicKey, byte[] data) {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 使用私钥加密
     * @param privateKey
     * @param data
     * @return
     */
    @SneakyThrows
    private static byte[] encrypt(RSAPrivateKey privateKey, byte[] data) {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 使用公钥解密
     * @param publicKey
     * @param data
     * @return
     */
    @SneakyThrows
    private static byte[] decrypt(RSAPublicKey publicKey, byte[] data) {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 使用私钥解密
     * @param privateKey
     * @param data
     * @return
     */
    @SneakyThrows
    private static byte[] decrypt(RSAPrivateKey privateKey, byte[] data) {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 使用公钥加密字符串，返回加密结果Base64字符串
     * @param publicKey
     * @param data
     * @return
     */
    public static String encrypt(RSAPublicKey publicKey, String data, Charset charset) {
        return Base64.getEncoder().encodeToString(encrypt(publicKey, data.getBytes(charset)));
    }

    public static String encrypt(RSAPublicKey publicKey, String data) {
        return Base64.getEncoder().encodeToString(encrypt(publicKey, data.getBytes(UTF8)));
    }

    /**
     * 使用公钥解密Base64字符串，返回解密后的字符串
     * @param publicKey
     * @param data
     * @return
     */
    public static String decrypt(RSAPublicKey publicKey, String data, Charset charset) {
        return new String(decrypt(publicKey, Base64.getDecoder().decode(data)), charset);
    }

    public static String decrypt(RSAPublicKey publicKey, String data) {
        return new String(decrypt(publicKey, Base64.getDecoder().decode(data)), UTF8);
    }

    /**
     * 使用私钥加密字符串，返回加密结果Base64字符串
     * @param privateKey
     * @param data
     * @return
     */
    public static String encrypt(RSAPrivateKey privateKey, String data, Charset charset) {
        return Base64.getEncoder().encodeToString(encrypt(privateKey, data.getBytes(charset)));
    }

    public static String encrypt(RSAPrivateKey privateKey, String data) {
        return Base64.getEncoder().encodeToString(encrypt(privateKey, data.getBytes(UTF8)));
    }

    /**
     * 使用私钥解密base64字符串，返回解密后的字符串
     * @param privateKey
     * @param data
     * @return
     */
    public static String decrypt(RSAPrivateKey privateKey, String data, Charset charset) {
        return new String(decrypt(privateKey, Base64.getDecoder().decode(data)), charset);
    }
    public static String decrypt(RSAPrivateKey privateKey, String data) {
        return new String(decrypt(privateKey, Base64.getDecoder().decode(data)), UTF8);
    }

    /**
     * 使用私钥对加密字符串进行签名
     * @param data
     * @param privateKey
     * @return
     */
    @SneakyThrows
    public static String sign(RSAPrivateKey privateKey, String data) {
        Signature signature = Signature.getInstance(SIGN_ALG);
        signature.initSign(privateKey);
        signature.update(data.getBytes(UTF8));
        return Base64.getEncoder().encodeToString(signature.sign());
    }

    /**
     * 使用公钥对加密字符串进行签名
     * @param data
     * @param publicKey
     * @return
     */
    @SneakyThrows
    public static String sign(RSAPublicKey publicKey, String data) {
        Signature signature = Signature.getInstance(SIGN_ALG);
        signature.update(data.getBytes(UTF8));
        return Base64.getEncoder().encodeToString(signature.sign());
    }

    /**
     * 使用公钥，对加密字符串和签名，进行验签
     * @param data
     * @param sign
     * @param publicKey
     * @return
     */
    @SneakyThrows
    public static boolean signCheck(PublicKey publicKey, String data, String sign) {
        Signature signature = Signature.getInstance(SIGN_ALG);
        signature.initVerify(publicKey);
        signature.update(data.getBytes(UTF8));
        return signature.verify(Base64.getDecoder().decode(sign));
    }

    @SneakyThrows
    public static void main(String[] args) {
        KeyPair keyPair = genKeyPair();
        String rsaPublicKey = genPublicKey(keyPair);
        String rsaPrivateKey = genPrivateKey(keyPair);
        System.out.println("----------");
        System.out.println("publicKey => " + rsaPublicKey);
        System.out.println("privateKey => " + rsaPrivateKey);

        RSAPublicKey publicKey = loadPublicKey(rsaPublicKey);
        RSAPrivateKey privateKey = loadPrivateKey(rsaPrivateKey);

        String data = "{\"xqj\":\"去雁无凭传锦字，春泥抵死污人衣，海棠过了有荼蘼\"}";
        System.out.println("--- origin => " + data);

        System.out.println("\n--- public encrypt, private decrypt ---");
        String encrypted1 = encrypt(publicKey, data);
        System.out.println("public encrypt => " + encrypted1);
        String decrypted1 = decrypt(privateKey, encrypted1);
        System.out.println("private decrypt => " + decrypted1);

        System.out.println("\n---private encrypt, public decrypt---");
        String encrypted2 = encrypt(privateKey, data);
        System.out.println("private encrypt => " + encrypted2);
        String decrypted2 = decrypt(publicKey, encrypted2);
        System.out.println("public decrypt => " + decrypted2);

        System.out.println("\n---sign & sign check---");
        String sign1 = sign(privateKey, encrypted1);
        System.out.println("sign encrypted1 => " + sign1);
        boolean checked1 = signCheck(publicKey, encrypted1, sign1);
        System.out.println(checked1);
        String sign2 = sign(privateKey, encrypted2);
        System.out.println("sign encrypted2 => " + sign2);
        boolean checked2 = signCheck(publicKey, encrypted2, sign2);
        System.out.println(checked2);
        boolean checked3 = signCheck(publicKey, encrypted1, sign2);
        System.out.println(checked3);
    }
}
