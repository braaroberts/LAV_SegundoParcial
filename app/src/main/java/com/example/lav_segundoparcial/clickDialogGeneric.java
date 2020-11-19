package com.example.lav_segundoparcial;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class clickDialogGeneric implements DialogInterface.OnClickListener {
    SharedPreferences prefs = null;
    View view = null;
    MainActivity mainActivity = null;

    public clickDialogGeneric(View view, SharedPreferences prefs, MainActivity mainActivity) {
        this.prefs = prefs;
        this.view = view;
        this.mainActivity = mainActivity;

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Gson gson = new Gson();
        SharedPreferences.Editor editor = prefs.edit();

        String jsonRestored = mainActivity.getJson();
        ContactoModel[] lista = gson.fromJson(jsonRestored, ContactoModel[].class);
        ArrayList<ContactoModel> miLista = new ArrayList<>();
        for (ContactoModel obj:lista) {
            miLista.add(obj);
        }
        EditText nombre = view.findViewById(R.id.nombre);
        EditText numero = view.findViewById(R.id.numero);

        ContactoModel contacto = new ContactoModel();



        if(!numero.getText().toString().equals("") && !nombre.getText().toString().equals("")){
            contacto.setNombre(nombre.getText().toString());
            contacto.setNumero(numero.getText().toString());
            miLista.add(contacto);
        }


        String json = gson.toJson(miLista);
        editor.putString("lista",json);
        editor.commit();
        mainActivity.setJson(json);



    }


}
