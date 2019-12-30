package it.trieste.simulazioneesame.controllers.utenti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import it.trieste.simulazioneesame.R;
import it.trieste.simulazioneesame.model.Utente;
import it.trieste.simulazioneesame.repository.Service;

import static androidx.recyclerview.widget.RecyclerView.*;

public class UtentiActivity extends AppCompatActivity implements OnClickListener, UsersEventsListener, AdapterRecyclerUtenti.UserEventListener {

    RecyclerView recyclerViewUtenti;
    LayoutManager layoutManager;
    AdapterRecyclerUtenti adapterRecyclerUtenti;

    FloatingActionButton floatingActionButtonUtenti;
    public static final String TAG_DIALOG_ADD_UPDATE_USER = "DIALOG_ADD_UPDATE_USER" ;

    int targetUser=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utenti);

        //Configura la recyclerView
        recyclerViewUtenti = findViewById(R.id.utentiRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerViewUtenti.setLayoutManager(layoutManager);

        adapterRecyclerUtenti = new AdapterRecyclerUtenti();
        adapterRecyclerUtenti.setUserEventListener(this);
        adapterRecyclerUtenti.setData(Service.utenti);
        recyclerViewUtenti.setAdapter(adapterRecyclerUtenti);
        adapterRecyclerUtenti.notifyDataSetChanged();

        //Add
        floatingActionButtonUtenti = findViewById(R.id.floatingActionButtonUtenti);
        floatingActionButtonUtenti.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        userAdd();
    }

    @Override
    public void userAdded(String Name) {
        Service.utenti.add(new Utente(Name));
        adapterRecyclerUtenti.notifyDataSetChanged();
    }

    @Override
    public void userUpdated(String NewName) {
        Service.utenti.get(targetUser).setNomeUtente(NewName);
        adapterRecyclerUtenti.notifyDataSetChanged();
    }

    @Override
    public void userDeleted() {
        Service.utenti.remove(targetUser);
        adapterRecyclerUtenti.notifyDataSetChanged();
    }


    @Override
    public void userAdd() {
        showDialog(UserDialogMode.ADD);
    }

    @Override
    public void userDelete(int target) {
        targetUser = target;
        showDialog(UserDialogMode.DELETE);
    }

    @Override
    public void userUpdate(int target) {
        targetUser = target;
        showDialog(UserDialogMode.UPDATE);
    }




    public  void showDialog(UserDialogMode userDialogMode)
    {
        DialogFragmentAddUpdateDelete dialogFragmentAddUpdate=(DialogFragmentAddUpdateDelete)getSupportFragmentManager().findFragmentByTag(TAG_DIALOG_ADD_UPDATE_USER);

        if (dialogFragmentAddUpdate==null){
            dialogFragmentAddUpdate = new DialogFragmentAddUpdateDelete();
            dialogFragmentAddUpdate.setUsersListener(this);
        }


        dialogFragmentAddUpdate.show(this.getSupportFragmentManager(),TAG_DIALOG_ADD_UPDATE_USER,userDialogMode);
    }
}
