package com.harryh5n1.vitqp;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class refdownload extends AppCompatActivity {
    private StorageReference mStorageRef;
    FirebaseStorage storage;
    ListView listView;
    TextView course_code;
    String coursecode;
    Button hp1;
    DatabaseReference databaseReference;
    List<uploaddd> arrayLists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refdownload);
        Bundle bundle=getIntent().getExtras();
        coursecode=bundle.getString("coursecode");

        listView=(ListView)findViewById(R.id.listView);
        arrayLists=new ArrayList<uploaddd>();
        course_code=(TextView)findViewById(R.id.course_code);
        Viewallfiles();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                uploaddd upload=arrayLists.get(position);

                Intent intent=new Intent();
                intent.setType(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(upload.getUrl()));
                startActivity(intent);
            }
        });

        hp1=(Button)findViewById(R.id.button5);

        hp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(refdownload.this,Reference.class);
                startActivity(intent);
                finish();
            }
        });



       /* storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference pathReference = storageRef.child("english.pdf");
        StorageReference gsReference = storage.getReferenceFromUrl("gs://vitqp-f0077.appspot.com/english.pdf");
        StorageReference httpsReference = storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/vitqp-f0077.appspot.com/o/english.pdf?alt=media&token=93980fba-57bf-47f2-8e19-c8d02cecc5da");

        File localFile = null;
        try {
            localFile = File.createTempFile("english", "pdf");
            storageRef.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(Downloads.this,"Successful",Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Toast.makeText(Downloads.this,"Failed"+exception,Toast.LENGTH_LONG).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    private void Viewallfiles() {

        databaseReference=FirebaseDatabase.getInstance().getReference(coursecode);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    uploaddd upload =postSnapshot.getValue(com.harryh5n1.vitqp.uploaddd.class);
                    arrayLists.add(upload);
                }

                String[] uploads=new String[arrayLists.size()];

                for(int i=0;i<uploads.length;i++){
                    uploads[i]=arrayLists.get(i).getName();
                }

                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,uploads){

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {

                        View view=super.getView(position,convertView,parent);

                        TextView mytext=(TextView)view.findViewById(android.R.id.text1);
                        mytext.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);
                        //mytext.setTypeface(mTypeface);
                        mytext.setTextColor(Color.WHITE);


                        return view;
                    }
                };

                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
