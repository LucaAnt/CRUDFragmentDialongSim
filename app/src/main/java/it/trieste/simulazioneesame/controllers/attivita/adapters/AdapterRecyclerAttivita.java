package it.trieste.simulazioneesame.controllers.attivita.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.ArrayList;
import java.util.List;

import it.trieste.simulazioneesame.R;
import it.trieste.simulazioneesame.model.Attivita;

public class AdapterRecyclerAttivita extends Adapter<AdapterRecyclerAttivita.AttivitaViewHolder> {

    private List<Attivita> attivita = new ArrayList<>();
    private RecyclerAttivitaListener listener;

    @NonNull
    @Override
    public AttivitaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AttivitaViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_attivita_layout,parent,false),listener);
    }

    @Override
    public void onBindViewHolder(@NonNull AttivitaViewHolder holder, int position) {
        holder.bindAttivita(attivita.get(position));
    }

    @Override
    public int getItemCount() {
        return attivita.size();
    }

    public  void setData(List<Attivita> attivita)
    {
        this.attivita = attivita;
    }

    public void setAttivitaListener(RecyclerAttivitaListener listener)
    {
        this.listener = listener;
    }

    class AttivitaViewHolder extends RecyclerView.ViewHolder implements OnClickListener, OnLongClickListener

    {
        TextView attivitaText;
        RecyclerAttivitaListener listener;

        public AttivitaViewHolder(@NonNull View itemView, RecyclerAttivitaListener listener) {
            super(itemView);
            attivitaText = itemView.findViewById(R.id.textViewRecyclerAttivita);
            this.listener = listener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public  void bindAttivita(Attivita a)
        {
            attivitaText.setText(a.getNomeAttivita());
        }

        @Override
        public void onClick(View v) {
            listener.editAttivita(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            listener.delAttivita(getAdapterPosition());
            return true;
        }
    }

    public  interface RecyclerAttivitaListener
    {
        public void addAttivita();
        public void editAttivita(int position);
        public void delAttivita(int position);
    }
}
