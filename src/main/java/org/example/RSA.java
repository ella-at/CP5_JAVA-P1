package org.example;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class RSA {

    private final BigInteger n, d, e;

    // Construtor para cliente (chave pública)
    public RSA(BigInteger n, BigInteger e) {
        this.n = n;
        this.e = e;
        this.d = null;
    }

    // Construtor para servidor (chave pública e privada)
    private RSA(BigInteger n, BigInteger e, BigInteger d) {
        this.n = n;
        this.e = e;
        this.d = d;
    }

    // Méodo estático para gerar chaves fixas
    public static RSA generateFixedKeys() {
        BigInteger p = new BigInteger("17");
        BigInteger q = new BigInteger("23");
        BigInteger n = p.multiply(q); // N = 391
        BigInteger e = new BigInteger("3");
        BigInteger d = new BigInteger("235");
        return new RSA(n, e, d); // Gera chave pública e privada
    }

    // Criptografa a mensagem caractere por caractere
    public synchronized String encrypt(String message) {
        List<String> encryptedChars = new ArrayList<>();
        for (char ch : message.toCharArray()) {
            BigInteger charAsBigInt = BigInteger.valueOf((int) ch);
            encryptedChars.add(charAsBigInt.modPow(e, n).toString());
        }
        return String.join(",", encryptedChars); // Junta todos os caracteres criptografados
    }

    // Descriptografa a mensagem caractere por caractere
    public synchronized String decrypt(String message) {
        if (d == null) {
            throw new IllegalStateException("Chave privada não está disponível.");
        }
        String[] encryptedChars = message.split(",");
        StringBuilder decryptedMessage = new StringBuilder();
        for (String encryptedChar : encryptedChars) {
            BigInteger encryptedBigInt = new BigInteger(encryptedChar);
            char decryptedChar = (char) encryptedBigInt.modPow(d, n).intValue();
            decryptedMessage.append(decryptedChar);
        }
        return decryptedMessage.toString(); // Retorna a mensagem completa
    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getE() {
        return e;
    }
}
