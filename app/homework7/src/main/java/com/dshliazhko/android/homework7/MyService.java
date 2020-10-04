package com.dshliazhko.android.homework7;

import android.app.Service;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MyService extends Service {
    @Nullable

    final String LOG_TAG = "DIma";
    private File folderPath;


    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "onCreate");

    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "СЕрвис запущен");
        someTask(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "onBind");
        return null;
    }

    void someTask(Intent intent) {

        DateFormat df = new SimpleDateFormat("yyyy/EEE/d, HH:mm ");
        String date = df.format(Calendar.getInstance().getTime());
        String fileName = "BroadcastReceiver";

        String newString = intent.getStringExtra("BroadcastReceiver");
        // folderPath = intent.getExtras("FolderPath");
        // sPref = Context.getPreferences(MODE_PRIVATE);
        //switchState = sPref.getBoolean("switchState", true);

        if (intent.getStringExtra("folderPath").equals("internal")) {

            try {
                // открываем поток для записи
                folderPath = getApplicationContext().getFilesDir();

                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                        openFileOutput(fileName, MODE_APPEND)));

                bw.write(date + " " + newString + "\n");
                // закрываем поток
                bw.close();
                Log.d("Dima", "Файл записан");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {

            if (!Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                Log.d(LOG_TAG, "SD-карта не доступна: " + Environment.getExternalStorageState());
                return;
            }

            folderPath = Environment.getExternalStorageDirectory().getAbsoluteFile();
            Log.d(LOG_TAG, "путь"+folderPath);

            folderPath.mkdirs();
            File sdFile = new File(folderPath, fileName);

            try {
                // открываем поток для записи
                BufferedWriter bw = new BufferedWriter(new FileWriter(sdFile));
                // пишем данные
                bw.write(date + " " + newString + "\n");
                // закрываем поток
                bw.close();
                Log.d(LOG_TAG, "Файл записан на SD: ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
