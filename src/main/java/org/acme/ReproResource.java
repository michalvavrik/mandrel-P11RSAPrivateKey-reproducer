package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

@Path("/repro")
public class ReproResource {

    private static final Cipher CIPHER;
    private static final KeyPair KEY_PAIR;

    static {
        try {
            var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024);
            KEY_PAIR = keyPairGenerator.generateKeyPair();

            CIPHER = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public void init() {
        try {
            CIPHER.init(Cipher.DECRYPT_MODE, KEY_PAIR.getPublic());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
