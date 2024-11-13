package message;

import pass.MessagePass;
import pipeline.MessagePipeline;

public class Messenger extends MessagePass<Message, Recipient> {

    @Override
    public Class<Message> getInputComponent() {
        return Message.class;
    }

    @Override
    public Class<Recipient> getOutputComponent() {
        return Recipient.class;
    }

    @Override
    public String getDebugName() {
        return "Messenger";
    }

    @Override
    public Recipient pass(Message input) {
        return new Recipient(input);
    }
}
