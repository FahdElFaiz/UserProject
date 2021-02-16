package com.elfaiz.examen2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.elfaiz.examen2020.model.User;
import com.elfaiz.examen2020.retrofit.IUser;
import com.elfaiz.examen2020.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    List<User> userList;

    static List<User> fahd;//la liste des users que je vais envoyer un l'activité des détails



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView) findViewById(R.id.user_list_recyclerview);
        userList=new ArrayList<>();
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("ING 3                                                   click");
        setSupportActionBar(toolbar);


        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "the person who created that whole 9lawi is me",Toast.LENGTH_LONG ).show();
            }
        });

        Retrofit retrofit= RetrofitClient.getInstace();
        IUser userAPI=retrofit.create(IUser.class);

        Call<List<User>> call=userAPI.getListUser();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.code()!=200){
                    return;
                }

                List<User> users=response.body();

                for(User u : users){

                    userList.add(u);

                }


                fahd=userList; //remplissage de la liste à envoyer

                PutDataIntoRecyclerView(userList);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });

    }



    private void PutDataIntoRecyclerView(List<User> a){
        UserRecyclerAdapter userRecyclerAdapter=new UserRecyclerAdapter(this,a);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //si c'est diviser en deux comme le cas de l'appli pokemon, il faut un grid view avec le nombre de colonne
        recyclerView.setAdapter(userRecyclerAdapter);
    }
}