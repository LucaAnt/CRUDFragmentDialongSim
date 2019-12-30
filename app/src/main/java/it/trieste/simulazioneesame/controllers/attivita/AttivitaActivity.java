package it.trieste.simulazioneesame.controllers.attivita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import it.trieste.simulazioneesame.R;
import it.trieste.simulazioneesame.controllers.attivita.adapters.AdapterRecyclerAttivita;
import it.trieste.simulazioneesame.controllers.attivita.adapters.AdapterSpinnerAttivita;
import it.trieste.simulazioneesame.model.Attivita;
import it.trieste.simulazioneesame.model.Utente;
import it.trieste.simulazioneesame.repository.Service;

public class AttivitaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, AdapterRecyclerAttivita.RecyclerAttivitaListener, DialogAttivitaEventListener {
    Spinner spinnerAttivita;
    AdapterSpinnerAttivita adapterSpinnerAttivita;
    FloatingActionButton floatingActionButton;
    Utente targetUtente=null;
    int targetAttivita=-1;
    AdapterRecyclerAttivita adapterRecyclerAttivita;
    public static String TAG_DIALOG_ADD_UPDATE_ATTIVITA = "DIALOG_ADD_UPDATE_ATTIVITA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attivita);
        spinnerAttivita = findViewById(R.id.spinnerAttivita);
        adapterSpinnerAttivita = new AdapterSpinnerAttivita(this,Service.utenti);

        // Apply the adapter to the spinner
        spinnerAttivita.setAdapter(adapterSpinnerAttivita);
        //Spinner listener
        spinnerAttivita.setOnItemSelectedListener(this);

        //Setup Fab
        floatingActionButton = findViewById(R.id.floatingActionButtonAttivita);
        floatingActionButton.setOnClickListener(this);
        floatingActionButton.setEnabled(false);

        //SetupRecyclerView
        RecyclerView recyclerViewAttivita = findViewById(R.id.RecyclerViewAttivita);
        LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerViewAttivita.setLayoutManager(layoutManager);


        adapterRecyclerAttivita = new AdapterRecyclerAttivita();
        adapterRecyclerAttivita.setAttivitaListener(this);
        recyclerViewAttivita.setAdapter(adapterRecyclerAttivita);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        floatingActionButton.setEnabled(true);
        targetUtente = Service.utenti.get(position);
        adapterRecyclerAttivita.setData(targetUtente.getAttivitaUtente());
        adapterRecyclerAttivita.notifyDataSetChanged();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        floatingActionButton.setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        addAttivita();
    }

    @Override
    public void addAttivita() {
        popDialog(AttivitaDialogMode.ADD);
    }

    @Override
    public void editAttivita(int position) {
        popDialog(AttivitaDialogMode.UPDATE);
        targetAttivita = position;
    }

    @Override
    public void delAttivita(int position) {
        popDialog(AttivitaDialogMode.DELETE);
        targetAttivita = position;
    }

    public void popDialog(AttivitaDialogMode mode)
    {
        DialogFragmentAddUpdateDeleteAttivita dialogFragmentAddUpdateDeleteAttivita = (DialogFragmentAddUpdateDeleteAttivita) getSupportFragmentManager().findFragmentByTag(TAG_DIALOG_ADD_UPDATE_ATTIVITA);
        if (dialogFragmentAddUpdateDeleteAttivita==null)
        {
            dialogFragmentAddUpdateDeleteAttivita  = new DialogFragmentAddUpdateDeleteAttivita();
        }
        dialogFragmentAddUpdateDeleteAttivita.setDialogAttivitaEventListener(this);
        dialogFragmentAddUpdateDeleteAttivita.show(getSupportFragmentManager(),TAG_DIALOG_ADD_UPDATE_ATTIVITA,mode,targetUtente);

    }

    @Override
    public void addedAttivita(String value) {
        targetUtente.addAttivita(new Attivita(value));
        adapterRecyclerAttivita.notifyDataSetChanged();
    }

    @Override
    public void editedAttivita(String value) {
        targetUtente.getAttivitaUtente().get(targetAttivita).setNomeAttivita(value);
        adapterRecyclerAttivita.notifyDataSetChanged();
    }

    @Override
    public void deletedAttivita() {
        targetUtente.getAttivitaUtente().remove(targetAttivita);
        adapterRecyclerAttivita.notifyDataSetChanged();
    }
}
