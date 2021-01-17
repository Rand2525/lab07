import support.Mixer;

public class MojMixer extends Mixer {

    public MojMixer(String name) {
        super(name);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void mix(int[] lightings) {
        for(int i =0; i< lightings.length; i++) {
            lightings[i]=i;
        }

    }




}
