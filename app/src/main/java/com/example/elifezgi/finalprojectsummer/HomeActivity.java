package com.example.elifezgi.finalprojectsummer;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ElifEzgi on 4.7.2017.
 */


    public class HomeActivity extends Activity {
        TabHost tabhost;

        ListView lv;
        ArrayAdapter<String> adapter;
        ArrayList<HashMap<String, String>> kitap_liste;
        String kitap_adlari[];
        int kitap_idler[];

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.home_activity);


            ActionBar actionBar = getActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);

            tabhost = (TabHost) findViewById(android.R.id.tabhost);
            tabhost.setup();
            TabHost.TabSpec tabspec;
            tabspec = tabhost.newTabSpec("screen1");
            tabspec.setContent(R.id.tab1);
            tabspec.setIndicator("Info", null);
            tabhost.addTab(tabspec);
            tabspec = tabhost.newTabSpec("screen2");
            tabspec.setContent(R.id.tab2);
            tabspec.setIndicator("Generator Password", null);
            tabhost.addTab(tabspec);
            tabspec = tabhost.newTabSpec("screen3");
            tabspec.setContent(R.id.tab3);
            tabspec.setIndicator("List Information", null);
            tabhost.addTab(tabspec);


            tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
                @Override
                public void onTabChanged(String tagId) {
                    String text = "Tab id:" + tagId;
                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                }
            });
        }



        public void onResume()
        {
            super.onResume();
            Database db = new Database(getApplicationContext()); // Db baÄŸlantÄ±sÄ± oluÅŸturuyoruz. Ä°lk seferde database oluÅŸturulur.
            kitap_liste = db.informations();//kitap listesini alÄ±yoruz
            if(kitap_liste.size()==0){//kitap listesi boÅŸsa
                Toast.makeText(getApplicationContext(), "HenÃ¼z Bilgi EklenmemiÅŸ.\nYukarÄ±daki + Butonundan Ekleyiniz", Toast.LENGTH_LONG).show();
            }else{
                kitap_adlari = new String[kitap_liste.size()]; // kitap adlarÄ±nÄ± tutucamÄ±z string arrayi olusturduk.
                kitap_idler = new int[kitap_liste.size()]; // kitap id lerini tutucamÄ±z string arrayi olusturduk.
                for(int i=0;i<kitap_liste.size();i++){
                    kitap_adlari[i] = kitap_liste.get(i).get("sifre_adi");
                    //kitap_liste.get(0) bize arraylist iÃ§indeki ilk hashmap arrayini dÃ¶ner. Yani tablomuzdaki ilk satÄ±r deÄŸerlerini
                    //kitap_liste.get(0).get("kitap_adi") //bize arraylist iÃ§indeki ilk hashmap arrayin anahtarÄ± kitap_adi olan value dÃ¶ner

                    kitap_idler[i] = Integer.parseInt(kitap_liste.get(i).get("id"));
                    //YukarÄ±daki ile aynÄ± tek farkÄ± deÄŸerleri integer a Ã§evirdik.
                }
                //KitaplarÄ± Listeliyoruz ve bu listeye listener atÄ±yoruz
                lv = (ListView) findViewById(R.id.list_view);

                adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.sifre_adi, kitap_adlari);
                lv.setAdapter(adapter);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                            long arg3) {
                        //Listedeki her hangibir yere tÄ±klandÄ±gÄ±nda tÄ±klanan satÄ±rÄ±n sÄ±rasÄ±nÄ± alÄ±yoruz.
                        //Bu sÄ±ra id arraydeki sÄ±rayla aynÄ± oldugundan tÄ±klanan satÄ±rda bulunan kitapÄ±n id sini alÄ±yor ve kitap detaya gÃ¶nderiyoruz.
                        Intent intent = new Intent(getApplicationContext(), DetailInformation.class);
                        intent.putExtra("id", (int)kitap_idler[arg2]);
                        startActivity(intent);

                    }
                });
            }

        }


        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu, menu);

            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle presses on the action bar items
            switch (item.getItemId()) {
                case R.id.ekle:
                    addSifre();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }

        private void addSifre() {
            Intent i = new Intent(HomeActivity.this, AddingInformation.class);
            startActivity(i);
        }
    }

