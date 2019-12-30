package it.trieste.simulazioneesame.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import it.trieste.simulazioneesame.controllers.attivita.AttivitaActivity;
import it.trieste.simulazioneesame.R;
import it.trieste.simulazioneesame.controllers.utenti.UtentiActivity;
import it.trieste.simulazioneesame.repository.Service;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonUtenti,buttonAttivita;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonAttivita = findViewById(R.id.buttonMAinActivityAttivita);
        buttonUtenti = findViewById(R.id.buttonMainActivityUtenti);
        buttonUtenti.setOnClickListener(this);
        buttonAttivita.setOnClickListener(this);
        Service.populate();
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId())
        {
            case R.id.buttonMAinActivityAttivita:
                i = new Intent(this, AttivitaActivity.class);
                break;
            case R.id.buttonMainActivityUtenti:
                i = new Intent(this, UtentiActivity.class);
                break;
                default:return;
        }
        startActivity(i);
    }
}
