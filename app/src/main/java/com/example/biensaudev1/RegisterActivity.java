package com.example.biensaudev1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RegisterActivity extends AppCompatActivity {

    EditText date;
    TextInputLayout textInputName, textInputApelido ,textInputBDate, textInputEmail, textInputPassword, textInputConfirmPassword;
    Button cirRegisterButton;
    RadioGroup radioGroup;
    RadioButton radioButton;

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    FirebaseUser user;
    StorageReference storageReference;


    String userId;
    String userEmail;

    ImageView ImgUserPhoto;

    private Uri mSelectedUri;

    DatePickerDialog datePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();

        ImgUserPhoto = findViewById(R.id.btn_selected_photo);

        ImgUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPhoto();
            }
        });

        textInputName = findViewById(R.id.textInputName);
        textInputApelido = findViewById(R.id.textInputApelido);
        textInputBDate = findViewById(R.id.textInputBDate);
        textInputEmail = findViewById(R.id.textInputEmail);
        textInputPassword = findViewById(R.id.textInputPassword);
        textInputConfirmPassword = findViewById(R.id.textInputConfirmPassword);
        date = findViewById(R.id.editTextBDate);
        radioGroup = findViewById(R.id.radioG);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference();


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                final String[] MONTHS = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};

                datePickerDialog=new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int myear, int mmonth, int mdayOfMonth) {
                        date.setText(mdayOfMonth+"/"+( MONTHS[mmonth] )+"/"+myear);
                    }
                },year,month,day);
                datePickerDialog.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());
                datePickerDialog.show();
            }
        });
    }


    private void selectPhoto() {
       // Intent intent = new Intent(Intent.ACTION_PICK);
        //intent.setType("image/*");
        //(intent, 0);
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,1000);
    }

   @Override
   protected void onActivityResult(int requestCode, int resultCode, @androidx.annotation.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            if(resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();

                ImgUserPhoto.setImageURI(imageUri);

                uploadImageToFirebase(imageUri);

            }
        }
    }

    private void uploadImageToFirebase(Uri imageUri) {
        // upload image to firebase storage
        final StorageReference fileRef = storageReference.child("users/" + fAuth.getCurrentUser().getUid() + "/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(ImgUserPhoto);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void onLoginClick(View view){
        startActivity(new Intent(this,LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
    }

    private boolean validateName(){
        String val = textInputName.getEditText().getText().toString().trim();

        if (val.isEmpty()){
            textInputName.setError("Este campo é obrigatório");
            return false;
        }
        else {
            textInputName.setError(null);
            return true;
        }
    }

    private boolean validateApelido(){
        String val = textInputApelido.getEditText().getText().toString().trim();

        if (val.isEmpty()){
            textInputApelido.setError("Este campo é obrigatório");
            return false;
        }
        else {
            textInputApelido.setError(null);
            textInputApelido.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateBDate(){
        String val = textInputBDate.getEditText().getText().toString().trim();

        if (val.isEmpty()){
            textInputBDate.setError("Este campo é obrigatório");
            return false;
        }
        else {
            textInputBDate.setError(null);
            textInputBDate.setErrorEnabled(false);
            return  true;
        }
    }

    private boolean validateEmail(){
        String val = textInputEmail.getEditText().getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()){
            textInputEmail.setError("E-mail é obrigatório");
            return false;
        } else if (!val.matches(emailPattern)){
            textInputEmail.setError("E-mail inválido");
            return false;
        } else {
            textInputEmail.setError(null);
            return  true;
        }
    }

    private boolean validatePassword(){
        String val = textInputPassword.getEditText().getText().toString().trim();
        String passwordVal = ( "^" +
                "(?=.*[0-9])"+
                "(?=.*[A-Z])"+
                "(?=.*[a-z])"+
                "(?=.*[@#$._%^&+=])"+
                ".{8,}"+
                "$");

        if (val.isEmpty()){
            textInputPassword.setError("Palavra-passe é obrigatória. Utilize 8 ou mais carateres numa mistura de letras Maiusculas e minusculas, números e símbolos. ");
            return false;
        } else if (!val.matches(passwordVal)){
            textInputPassword.setError("A palavra-passe é muito fraca");
            return false;
        }
        else {
            textInputPassword.setError(null);
            return  true;
        }
    }

    private boolean validateConfirmPassword(){
        String valp = textInputPassword.getEditText().getText().toString().trim();
        String valcp = textInputConfirmPassword.getEditText().getText().toString().trim();

        if (valcp.isEmpty()){
            textInputConfirmPassword.setError("Palavras-passe é obrigatório");
            return false;
        } else if (!valcp.equals(valp)){
            textInputConfirmPassword.setError("As Palavras-passe não coicidem");
            return false;
        }
        else {
            textInputConfirmPassword.setError(null);
            return true;
        }
    }

    public void registerUser(View view){

        if(!validateName() | !validateApelido() | !validateBDate() | !validateEmail() | !validatePassword() | !validateConfirmPassword()){
            return;
        }

        String name = textInputName.getEditText().getText().toString();
        String apelido = textInputApelido.getEditText().getText().toString();
        String bdate = textInputBDate.getEditText().getText().toString();
        String email = textInputEmail.getEditText().getText().toString();
        String password = textInputConfirmPassword.getEditText().getText().toString();
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton  = findViewById(radioId);



        fAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                userId = fAuth.getCurrentUser().getUid();
                userEmail = fAuth.getCurrentUser().getEmail();

                try {
                    String filename = UUID.randomUUID().toString();
                    final StorageReference ref = FirebaseStorage.getInstance().getReference("/uploads/prfiles/" + filename);
                    ref.putFile(mSelectedUri)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            String profileUrl  = uri.toString();
                                            Map<String, Object> profile = new HashMap<>();
                                            profile.put("name", name);
                                            profile.put("email", email );
                                            profile.put("id", userId );
                                            profile.put("surname", apelido );
                                            profile.put("birth", bdate );
                                            profile.put("gender", radioButton.getText());
                                            profile.put("image", profileUrl);
                                            profile.put("mobile", "");


                                            fStore.collection("profiles").document(userId)
                                                    .set(profile)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            //send user to next page
                                                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                                                            //overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
                                                            finish();
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Log.i("Teste: ", e.getMessage());
                                                        }
                                                    });

                                        }
                                    });
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e("Test", e.getMessage(),e);
                                }
                            });


                }catch (Exception e){
                    Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}