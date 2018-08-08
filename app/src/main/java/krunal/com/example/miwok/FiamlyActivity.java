package krunal.com.example.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FiamlyActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };
    private MediaPlayer.OnCompletionListener oncomplet= new MediaPlayer.OnCompletionListener(){
        public void onCompletion(MediaPlayer mp){
            releaseMediaPlayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiamly);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Words> words = new ArrayList<Words>();
        words.add(new Words("father", "әpә", R.drawable.family_father,R.raw.family_father));
        words.add(new Words("mother", "әṭa", R.drawable.family_mother,R.raw.family_mother));
        words.add(new Words("son", "angsi", R.drawable.family_son,R.raw.family_son));
        words.add(new Words("daughter", "tune", R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new Words("older brother", "taachi", R.drawable.family_older_brother,R.raw.family_older_brother));
        words.add(new Words("younger brother", "chalitti", R.drawable.family_younger_brother,R.raw.family_younger_brother));
        words.add(new Words("older sister", "teṭe", R.drawable.family_older_sister,R.raw.family_older_sister));
        words.add(new Words("younger sister", "kolliti", R.drawable.family_younger_sister,R.raw.family_older_sister));
        words.add(new Words("grandmother ", "ama", R.drawable.family_grandmother,R.raw.family_grandmother));
        words.add(new Words("grandfather", "paapa", R.drawable.family_grandfather,R.raw.family_grandfather));

        WordAdapter list = new WordAdapter(this, words,R.color.category_family);
        ListView wordlist= (ListView)findViewById(R.id.word);

        wordlist.setAdapter(list);

        wordlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Get the {@link Word} object at the given position the user clicked on
                releaseMediaPlayer();
                Words word = words.get(position);


                mediaPlayer = MediaPlayer.create(FiamlyActivity.this, word.getaudio());

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // We have audio focus now.
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(oncomplet);

                }
            }
        });
    }
    protected void onStop()
    {
        super.onStop();
        releaseMediaPlayer();
    }
    private void releaseMediaPlayer() {

        if (mediaPlayer != null) {

            mediaPlayer.release();
            mediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
