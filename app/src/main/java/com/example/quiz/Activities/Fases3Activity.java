package com.example.quiz.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.quiz.Adapters.SetAdapter3;
import com.example.quiz.MainActivity;
import com.example.quiz.Models.SetModel;
import com.example.quiz.R;
import com.example.quiz.databinding.ActivityFases3Binding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

//Fases CSS
public class Fases3Activity extends AppCompatActivity {

    ActivityFases3Binding binding;
    ArrayList<SetModel> list;
    ImageView ic_voltar;
    private FirebaseFirestore firebaseFirestore;
    private List<DocumentSnapshot> listDocuments = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseFirestore = FirebaseFirestore.getInstance();
        binding = ActivityFases3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        list = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.setsRecycler.setLayoutManager(linearLayoutManager);

        list.add(new SetModel("FASE 1"));
        list.add(new SetModel("FASE 2"));
        list.add(new SetModel("FASE 3"));
        list.add(new SetModel("FASE 4"));

        ic_voltar = findViewById(R.id.ic_voltar);
        ic_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Fases3Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        buscarDocumento(new OnCompleteListener<Boolean>() {
            @Override
            public void onComplete(@NonNull Task<Boolean> task) {
                if (task.getResult()) {
                    SetAdapter3 adapter = new SetAdapter3(Fases3Activity.this, list, listDocuments);
                    binding.setsRecycler.setAdapter(adapter);
                }
            }
        });

    }

    private void buscarDocumento(OnCompleteListener<Boolean> listener) {
        firebaseFirestore.collection("Css").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                QuerySnapshot querySnapshot = task.getResult();
                listDocuments = querySnapshot.getDocuments();
                listener.onComplete(Tasks.forResult(true));
            }
        });
    }

}