package com.harryh5n1.vitqp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Uploads extends AppCompatActivity {

    TextView code,pass,course_code,textView;
    Button update,hp1;
    StorageReference storageReference;
    StorageReference reference;
    int i=-1;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploads);

        code=(TextView)findViewById(R.id.code);
        course_code=(TextView)findViewById(R.id.Course_code);
        pass=(TextView)findViewById(R.id.Admin_pass);
        textView=(TextView)findViewById(R.id.textView4);

        update=(Button)findViewById(R.id.update);
        storageReference= FirebaseStorage.getInstance().getReference();



        hp1=(Button)findViewById(R.id.button);

        hp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Uploads.this,OpenPage.class);
                startActivity(intent);
                finish();
            }
        });

        Switch sw = (Switch) findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //Toast.makeText(Uploads.this,"QP UPLOAD",Toast.LENGTH_SHORT).show();
                    //textView.setTextColor(Color.RED);
                    textView.setText("QP IN");
                    i=0;

                } else {
                    //Toast.makeText(Uploads.this,"RM UPLOAD",Toast.LENGTH_SHORT).show();
                    //textView.setTextColor(Color.BLUE);
                    textView.setText("RM IN");
                    i=1;
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name =course_code.getText().toString();
                final String email = code.getText().toString().trim();
                final String admin = pass.getText().toString().trim();

                if(name.isEmpty()) {
                    course_code.setError("Course Code Required");
                    course_code.requestFocus();
                    return;
                }
                if(email.isEmpty()) {
                    code.setError("Paper Details Required");
                    code.requestFocus();
                    return;
                }
                if (admin.isEmpty()) {
                    pass.setError("Admin Password Required");
                    pass.requestFocus();
                    return;
                }

                if (!admin.isEmpty()&& !admin.contentEquals("12345")) {
                    pass.setError("Admin Password Invalid");
                    pass.requestFocus();
                    return;
                }
                if(i==1) {
                    databaseReference = FirebaseDatabase.getInstance().getReference("rm" + course_code.getText().toString());
                }
                if(i==0){
                    databaseReference = FirebaseDatabase.getInstance().getReference("qp" + course_code.getText().toString());
                }
                if(i==-1){
                    Toast.makeText(Uploads.this,"Check In The Mode",Toast.LENGTH_SHORT).show();
                    return;
                }
                selectPDFfile();
            }
        });
    }


    private void selectPDFfile(){

        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent.createChooser(intent,"SELECT PDF FILE"),1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){

            uploadPDFFile(data.getData());

        }
    }

    private void uploadPDFFile(Uri data) {

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Uploading....");
        progressDialog.show();
        if(i==0) {
            reference = storageReference.child("qp" + course_code.getText().toString() + "/" + System.currentTimeMillis() + ".pdf");
        }
        if(i==1){
            reference = storageReference.child("rm" + course_code.getText().toString() + "/" + System.currentTimeMillis() + ".pdf");
        }
        reference.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Task<Uri> uri=taskSnapshot.getStorage().getDownloadUrl();
                        while(!uri.isComplete());

                            Uri url = uri.getResult();
                            uploaddd uploadpdf = new uploaddd(code.getText().toString(), url.toString());
                            databaseReference.child(databaseReference.push().getKey()).setValue(uploadpdf);
                            Toast.makeText(Uploads.this, "Upload Successful", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                double progress=(100.0* taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                progressDialog.setMessage("Uploaded: "+(int)progress+"%");
                /*course_code.setText("");
                pass.setText("");
                code.setText("");*/
            }
        });


    }
}
