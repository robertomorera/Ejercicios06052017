package com.example.cice.programarservicio;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented")
        Log.d("MENSAJE","Ha saltado la alarma");
        //ALARMA INFINITA.
        Intent intent_serv=new Intent(context,MyService.class);
        context.startService(intent_serv);

    }
}
