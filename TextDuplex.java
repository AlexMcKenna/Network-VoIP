package audiotest;


import static java.lang.ProcessBuilder.Redirect.to;


public class TextDuplex {

    public static void main(String[] args) {

        TextReceiverThread receiver = new TextReceiverThread();
        TextSenderThread sender = new TextSenderThread();

        receiver.start();
        sender.start();

    }

    public class MyThread implements Runnable {

        public void start() {
//Make a new thread from this class
            Thread thread = new Thread(this);
//Start the thread
            thread.start();
        }

        @Override
        public void run() {
            boolean running = true;
            while (running) {
//DO SOMTHING until running = false (i.e. set
                running = false; //to stop the thread

            }
        }
    }

}
