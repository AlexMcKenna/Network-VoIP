
package audiotest;

import CMPC3M06.AudioPlayer;
import CMPC3M06.AudioRecorder;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author fvr15pqu
 */
public class AudioTest {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String args[]) throws Exception {
        //Vector used to store audio blocks (32ms/512bytes each)
        Vector<byte[]> voiceVector = new Vector<>();

        //Initialise AudioPlayer and AudioRecorder objects
        AudioRecorder recorder = new AudioRecorder();
        AudioPlayer player = new AudioPlayer();

        //Recording time in seconds
        int recordTime = 5;

        //Capture audio data and add to voiceVector
        System.out.println("Recording Audio...");

        for (int i = 0; i < Math.ceil(recordTime / 0.032); i++) {
            byte[] block = recorder.getBlock();
            voiceVector.add(block);
        }

        //Close audio input
        recorder.close();

        //Iterate through voiceVector and play out each audio block
        System.out.println("Playing Audio...");

        Iterator<byte[]> voiceItr = voiceVector.iterator();
        while (voiceItr.hasNext()) {
            player.playBlock(voiceItr.next());
        }

        //Close audio output
        player.close();
    }
}

    

