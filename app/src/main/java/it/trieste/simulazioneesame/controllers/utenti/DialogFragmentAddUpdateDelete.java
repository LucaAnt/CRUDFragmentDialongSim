package it.trieste.simulazioneesame.controllers.utenti;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import it.trieste.simulazioneesame.R;

public class DialogFragmentAddUpdateDelete extends DialogFragment implements View.OnClickListener {

    Button buttonOk,buttonCancel;
    EditText editTextUtente;
    TextView textViewDialogUtenti;
    UsersEventsListener usersEventsListener;
    UserDialogMode mode;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_utenti_dialog_add_update,container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonOk = view.findViewById(R.id.buttonUserOK);
        buttonOk.setOnClickListener(this);
        buttonCancel = view.findViewById(R.id.buttonUserCancel);

        buttonCancel.setOnClickListener(this);
        editTextUtente = view.findViewById(R.id.editTextUser);
        textViewDialogUtenti = view.findViewById(R.id.textViewDialogAddUser);

        switch (mode)
        {
            case ADD:textViewDialogUtenti.setText("Inserisci nuovo utente:");editTextUtente.setVisibility(View.VISIBLE);break;
            case DELETE:textViewDialogUtenti.setText("Sei sicuro di voler eliminare l'utente selezionato ?");editTextUtente.setVisibility(View.GONE);break;
            case UPDATE:textViewDialogUtenti.setText("Modifica utente:");editTextUtente.setVisibility(View.VISIBLE);break;
            default:break;

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonUserOK:
                buttonOkPressed();
                break;
            case R.id.buttonUserCancel:
                buttonCancelPressed();
                break;
                default:return;
        }
    }

    public void buttonOkPressed()
    {
        switch (mode)
    {
        case ADD:usersEventsListener.userAdded(editTextUtente.getText().toString());break;
        case DELETE:usersEventsListener.userDeleted();break;
        case UPDATE:usersEventsListener.userUpdated(editTextUtente.getText().toString());break;
        default:break;

    }
        dismiss();
    }

    public void buttonCancelPressed()
    {
        dismiss();
    }

    public void setUsersListener(UsersEventsListener listener) {
        usersEventsListener = listener;
    }


    public void show(FragmentManager supportFragmentManager, String tagDialogAddUpdateUser, UserDialogMode mode) {
        show(supportFragmentManager,tagDialogAddUpdateUser);
        this.mode = mode;

    }
}
