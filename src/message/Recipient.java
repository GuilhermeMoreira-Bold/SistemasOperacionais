package message;

import user.User;

public class Recipient extends IOComponent<Recipient>{
    private Message message;


    public Recipient(Message message) {
        System.out.println(message.getSender().getUserName() + " say: " +  message.getMessage()+ " to: " + message.getRecipient().getUserName());
        this.message = message;
    }

    public void sendMessage(){

    }
}