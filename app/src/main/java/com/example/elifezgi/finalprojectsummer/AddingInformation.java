package com.example.elifezgi.finalprojectsummer;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by ElifEzgi on 4.7.2017.
 */

public class AddingInformation extends Activity {
    Button b1;
    EditText e1,e2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_information);


        b1 = (Button)findViewById(R.id.button1);
        e1 = (EditText)findViewById(R.id.editText1);
        e2 = (EditText)findViewById(R.id.editText2);


        b1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String sifre_adi,sifre;
                sifre_adi = e1.getText().toString();
                sifre = e2.getText().toString();
                if(sifre_adi.matches("") || sifre.matches("")  ){
                    Toast.makeText(getApplicationContext(), "Tüm Bilgileri Eksiksiz Doldurunuz", Toast.LENGTH_LONG).show();
                }else{
                    Database db = new Database(getApplicationContext());
                    db.addInfo(sifre_adi, sifre);
                    db.close();
                    Toast.makeText(getApplicationContext(), "Bilgileriniz Başarıyla Eklendi.", Toast.LENGTH_LONG).show();
                    e1.setText("");
                    e2.setText("");

                }


            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }


}
