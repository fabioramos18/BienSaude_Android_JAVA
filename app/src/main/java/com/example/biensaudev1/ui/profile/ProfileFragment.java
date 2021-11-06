package com.example.biensaudev1.ui.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biensaudev1.LoginActivity;
import com.example.biensaudev1.MainActivity;
import com.example.biensaudev1.R;
import com.example.biensaudev1.RegisterActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class ProfileFragment extends Fragment {

 LinearLayout editProfile, reservas;
 Calendar calendar;
 SimpleDateFormat simpleDateFormat;
 String Date;

    ImageView ImgUserPhoto;
    TextView name,email;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    StorageReference storageReference;
    String userId;


    private Uri mSelectedUri;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        name = view.findViewById(R.id.nameUser);
        email = view.findViewById(R.id.userEmail);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        user = fAuth.getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference();
        ImgUserPhoto = view.findViewById(R.id.btn_selected_photo);
        StorageReference profileRef = storageReference.child("users/"+userId+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(uri -> {

            Picasso.get().load(uri).into(ImgUserPhoto);
        }) .addOnFailureListener(e -> Toast.makeText(getContext(), "A imagem não foi carregada!!", Toast.LENGTH_SHORT).show());

        DocumentReference noteRef = fStore.collection("profiles").document(userId);
        noteRef.get()
                .addOnSuccessListener(documentSnapshot -> {
                    if(documentSnapshot.exists()){
                        String uname = documentSnapshot.getString("name");
                        String usurname = documentSnapshot.getString("surname");
                        String uemail = documentSnapshot.getString("email");

                        name.setText(uname + " " + usurname);
                        email.setText(uemail);
                    }else{
                        Toast.makeText(getContext(), "Dados Não existentes", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> Toast.makeText(getContext(), "Erro!!", Toast.LENGTH_SHORT).show());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        reservas = view.findViewById(R.id.reservas);

        reservas.setOnClickListener(v -> {
            navController.navigate(R.id.action_nav_profile_to_reservesFragment);
        });

    }


}