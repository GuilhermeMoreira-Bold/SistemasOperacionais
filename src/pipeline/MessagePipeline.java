    package pipeline;

    import controllers.AcessController;
    import execptions.PassExecption;
    import message.IOComponent;
    import pass.MessagePass;

    import java.util.ArrayList;
    import java.util.List;

    public class MessagePipeline {
        private List<MessagePass<? extends IOComponent, ? extends IOComponent>> messages;
        private AcessController controller;
        public MessagePipeline(AcessController controller) {
            messages = new ArrayList<>();
            this.controller = controller;
        }

        public MessagePipeline insertStage(MessagePass<? extends IOComponent, ? extends IOComponent> message){
            messages.add(message);
            return this;
        }

        public void execute(IOComponent input) throws PassExecption {
            IOComponent currentInput = input;

            for(MessagePass<? extends IOComponent, ? extends IOComponent> message : messages){
                checkInputType(message,currentInput);
                controller.requestWrite();
                currentInput = runPass(message, currentInput);
            }
        }

        private <I extends IOComponent<I>, O extends IOComponent<O>> IOComponent runPass(MessagePass<I,O> pass, IOComponent input) throws PassExecption {
            checkInputType(pass, input);
            return pass.pass((I) input);
        }

        private void checkInputType(MessagePass<? extends IOComponent, ? extends IOComponent> message, IOComponent input) throws PassExecption {
            if(!message.getInputComponent().isInstance(input)){
                throw new PassExecption();
            }
        }
    }
