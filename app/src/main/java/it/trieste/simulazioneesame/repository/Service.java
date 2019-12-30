package it.trieste.simulazioneesame.repository;

import java.util.LinkedList;
import java.util.List;

import it.trieste.simulazioneesame.model.Attivita;
import it.trieste.simulazioneesame.model.Utente;

public class Service {
    static public List<Utente> utenti= new LinkedList<>();
    static private boolean populated = false;
    public static void populate()
    {
        if (!populated)
        {
            populated = true;
            Utente utente = new Utente("Mario");
            utenti.add(utente);

            utente = new Utente("Pietro");
            utente.getAttivitaUtente().add(new Attivita("Pizza 20:00"));
            utenti.add(utente);

            utente = new Utente("Mauro");
            utente.getAttivitaUtente().add(new Attivita("Workout 18:00"));
            utente.getAttivitaUtente().add(new Attivita("Shower 19:00"));
            utenti.add(utente);

        }
    }
}
