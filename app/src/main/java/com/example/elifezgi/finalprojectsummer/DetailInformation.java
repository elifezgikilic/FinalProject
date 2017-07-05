package com.example.elifezgi.finalprojectsummer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by ElifEzgi on 4.7.2017.
 */


    public class DetailInformation extends Activity {
        Button b1,b2;
        TextView t1,t2;
        int id;
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.detail_info);


            b1 = (Button)findViewById(R.id.button1);
            b2 = (Button)findViewById(R.id.button2);

            t1 = (TextView)findViewById(R.id.sifre_adi);
            t2 = (TextView)findViewById(R.id.sifre);


            Intent intent=getIntent();
            id = intent.getIntExtra("id", 0);//id deÄŸerini integer olarak aldÄ±k. Burdaki 0 eÄŸer deÄŸer alÄ±nmazsa default olrak verilecek deÄŸer

            Database db = new Database(getApplicationContext());
            HashMap<String, String> map = db.detailSifre(id);//Bu id li row un deÄŸerini hashmap e aldÄ±k

            t1.setText(map.get("sifre_adi"));
            t2.setText(map.get("sifre").toString());



            b1.setOnClickListener(new View.OnClickListener() {//Kitap dÃ¼zenle butonuna tÄ±klandÄ±gÄ±nda tekrardan kitabÄ±n id sini gÃ¶nderdik

                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), EditInformation.class);
                    intent.putExtra("id", (int)id);
                    startActivity(intent);
                }
            });

            b2.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(DetailInformation.this);
                    alertDialog.setTitle("UyarÄ±");
                    alertDialog.setMessage("Bilgileriniz Silinsin mi?");
                    alertDialog.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            Database db = new Database(getApplicationContext());
                            db.deleteInfo(id);
                            Toast.makeText(getApplicationContext(), "Bilgiler BaÅŸarÄ±yla Silindi", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);//bu id li kitabÄ± sildik ve Anasayfaya dÃ¶ndÃ¼k
                            finish();

                        }
                    });
                    alertDialog.setNegativeButton("HayÄ±r", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {

                        }
                    });
                    alertDialog.show();

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
