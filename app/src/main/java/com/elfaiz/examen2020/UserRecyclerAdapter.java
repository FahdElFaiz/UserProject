package com.elfaiz.examen2020;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.elfaiz.examen2020.Interace.IItemClickListener;
import com.elfaiz.examen2020.model.User;

import java.util.List;

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.MyViewHolder> {



    private Context uContext;
    private List<User> userList;

    public UserRecyclerAdapter(Context uContext, List<User> userList) {
        this.uContext = uContext;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater layoutInflater=LayoutInflater.from(uContext);
        v=layoutInflater.inflate(R.layout.user_list_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserRecyclerAdapter.MyViewHolder holder, int position) {
        Glide.with(uContext).load("https://pbs.twimg.com/profile_images/563465245159473152/TNJBoyzo_400x400.jpeg").into(holder.img);
        holder.name.setText(userList.get(position).getName());
        holder.mail.setText(userList.get(position).getEmail());
        holder.num.setText(userList.get(position).getPhone());


        holder.setiItemClickListener(new IItemClickListener() { //IItemClickListner est créer au but de rajouter position au param de onClick (c une interface dans le package Interface)
            @Override
            public void onClick(View view, int position) {
               // Toast.makeText(uContext,"there has been a click on: "+userList.get(position).getName(),Toast.LENGTH_LONG ).show();
                Intent intent = new Intent(uContext, UserDetails.class);
                intent.putExtra("position", position);
                uContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img;
        TextView name;
        TextView mail;
        TextView num;

        IItemClickListener iItemClickListener;//rajouter pour le clique sur item

        public void setiItemClickListener(IItemClickListener iItemClickListener) {//rajouter pour le clique sur item
            this.iItemClickListener = iItemClickListener;//rajouter pour le clique sur item
        }//rajouter pour le clique sur item



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img=(ImageView) itemView.findViewById(R.id.img);
            name=(TextView) itemView.findViewById(R.id.name_txt);
            mail=(TextView) itemView.findViewById(R.id.mail_txt);
            num=(TextView) itemView.findViewById(R.id.num_txt);

            itemView.setOnClickListener(this);//rajouter pour le clique sur item
        }

        @Override
        public void onClick(View v) {
            iItemClickListener.onClick(v,getAdapterPosition());//rajouter pour le clique sur item
        }
    }
}
