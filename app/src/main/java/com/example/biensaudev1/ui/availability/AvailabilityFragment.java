package com.example.biensaudev1.ui.availability;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.biensaudev1.R;
import com.example.biensaudev1.RegisterActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;


public class AvailabilityFragment extends Fragment {


    TextInputEditText date, txtDays, txtHours;

    TextInputLayout textInputBDate;
    DatePickerDialog datePickerDialog;
    private static final String TAG = "AvailabilityFragment";

    //day
    boolean[] selectedDay;
    ArrayList<Integer> dayList = new ArrayList<>();
    String[] dayArray = {"2ª Feira","3ª Feira","4ª Feira","5ª Feira","6ª Feira", "Sábado", "Domingo"};

    //hours
    boolean[] selectedHours;
    ArrayList<Integer> hoursList = new ArrayList<>();
    String[] hoursArray = {"Manha (10h-12h)","Almoço (12h-14h)","Tarde (14h-18h)","Noite (18h-22h)"};

    public AvailabilityFragment() {
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
        View v = inflater.inflate(R.layout.fragment_availability, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        date = view.findViewById(R.id.editTextBDate);
        textInputBDate = view.findViewById(R.id.textInputBDate);
        txtDays = view.findViewById(R.id.txtDays);
        txtHours = view.findViewById(R.id.txtHours);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                final String[] MONTHS = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};

                datePickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int myear, int mmonth, int mdayOfMonth) {
                        date.setText(mdayOfMonth+"/"+( MONTHS[mmonth] )+"/"+myear);
                    }
                },year,month,day);
                datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());
                datePickerDialog.show();
            }
        });

        selectedDay = new boolean[dayArray.length];
        selectedHours = new boolean[hoursArray.length];
        txtDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    getContext()
            );

            builder.setTitle("Selecione os dias:");
            builder.setMultiChoiceItems(dayArray, selectedDay, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                    if (isChecked){
                        dayList.add(which);
                        Collections.sort(dayList);
                    }else {
                        dayList.remove(which);
                    }
                }
            });

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    StringBuilder stringBuilder = new StringBuilder();

                    for (int j=0; j<dayList.size(); j++){
                        stringBuilder.append(dayArray[dayList.get(j)]);

                        if (j != dayList.size()-1){
                            stringBuilder.append(", ");
                        }
                    }
                    txtDays.setText(stringBuilder.toString());
                }
            });

            builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builder.setNeutralButton("Limpar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    for (int j=0; j<selectedDay.length; j++){
                        selectedDay[j] =false;

                        dayList.clear();

                        txtDays.setText("");
                    }
                }
            });
            builder.show();

            }
        });

        txtHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        getContext()
                );

                builder.setTitle("Selecione as horas:");
                builder.setMultiChoiceItems(hoursArray, selectedHours, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                        if (isChecked) {
                            hoursList.add(which);
                            Collections.sort(hoursList);
                        } else {
                            hoursList.remove(which);
                        }

                    }
                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder stringBuilder = new StringBuilder();

                        for (int h = 0; h < hoursList.size(); h++) {
                            stringBuilder.append(hoursArray[hoursList.get(h)]);

                            if (h != hoursList.size() - 1) {
                                stringBuilder.append(", ");
                            }
                        }

                        txtHours.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setNeutralButton("Limpar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        for (int h=0; h<selectedHours.length; h++){
                            selectedHours[h] =false;

                            hoursList.clear();

                            txtHours.setText("");
                        }
                    }
                });
                builder.show();

            }
        });

        final NavController navController = Navigation.findNavController(view);

        Button next1 = view.findViewById(R.id.next1);
        next1.setOnClickListener(v -> {
            if(getArguments() != null){

                AvailabilityFragmentArgs args = AvailabilityFragmentArgs.fromBundle(getArguments());
                String type = args.getType();
                String  center = args.getCenter();
                String service = args.getService();
                Log.i(TAG, "onViewCreated: " + type + ","+ center + "," +service);

                AvailabilityFragmentDirections.ActionAvailabilityFragmentToBookingDatasFragment action = AvailabilityFragmentDirections.actionAvailabilityFragmentToBookingDatasFragment();

                action.setType(args.getType());
                action.setCenter(args.getCenter());
                action.setService(args.getService());
                action.setDatefrom(date.getText().toString());
                action.setDateday(txtDays.getText().toString());
                action.setDatehours(txtHours.getText().toString());

                navController.navigate(action);

            }



        });

    }
}