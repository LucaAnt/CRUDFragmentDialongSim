package it.trieste.simulazioneesame.controllers.utenti;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

import it.trieste.simulazioneesame.R;
import it.trieste.simulazioneesame.model.Utente;

import static androidx.recyclerview.widget.RecyclerView.*;

public class AdapterRecyclerUtenti extends Adapter<AdapterRecyclerUtenti.UtentiViewHolder> {

    List<Utente> utenti = new LinkedList<>();
    UserEventListener userEventListener;
    @NonNull
    @Override
    public UtentiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UtentiViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_utenti_layout,parent,false),userEventListener);
    }

    @Override
    public void onBindViewHolder(@NonNull UtentiViewHolder holder, int position) {
        Utente utente = utenti.get(position);
        holder.bindViewHolder(utente.getNomeUtente(),utente.getAttivitaUtente().size());
    }

    @Override
    public int getItemCount() {
        return utenti.size();
    }

    public void setData(List<Utente> utenti)
    {
        this.utenti = utenti;
    }

    public void setUserEventListener(UserEventListener listener)
    {
        this.userEventListener = listener;
    }

    class UtentiViewHolder extends ViewHolder implements OnClickListener,OnLongClickListener
    {
        TextView nomeUtenteTextView,quantitaUtentiTextView;
        UserEventListener userEventListener;
        public UtentiViewHolder(@NonNull View itemView,UserEventListener userEventListener) {
            super(itemView);
            nomeUtenteTextView = itemView.findViewById(R.id.textViewRecyclerUtentiNome);
            quantitaUtentiTextView = itemView.findViewById(R.id.textViewRecyclerUtentiQuantitaAttivita);
            this.userEventListener = userEventListener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
        public void bindViewHolder(String nomeUtente,int quantita)
        {
            nomeUtenteTextView.setText(nomeUtente);
            quantitaUtentiTextView.setText(String.valueOf(quantita));
        }

        @Override
        public void onClick(View v) {
            userEventListener.userUpdate(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            userEventListener.userDelete(getAdapterPosition());
            return true;
        }
    }

    public  interface UserEventListener
    {
        public void userDelete(int target);
        public void userUpdate(int target);
        public void userAdd();
    }

}
