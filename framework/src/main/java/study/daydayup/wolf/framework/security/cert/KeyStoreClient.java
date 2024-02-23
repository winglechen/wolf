package study.daydayup.wolf.framework.security.cert;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import study.daydayup.wolf.common.util.lang.StringUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.concurrent.ConcurrentHashMap;

/**
 * study.daydayup.wolf.framework.security.cert
 *
 * @author Wingle
 * @since 2021/8/17 下午5:21
 **/
@Slf4j
@Getter
public class KeyStoreClient {
    private static final ConcurrentHashMap<String, KeyStoreClient> db = new ConcurrentHashMap<>(2);

    public static synchronized KeyStoreClient getInstance(@NonNull String path, String password) {
        KeyStoreClient client = db.get(path);
        if (null != client) {
            return client;
        }

        client = new KeyStoreClient(path, password);
        db.put(path, client);

        return client;
    }

    public static KeyStore load(@NonNull String path, String password) {
        KeyStoreClient client = getInstance(path, password);
        return client.getStore();
    }


    private KeyStore store;
    private final String path;
    private final String password;

    private KeyStoreClient(@NonNull String path, String password) {
        this.path = path;
        this.password = password;
        init();
    }

    private void init() {
        File file = new File(path);

        try (InputStream inputStream = new FileInputStream(file.getAbsoluteFile())) {
            store = KeyStore.getInstance(KeyStore.getDefaultType());
            store.load(inputStream, password.toCharArray());
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(StringUtil.join("KeyStore file ", path, " not found"));
        } catch (KeyStoreException e) {
            throw new IllegalArgumentException(StringUtil.join("KeyStore ", path, " init failed"));
        } catch (Exception e) {
            log.info("create key store failed: ", e);
            throw new IllegalArgumentException(StringUtil.join("KeyStore ", path, " create failed"));
        }
    }

    public Certificate getCertificate(@NonNull String alias) {
        try {
            return store.getCertificate(alias);
        } catch (KeyStoreException e) {
            log.info("get certificate {} failed", alias, e);
        }

        throw new IllegalArgumentException(StringUtil.join("Certificate with alias: ", alias, " not found"));
    }

    public X509Certificate getX509Certificate(@NonNull String alias) {
        return (X509Certificate)getCertificate(alias);
    }

    public RSAPublicKey getRSAPublicKey(@NonNull String alias) {
        X509Certificate certificate = (X509Certificate) getCertificate(alias);
        return (RSAPublicKey) certificate.getPublicKey();
    }

    public RSAPrivateKey getRSAPrivateKey(@NonNull String alias) {
        try {
            Key key = store.getKey(alias, password.toCharArray());
            return (RSAPrivateKey) key;
        } catch (Exception e) {
            log.info("get getRSAPrivateKey {} failed", alias, e);
        }
        return null;
    }

//    public static void main(String[] args) {
//        String path = "/Users/wingle/code/cert/uat/onion.jks";
//        String password = "w8ADvcmJYNVr3F42";
//
//        KeyStoreClient client = KeyStoreClient.getInstance(path, password);
//
//        RSAPublicKey publicKey = client.getRSAPublicKey("1");
//        RSAPrivateKey privateKey = client.getRSAPrivateKey("1");
//
//        new KeyPair(publicKey, privateKey);
//    }
}
