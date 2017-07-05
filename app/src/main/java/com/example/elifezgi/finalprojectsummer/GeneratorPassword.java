package com.example.elifezgi.finalprojectsummer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by ElifEzgi on 4.7.2017.
 */


    public class GeneratorPassword extends HomeActivity {


        public class MainActivity extends Activity {
            Random random = new Random();
            private static final String _CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            private static final int RANDOM_STR_LENGTH = 12;

            // onCreate methon and initialize.
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                Button btn = (Button) findViewById(R.id.button1);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView text_Random = (TextView) findViewById(R.id.textView1);
                        text_Random.setText(getRandomString());
                    }
                });

            }
            // create random string
            public String getRandomString() {
                StringBuffer randStr = new StringBuffer();
                for (int i = 0; i < RANDOM_STR_LENGTH; i++) {
                    int number = getRandomNumber();
                    char ch = _CHAR.charAt(number);
                    randStr.append(ch);
                }
                return randStr.toString();
            }
            //create random number
            private int getRandomNumber() {
                int randomInt = 0;
                randomInt = random.nextInt(_CHAR.length());
                if (randomInt - 1 == -1) {
                    return randomInt;
                } else {
                    return randomInt - 1;
                }
            }
        }
    }

