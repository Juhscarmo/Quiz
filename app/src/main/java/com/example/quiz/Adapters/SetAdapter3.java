package com.example.quiz.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quiz.Activities.Pontuacao3Activity;
import com.example.quiz.Activities.Questoes3Activity;
import com.example.quiz.Models.SetModel;
import com.example.quiz.R;
import com.example.quiz.databinding.ItemFasesBinding;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

//Adapter CSS
public class SetAdapter3 extends RecyclerView.Adapter<SetAdapter3.viewHolder> {

    Context context;
    ArrayList<SetModel> list;
    List<DocumentSnapshot> listDocuments;

    public SetAdapter3(Context context, ArrayList<SetModel> list, List<DocumentSnapshot> listDocuments) {
        this.context = context;
        this.list = list;
        this.listDocuments = listDocuments;
    }

    //Criando e inflando o layout de item para cada item
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fases, parent, false);
        return new viewHolder(view);
    }

    //Recebe um viewHolder e uma posição como parâmetro
    //Recupera o objeto SetModel à posição correspondente e define o texto do TextView
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final SetModel model = list.get(position);
        holder.binding.setFase.setText(model.getSetNome());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(DocumentSnapshot dc: listDocuments){
                    if(model.getSetNome().toLowerCase().equals(dc.getId().toLowerCase())){
                        if(dc.getBoolean("status")){
                            //Se já jogou, mostrará a pontuação
                            Intent intent = new Intent(context, Pontuacao3Activity.class);
                            intent.putExtra("total", Integer.parseInt(String.valueOf(dc.getLong("total"))));
                            intent.putExtra("fase3", model.getSetNome());
                            intent.putExtra("pontos", Integer.parseInt(String.valueOf(dc.getLong("respCertas"))));
                            context.startActivity(intent);
                        }else {
                            Intent intent = new Intent(context, Questoes3Activity.class);
                            intent.putExtra("fase3", model.getSetNome());
                            context.startActivity(intent);
                        }
                    }
                }
            }
        });
    }

    //Retorna o número total de itens na lista
    @Override
    public int getItemCount() {
        return list.size();
    }

    //Classe para representar e gerenciar a exibição de cada item
    public class viewHolder extends RecyclerView.ViewHolder {
        ItemFasesBinding binding;

        //Acessando os elementos do layout
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemFasesBinding.bind(itemView);
        }
    }

}
