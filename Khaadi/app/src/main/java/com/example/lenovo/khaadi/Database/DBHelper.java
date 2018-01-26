package com.example.lenovo.khaadi.Database;
import com.example.lenovo.khaadi.Globals;
import com.example.lenovo.khaadi.Models.DressInfo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


/**
 * Created by abc on 1/18/18.
 *
 * @package pk.edu.pucit.mobilecomputing.database.Database
 * @project Database
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "DressStock";
   // public static final String TABLE_NAME2 = "Users";

    //COLUMN NAMES
    public static final String ID = "id";
    public static final String DCODE = "dress_code";
    public static final String DTYPE = "type";
    public static final String QUANTITY = "quantity";
    public static final String CATEGORY = "category";

    //COLUMN TYPES
    public static final String TYPE_TEXT = " TEXT ";
    public static final String TYPE_INT = " INTEGER ";
    public static final String SEPERATOR = ", ";
    private static final String DATABASE_NAME = "test.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_query = "Create table " + TABLE_NAME + " ("
                //need to make primary key NOT NULL
                // and AUTOINCREMENT instead of AUTO_INCREMENT
                + ID + TYPE_INT + " PRIMARY KEY AUTOINCREMENT NOT NULL " + SEPERATOR
                + DCODE + TYPE_TEXT + SEPERATOR
                + DTYPE + TYPE_TEXT + SEPERATOR
                + CATEGORY + TYPE_TEXT + SEPERATOR
                + QUANTITY + TYPE_INT + ");";
        db.execSQL(create_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            String drop_query = "drop database if exists " + DATABASE_NAME;
            db.execSQL(drop_query);
            onCreate(db);
        }
    }

    /**
     * This function inserts data into database
     *
     * @param code    takes a string user name
     * @param dtype   takes a string type email
     * @param category takes a string type address
     * @return true when data inserted false when failed
     */
    public boolean insert(String code, String dtype,String category, int quantity) {
        SQLiteDatabase db = getWritableDatabase();
        boolean b;
        Cursor c=db.rawQuery("SELECT * FROM "+ TABLE_NAME+" WHERE "+DCODE+"='"+code+"'", null);
        if(c.moveToFirst())
        {
           b=false;
        }
        else
        {
            ContentValues cv = new ContentValues();
            cv.put(DCODE, code);
            cv.put(DTYPE, dtype);
            cv.put(CATEGORY, category);
            cv.put(QUANTITY,quantity);
            long i = db.insert(TABLE_NAME, null, cv);
            Log.d("Database_helper", String.valueOf(i));
            //be sure to close database after work is done
            db.close();
            b=true;
        }
        return b;
    }
    public long update(DressInfo info)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(DCODE, info.getDcoe());
        cv.put(DTYPE, info.getDtype());
        cv.put(CATEGORY, info.getCategory());
        cv.put(QUANTITY,info.getQuantity());

        long i=db.update(TABLE_NAME, cv, ID + "=" + info.getId(), null);
        db.close();
        return i;
    }

    /*
        tableColumns"
        null for all columns as in SELECT * FROM ...
                new String[] { "column1", "column2", ... } for specific columns as in SELECT column1, column2 FROM ... - you can also put complex expressions here:
                new String[] { "(SELECT max(column1) FROM table1) AS max" } would give you a column named max holding the max value of column1
        whereClause:
        the part you put after WHERE without that keyword, e.g. "column1 > 5"
        should include ? for things that are dynamic, e.g. "column1=?" -> see whereArgs

        whereArgs:
        specify the content that fills each ? in whereClause in the order they appear

        Remaining params:
        just like whereClause the statement after the keyword or null if you don't use it.
    */
    public Cursor read(long id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TABLE_NAME, new String[]{ID,DCODE}, null, null, null, null, null);//"SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = " + id);
        return c;
    }

    /**
     * Deletes Record Based On Email Address
     *
     * @param id
     * @return
     */
    public boolean delete_row(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, ID + "=" + id, null);
        db.close();
        return false;
    }
    public void Delete_Item(DressInfo info)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE "+ID+"='"+info.getId()+"'");
        db.close();
    }

    public ArrayList getAll() {
        ArrayList<DressInfo> ui = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Log.d(Globals.LOG_TAG, cursor.getColumnNames()[0] + ":" + cursor.getString(cursor.getColumnIndex(ID)));
            Log.d(Globals.LOG_TAG, cursor.getColumnNames()[1] + ":" + cursor.getString(cursor.getColumnIndex(DCODE)));
            Log.d(Globals.LOG_TAG, cursor.getColumnNames()[2] + ":" + cursor.getString(cursor.getColumnIndex(DTYPE)));
            Log.d(Globals.LOG_TAG, cursor.getColumnNames()[3] + ":" + cursor.getString(cursor.getColumnIndex(CATEGORY)));
            Log.d(Globals.LOG_TAG, cursor.getColumnNames()[4] + ":" + cursor.getString(cursor.getColumnIndex(QUANTITY)));

            ui.add(new DressInfo(cursor.getInt(cursor.getColumnIndex(ID)),
                    cursor.getString(cursor.getColumnIndex(DCODE)),
                    cursor.getString(cursor.getColumnIndex(DTYPE)),
                    cursor.getString(cursor.getColumnIndex(CATEGORY)),
                    cursor.getInt(cursor.getColumnIndex(QUANTITY))
            ));
        }
        return ui;
    }
    public ArrayList getKids() {
        ArrayList<DressInfo> ui = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            if(cursor.getString(cursor.getColumnIndex(CATEGORY)).equals("Kids")) {
                Log.d(Globals.LOG_TAG, cursor.getColumnNames()[0] + ":" + cursor.getString(cursor.getColumnIndex(ID)));
                Log.d(Globals.LOG_TAG, cursor.getColumnNames()[1] + ":" + cursor.getString(cursor.getColumnIndex(DCODE)));
                Log.d(Globals.LOG_TAG, cursor.getColumnNames()[2] + ":" + cursor.getString(cursor.getColumnIndex(DTYPE)));
                Log.d(Globals.LOG_TAG, cursor.getColumnNames()[3] + ":" + cursor.getString(cursor.getColumnIndex(CATEGORY)));
                Log.d(Globals.LOG_TAG, cursor.getColumnNames()[4] + ":" + cursor.getString(cursor.getColumnIndex(QUANTITY)));

                ui.add(new DressInfo(cursor.getInt(cursor.getColumnIndex(ID)),
                        cursor.getString(cursor.getColumnIndex(DCODE)),
                        cursor.getString(cursor.getColumnIndex(DTYPE)),
                        cursor.getString(cursor.getColumnIndex(CATEGORY)),
                        cursor.getInt(cursor.getColumnIndex(QUANTITY))
                ));
            }
        }
        return ui;
    }
    public ArrayList getWoman() {
        ArrayList<DressInfo> ui = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            if(cursor.getString(cursor.getColumnIndex(CATEGORY)).equals("Woman")) {
                Log.d(Globals.LOG_TAG, cursor.getColumnNames()[0] + ":" + cursor.getString(cursor.getColumnIndex(ID)));
                Log.d(Globals.LOG_TAG, cursor.getColumnNames()[1] + ":" + cursor.getString(cursor.getColumnIndex(DCODE)));
                Log.d(Globals.LOG_TAG, cursor.getColumnNames()[2] + ":" + cursor.getString(cursor.getColumnIndex(DTYPE)));
                Log.d(Globals.LOG_TAG, cursor.getColumnNames()[3] + ":" + cursor.getString(cursor.getColumnIndex(CATEGORY)));
                Log.d(Globals.LOG_TAG, cursor.getColumnNames()[4] + ":" + cursor.getString(cursor.getColumnIndex(QUANTITY)));

                ui.add(new DressInfo(cursor.getInt(cursor.getColumnIndex(ID)),
                        cursor.getString(cursor.getColumnIndex(DCODE)),
                        cursor.getString(cursor.getColumnIndex(DTYPE)),
                        cursor.getString(cursor.getColumnIndex(CATEGORY)),
                        cursor.getInt(cursor.getColumnIndex(QUANTITY))
                ));
            }
        }
        return ui;
    }
    public ArrayList getMan() {
        ArrayList<DressInfo> ui = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            if(cursor.getString(cursor.getColumnIndex(CATEGORY)).equals("Man")) {
                Log.d(Globals.LOG_TAG, cursor.getColumnNames()[0] + ":" + cursor.getString(cursor.getColumnIndex(ID)));
                Log.d(Globals.LOG_TAG, cursor.getColumnNames()[1] + ":" + cursor.getString(cursor.getColumnIndex(DCODE)));
                Log.d(Globals.LOG_TAG, cursor.getColumnNames()[2] + ":" + cursor.getString(cursor.getColumnIndex(DTYPE)));
                Log.d(Globals.LOG_TAG, cursor.getColumnNames()[3] + ":" + cursor.getString(cursor.getColumnIndex(CATEGORY)));
                Log.d(Globals.LOG_TAG, cursor.getColumnNames()[4] + ":" + cursor.getString(cursor.getColumnIndex(QUANTITY)));

                ui.add(new DressInfo(cursor.getInt(cursor.getColumnIndex(ID)),
                        cursor.getString(cursor.getColumnIndex(DCODE)),
                        cursor.getString(cursor.getColumnIndex(DTYPE)),
                        cursor.getString(cursor.getColumnIndex(CATEGORY)),
                        cursor.getInt(cursor.getColumnIndex(QUANTITY))
                ));
            }
        }
        return ui;
    }
    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "message" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){
            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }
    }

}
