package it.trieste.simulazioneesame.controllers.attivita.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;
import it.trieste.simulazioneesame.R;
import it.trieste.simulazioneesame.model.Utente;

public class AdapterSpinnerAttivita extends ArrayAdapter<Utente> {
    public AdapterSpinnerAttivita(Context context, List<Utente> utenti)
    {
        super(context, 0,utenti);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    private  View initView(int position,View convertView,ViewGroup parent)
    {
        TextView nomeUtenteTextView,quantitaUtentiTextView;

        if (convertView==null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.recycler_utenti_layout,parent,false);


        nomeUtenteTextView = convertView.findViewById(R.id.textViewRecyclerUtentiNome);
        quantitaUtentiTextView = convertView.findViewById(R.id.textViewRecyclerUtentiQuantitaAttivita);

        Utente utente = getItem(position);

        if (utente!=null)
        {
            nomeUtenteTextView.setText(utente.getNomeUtente());
            quantitaUtentiTextView.setText(String.valueOf(utente.getAttivitaUtente().size()));
        }


        return convertView;
    }
}
