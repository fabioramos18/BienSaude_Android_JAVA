package com.example.biensaudev1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.biensaudev1.adapters.FragmentAdapter;
import com.example.biensaudev1.adapters.ZoomOutPageTransformer;
import com.example.biensaudev1.models.MassageModel;
import com.example.biensaudev1.models.RehabilitationModel;
import com.example.biensaudev1.ui.description.DescriptionDetailFragment;
import com.example.biensaudev1.ui.expectation.ExpectationDetailFragment;
import com.example.biensaudev1.ui.recipients.RecipientsDetailFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;


public class DetailedActivity extends AppCompatActivity {

    ImageView detailedImg;
    TextView description,recipients, expectation, more1, more2, more3;
    Toolbar toolbar;

    MassageModel massageModel = null;
    RehabilitationModel rehabilitationModel =null;

    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
     //   getSupportActionBar().setDisplayShowHomeEnabled(true);

        final Object object = getIntent().getSerializableExtra("detail");
        final  Object objectrehabilitation =  getIntent().getSerializableExtra("rehabilitationdetails");


        if(object instanceof MassageModel){
            massageModel = (MassageModel) object;
        }

        if(objectrehabilitation instanceof RehabilitationModel){
           rehabilitationModel  = (RehabilitationModel) objectrehabilitation;
        }


        detailedImg = findViewById(R.id.detailed_img);
        description = findViewById(R.id.detailed_description);
        recipients = findViewById(R.id.detailed_recipients);
        expectation = findViewById(R.id.detailed_expectation);


        if (massageModel != null){
            Glide.with(getApplicationContext()).load(massageModel.getImage()).into(detailedImg);
            getSupportActionBar().setTitle(massageModel.getTitle());
            description.setText(massageModel.getDescription());
            recipients.setText(massageModel.getRecipients());
            expectation.setText(massageModel.getExpectation());
        }

        if (rehabilitationModel != null){
            Glide.with(getApplicationContext()).load(rehabilitationModel.getImage()).into(detailedImg);
            getSupportActionBar().setTitle(rehabilitationModel.getTitle());
            description.setText(rehabilitationModel.getDescription());
            recipients.setText(rehabilitationModel.getRecipients());
            expectation.setText(rehabilitationModel.getExpectation());
        }


        more1 = findViewById(R.id.more_detailed_description);
        more2 = findViewById(R.id.more_detailed_recipients);
        more3 = findViewById(R.id.more_detailed_expectation);

        more1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                description.setMaxLines(20);
            }
        });
        more2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipients.setMaxLines(20);
            }
        });
        more3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expectation.setMaxLines(20);
            }
        });


        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        pager2 =findViewById(R.id.view_pager2);
        pager2.setPageTransformer( new ZoomOutPageTransformer());

        FragmentManager fm = getSupportFragmentManager();

        adapter = new FragmentAdapter(fm, getLifecycle());
        pager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Descrição"));
        tabLayout.addTab(tabLayout.newTab().setText("Destinatários"));
        tabLayout.addTab(tabLayout.newTab().setText("Expectativas"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {

                tabLayout.selectTab(tabLayout.getTabAt(position));

            }
        });
    }


}