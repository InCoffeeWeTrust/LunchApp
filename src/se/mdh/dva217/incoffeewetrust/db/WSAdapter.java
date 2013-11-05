package se.mdh.dva217.incoffeewetrust.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created with IntelliJ IDEA.
 * User: d3eniz
 * Date: 11/5/13
 * Time: 9:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class WSAdapter {

    public static String convertStreamToString(InputStream is)
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static boolean getMenu(String UserName,String Password)
    {
        try
        {
            System.out.println("guru");
            DefaultHttpClient httpClient=new DefaultHttpClient();

            //Connect to the server
            HttpGet httpGet=new HttpGet("http://10.0.2.2:51220/Service1.svc/checkLogin?name="+UserName+"&pass="+Password);

            //Get the response
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            InputStream stream=httpEntity.getContent();

            //Convert the stream to readable format
            String result= convertStreamToString(stream);

            if(result.charAt(1)=='1')
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(Exception e)
        {
            return false;
        }
    }
}
