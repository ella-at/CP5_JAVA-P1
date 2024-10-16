package org.example;

import java.io.*;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Servidor ouvindo na porta 5000...");

        // Aceitar a conexão do cliente
        Socket clientSocket = serverSocket.accept();
        System.out.println("Cliente conectado.");

        BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

        // Usar chaves RSA fixas da tabela
        RSA rsa = RSA.generateFixedKeys();
        System.out.println("Chaves RSA carregadas (fixas).");

        // Enviar chave pública ao cliente (N e E)
        output.println(rsa.getN() + "," + rsa.getE());

        // Receber e descriptografar a mensagem
        String encryptedMessage = input.readLine();
        System.out.println("Mensagem criptografada recebida: " + encryptedMessage);

        String decryptedMessage = rsa.decrypt(encryptedMessage);
        System.out.println("Mensagem descriptografada: " + decryptedMessage);

        clientSocket.close();
        serverSocket.close();
    }
}


// Comece iniciando o servidor no 1º terminal com o codigo: java -cp target/classes org.example.TCPServer
// Depois então iniciar o cliente no 2º terminal :  java -cp target/classes org.example.TCPClient
