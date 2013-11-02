package se.mdh.dva217.incoffeewetrust.db;


import android.os.AsyncTask;
import android.util.Log;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import net.sourceforge.jtds.jdbc.*;




/**
 * Created with IntelliJ IDEA.
 * User: d3eniz
 * Date: 10/22/13
 * Time: 10:32 AM
 * To change this template use File | Settings | File Templates.
 */


public class DBAdapter{


    private final String Driver ="net.sourceforge.jtds.jdbc.Driver";



    private final String SQLAdress = "www3.idt.mdh.se";
    private final String SQLUserName = "jkr07001";
    private final String SQLUserPassword = "Databas1";
    private final String DBNAME = "/Lunch_AppDB";


    private final String connectionString = "jdbc:jtds:sqlserver://"+SQLAdress+";databaseName="+DBNAME+";integratedSecurity=true;";


    //private final String connectionString = "jdbc:jtds:sqlserver://"+SQLAdress+DBNAME;
    //private final String connectionString = "jdbc:sqlserver://"+SQLAdress+";DatabaseName="+DBNAME;
    //private final String connectionString = "jdbc:jtds:sqlserver://"+SQLAdress+";DatabaseName="+DBNAME;

    //vill vi ha dessa gemensamma eller vill vi passa runt connections och result sets?

    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;


    private void open() {
        try
        {


            Class.forName(Driver).newInstance();
            conn = DriverManager.getConnection(connectionString, SQLUserName,SQLUserPassword);


        }
        catch (Exception e)
        {


            Log.w("SQL OPEN ERROR", "" + e.getMessage());


        }
    }

    private void close() {
        try {
            if (conn != null || conn.isClosed() != true) {
                conn.close();
            }
        } catch (Exception e) {
            Log.w("SQL CLOSE ERROR", e.getMessage());

        }
    }

    public void getState()
    {
    }

    public void testquery() {
        try {

            open();

            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM States");

            close();
        } catch (Exception e) {
            Log.w("test query ERROR", e.getMessage());
        }
    }
}
