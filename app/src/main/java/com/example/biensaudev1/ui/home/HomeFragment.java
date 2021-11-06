package com.example.biensaudev1.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biensaudev1.R;
import com.example.biensaudev1.adapters.MassageAdapters;
import com.example.biensaudev1.adapters.RehabilitationAdapters;
import com.example.biensaudev1.models.MassageModel;
import com.example.biensaudev1.models.RehabilitationModel;
import com.example.biensaudev1.ui.massage.MassageFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    TextView view_all_massage,  view_all_rehabilitation;
    RecyclerView massageRec, rehabilitationRec;

    FirebaseFirestore db;


    RelativeLayout view_all_massage_card, view_all_rehabilitation_card;

    //massage items
    List<MassageModel> massageModelList;
    MassageAdapters massageAdapters;

    //rehabilitation List
    List<RehabilitationModel> rehabilitationModelList;
    RehabilitationAdapters rehabilitationAdapters;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        db = FirebaseFirestore.getInstance();

        massageRec = root.findViewById(R.id.massage_rec);
        rehabilitationRec = root.findViewById(R.id.rehabilitation_rec);



        //massage items
        massageRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        massageModelList = new ArrayList<>();
        massageAdapters = new MassageAdapters(getActivity(), massageModelList);
        massageRec.setAdapter(massageAdapters);

        db.collection("massages").orderBy("title").limit(5)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                MassageModel massageModel = document.toObject(MassageModel.class);
                                massageModelList.add(massageModel);
                                massageAdapters.notifyDataSetChanged();


                            }
                        } else {
                            Toast.makeText(getActivity(),"Erro", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        //Rehabilitation items
        rehabilitationRec.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        rehabilitationModelList = new ArrayList<>();
        rehabilitationAdapters = new RehabilitationAdapters(getActivity(), rehabilitationModelList);
        rehabilitationRec.setAdapter(rehabilitationAdapters);

        db.collection("rehabilitations").orderBy("title").limit(5)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                RehabilitationModel rehabilitationModel = document.toObject(RehabilitationModel.class);
                                rehabilitationModelList.add(rehabilitationModel);
                                rehabilitationAdapters.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(),"Erro", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        view_all_massage = view.findViewById(R.id.view_all_massage);
        view_all_massage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_nav_home_to_nav_massage);
            }
        });

        view_all_rehabilitation = view.findViewById(R.id.view_all_rehabilitation);
        view_all_rehabilitation.setOnClickListener(v -> {
            navController.navigate(R.id.action_nav_home_to_nav_rehabilitation);
        });

        view_all_massage_card = view.findViewById(R.id.view_all_massage_card);
        view_all_massage_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_nav_home_to_nav_massage);
            }
        });

        view_all_rehabilitation_card = view.findViewById(R.id.view_all_rehabilitation_card);
        view_all_rehabilitation_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // HomeFragmentDirections action = HomeFragmentDirections.actionNavHomeToNavMassage();
                navController.navigate(R.id.action_nav_home_to_nav_rehabilitation);
            }
        });
    }
}