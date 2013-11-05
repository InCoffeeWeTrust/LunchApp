package se.mdh.dva217.incoffeewetrust.db;

import android.*;
import android.R;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: d3eniz
 * Date: 11/5/13
 * Time: 11:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class Databasehelper extends SQLiteOpenHelper {

    static final String dbName="internalDB";
    static final String schoolTable="Schools";
    static final String colID="Name";

    static final String menuTable="WeekMenu";
    static final String colSchoolID="Name";
    static final String colWeekNumber="Week";
    static final String colMonday="Monday";
    static final String colTuesday="Tuesday";
    static final String colWednesday="Wednesday";
    static final String colThursday="Thursday";
    static final String colFriday="Friday";

    public Databasehelper(Context context) {
        super(context,dbName,null,33);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+schoolTable+" ("+colID+" TEXT PRIMARY KEY )");
            //todo make a primary key relationship between tables schools and weekmenu
       db.execSQL("CREATE TABLE "+menuTable+
                 " ("+colSchoolID+" TEXT PRIMARY KEY, "+
                    colWeekNumber+" INTEGER NOT NULL, "+
                    colMonday+" TEXT, "+
                    colTuesday+" TEXT, "+
                    colWednesday+" TEXT, "+
                    colThursday+" TEXT, "+
                    colFriday+" TEXT)"
       );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+schoolTable);
        db.execSQL("DROP TABLE IF EXISTS "+menuTable);
        onCreate(db);
    }


    public String[] getSchools()
    {
        SQLiteDatabase db = this.getReadableDatabase();


        String selectStatement = "SELECT * FROM "+schoolTable;
        Cursor cursor = db.rawQuery(selectStatement,null);

        String[] result = new String[cursor.getCount()];

        for (int i = 0; i <result.length ; i++)
        {
            cursor.moveToPosition(i);
            result[i] = cursor.getString(0);
        }

        return result;
    }

    public boolean addSchool(String school)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(colID, school);


        //insert returns rowID or -1.
        long rowID = db.insert(schoolTable,null,values);


        return rowID != -1;
    }

    public boolean addSchools(String... schools)
    {
        boolean result = false;

        for(String s:schools)
        {
            result |= addSchool(s);
        }

        return result;
    }

}
