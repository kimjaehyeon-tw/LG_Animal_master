package com.example.lg_animal.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lg_animal.R;

import java.util.ArrayList;

public class PetInfoAdapter extends RecyclerView.Adapter<PetInfoAdapter.MyHolder> {
    ArrayList<String> myData = null;

    public PetInfoAdapter(ArrayList<String> myData) {
        this.myData = myData;
    }

    @NonNull
    @Override
    public PetInfoAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_pet_info, parent, false);
        PetInfoAdapter.MyHolder holder = new PetInfoAdapter.MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PetInfoAdapter.MyHolder holder, int position) {
        holder.petName.setText("이름 : 거부기와 두루미" + position);
        holder.petSex.setText("성별 : 남" + position + "  ");
        holder.petBirth.setText("생일 : 2020. 10. 13 + " + position);
    }

    @Override
    public int getItemCount() {
        return myData.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        ImageView petImage;
        TextView petName;
        TextView petSex;
        TextView petBirth;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            petImage = itemView.findViewById(R.id.pet_image);
            petName = itemView.findViewById(R.id.pet_name);
            petSex = itemView.findViewById(R.id.pet_sex);
            petBirth = itemView.findViewById(R.id.pet_birth);
        }
    }
}