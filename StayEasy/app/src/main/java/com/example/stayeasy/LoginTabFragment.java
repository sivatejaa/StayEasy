package com.example.stayeasy;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginTabFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_tab, container, false);
        Button loginButton = view.findViewById(R.id.login_button);

        EditText emailEditText = view.findViewById(R.id.login_email);
        EditText passwordEditText = view.findViewById(R.id.login_password);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {

                    Toast.makeText(getActivity(), "Please enter email and password", Toast.LENGTH_SHORT).show();
                } else {

                    Intent intent = new Intent(getActivity(), Hotels_List.class);
                    startActivity(intent);
                }
            }
        });
        return view;
    }
}