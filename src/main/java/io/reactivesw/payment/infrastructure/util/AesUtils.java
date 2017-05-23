package io.reactivesw.payment.infrastructure.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * The type Aes utils.
 */
public final class AesUtils {

  /**
   * The secret key.
   */
  private static SecretKeySpec secretKey;

  /**
   * Instantiates a new Aes utils.
   */
  private AesUtils() {
  }

  /**
   * Set secret key.
   *
   * @param myKey the secret key
   */
  private static void setKey(String myKey)
      throws UnsupportedEncodingException, NoSuchAlgorithmException {
    MessageDigest sha = null;
    byte[] key;
    key = myKey.getBytes("UTF-8");
    sha = MessageDigest.getInstance("SHA-1");
    key = sha.digest(key);
    key = Arrays.copyOf(key, 16); // use only first 128 bit
    secretKey = new SecretKeySpec(key, "AES");
  }

  /**
   * Encrypt string.
   *
   * @param strToEncrypt the str to encrypt
   * @param encryptKey the encrypt key
   * @return the string
   */
  public static String encrypt(String strToEncrypt, String encryptKey)
      throws UnsupportedEncodingException, NoSuchAlgorithmException,
      NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
    setKey(encryptKey);

    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    cipher.init(Cipher.ENCRYPT_MODE, secretKey);

    return Base64.encodeBase64String(cipher.doFinal(StringUtils.getBytesUtf8(strToEncrypt)));
  }

  /**
   * Decrypt string.
   *
   * @param strToDecrypt the str to decrypt
   * @param encryptKey the encrypt key
   * @return the string
   */
  public static String decrypt(String strToDecrypt, String encryptKey)
      throws UnsupportedEncodingException, NoSuchAlgorithmException, IllegalBlockSizeException,
      InvalidKeyException, BadPaddingException, NoSuchPaddingException {
    setKey(encryptKey);

    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
    cipher.init(Cipher.DECRYPT_MODE, secretKey);

    return StringUtils.newStringUtf8(cipher.doFinal(Base64.decodeBase64(strToDecrypt)));
  }
}
