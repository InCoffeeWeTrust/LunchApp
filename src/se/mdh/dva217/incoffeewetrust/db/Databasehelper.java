package se.mdh.dva217.incoffeewetrust.db;

import android.*;
import android.R;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: d3eniz
 * Date: 11/5/13
 * Time: 11:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseHelper extends SQLiteOpenHelper implements IStorage {

    static final String dbName="internalDB";

    static final String schoolTable="Schools";
    static final String colID="Name";
    static final String colFavorite="Favorite";

    static final String menuTable="WeekMenu";
    static final String colSchoolID="Name";
    static final String colWeekNumber="Week";
    static final String colMonday="Monday";
    static final String colTuesday="Tuesday";
    static final String colWednesday="Wednesday";
    static final String colThursday="Thursday";
    static final String colFriday="Friday";


    private final Set<Listener> listeners = new LinkedHashSet<Listener>();

    public DatabaseHelper(Context context) {
        super(context,dbName,null,33);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+schoolTable+" ("+colID+" TEXT PRIMARY KEY, "+colFavorite+" INTEGER NOT NULL)");

            //todo make a primary key relationship between tables schools and weekmenu
        db.execSQL("CREATE TABLE "+menuTable+
                 " ("+colSchoolID+" TEXT PRIMARY KEY, "+
                    colWeekNumber+" INTEGER NOT NULL, "+
                    colMonday+" TEXT, "                +
                    colTuesday+" TEXT, "               +
                    colWednesday+" TEXT, "             +
                    colThursday+" TEXT, "              +
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

        int entries = cursor.getCount();
        int columns = cursor.getColumnCount();

        String[] result = new String[entries];

        for (int i = 0; i <result.length ; i++)
        {
            cursor.moveToPosition(i);
            result[i] = cursor.getString(0);
        }

        return result;
    }

    @Override
    public String[] getFavorites() {
        SQLiteDatabase db = this.getReadableDatabase();


        String selectStatement = "SELECT * FROM "+schoolTable+" WHERE "+colFavorite+" = '1'";
        Cursor cursor = db.rawQuery(selectStatement,null);

        String[] result = new String[cursor.getCount()];

        for (int i = 0; i <result.length ; i++)
        {
            cursor.moveToPosition(i);
            result[i] = cursor.getString(0);
        }

        return result;
    }

      @Override
    public String[] getMenu(String school, int weekNumber) {
        SQLiteDatabase db = this.getReadableDatabase();


        String selectStatement = "SELECT * FROM "+ menuTable + " WHERE "+colSchoolID+" = '"+school+"'";
        Cursor cursor = db.rawQuery(selectStatement,null);

        // todo return null for missing menu?
        if (cursor.getCount() == 0)
            return null;

        String[] result = new String[5];

        cursor.moveToFirst();
        for (int i = 0; i < result.length; i++)
        {
            result[i] = cursor.getString(i+2);
        }

        cursor.close();
        return result;  //To change body of implemented methods use File | Settings | File Templates.


    }

    private boolean addSchool(String school)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(colID, school);
        values.put(colFavorite, 0);


        //insert returns rowID or -1.
        long rowID = db.insert(schoolTable,null,values);


        return rowID != -1;
    }


    @Override
    public boolean addSchools(String... schools)
    {
        boolean changed = false;

        for(String s:schools)
        {
            changed |= addSchool(s);
        }

        if (changed)
            notifyListeners(Type.School);

        return changed;
    }

    @Override
    public boolean addFavorite(String school) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(colID, school);
        values.put(colFavorite,1);

        int updateStatus = db.update(schoolTable, values,colID+" =?",new String[]{school});

        if (updateStatus != -1) {
            notifyListeners(Type.Favorite);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeFavorite(String school) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(colID, school);
        values.put(colFavorite,0);

        int updateStatus = db.update(schoolTable, values, colID + " = ?", new String[]{school});

        if (updateStatus != -1) {
            notifyListeners(Type.Favorite);
            return true;
        }
        return false;
    }

    @Override
    public boolean addMenu(String school, int weekNumber, String[] dishes) {

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+menuTable+" WHERE "+colSchoolID+" = '"+school+"'",null);

        boolean changed = false;
        switch (cursor.getCount())
        {
            case 0:
                cursor.close();
                changed |= insertMenu(school,weekNumber,dishes);
                break;

            default:
                cursor.close();
                changed |=  updateMenu(school,weekNumber,dishes);
                break;
        }

        if (changed)
            notifyListeners(Type.Menu);

        return changed;
    }

    private boolean insertMenu(String school, int weekNumber, String[] dishes)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(colSchoolID, school);
        values.put(colWeekNumber, weekNumber);
        values.put(colMonday, dishes[0]);
        values.put(colTuesday, dishes[1]);
        values.put(colWednesday, dishes[2]);
        values.put(colThursday, dishes[3]);
        values.put(colFriday, dishes[4]);

        long updateStatus = db.insert(menuTable, null, values);


        return updateStatus != -1;
    }

    private boolean updateMenu(String school, int weekNumber, String[] dishes)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(colSchoolID, school);
        values.put(colWeekNumber, weekNumber);
        values.put(colMonday, dishes[0]);
        values.put(colTuesday, dishes[1]);
        values.put(colWednesday, dishes[2]);
        values.put(colThursday, dishes[3]);
        values.put(colFriday, dishes[4]);

        int updateStatus = db.update(menuTable, values, colSchoolID + " = ?", new String[]{school});

        return updateStatus != -1;
    }

    public void dropAll(SQLiteDatabase db)
    {
        db.execSQL("DROP TABLE IF EXISTS "+schoolTable);
        db.execSQL("DROP TABLE IF EXISTS "+menuTable);
        onCreate(db);

    }

    public int  checkForTables(SQLiteDatabase db)
    {
        String selectStatement = "SELECT count(*) > 0 FROM sqlite_master where tbl_name = '"+menuTable+"' and type='table'";
        Cursor cursor = db.rawQuery(selectStatement,null);

        return cursor.getCount();
    }


    public void deleteDB(Context context)
    {
        context.deleteDatabase(dbName);

    }

    public void populateDB(Context context)
    {

       String[] schoolStringArray = new String[30];

       addSchools(schoolStringArray);
    }
    @Override
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    private void notifyListeners(Type type) {
        for (Listener l : listeners)
            l.storageChanged(type);
    }


}
