package com.elfaiz.examen2020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.elfaiz.examen2020.model.User;

import java.util.List;

public class UserDetails extends AppCompatActivity {
    Toolbar toolbar;
    ImageView img;
    TextView numTxt,adressTxt,nameTxt;
    Button linkedinBtn,callBtn,mapBtn;

    List<User> userList=MainActivity.fahd;//la liste des users venant de mainactivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);



        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);


        img=(ImageView) findViewById(R.id.img2);
        nameTxt=(TextView) findViewById(R.id.name);
        numTxt=(TextView) findViewById(R.id.tel_txt2);
        adressTxt=(TextView) findViewById(R.id.adr_txt2);
        linkedinBtn=(Button) findViewById(R.id.linkedin_btn);
        callBtn=(Button) findViewById(R.id.apl_btn);
        mapBtn=(Button) findViewById(R.id.adr_btn);
        toolbar=(Toolbar) findViewById(R.id.toolbar3);
        toolbar.setTitle("Details");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //en plus de ça, il faut aller sur manifest pour mentionner l'activité parent
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Glide.with(this).load("https://pbs.twimg.com/profile_images/563465245159473152/TNJBoyzo_400x400.jpeg").into(this.img);

        final User user= userList.get(position);

        nameTxt.setText(user.getName());
        numTxt.setText(user.getPhone());
        adressTxt.setText(user.getAddress().getCity());

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent call=new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:"+user.getPhone()));
                startActivity(call);
            }
        });


        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent map=new Intent(Intent.ACTION_VIEW);
                map.setData(Uri.parse("geo:"+user.getAddress().getGeo().getLng()+","+user.getAddress().getGeo().getLat()));
                startActivity(map);
            }
        });

        linkedinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent linkedin = new Intent(Intent.ACTION_VIEW);
                linkedin.setData(Uri.parse("https://www.linkedin.com/in/fahd-el-faiz-a57821151/"));
                startActivity(linkedin);
            }
        });

    }


}