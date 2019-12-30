package it.trieste.simulazioneesame.controllers.utenti;

interface UsersEventsListener {
    void userAdded(String Name);

    void userUpdated(String NewName);

    void  userDeleted();
}
