package com.example.lav_segundoparcial;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import org.json.JSONException;
import org.json.JSONObject;

public class DialogGeneric extends DialogFragment  {
    private String mensaje;
    private String titulo;
    private MainActivity main;
    SharedPreferences prefs;
    public DialogGeneric(String mensaje, String titulo,MainActivity main   ){
        this.mensaje = mensaje;
        this.titulo = titulo;
        this.main = main;
    }
    public DialogGeneric(String titulo,MainActivity main ){
         this.mensaje = null;
        this.titulo = titulo;
        this.main = main;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        prefs = main.SharedPreferences();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(this.titulo);
        LayoutInflater li =LayoutInflater.from(getActivity());
        View viewAlert = li.inflate(R.layout.item,null);
        clickDialogGeneric clickDialog = new clickDialogGeneric(viewAlert,prefs,main);
        if (this.mensaje != null) {
            builder.setMessage(this.mensaje);
            builder.setPositiveButton("OK",null);
        }else{
            builder.setView(viewAlert);
            builder.setPositiveButton("GUARDAR",clickDialog);
            builder.setNeutralButton("CANCELAR",null);
        }







        return builder.create();
    }
}
