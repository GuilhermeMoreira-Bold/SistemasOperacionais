package pass;

import message.IOComponent;

public abstract class MessagePass<I extends IOComponent<I>, O extends IOComponent<O>> {
    public abstract Class<I> getInputComponent();
    public abstract Class<O> getOutputComponent();
    public abstract String getDebugName();
    public abstract O pass(I input);
}
