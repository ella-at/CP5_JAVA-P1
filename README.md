CP5 - JAVA - Parte 1


Integrantes:

Marcela Morera - RM 552051 

Nathalia Braga - RM 552221 

Daniele Vargas - RM 99400 

Felipe




Nosso projeto implementa uma aplicação de comunicação segura entre um servidor e um cliente utilizando o algoritmo de criptografia RSA. O foco está na transmissão segura de mensagens entre as duas partes por meio de sockets TCP. A criptografia RSA é um dos métodos mais utilizados para garantir a segurança das comunicações, garantindo que apenas o destinatário correto seja capaz de ler o conteúdo da mensagem. Neste projeto, o servidor é responsável por gerar chaves RSA e compartilhá-las com o cliente, que então as utiliza para criptografar mensagens enviadas de forma segura.

Vamos explicar a estrutura, funcionamento e fluxo de execução do projeto, detalhando as classes principais, o uso de criptografia, e como o cliente e o servidor interagem para garantir a segurança da comunicação.



Estrutura do Projeto

O projeto é estruturado em diversas classes Java que desempenham funções específicas:

Main.java: O ponto de entrada da aplicação, responsável por inicializar tanto o servidor quanto o cliente.
RSA.java: Contém a implementação do algoritmo de criptografia RSA, com funcionalidades para geração de chaves, criptografia e descriptografia.
TCPClient.java: Cliente que se conecta ao servidor, recebe a chave pública para criptografar a mensagem e envia a mensagem criptografada de volta ao servidor.
TCPServer.java: Servidor que gera as chaves RSA, envia a chave pública ao cliente e recebe e descriptografa a mensagem criptografada.

![image](https://github.com/user-attachments/assets/de2808e8-3ae3-4dac-b64e-3fc37c42fc50)



Detalhamento das Classes

Main.java - Esta classe contém a função principal (main) do projeto, que coordena a execução inicial tanto do cliente quanto do servidor.

![image](https://github.com/user-attachments/assets/edb43959-ee0a-4839-a0a6-cc4160369d74)



RSA.java - Esta classe implementa o algoritmo RSA. Ela permite a geração de chaves, além de possibilitar a criptografia e descriptografia de mensagens.
Geração de Chaves: As chaves públicas e privadas são geradas de forma fixa para simplificação.
Criptografia: A mensagem é criptografada caractere por caractere usando a chave pública.
Descriptografia: Somente o detentor da chave privada pode descriptografar a mensagem.

![image](https://github.com/user-attachments/assets/7e15c55c-23b4-4ec6-bd69-4fad0e59c2b9)



TCPClient.java - Esta classe representa o cliente que se conecta ao servidor. Ao se conectar, ele recebe a chave pública RSA e utiliza essa chave para criptografar uma mensagem que é enviada ao servidor.

![image](https://github.com/user-attachments/assets/46ad9bba-060b-4ac8-8044-d03761f43e97)




TCPServer.java - Esta classe implementa o servidor que aguarda conexões de clientes. Ao receber uma conexão, ele gera suas chaves RSA e envia a chave pública para o cliente. Em seguida, recebe a mensagem criptografada do cliente, descriptografa e exibe a mensagem original.

![image](https://github.com/user-attachments/assets/dd306a5f-d2c8-43ee-87f3-258643b8703c)



Fluxo de Execução
Iniciando o Servidor: O servidor é iniciado e fica aguardando conexões na porta 5000.

Ele gera as chaves RSA e compartilha a chave pública (composta por n e e) com o cliente.
Iniciando o Cliente: O cliente se conecta ao servidor, recebe a chave pública e criptografa uma mensagem de texto.

A mensagem "Olá, Servidor!" é criptografada utilizando a chave pública e enviada ao servidor.
Recebendo e Descriptografando no Servidor: O servidor recebe a mensagem criptografada e a descriptografa utilizando sua chave privada, exibindo a mensagem original.

Este processo garante que apenas o servidor, que possui a chave privada, pode ler a mensagem enviada pelo cliente.



Exemplos de Uso
1º Executar o Servidor - Execute o servidor no terminal 1 com o comando: java -cp target/classes org.example.TCPServer
2º Executar o Cliente - Em outro terminal, enquato o servidor estiver rodando, execute o comando: java -cp target/classes org.example.TCPClient

![image](https://github.com/user-attachments/assets/8aa98594-3575-4cf4-910c-a4b4e0518579)

![image](https://github.com/user-attachments/assets/21595f6f-5677-4694-a5bd-ef06b0eda57f)

Como podemos ver na imagem, ao executar o cliente ele se conectou ao servidor, recebeu a chave pública, criptografou a mensagem "Olá, Servidor!" e a enviou ao servidor. O servidor descriptografou e exibiu a mensagem recebida.


Podemos concluir que nosso projeto apresenta a implementação de uma comunicação segura utilizando o algoritmo de criptografia RSA em uma aplicação cliente-servidor. O servidor e o cliente se comunicam através de sockets TCP, garantindo que as mensagens enviadas sejam criptografadas de forma segura, utilizando chaves RSA fixas. Embora a implementação use chaves fixas para fins de simplicidade, futuras melhorias poderiam incluir a geração dinâmica de chaves e o uso de bibliotecas criptográficas mais robustas para aumentar a segurança.

Oferecendo uma base sólida para entender como a criptografia RSA pode ser aplicada em comunicações seguras, e um ótimo ponto de partida para explorar técnicas mais avançadas de criptografia em sistemas distribuídos.







