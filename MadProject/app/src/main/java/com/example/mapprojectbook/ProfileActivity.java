package com.example.mapprojectbook;

<<<<<<< HEAD
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;


public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "ProfileActivity";

    EditText username, fname, lname, email, password, address, contactNo;
    ImageView image, addImage;
    Button save;
    String userEmail;
    Intent intent;

    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 22;

    private FirebaseStorage storage;
    private StorageReference storageReference;
    private DatabaseReference db;

    private DrawerLayout drawer;

    private String EMAIL;

    private User foundUser;

=======
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class ProfileActivity extends AppCompatActivity {

    EditText username, fname, lname, email, password, address, contactNo;
    Button button;
    String userEmail;
    Intent intent;

>>>>>>> 0b1bcbd06bb6eddba55dff7f8f9ccd21762eaf0a
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

<<<<<<< HEAD
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        db = FirebaseDatabase.getInstance().getReference().child("Users");

        Intent thisIntent = getIntent();
        EMAIL = thisIntent.getExtras().getString("email");

        loadData();
        initViews();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout_profile);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this, drawer, toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveButtonClicked();
            }
        });


        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectImage();
                uploadImage();
            }
        });
    }

    private Void loadData() {
        Log.d(TAG, "loadData: Started");
        Query query = db.orderByChild("email").equalTo(EMAIL);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange: "+ dataSnapshot.getRef());
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Log.d(TAG, "onDataChange: " + dataSnapshot.toString());
                    foundUser = ds.getValue(User.class);
                    Log.d(TAG, "onDataChange: " + foundUser.getEmail());
                    initViews();
                    populateUI(foundUser);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
        Log.d(TAG, "loadData: Finished");

        return null;
    }

    private void SelectImage()
    {

        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }

    // Override onActivityResult method
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data)
    {

        super.onActivityResult(requestCode,
                resultCode,
                data);


        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);
                image.setImageBitmap(bitmap);
            }

            catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }

    // UploadImage method
    private void uploadImage()
    {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref = storageReference.child("/" + foundUser.getEmail() + "/images/" + UUID.randomUUID().toString());

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Toast
                                            .makeText(ProfileActivity.this,
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(ProfileActivity.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int)progress + "%");
                                }
                            });
        }
    }

    private void populateUI(User user) {
        Log.d(TAG, "populateUI: started");
=======
        initViews();


    }

    private void populateUI(User user) {

>>>>>>> 0b1bcbd06bb6eddba55dff7f8f9ccd21762eaf0a
        if (user == null) {
            return;
        }
        username.setText((user.getUsername()));
<<<<<<< HEAD

        if(fname.getText().toString().equals("null")) {
            fname.setText((" "));
        } else {
            fname.setText(user.getFname());
        }

        if(lname.getText().toString().equals("null")) {
            lname.setText((" "));
        } else {
            lname.setText(user.getLname());
        }

        email.setText(user.getEmail());
        password.setText(user.getPassword());

        if(address.getText().toString().equals("null")) {
            address.setText((" "));
        } else {
            address.setText(user.getAddress());
        }

        if(contactNo.getText().toString().equals("null")) {
            contactNo.setText((" "));
        } else {
            contactNo.setText(user.getContactNo());
        }


        Log.d(TAG, "populateUI: Finished");
    }

    private void initViews() {
        Log.d(TAG, "initViews: Started");
        image = findViewById(R.id.image);
=======
        fname.setText(user.getFname());
        lname.setText(user.getLname());
        email.setText(user.getEmail());
        password.setText(user.getPassword());
        contactNo.setText(user.getContactNo());
        address.setText(user.getAddress());
    }

    private void initViews() {
>>>>>>> 0b1bcbd06bb6eddba55dff7f8f9ccd21762eaf0a
        username = findViewById(R.id.edit_username);
        fname = findViewById(R.id.edit_fname);
        lname = findViewById(R.id.edit_lname);
        password = findViewById(R.id.edit_pass);
        email = findViewById(R.id.edit_email);
        address = findViewById(R.id.edit_address);
        contactNo = findViewById(R.id.edit_contactNo);

<<<<<<< HEAD
        addImage = findViewById(R.id.button_addImage);
        save = findViewById(R.id.button_profileSave);
        Log.d(TAG, "initViews: Finished");

=======
        button = findViewById(R.id.save_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveButtonClicked();
            }
        });
>>>>>>> 0b1bcbd06bb6eddba55dff7f8f9ccd21762eaf0a
    }

    public void onSaveButtonClicked() {
        User user = new User(
                email.getText().toString(),
                username.getText().toString(),
                fname.getText().toString(),
                lname.getText().toString(),
                password.getText().toString(),
                address.getText().toString(),
                contactNo.getText().toString()
        );
<<<<<<< HEAD
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch(item.getItemId()) {
            case R.id.nav_home:
                intent = new Intent(this, HomeActivity.class);
                intent.putExtra("email", EMAIL);
                startActivity(intent);
                break;
            case R.id.nav_profile:
                intent = new Intent(this, ProfileActivity.class);
                intent.putExtra("email", EMAIL);
                startActivity(intent);
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen((GravityCompat.START))) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
=======

>>>>>>> 0b1bcbd06bb6eddba55dff7f8f9ccd21762eaf0a
    }

}