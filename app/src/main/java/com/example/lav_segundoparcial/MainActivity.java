package com.example.lav_segundoparcial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity  implements SearchView.OnQueryTextListener {
    ActionBar ab ;
    SharedPreferences prefs;
    String jsonlista ;
    TextView texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = SharedPreferences();

        jsonlista = getJson();

        texto = findViewById(R.id.textoLista);
        texto.setText(jsonlista);

        ab  = super.getSupportActionBar();
        ab.setTitle(R.string.app_name);
        ab.setDisplayHomeAsUpEnabled(true);
    }



    public SharedPreferences SharedPreferences() {
        return getSharedPreferences("configs", Context.MODE_PRIVATE);
    }

    public void setJson(String JSON){
        texto.setText(JSON);
    }
    public String getJson(){
        return this.prefs.getString("lista","[]");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem menuItem = menu.findItem(R.id.buscar);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.add){
            DialogGeneric dialog = new DialogGeneric("Agregar Contacto",this);
            dialog.show(getSupportFragmentManager(),"etiqueta");

        }else if(item.getItemId()==R.id.buscar) {
            Log.d("Click", "Se hizo Click en la opt2");
        }
        else if(item.getItemId()== android.R.id.home){
            super.finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        Gson gson = new Gson();
        ContactoModel[] lista = gson.fromJson(jsonlista, ContactoModel[].class);
        ContactoModel obj  = new ContactoModel();
        Boolean encontro = false;
        for (ContactoModel contacto :lista) {
            if(contacto.getNombre().toLowerCase().equals(s.toLowerCase())){
                // nuevalista.add(persona);
                encontro = true;
                obj = contacto;
            }

        }
        if(encontro){
            DialogGeneric dialog = new DialogGeneric("El telefono es "+obj.getNumero(),"Persona Encontrada",this);
            dialog.show(getSupportFragmentManager(),"etiqueta");
        }else{
            DialogGeneric dialog = new DialogGeneric("La persona que busco no esta dentro de la lista","No Encontrada",this);
            dialog.show(getSupportFragmentManager(),"etiqueta");
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        return false;
    }

}