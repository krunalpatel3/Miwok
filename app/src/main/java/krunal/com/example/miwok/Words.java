package krunal.com.example.miwok;

/**
 * Created by acer on 9/10/2017.
 */

public class Words
{

    private String mMiwok;
    private  String meng;
    private int mimage = NO_IMAGE_PROVIDED;
    private int maudio;


    private static final int  NO_IMAGE_PROVIDED = -1;

    public Words(String Miwok, String eng,int audio){
        mMiwok = Miwok;
        meng = eng;
        maudio = audio;
    }

    public Words(String Miwok, String eng, int image,int audio){
        mMiwok = Miwok;
        meng = eng;
        mimage = image;
        maudio = audio;

    }

    public String getMiwok(){
        return mMiwok;
    }

    public String geteng(){
        return meng;
    }

    public int getimage(){
        return mimage;
    }

    public boolean hasImage() {
        return mimage != NO_IMAGE_PROVIDED;
    }

    public int getaudio(){
        return maudio;
    }

}
