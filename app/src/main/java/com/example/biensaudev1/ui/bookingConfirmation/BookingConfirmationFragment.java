package com.example.biensaudev1.ui.bookingConfirmation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.biensaudev1.LoginActivity;
import com.example.biensaudev1.R;
import com.example.biensaudev1.ui.bookingType.BookingTypeFragmentDirections;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.type.DateTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BookingConfirmationFragment extends Fragment {

   TextView tv_center, tv_service, tv_bookingFrom, tv_preferredDays, tv_periods, tv_name, tv_surname;
   TextView tv_email, tv_birth,tv_mobile,tv_dor,tv_musc,tv_alergias, tv_ansiedade,tv_patologias, tv_febre;
String center;

    FirebaseFirestore db;
    FirebaseAuth fAuth;
    String userId;

    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String Date;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        center= getArguments().getString("centerr");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v = inflater.inflate(R.layout.fragment_booking_confirmation, container, false);
        return v;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_center = view.findViewById(R.id.tv_center);
        tv_service = view.findViewById(R.id.tv_service);
        tv_bookingFrom = view.findViewById(R.id.tv_bookingFrom);
        tv_preferredDays = view.findViewById(R.id.tv_preferredDays);
        tv_periods = view.findViewById(R.id.tv_periods);
        tv_name = view.findViewById(R.id.tv_name);
        tv_surname = view.findViewById(R.id.tv_surname);
        tv_email = view.findViewById(R.id.tv_email);
        tv_birth = view.findViewById(R.id.tv_birth);
        tv_mobile = view.findViewById(R.id.tv_mobile);
        tv_dor = view.findViewById(R.id.tv_dor);
        tv_musc  = view.findViewById(R.id.tv_musc);
        tv_alergias = view.findViewById(R.id.tv_alergias);
        tv_ansiedade = view.findViewById(R.id.tv_ansiedade);
        tv_patologias = view.findViewById(R.id.tv_patologias);
        tv_febre = view.findViewById(R.id.tv_febre);


            BookingConfirmationFragmentArgs args = BookingConfirmationFragmentArgs.fromBundle(getArguments());

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
            String dor = args.getDores();
            String cansaco = args.getCansaco();
            String alergias = args.getAlergias();
            String ansiedade = args.getAnsiedade();
            String patologias =args.getPatologias();
            String febre = args.getFebre();

            tv_center.setText(center);
            tv_service.setText(service);
            tv_bookingFrom.setText(datefrom);
            tv_preferredDays.setText(dateday);
            tv_periods.setText(datehours);
            tv_name.setText(name);
            tv_surname.setText(surname);
            tv_email.setText(email);
            tv_birth.setText(birth);
            tv_mobile.setText(mobile);
            tv_dor.setText(dor);
            tv_musc.setText(cansaco);
            tv_alergias.setText(alergias);
            tv_ansiedade.setText(ansiedade);
            tv_patologias.setText(patologias);
            tv_febre.setText(febre);

        final NavController navController = Navigation.findNavController(view);
        fAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();

        calendar = Calendar.getInstance();
        simpleDateFormat=new SimpleDateFormat("d 'de' MMMM 'de' yyyy");
        Date= simpleDateFormat.format(calendar.getTime());

        Map<String, Object> bookings = new HashMap<>();
        bookings.put("alergias", alergias );
        bookings.put("bookingday", Timestamp.now());
       // bookings.put("bookingday", new Timestamp(new Date(birth)));


        bookings.put("cansMusc", cansaco);
        bookings.put("center", center);
        bookings.put("confirmateDay", "" );
        bookings.put("dateFrom", datefrom);
        bookings.put("dores", dor);
        bookings.put("favoriteDays", dateday);
        bookings.put("favouriteHours", datehours);
        bookings.put("febre", febre);
        bookings.put("patologias", patologias);
        bookings.put("reserveday", Date);
        bookings.put("service", service);
        bookings.put("serviceEmployee", "");
        bookings.put("status", "Em confirmação");
        bookings.put("userId", userId );
        bookings.put("utentNumber", mobile);
        bookings.put("utenteName", name);
        bookings.put("utenteBirth", birth );
        bookings.put("utenteEmail", email );
        bookings.put("utenteSurname", surname );
        bookings.put("tech", "" );

        Map<String, Object> profile = new HashMap<>();
        profile.put("name", name);
        profile.put("surname", surname );
        profile.put("mobile", "");



        Button next1 = view.findViewById(R.id.next1);
        next1.setOnClickListener(v -> {


            db.collection("bookings")
                    .add(bookings)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            db.collection("profiles")
                                    .add(profile)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            navController.navigate(R.id.action_bookingConfirmationFragment_to_nav_home);
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.i("Teste: ", e.getMessage());
                                        }
                                    });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.i("Erro", e.getMessage());
                        }
                    });


        });

    }
}