package krunal.com.example.miwok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView num = (TextView) findViewById(R.id.numbers);
        num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent numbers = new Intent(MainActivity.this,NumberActivity.class);
                startActivity(numbers);
            }
        });

        TextView fiam = (TextView) findViewById(R.id.family);
        fiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fiamly = new Intent(MainActivity.this,FiamlyActivity.class);
                startActivity(fiamly);
            }
        });

        TextView colour = (TextView) findViewById(R.id.colors);
        colour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent colours = new Intent(MainActivity.this,ColorsActivity.class);
                startActivity(colours);
            }
        });


        TextView pharse = (TextView) findViewById(R.id.phrases);
        pharse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pharese = new Intent(MainActivity.this,PhaseActivity.class);
                startActivity(pharese);
            }
        });



    }
}
