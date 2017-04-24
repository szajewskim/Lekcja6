package com.example.marek.lekcja5;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.util.Log;

import java.io.IOException;

public class MainActivity extends Activity {

    private ListView lv;
    private String[] pho;

    static final private int ALERT_DIALOG_BUTTONS = 1;
    static final private int ALERT_DIALOG_LIST = 2;
    static final private int CUSTOM_ALERT_DIALOG = 3;
    private Button btnNewAlertDialogButton;
    private Button btnNewListDialogButton;
    private Button btnNewCustomAlertDialog;
    private MediaPlayer mediaplayer = null;






    private void initButtonsClick() {
        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View v){
                switch (v.getId()) {
                    case R.id.button4:
                        showDialog(ALERT_DIALOG_BUTTONS);
                        break;
                    case R.id.button5:
                        showDialog(ALERT_DIALOG_LIST);
                        break;
                    case R.id.button6:
                        showDialog(CUSTOM_ALERT_DIALOG);
                        break;
                    default:
                        break;
                }
            }
        };
        btnNewAlertDialogButton.setOnClickListener(listener);
        btnNewListDialogButton.setOnClickListener(listener);
        btnNewCustomAlertDialog.setOnClickListener(listener);
    }
    protected Dialog onCreateDialog(int id)
    {
        switch(id) {
            case ALERT_DIALOG_BUTTONS:
                return createAlertDialogWithButtons();
            case ALERT_DIALOG_LIST:
                return createDialogWithList();
            case CUSTOM_ALERT_DIALOG:
                return createCustomAlertDialog();
            default:
                return null;
        }
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    private Dialog createAlertDialogWithButtons() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Wyjście");
        dialogBuilder.setMessage("Czy na pewno");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("Tak", new Dialog.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton){
                showToast("Wychodzę");
                MainActivity.this.finish();
            }
        });
        dialogBuilder.setNegativeButton("Nie", new Dialog.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                showToast("Anulowanie");
            }
        });

        return dialogBuilder.create();
    }

    public void playSound() {
        if(mediaplayer != null) {
            mediaplayer.release();
        }

        mediaplayer = MediaPlayer.create(this, R.raw.humble);
        mediaplayer.start();
    }

    public void playSound2() {
        if(mediaplayer != null) {
            mediaplayer.release();
        }

        mediaplayer = MediaPlayer.create(this, R.raw.bounce);
        mediaplayer.start();
    }
    public void stopSound(){
        mediaplayer.stop();
    }




    private Dialog createDialogWithList() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        final String[] options = {"Utwór pierwszy", "Utwór drugi", "Stop"};
        dialogBuilder.setTitle("Lista utworów");
        dialogBuilder.setItems(options, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int position) {
                showToast("Wybrałeś " + options[position]);
                if(options[position] == "Utwór pierwszy")
                    playSound();
                else if(options[position] == "Utwór drugi")
                    playSound2();
                else if(options[position] == "Stop")
                    stopSound();

            }
        });
        return dialogBuilder.create();
    }

    private void initResources() {
        Resources res = getResources();
        pho = res.getStringArray(R.array.phones);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?>parent, View v, int pos, long id){
                if(pos == 0) {
                    Context context;
                    context = getApplicationContext();
                    Intent intent = new Intent(context, iPhone6s.class);
                    startActivity(intent);
                }
                else if(pos == 1){
                    Context context;
                    context = getApplicationContext();
                    Intent intent = new Intent(context, SamsungGalaxyS7.class);
                    startActivity(intent);
                }
                else if(pos == 2){
                    Context context;
                    context = getApplicationContext();
                    Intent intent = new Intent(context, Nokia6310i.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void initLanguagesListView(){
        lv.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, pho));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.phones);
        initResources();
        initLanguagesListView();
        btnNewAlertDialogButton = (Button) findViewById(R.id.button4);
        btnNewListDialogButton = (Button) findViewById(R.id.button5);
        btnNewCustomAlertDialog = (Button) findViewById(R.id.button6);
        initButtonsClick();



    }
    private Dialog createCustomAlertDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        View layout = getCustomDialogLayout();
        dialogBuilder.setView(layout);
        dialogBuilder.setTitle("To jest alert dialog!");
        return dialogBuilder.create();
    }
    public View getCustomDialogLayout() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(R.layout.dialog, (ViewGroup)findViewById(R.id.wlasny));
    }
}