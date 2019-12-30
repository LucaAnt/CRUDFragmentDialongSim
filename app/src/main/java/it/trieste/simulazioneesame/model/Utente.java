package it.trieste.simulazioneesame.model;

import java.util.LinkedList;
import java.util.List;

public class Utente {
    private String nomeUtente;
    private List<Attivita> attivitaUtente;

    public Utente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
        this.attivitaUtente = new LinkedList<>();
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    public List<Attivita> getAttivitaUtente() {
        return attivitaUtente;
    }

    public void setAttivitaUtente(List<Attivita> attivitaUtente) {
        this.attivitaUtente = attivitaUtente;
    }
    public void addAttivita(Attivita attivita)
    {
        attivitaUtente.add(attivita);
    }
}
