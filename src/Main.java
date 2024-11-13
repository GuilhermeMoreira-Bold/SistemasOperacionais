import controllers.AcessController;
import controllers.BarrierAcessController;
import controllers.MutexAcessController;
import controllers.SemaphoreAcessController;
import execptions.PassExecption;
import message.IOComponent;
import message.Message;
import message.Messenger;
import pipeline.MessagePipeline;
import user.User;





public class Main {
    public static void semaphoreExample(){
        AcessController controller = new SemaphoreAcessController();
        MessagePipeline pipeline = new MessagePipeline(controller);
        Messenger messenger = new Messenger();
        User user1 = new User("Guilherme");
        User user2 = new User("Athos");

        pipeline.insertStage(messenger);
        user1.start();
        user2.start();
        Thread senderGuilherme = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    String messageContent = "Mensagem " + (i + 1) + " de " + user1.getUserName() + " para " + user2.getUserName();
                    IOComponent message = new Message(messageContent, user1, user2);
//                    System.out.println(user1.getUserName() + " enviando: " + messageContent);
                    pipeline.execute(message);
                }
            } catch (PassExecption e) {
                e.printStackTrace();
            }
        });

        Thread senderAthos = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    String messageContent = "Mensagem " + (i + 1) + " de " + user2.getUserName() + " para " + user1.getUserName();
                    IOComponent message = new Message(messageContent, user2, user1);
                    pipeline.execute(message);
                }
            } catch (PassExecption e) {
                e.printStackTrace();
            }
        });

        senderGuilherme.start();
        senderAthos.start();

        try {
            senderGuilherme.join();
            senderAthos.join();
            user1.join();
            user2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void mutexExample(){
        AcessController controller = new MutexAcessController();
        MessagePipeline pipeline = new MessagePipeline(controller);
        Messenger messenger = new Messenger();
        User user1 = new User("Guilherme");
        User user2 = new User("Athos");

        pipeline.insertStage(messenger);
        user1.start();
        user2.start();
        Thread senderGuilherme = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    String messageContent = "Mensagem " + (i + 1) + " de " + user1.getUserName() + " para " + user2.getUserName();
                    IOComponent message = new Message(messageContent, user1, user2);
//                    System.out.println(user1.getUserName() + " enviando: " + messageContent);
                    pipeline.execute(message);
                }
            } catch (PassExecption e) {
                e.printStackTrace();
            }
        });

        Thread senderAthos = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    String messageContent = "Mensagem " + (i + 1) + " de " + user2.getUserName() + " para " + user1.getUserName();
                    IOComponent message = new Message(messageContent, user2, user1);
                    pipeline.execute(message);
                }
            } catch (PassExecption e) {
                e.printStackTrace();
            }
        });

        senderGuilherme.start();
        senderAthos.start();

        try {
            senderGuilherme.join();
            senderAthos.join();
            user1.join();
            user2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void barrierExample(){
        BarrierAcessController controller = new BarrierAcessController();
        MessagePipeline pipeline = new MessagePipeline(controller);
        Messenger messenger = new Messenger();
        User user1 = new User("Guilherme");
        User user2 = new User("Athos");

        pipeline.insertStage(messenger);
        user1.start();
        user2.start();
        Thread senderGuilherme = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    String messageContent = "Mensagem " + (i + 1) + " de " + user1.getUserName() + " para " + user2.getUserName();
                    IOComponent message = new Message(messageContent, user1, user2);
                    controller.waitBarrier();
                    pipeline.execute(message);
                }
            } catch (PassExecption e) {
                e.printStackTrace();
            }
        });

        Thread senderAthos = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    String messageContent = "Mensagem " + (i + 1) + " de " + user2.getUserName() + " para " + user1.getUserName();
                    IOComponent message = new Message(messageContent, user2, user1);
                    controller.waitBarrier();
                    pipeline.execute(message);
                }
            } catch (PassExecption e) {
                e.printStackTrace();
            }
        });

        senderGuilherme.start();
        senderAthos.start();

        try {
            senderGuilherme.join();
            senderAthos.join();
            user1.join();
            user2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws PassExecption {
       barrierExample();
    }
}