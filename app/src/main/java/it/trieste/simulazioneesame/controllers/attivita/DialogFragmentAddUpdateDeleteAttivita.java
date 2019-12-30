package it.trieste.simulazioneesame.controllers.attivita;

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
import it.trieste.simulazioneesame.model.Utente;

public class DialogFragmentAddUpdateDeleteAttivita extends DialogFragment implements View.OnClickListener {
    AttivitaDialogMode mode;
    Utente utente;
    DialogAttivitaEventListener dialogAttivitaEventListener;
    TextView title;
    EditText editText;
    Button buttonOk,buttonCancel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  LayoutInflater.from(getContext()).inflate(R.layout.fragment_attivita_dialog_add_update,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title = view.findViewById(R.id.textViewDialogAttivita);
        editText = view.findViewById(R.id.editTextDialogAttivita);
        buttonOk = view.findViewById(R.id.buttonOkDialogAttivita);
        buttonCancel = view.findViewById(R.id.buttonCancelDialogAttivita);
        switch (mode)
        {
            case ADD:title.setText("Inserisci nuova attivita per " + utente.getNomeUtente()+" :");editText.setVisibility(View.VISIBLE);break;
            case DELETE:title.setText("Sei sicuro di voler eliminare l'attivita per "+utente.getNomeUtente()+" ?");editText.setVisibility(View.GONE);break;
            case UPDATE:title.setText("Modifica attivita per "+utente.getNomeUtente()+" :");editText.setVisibility(View.VISIBLE);break;
            default:break;

        }

        buttonOk.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
    }

    public void show(FragmentManager supportFragmentManager, String tagDialogAddUpdateAttivita, AttivitaDialogMode mode, Utente targetUtente) {
        show(supportFragmentManager,tagDialogAddUpdateAttivita);
        this.mode = mode;
        this.utente = targetUtente;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonOkDialogAttivita:buttonOkPressed();break;
            case  R.id.buttonCancelDialogAttivita:dismiss();break;
            default:break;
        }
    }

    public void buttonOkPressed()
    {
        switch (mode)
        {
            case ADD:dialogAttivitaEventListener.addedAttivita(editText.getText().toString());break;
            case DELETE:dialogAttivitaEventListener.deletedAttivita();break;
            case UPDATE:dialogAttivitaEventListener.editedAttivita(editText.getText().toString());break;
            default:break;

        }
        dismiss();
    }

    public  void setDialogAttivitaEventListener(DialogAttivitaEventListener listener)
    {
        this.dialogAttivitaEventListener = listener;
    }
}
