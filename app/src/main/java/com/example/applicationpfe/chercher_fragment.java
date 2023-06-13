package com.example.applicationpfe;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class chercher_fragment extends Fragment {

    private Button button;

    public chercher_fragment() {
        // Constructeur par défaut requis
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chercher_fragment, container, false);

        button = view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancer l'activité souhaitée
                Intent intent = new Intent(getActivity(), chercher_technicien.class);
                startActivity(intent);
            }
        });

        return view;
    }
}