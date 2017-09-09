package com.app.expotecnica.concurso.padros.santiago.concursoexpo;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Main3Activity extends AppCompatActivity {
   StorageReference storageReference;
    private FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference2;




    //VideoView videoViewLandscape;
    Button videoup, videoup2;
    int Image_Request_Code = 7;
    ProgressDialog progressDialog;
    Uri FilePathUri;
    EditText VideoName, Apellido,Nombre,Curso;
    String Storage_Path = "Videos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference(Storage_Path);


        //currentUserDB3 = mDatabase.child(mAuth.getCurrentUser().getUid()).child("voto");
        mAuth = FirebaseAuth.getInstance();

        //videoViewLandscape =  (VideoView) findViewById(R.id.VideoView1);

        videoup = (Button) findViewById(R.id.button5);
        videoup2 = (Button) findViewById(R.id.button6);
        videoup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Calling method to upload selected image on Firebase storage.
                //UploadImageFileToFirebaseStorage();
                uploadFile();

            }
        });
        videoup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Creating intent.
                Intent intent = new Intent();

                // Setting intent type as image to select image from phone storage.
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Seleccione un Video"), Image_Request_Code);

            }

        });
       /* final VideoView videoView =
                (VideoView) findViewById(R.id.videoView1);

        videoView.setVideoPath(
                "https://firebasestorage.googleapis.com/v0/b/concursoexpotecnica.appspot.com/o/wos_freestyle_indito.mp4?alt=media&token=a95e6bdd-4e68-4a62-9db3-321752b48271");
        MediaController mediaController = new
                MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.start();*/
    }

    protected void onStart() {

        super.onStart();
        /*String str = "gs://concursoexpotecnica.appspot.com/20160331_211535.mp4";
        Uri uri = Uri.parse(str);
        videoViewLandscape.setVideoURI(uri);
        //progressBarLandScape.setVisibility(View.VISIBLE);
        videoViewLandscape.requestFocus();
        videoViewLandscape.start();*/

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();



            // After selecting image change choose button above text.
            videoup.setText("Video Seleccionado ");



        }
    }
    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }
    private void uploadFile() {
        //if there is a file to upload
        if (FilePathUri != null) {
            //displaying a progress dialog while upload is going on
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Subiendo Video");
            progressDialog.show();

            StorageReference riversRef = storageReference.child(Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));
            riversRef.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //if the upload is successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();
                            String TempVideoName = VideoName.getText().toString().trim();
                            String TempApellido = Apellido.getText().toString().trim();
                            String TempNombre = Nombre.getText().toString().trim();
                            String TempCurso = Curso.getText().toString().trim();

                            @SuppressWarnings("VisibleForTests")
                            VideoUploadInfo videoUploadInfo = new VideoUploadInfo(TempVideoName,TempApellido,TempNombre,TempCurso, taskSnapshot.getDownloadUrl().toString());

                            // Getting image upload ID.
                            String VideoUploadId = databaseReference.push().getKey();

                            // Adding image upload id s child element into databaseReference.
                            databaseReference.child(VideoUploadId).setValue(videoUploadInfo);


                            //and displaying a success toast
                            Toast.makeText(getApplicationContext(), "Video Subido ", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                            public void onFailure(@NonNull Exception exception) {
                            //if the upload is not successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();

                            //and displaying error message
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //calculating progress percentage
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            //displaying percentage in progress dialog
                            progressDialog.setMessage("Subiendo " + ((int) progress) + "%...");
                        }
                    });
        }
        //if there is not any file
        else {
            //you can display an error toast
        }
    }
   /* public void UploadImageFileToFirebaseStorage() {

        // Checking whether FilePathUri Is empty or not.
        if (FilePathUri != null) {

            // Setting progressDialog Title.
            progressDialog.setTitle("Subiendo Video...");

            // Showing progressDialog.
            progressDialog.show();

            // Creating second StorageReference.
            StorageReference storageReference2nd = storageReference.child(Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));

            // Adding addOnSuccessListener to second StorageReference.
            storageReference2nd.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            // Getting image name from EditText and store into string variable.
                            String TempVideoName = VideoName.getText().toString().trim();
                            String TempApellido = Apellido.getText().toString().trim();
                            String TempNombre = Nombre.getText().toString().trim();
                            String TempCurso = Curso.getText().toString().trim();


                            // Hiding the progressDialog after done uploading.
                            progressDialog.dismiss();

                            // Showing toast message after done uploading.
                            Toast.makeText(getApplicationContext(), "Video subido correctamente ", Toast.LENGTH_LONG).show();


                            @SuppressWarnings("VisibleForTests")
                            VideoUploadInfo videoUploadInfo = new VideoUploadInfo(TempVideoName,TempApellido,TempNombre,TempCurso, taskSnapshot.getDownloadUrl().toString());

                            // Getting image upload ID.
                            String VideoUploadId = databaseReference.push().getKey();

                            // Adding image upload id s child element into databaseReference.
                            databaseReference.child(VideoUploadId).setValue(videoUploadInfo);
                        }
                    })
                    // If something goes wrong .
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {

                            // Hiding the progressDialog.
                            progressDialog.dismiss();

                            // Showing exception erro message.
                            Toast.makeText(Main3Activity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })

                    // On progress change upload time.
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            // Setting progressDialog Title.
                            progressDialog.setTitle("El video se esta subiendo");

                        }
                    });
        }
        else {

            Toast.makeText(Main3Activity.this, "Complete todos los campos", Toast.LENGTH_LONG).show();

        }
    }*/
}
