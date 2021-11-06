package com.example.biensaudev1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class BookingDatasFragment extends Fragment {

    FirebaseFirestore fStore;
    String userId;
    FirebaseAuth fAuth;

    private static final String TAG = "AvailabilityFragment";
    TextInputEditText txtName, txtSurname, txtEmail, txtBdate,txtMobile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booking_datas, container, false);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();

        txtName = view.findViewById(R.id.txtName);
        txtSurname = view.findViewById(R.id.txtSurname);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtBdate = view.findViewById(R.id.txtBdate);
        txtMobile = view.findViewById(R.id.txtMobile);

        DocumentReference noteRef = fStore.collection("profiles").document(userId);
        noteRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            String uname = documentSnapshot.getString("name");
                            String usurname = documentSnapshot.getString("surname");
                            String uemail = documentSnapshot.getString("email");
                            String birth = documentSnapshot.getString("birth");
                            txtName.setText(uname);
                            txtSurname.setText(usurname);
                            txtEmail.setText(uemail);
                            txtBdate.setText(birth);

                        }else{
                            Toast.makeText(getContext(), "Dados Não existentes", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Erro", Toast.LENGTH_SHORT).show();
                    }
                });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        txtName = view.findViewById(R.id.txtName);
        txtSurname = view.findViewById(R.id.txtSurname);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtBdate = view.findViewById(R.id.txtBdate);
        txtMobile = view.findViewById(R.id.txtMobile);
        final NavController navController = Navigation.findNavController(view);

        Button next1 = view.findViewById(R.id.next1);
        next1.setOnClickListener(v -> {
            BookingDatasFragmentDirections.ActionBookingDatasFragmentToClinicalPictureFragment action = BookingDatasFragmentDirections.actionBookingDatasFragmentToClinicalPictureFragment();
            BookingDatasFragmentArgs args = BookingDatasFragmentArgs.fromBundle(getArguments());
            if (getArguments() !=null){
                String type = args.getType();
                String center = args.getCenter();
                String service = args.getService();
                String datefrom = args.getDatefrom();
                String dateday = args.getDateday();
                String datehours = args.getDatehours();
                action.setType(type);
                action.setCenter(center);
                action.setService(service);
                action.setDatefrom(datefrom);
                action.setDateday(dateday);
                action.setDatehours(datehours);

                Log.i(TAG, "onViewCreated: "+ type + "," + center + "," + service + "," + datefrom + "," + dateday + "," + datehours);

            }
            action.setName(txtName.getText().toString());
            action.setSurname(txtSurname.getText().toString());
            action.setEmail(txtEmail.getText().toString());
            action.setBirth(txtBdate.getText().toString());
            action.setMobile(txtMobile.getText().toString());

            navController.navigate(action);

        });


    }
}