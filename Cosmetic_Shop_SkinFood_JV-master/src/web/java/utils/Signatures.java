package web.java.utils;

import javax.servlet.http.Part;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Signatures {

    private PublicKey publicKey;
    private PrivateKey privateKey;

    public Signatures(){
        try {
            KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("DSA");
            SecureRandom secureRandom = new SecureRandom();
            keyGenerator.initialize(1024, secureRandom);
            KeyPair keyPair = keyGenerator.generateKeyPair();
            this.publicKey = keyPair.getPublic();
            this.privateKey = keyPair.getPrivate();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public String base64Key(byte[] data){
        return Base64.getEncoder().encodeToString(data);
    }

    public boolean check(Part part, Path pathUpload, String publicKey) {
        String docFileKey = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        String pathFileUpload = Paths.get(pathUpload.toString(), docFileKey).toString();
        String message = "To Be or not To Be";

        FileInputStream inputStream = null;
        try {
            part.write(pathFileUpload);
            inputStream = new FileInputStream(pathFileUpload);
            int ch;
            String privateKey = "";
            while ((ch = inputStream.read()) != -1) {
                privateKey += ((char) ch);
            }
            KeyFactory keyFactory = KeyFactory.getInstance("DSA");
            X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
            PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey));
            PrivateKey prvKey = keyFactory.generatePrivate(keySpecPKCS8);
            PublicKey pubKey = keyFactory.generatePublic(keySpecX509);

            Signature privateSignature = Signature.getInstance("DSA");

            privateSignature.initSign(prvKey);
            privateSignature.update(message.getBytes(StandardCharsets.UTF_8));
            byte[] sign = privateSignature.sign();
            Signature publicSignature = Signature.getInstance("DSA");
            publicSignature.initVerify(pubKey);
            publicSignature.update(message.getBytes(StandardCharsets.UTF_8));
            boolean isCorrect = publicSignature.verify(sign);
            return isCorrect;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | SignatureException | InvalidKeyException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
