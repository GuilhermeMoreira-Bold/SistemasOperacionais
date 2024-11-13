package message;

import user.User;

public class Message  extends IOComponent<Message> {
    private String message;
    private User sender;
    private User recipient;

    public Message(String message, User sender, User recipient) {
        this.message = message;
        this.sender = sender;
        this.recipient = recipient;
    }

    public User getSender() {
        return sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }
};
