package audiotest;

import CMPC3M06.AudioPlayer;
import java.net.*;
import java.util.Iterator;
import java.util.Vector;

public class TextReceiverThread implements Runnable {

    static DatagramSocket receiving_socket;

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    /**
     *
     */
    @Override
    public void run() {

        //***************************************************
        //Port to open socket on
        int PORT = 55557;
        //***************************************************

        //***************************************************
        //Open a socket to receive from on port PORT
        //DatagramSocket receiving_socket;
        try {
            receiving_socket = new DatagramSocket(PORT);

        } catch (SocketException e) {
            System.out.println("ERROR: TextReceiver: Could not open UDP socket to receive from.");
            e.printStackTrace();
            System.exit(0);
        }
//        try {
//            AudioRecorder recorder = new AudioRecorder();
//        } catch (LineUnavailableException ex) {
//            Logger.getLogger(TextReceiverThread.class.getName()).log(Level.SEVERE, null, ex);
//        }

        //Main loop.
        boolean running = true;

        while (running) {
            //Vector used to store audio blocks (32ms/512bytes each)

            try {
                //Receive a DatagramPacket (note that the string cant be more than 80 chars)
                Vector<byte[]> voiceVector = new Vector<>();
                AudioPlayer player = new AudioPlayer();
                byte[] buffer = new byte[512];
                DatagramPacket packet = new DatagramPacket(buffer, 0, 512);

                receiving_socket.receive(packet);

                Iterator<byte[]> voiceItr = voiceVector.iterator();
                while (voiceItr.hasNext()) {
                    player.playBlock(voiceItr.next());
                }

            } catch (Exception e) {
                System.out.println("Please wait...");
            }
        }
        //Close the socket
        receiving_socket.close();
        
    }
}
