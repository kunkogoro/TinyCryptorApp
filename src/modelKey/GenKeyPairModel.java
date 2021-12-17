package modelKey;

import java.util.Iterator;
import java.util.Collection;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PublicKey;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.security.KeyPairGenerator;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Base64;
import modelSymmetric.AlgorithmsItem;
import java.util.List;

public class GenKeyPairModel
{
    private List<AlgorithmsItem> algorithmsItems;
    private static Base64.Encoder encoder;
    private static Base64.Decoder decoder;
    
    static {
        GenKeyPairModel.encoder = Base64.getEncoder();
        GenKeyPairModel.decoder = Base64.getDecoder();
    }
    
    public GenKeyPairModel() {
        this.init();
        this.loadData();
    }
    
    private void init() {
        this.algorithmsItems = new ArrayList<AlgorithmsItem>();
    }
    
    public void loadData() {
        final AlgorithmsItem des = new AlgorithmsItem();
        final List<Integer> keysizeDES = new ArrayList<Integer>();
        keysizeDES.add(1024);
        keysizeDES.add(2048);
        keysizeDES.add(4096);
        des.setAlgorithms("RSA");
        des.setKeySize((List)keysizeDES);
        this.algorithmsItems.add(des);
    }
    
    public KeyPair createAutoKey(final String algorthms, final int keySize) {
        KeyPairGenerator keyGenerator = null;
        try {
            keyGenerator = KeyPairGenerator.getInstance(algorthms);
            keyGenerator.initialize(keySize);
            return keyGenerator.generateKeyPair();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean saveKeyFile(final String filePath, final byte[] data) throws IOException {
        final File file = new File(filePath);
        try {
            final BufferedOutputStream bufferedInputStream = new BufferedOutputStream(new FileOutputStream(file));
            bufferedInputStream.write(GenKeyPairModel.encoder.encodeToString(data).getBytes(StandardCharsets.UTF_8));
            bufferedInputStream.close();
            return true;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public PublicKey readPublicKey(final String path) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] bytedata = Files.readAllBytes(Paths.get(path, new String[0]));
        final String publicStringKey = new String(bytedata, StandardCharsets.UTF_8);
        bytedata = GenKeyPairModel.decoder.decode(publicStringKey);
        final X509EncodedKeySpec ks = new X509EncodedKeySpec(bytedata);
        final KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(ks);
    }
    
    public PrivateKey readPrivateKey(final String path) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] bytedata = Files.readAllBytes(Paths.get(path, new String[0]));
        final String publicStringKey = new String(bytedata, StandardCharsets.UTF_8);
        bytedata = GenKeyPairModel.decoder.decode(publicStringKey);
        final PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(bytedata);
        final KeyFactory kf = KeyFactory.getInstance("RSA");
        final PrivateKey pub = kf.generatePrivate(ks);
        return pub;
    }
    
    public PrivateKey convertPrivateKeyFromByte(final byte[] bkey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        final PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(bkey);
        final KeyFactory kf = KeyFactory.getInstance("RSA");
        final PrivateKey pub = kf.generatePrivate(ks);
        return pub;
    }
    
    public List<Integer> getDataKeySizeByID(final String mode) {
        final List<Integer> list = new ArrayList<Integer>();
        for (final AlgorithmsItem algorithmsItem : this.getAlgorithmsItems()) {
            if (algorithmsItem.getAlgorithms().equals(mode)) {
                list.addAll(algorithmsItem.getKeySize());
                break;
            }
        }
        return list;
    }
    
    public String printBytetoString(final byte[] key) {
        return Base64.getEncoder().encodeToString(key);
    }
    
    public byte[] printStringtoByte(final String key) {
        return Base64.getDecoder().decode(key);
    }
    
    public List<AlgorithmsItem> getAlgorithmsItems() {
        return this.algorithmsItems;
    }
    
    public void setAlgorithmsItems(final List<AlgorithmsItem> algorithmsItems) {
        this.algorithmsItems = algorithmsItems;
    }
    
    public static void main(final String[] args) throws InvalidKeySpecException, Exception {
        final GenKeyPairModel genKeyPairModel = new GenKeyPairModel();
        final KeyPair keyPair = genKeyPairModel.createAutoKey("RSA", 1024);
        System.out.println(genKeyPairModel.saveKeyFile("D:\\OneDrive\\Desktop\\privatekey1.key", keyPair.getPrivate().getEncoded()));
        System.out.println(genKeyPairModel.saveKeyFile("D:\\OneDrive\\Desktop\\publickey1.key", keyPair.getPublic().getEncoded()));
        System.out.println(genKeyPairModel.printBytetoString(genKeyPairModel.readPrivateKey("D:\\OneDrive\\Desktop\\privatekey1.key").getEncoded()));
        System.out.println(genKeyPairModel.printBytetoString(keyPair.getPrivate().getEncoded()));
    }
}