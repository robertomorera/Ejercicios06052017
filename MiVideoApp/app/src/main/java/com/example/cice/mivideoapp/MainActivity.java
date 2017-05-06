package com.example.cice.mivideoapp;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Uri video_uri;

    private Uri crearFicheroVideo(){

        Uri uri_destino=null;
        File file=null;
        //Podría generar un número/nombre aleatorio para el fichero de video.
        String nombre_fichero="video7.mp4";
        String ruta_completa_video= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath()+"/"+nombre_fichero;
        Log.d("MENSAJE","La ruta del video= "+ruta_completa_video);
        file=new File(ruta_completa_video);
        try {
            boolean creado=file.createNewFile();
            if(creado){
                Log.d("MENSAJE","FICHERO CREADO CON ÉXITO");
                uri_destino=Uri.fromFile(file);
                Log.d("MENSAJE","Uri del video "+uri_destino.toString());
            }else{
                Log.d("MENSAJE","FICHERO NO CREADO");
            }
        } catch (IOException e) {
            Log.e("MENSAJE","ERROR",e);
            e.printStackTrace();
        }

        return uri_destino;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},100);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Tocó el subir.
        Log.d("MENSAJE","TOCÓ EL SUBIR");
        new SubirVideo().execute(video_uri);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("SUBIR");
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Intent intent=new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        video_uri=crearFicheroVideo();
        if(video_uri!=null) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, video_uri);
            startActivityForResult(intent, 200);
        }else{
            Log.e("MENSAJE","Error algo no fue bien,INTENT VIDEO NO LANZADO");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        Log.d("MENSAJE","Volvio de grabar el video");
        switch(resultCode){
            case RESULT_OK:

                //Recupero la URI del Intent.
                video_uri=data.getData();
                VideoView videoView=(VideoView)findViewById(R.id.video);
                MediaController mediaController=new MediaController(this);
                mediaController.setAnchorView(videoView);
                videoView.setMediaController(mediaController);
                videoView.setVideoURI(video_uri);
                videoView.start();

                break;
            case RESULT_CANCELED:
                Log.d("MENSAJE","EL VIDEO NO SE HIZO-cancel-");
                break;
            default:
                Log.d("MENSAJE","EL VIDEO NO SE HIZO");
        }
    }
}
