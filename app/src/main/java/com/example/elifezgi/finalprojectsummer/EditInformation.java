package com.example.elifezgi.finalprojectsummer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by ElifEzgi on 4.7.2017.
 */


public class EditInformation extends Activity {
    Button b1;
    EditText e1,e2;
    int id;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_info);



        b1 = (Button)findViewById(R.id.button1);
        e1 = (EditText)findViewById(R.id.editText1);
        e2 = (EditText)findViewById(R.id.editText2);


        Intent intent=getIntent();
        id = intent.getIntExtra("id", 0);

        Database db = new Database(getApplicationContext());
        HashMap<String, String> map = db.detailSifre(id);

        e1.setText(map.get("sifre_adi"));
        e2.setText(map.get("sifre").toString());

        b1.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                String sifre_adi,sifre;
                sifre_adi = e1.getText().toString();
                sifre = e2.getText().toString();

                if(sifre_adi.matches("") || sifre.matches("")  ){
                    Toast.makeText(getApplicationContext(), "TÃ¼m Bilgileri Eksiksiz Doldurunuz", Toast.LENGTH_LONG).show();
                }else{
                    Database db = new Database(getApplicationContext());
                    db.editInfo(sifre_adi, sifre,id);//gÃ¶nderdiÄŸimiz id li kitabÄ±n deÄŸperlerini gÃ¼ncelledik.
                    db.close();
                    Toast.makeText(getApplicationContext(), "Bilgileriniz BaÅŸarÄ±yla DÃ¼zenlendi.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();
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
