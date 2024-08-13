package kr.mainstream.seolyu.common.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;

@Service
public class EncryptService {
    private final Aes256 aes256;
    private final MessageDigester messageDigester;

    public EncryptService(@Value("${security.aes-key}") String AES_KEY) throws Exception {
        this.aes256 = new Aes256(AES_KEY);
        this.messageDigester = new MessageDigester();
    }

    public String encrypt(String str) throws Exception {
        return aes256.encode(str);
    }

    public String decrypt(String str) throws Exception {
        return aes256.decode(str);
    }

    public String digest(String str) throws Exception {
        return messageDigester.digest(str);
    }

    private static class Aes256 {
        private String iv;
        private Key keySpec;

        private Aes256(String key) {
            this.iv = key.substring(0, 16);

            byte[] keyBytes = new byte[16];
            byte[] b = key.getBytes(StandardCharsets.UTF_8);
            int len = Math.min(b.length, keyBytes.length);
            System.arraycopy(b, 0, keyBytes, 0, len);
            this.keySpec = new SecretKeySpec(keyBytes, "AES");
        }

        private String encode(String str) throws Exception {
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));

            byte[] encrypted = c.doFinal(str.getBytes(StandardCharsets.UTF_8));
            String enStr = Base64.getEncoder().encodeToString(encrypted);

            enStr = URLEncoder.encode(enStr, StandardCharsets.UTF_8.toString());
            return enStr;
        }

        private String decode(String str) throws Exception {
            if (str.contains("+")) {
                str = URLEncoder.encode(str, StandardCharsets.UTF_8.toString());
            }
            str = URLDecoder.decode(str, StandardCharsets.UTF_8.toString());

            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8)));
            byte[] byteStr = Base64.getDecoder().decode(str);
            return new String(c.doFinal(byteStr), StandardCharsets.UTF_8);
        }
    }

    private class MessageDigester {
        private MessageDigest md;

        private MessageDigester() throws Exception {
            md = MessageDigest.getInstance("SHA-256");
        }

        private String digest(String planText) {
            md.update(planText.getBytes());
            return Base64.getEncoder().encodeToString(md.digest());
        }
    }

    public String encryptToBase64(String str) throws Exception {
        return URLDecoder.decode(this.encrypt(str), StandardCharsets.UTF_8);
    }

    public String decryptToPlainText(String str) throws Exception {
        return this.decrypt(URLEncoder.encode(str, StandardCharsets.UTF_8));
    }
}
