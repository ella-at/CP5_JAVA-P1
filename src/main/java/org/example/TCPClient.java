package org.example;

import java.io.*;
import java.math.BigInteger;
import java.net.Socket;

public class TCPClient {

    public static void main(String[] args) throws Exception {
        // Estabelece conexão com o servidor
        Socket socket = new Socket("localhost", 5000);
        System.out.println("Conectado ao servidor.");

        // Inicializa buffers para entrada e saída de dados
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        // Receber chave pública do servidor (N e E)
        String publicKey = input.readLine();
        String[] keyParts = publicKey.split(",");
        BigInteger n = new BigInteger(keyParts[0]);
        BigInteger e = new BigInteger(keyParts[1]);

        // Inicializa o RSA com a chave pública recebida
        RSA rsa = new RSA(n, e);
        System.out.println("Chave pública recebida.");

        // Criptografar e enviar mensagem
        String message = "Olá, servidor!";
        String encryptedMessage = rsa.encrypt(message);
        output.println(encryptedMessage);
        System.out.println("Mensagem criptografada enviada: " + encryptedMessage);

        // Fecha a conexão
        socket.close();
    }
}

// Primeiro deve iniciar o servidor no 1º terminal: (java -cp target/classes org.example.TCPServer)
// Depois então iniciar o cliente no 2º terminal :  java -cp target/classes org.example.TCPClient