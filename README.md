
# Projeto de Pipeline de Mensagens com Controle de Acesso

## Descrição

Este projeto é um sistema de envio de mensagens que simula a comunicação entre dois usuários utilizando threads para enviar mensagens simultâneas. O projeto demonstra o uso de conceitos de controle de acesso com semáforos, pipelines de mensagens e componentes para modularidade. O sistema possui um controlador de acesso (`AcessController`) para garantir que apenas uma operação de leitura ou escrita ocorra por vez.

## Estrutura do Projeto

- **controllers**: Contém classes de controle de acesso, incluindo implementações com semáforos.
- **execptions**: Contém exceções personalizadas usadas no pipeline.
- **message**: Contém classes relacionadas a mensagens e seus componentes.
- **pipeline**: Define a estrutura do pipeline de mensagens, que processa mensagens em diferentes estágios.
- **user**: Contém a definição de usuários que enviam e recebem mensagens.

## Funcionamento

1. **Pipeline de Mensagens**: O sistema utiliza um pipeline para processar mensagens em diferentes estágios.
2. **Controle de Acesso**: O acesso à escrita e leitura no pipeline é controlado por semáforos, para garantir que operações concorrentes sejam gerenciadas corretamente.
3. **Threads para Comunicação Simultânea**: O envio de mensagens é realizado por threads distintas, simulando comunicação simultânea entre usuários.

## Requisitos

- Java 8 ou superior.

## Execução do Projeto

1. Clone este repositório:
   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd <NOME_DO_PROJETO>
   ```
2. Compile o projeto:
   ```bash
   javac Main.java
   ```
3. Execute o projeto:
   ```bash
   java Main
   ```

## Classes Principais

### `controllers.AcessController`

Interface que define os métodos para controle de acesso.

### `controllers.SemaphoreAcessController`

Implementação de `AcessController` utilizando semáforos para controlar o acesso à escrita e leitura.

### `pipeline.MessagePipeline`

Classe responsável por processar mensagens através de um pipeline. Possui suporte a múltiplos estágios e controle de acesso.

### `message.Message`

Representa uma mensagem com remetente, destinatário e conteúdo.

### `message.Messenger`

Responsável por processar e entregar mensagens ao destinatário.

### `user.User`

Representa um usuário que envia e recebe mensagens.

## Exemplo de Uso

O exemplo abaixo mostra como criar e executar uma simulação de envio de mensagens simultâneas entre dois usuários:

```java
public class Main {
    public static void main(String[] args) throws PassExecption {
        AcessController controller = new SemaphoreAcessController();
        MessagePipeline pipeline = new MessagePipeline(controller);
        Messenger messenger = new Messenger();
        User user1 = new User("Guilherme");
        User user2 = new User("Athos");

        // Inserindo estágio de mensagens no pipeline
        pipeline.insertStage(messenger);

        // Criando threads para enviar mensagens simultâneas
        Thread senderGuilherme = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    IOComponent message = new Message("Mensagem " + (i + 1), user1, user2);
                    pipeline.execute(message);
                }
            } catch (PassExecption e) {
                e.printStackTrace();
            }
        });

        Thread senderAthos = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    IOComponent message = new Message("Mensagem " + (i + 1), user2, user1);
                    pipeline.execute(message);
                }
            } catch (PassExecption e) {
                e.printStackTrace();
            }
        });

        // Iniciando as threads
        senderGuilherme.start();
        senderAthos.start();
    }
}
```
