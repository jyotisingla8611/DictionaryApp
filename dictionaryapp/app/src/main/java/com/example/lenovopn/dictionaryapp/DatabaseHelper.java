package com.example.lenovopn.dictionaryapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class DatabaseHelper {
    Context context;
    SQLiteDatabase db;

    DatabaseHelper(Context context){
        this.context = context;
        createDatabase();
        createTable();
        insert();
    }

    private void createDatabase() {
        try {
            db = context.openOrCreateDatabase("dictionary", Context.MODE_PRIVATE,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void createTable(){
        try{
            db.execSQL("CREATE TABLE IF NOT EXISTS DICTIONARY(SNO INTEGER,WORD VARCHAR , WORD_TYPE VARCHAR , WORD_MEANING VARCHAR) ");
        }catch (Exception e){
            throw e;
        }
    }
    void insert(){
        try {
            db.execSQL("INSERT INTO DICTIONARY VALUES(1,'Able','ADJECTIVE','having the power, skill, money, etc., that is needed to do something')");
            db.execSQL("INSERT INTO DICTIONARY VALUES(2,'About','ADVERB',' almost or nearly')");
            db.execSQL("INSERT INTO DICTIONARY VALUES(3,'Backward','ADVERB',' to or toward what is behind')");
            db.execSQL("INSERT INTO DICTIONARY VALUES(4,'Balance','NOUN',' the state of having your weight spread equally so that you do not fall')");
            db.execSQL("INSERT INTO DICTIONARY VALUES(5,'Calculate','VERB','to find (a number, answer, etc.) by using mathematical processes')");
            db.execSQL("INSERT INTO DICTIONARY VALUES(6,'Capacity','NOUN','the ability to hold or contain people or things ')");
            db.execSQL("INSERT INTO DICTIONARY VALUES(7,'Damage','VERB','physical harm that is done to something or to someone body')");
            db.execSQL("INSERT INTO DICTIONARY VALUES(8,'Dark','NOUN','having very little or no light')");
            db.execSQL("INSERT INTO DICTIONARY VALUES(9,'Gather','VERB','to bring (things or people) together into a group')");
            db.execSQL("INSERT INTO DICTIONARY VALUES(10,'Giant','ADJECTIVE',' very large')");
            db.execSQL("INSERT INTO DICTIONARY VALUES(11,'Jacket','NOUN','an outer covering')");
            db.execSQL("INSERT INTO DICTIONARY VALUES(12,'Jealous','ADJECTIVE','feeling or showing an unhappy or angry desire to have what someone else has')");
            db.execSQL("INSERT INTO DICTIONARY VALUES(13,'Mail','NOUN','the system used for sending letters and packages from one person to another')");
            db.execSQL("INSERT INTO DICTIONARY VALUES(14,'Manner','NOUN',' somewhat formal : the way that something is done or happens')");
            db.execSQL("INSERT INTO DICTIONARY VALUES(15,'Rapid','ADJECTIVE',' happening in a short amount of time : happening quickly')");
            db.execSQL("INSERT INTO DICTIONARY VALUES(16,'Rather','ADVERB','to some degree or extent')");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    String  search(String searchStr){
        Cursor c=db.rawQuery("SELECT * FROM DICTIONARY where WORD='"+searchStr+"' ",null);
        String ans="";
        if(c!=null){
            c.moveToFirst();
        }
        if(c.moveToNext()){
            int index=c.getColumnIndex("WORD");
            String word=c.getString(index);
            if(word.equals(searchStr)){
                ans=ans+word;
                int index1=c.getColumnIndex("WORD_TYPE");
                String word_type=c.getString(index1);
                int index2=c.getColumnIndex("WORD_MEANING");
                String meaning=c.getString(index2);
                ans=ans+"\n"+word_type+"\n"+meaning;
            }
        }
        Log.e("search",ans);
        return ans;
    }
   int getIndex(String str)
    {
        Cursor c=db.rawQuery("SELECT SNO FROM DICTIONARY where WORD='"+str+"' ",null);
        int index;
        //index=c.getColumnIndex("SNO");
        Log.d("Total rows fetched : ",""+c.getCount());
        if(c.getCount()>0){
            c.moveToFirst();
            index = c.getInt(0);
            return index;
        }
        return -1;

    }
    String getStrings(int loc)
    {
        String s;
        Cursor c = db.rawQuery("select * from DICTIONARY where SNO="+loc, null);
        try {
            c.moveToFirst();
            int index = c.getColumnIndex("WORD");
            s = c.getString(index);
        } finally {
            c.close();
        }
        return s;
    }
}