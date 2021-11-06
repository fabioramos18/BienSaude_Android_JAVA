package com.example.biensaudev1.ui.clinicalPicture;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.biensaudev1.R;
import com.example.biensaudev1.ui.booking.BookingFragmentDirections;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class ClinicalPictureFragment extends Fragment {

    private static final String TAG = "ClinicalPictureFragment";

    RadioGroup radioGroup, radioGroupAlergias, radioGroupAnsiedade, radioGroupPatologias, radioGroupFebre, radioGroupMusc;
    RadioButton radioButton;
    TextInputLayout textInputDorLocation, textInputDorDuration, textInputAlergias, textInputPatologias;
    TextInputEditText txtDorLocation, txtDorDuration, txtAlergias, txtPatologias;

    private String dor;
    private String  alergias;
    private String  musc;
    private String ans;
    private String pa;
    private String febre;
    public ClinicalPictureFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_clinical_picture, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        radioGroup = view.findViewById(R.id.radioGroupDor);
        radioGroupAlergias = view.findViewById(R.id.radioGroupAlergias);
        radioGroupAnsiedade = view.findViewById(R.id.radioGroupAnsiedade);
        radioGroupMusc  = view.findViewById(R.id.radioGroupMusc);
        radioGroupPatologias = view.findViewById(R.id.radioGroupPatologias);
        radioGroupFebre = view.findViewById(R.id.radioGroupFebre);

        textInputDorLocation = view.findViewById(R.id.textInputDorLocation);
        textInputDorDuration = view.findViewById(R.id.textInputDorDuration);
        textInputAlergias = view.findViewById(R.id.textInputAlergias);
        textInputPatologias = view.findViewById(R.id.textInputPatologias);
        textInputDorLocation.setVisibility(View.GONE);
        textInputDorDuration.setVisibility(View.GONE);
        textInputAlergias.setVisibility(View.GONE);
        textInputPatologias.setVisibility(View.GONE);

        txtDorLocation = view.findViewById(R.id.txtDorLocation);
        txtDorDuration = view.findViewById(R.id.txtDorDuration);
        txtAlergias = view.findViewById(R.id.txtAlergias);
        txtPatologias = view.findViewById(R.id.txtPatologias);


         radioGroup.setOnCheckedChangeListener((group, checkedId) -> {

            if(checkedId == R.id.radio_dor1){
                 dor = "Sim. ";
                textInputDorLocation.setVisibility(View.VISIBLE);
            textInputDorDuration.setVisibility(View.VISIBLE);
            } else {
                dor = "Não.";
                textInputDorLocation.setVisibility(View.GONE);
            textInputDorDuration.setVisibility(View.GONE);

            }
        });

        radioGroupMusc.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId == R.id.radio_musc1){
                 musc = "Sim. ";
            } else {
                musc = "Não.";
            }

        });

        radioGroupAlergias.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.radio_alergias1){
                     alergias = "Sim. ";
                    textInputAlergias.setVisibility(View.VISIBLE);
                } else {
                     alergias = "Não.";
                    textInputAlergias.setVisibility(View.GONE);
                }
            }
        });

        radioGroupAnsiedade.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId == R.id.radio_ans1){
                 ans = "Sim. ";
            } else {
                 ans = "Não.";
            }

        });

        radioGroupPatologias.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.radio_pat1){
                     pa = "Sim. ";
                    textInputPatologias.setVisibility(View.VISIBLE);
                } else {
                     pa = "Não.";
                    textInputPatologias.setVisibility(View.GONE);
                }
            }
        });

        radioGroupFebre.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId == R.id.radio_febre1){
               febre = "Sim. ";
            } else {
                febre = "Não.";
            }

        });

        final  NavController navController = Navigation.findNavController(view);
        Button next1 = view.findViewById(R.id.next1);
        next1.setOnClickListener(v -> {
            ClinicalPictureFragmentDirections.ActionClinicalPictureFragmentToBookingConfirmationFragment action = ClinicalPictureFragmentDirections.actionClinicalPictureFragmentToBookingConfirmationFragment("","","","","","");
            ClinicalPictureFragmentArgs args = ClinicalPictureFragmentArgs.fromBundle(getArguments());
            if(getArguments() != null) {

                String type = args.getType();
                String center = args.getCenter();
                String service = args.getService();
                String datefrom = args.getDatefrom();
                String dateday = args.getDateday();
                String datehours = args.getDatehours();
                String name = args.getName();
                String surname = args.getSurname();
                String email = args.getEmail();
                String birth = args.getBirth();
                String mobile = args.getMobile();

                action.setCenter(center);
                action.setService(service);
                action.setName(name);
                action.setDatefrom(datefrom);
                action.setDateday(dateday);
                action.setDatehours(datehours);
                action.setSurname(surname);
                action.setEmail(email);
                action.setBirth(birth);
                action.setMobile(mobile);
                action.setName(name);
                action.setSurname(surname);
                action.setEmail(email);
                action.setBirth(birth);
                action.setMobile(mobile);

                Log.i(TAG, "onViewCreated: " + type + ", " + center + ", " + service + ", " + datefrom + ", " + dateday + ", " + datehours+ ", " +name+ ", " +surname+ ", " +email+ ", " +birth+ ", " +mobile);
            }

            if(dor == null){
                dor= "Nenhuma opção";
            }
            if(musc == null){
                musc= "Nenhuma opção";
            }
            if(alergias == null){
                alergias= "Nenhuma opção ";
            }
            if(ans == null){
                ans= "Nenhuma opção";
            }
            if(pa == null){
                pa= "Nenhuma opção";
            }
            if(febre == null){
                febre= "Nenhuma opção";
            }

            action.setDores(dor+""+ txtDorLocation.getText().toString()+" "+ txtDorDuration.getText().toString());
            action.setCansaco(musc);
            action.setAlergias(alergias+""+ txtAlergias.getText().toString());
            action.setAnsiedade(ans);
            action.setPatologias(pa+""+ txtPatologias.getText().toString());
            action.setFebre(febre);

            navController.navigate(action);

        });

    }
}