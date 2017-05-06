package com.example.cice.consumirvideo;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by cice on 6/5/17.
 */

public class DescargaVideo extends AsyncTask<Void,Void,String> {

    private File crearFicheroVideo(){
        File fdev=null;
        String ruta_f_video= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/"+"vdescarga.mp4";
        fdev=new File(ruta_f_video);
        try {
            fdev.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fdev;
    }

    @Override
    protected String doInBackground(Void... params) {
        String ruta_video=null;
        URL url=null;
        HttpURLConnection httpURLConnection=null;

        try{

            url=new URL("http://192.168.3.18:8080/DniApp/SubirVideo?fichero=roberVideo");
            httpURLConnection=(HttpURLConnection)url.openConnection();
            if(httpURLConnection.getResponseCode()==200){
                InputStream is=httpURLConnection.getInputStream();
                byte[] bloque_lectura= new byte[1024*5];

                File fdes=crearFicheroVideo();
                FileOutputStream fos=new FileOutputStream(fdes);
                ruta_video=fdes.getPath();
                int bleidos=-1;
                while((bleidos=is.read(bloque_lectura))!=-1){
                    fos.write(bloque_lectura,0,bleidos);
                }

                fos.close();
                httpURLConnection.disconnect();

            }

        }catch(Exception e){
            Log.e("MENSAJE","ERROR",e);
        }

        return ruta_video;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d("MENSAJE","FICHERO GUARDADO EN:"+s);
    }
}
