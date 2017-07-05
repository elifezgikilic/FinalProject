package com.example.elifezgi.finalprojectsummer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ElifEzgi on 4.7.2017.
 */


public class Database extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "selamet";//database adÄ±

    private static final String TABLE_NAME = "kitap_listesi";
    private static String SIFRE_ADI = "sifre_adi";
    private static String SIFRE_ID = "id";
    private static String SIFRE = "sifre";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {  // Databesi oluÅŸturuyoruz.Bu methodu biz Ã§aÄŸÄ±rmÄ±yoruz. Databese de obje oluÅŸturduÄŸumuzda otamatik Ã§aÄŸÄ±rÄ±lÄ±yor.
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + SIFRE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + SIFRE_ADI + " TEXT,"
                + SIFRE + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE);
    }




    public void deleteInfo(int id){ //id si belli olan row u silmek iÃ§in

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, SIFRE_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }

    public void addInfo(String sifre_adi, String sifre) {
        //kitapEkle methodu ise adÄ± Ã¼stÃ¼nde Databese veri eklemek iÃ§in
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SIFRE_ADI, sifre_adi);
        values.put(SIFRE, sifre);


        db.insert(TABLE_NAME, null, values);
        db.close(); //Database BaÄŸlantÄ±sÄ±nÄ± kapattÄ±k*/
    }


    public HashMap<String, String> detailSifre(int id){
        //Databeseden id si belli olan row u Ã§ekmek iÃ§in.
        //Bu methodda sadece tek row deÄŸerleri alÄ±nÄ±r.

        //HashMap bir Ã§ift boyutlu arraydir.anahtar-deÄŸer ikililerini bir arada tutmak iÃ§in tasarlanmÄ±ÅŸtÄ±r.
        //mesala map.put("x","300"); mesala burda anahtar x deÄŸeri 300.

        HashMap<String,String> sifreStringHashMap = new HashMap<String,String>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME+ " WHERE id="+id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            sifreStringHashMap.put(SIFRE_ADI, cursor.getString(1));
            sifreStringHashMap.put(SIFRE, cursor.getString(2));

        }
        cursor.close();
        db.close();
        // return kitap
        return sifreStringHashMap;
    }

    public  ArrayList<HashMap<String, String>> informations(){

        //Bu methodda ise tablodaki tÃ¼m deÄŸerleri alÄ±yoruz
        //ArrayList adÄ± Ã¼stÃ¼nde Array lerin listelendiÄŸi bir Array.Burda hashmapleri listeleyeceÄŸiz
        //Herbir satÄ±rÄ± deÄŸer ve value ile hashmap a atÄ±yoruz. Her bir satÄ±r 1 tane hashmap arrayÄ± demek.
        //olusturdugumuz tÃ¼m hashmapleri ArrayList e atÄ±p geri dÃ¶nÃ¼yoruz(return).

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<HashMap<String, String>> listInformations = new ArrayList<HashMap<String, String>>();

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                for(int i=0; i<cursor.getColumnCount();i++)
                {
                    map.put(cursor.getColumnName(i), cursor.getString(i));
                }

                listInformations.add(map);
            } while (cursor.moveToNext());
        }
        db.close();
        // return kitap liste
        return listInformations;
    }

    public void editInfo(String sifre_adi, String sifre,int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        //Bu methodda ise var olan veriyi gÃ¼ncelliyoruz(update)
        ContentValues values = new ContentValues();
        values.put(SIFRE_ADI, sifre_adi);
        values.put(SIFRE, sifre);

        // updating row
        db.update(TABLE_NAME, values, SIFRE_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    public int getRowCount() {
        // Bu method bu uygulamada kullanÄ±lmÄ±yor ama her zaman lazÄ±m olabilir.Tablodaki row sayÄ±sÄ±nÄ± geri dÃ¶ner.
        //Login uygulamasÄ±nda kullanacaÄŸÄ±z
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();
        // return row count
        return rowCount;
    }


    public void resetTables(){
        //Bunuda uygulamada kullanmÄ±yoruz. TÃ¼m verileri siler. tabloyu resetler.
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }

}
