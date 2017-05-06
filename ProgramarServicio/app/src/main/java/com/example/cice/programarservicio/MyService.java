package com.example.cice.programarservicio;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

import java.util.Calendar;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //"BINDED SERVICE"
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        //"STARTED SERVICE"
        Log.d("MENSAJE","Se ha iniciado la ejecución del servicio");

        //TODO CÓDIGO NORMAL.

        //Matamos el servicio con la instancia referida al startId.
        stopSelf(startId);
        return START_NOT_STICKY;
    }

    private void programarAlarma(){

        AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        //CALCULO EL TIEMPO DE LA ALARMA.
        Calendar calendar=Calendar.getInstance();
        long tiempo_actual_ms=calendar.getTimeInMillis();
        Log.d("MENSAJE","Tiempo actual " +tiempo_actual_ms);
        long tiempo_alarma=tiempo_actual_ms+10000;
        Log.d("MENSAJE","Tiempo alarma " +tiempo_alarma);
        //INTENT PARA IR AL RECEIVER.
        Intent intent=new Intent(this,MyReceiver.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,103,intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP,tiempo_alarma,pendingIntent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MENSAJE","Se ha detenido el servicio");
        programarAlarma();
    }




}
