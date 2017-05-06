package com.example.cice.mivideoapp;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by cice on 6/5/17.
 */

public class SubirVideo extends AsyncTask<Uri,Void,String> {

    @Override
    protected String doInBackground(Uri... params) {
        URL server_url=null;
        HttpURLConnection httpCon=null;
        try{
            server_url=new URL("http://192.168.3.18:8080/DniApp/SubirVideo?fichero=roberVideo");
            httpCon=(HttpURLConnection)server_url.openConnection();
            httpCon.setRequestMethod("POST");
            File f=new File(params[0].getPath());
            FileInputStream fis=new FileInputStream(f);
            byte[] videoBloque= new byte[1024*4];
            OutputStream os=httpCon.getOutputStream();
            int escrito=-1;

            while((escrito=fis.read(videoBloque))!=-1){
                os.write(videoBloque,0,escrito);
            }
            //Es importante devolver el código de respuesta sino no llega la petición al servidor.
            int cod_resp=httpCon.getResponseCode();

            fis.close();
            os.close();

        }catch(Exception e){
            Log.e("MENSAJE","ERROR",e);

        }finally{
            if(httpCon!=null){
                httpCon.disconnect();
            }
        }

        return "hola";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
