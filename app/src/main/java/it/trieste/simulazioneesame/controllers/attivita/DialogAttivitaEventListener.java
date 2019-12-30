package it.trieste.simulazioneesame.controllers.attivita;

interface DialogAttivitaEventListener {
    void addedAttivita(String value);

    void editedAttivita(String value);

    void deletedAttivita();
}
