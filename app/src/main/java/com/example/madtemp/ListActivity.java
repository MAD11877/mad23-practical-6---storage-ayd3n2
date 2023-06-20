package com.example.madtemp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    Boolean f;
    int id;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);

        User person1 = new User("Ben","1231235451",1590762919,true);
        User person2 = new User("John","1231235451",751273111,true);
        User person3 = new User("Pat","1231235451",291810079,true);
        User person4 = new User("Mandy","1231235451",888585057,true);
        User person5 = new User("Sarah","1231235451",1101588044,true);
        User person6 = new User("Ren","1231235451",420942097,true);
        User person7 = new User("Dan","1231235451",345123945,false);
        User person8 = new User("Ethan","1231235451",321039443,false);
        User person9 = new User("Sam","1231235451",924921807,false);
        User person10 = new User("Keith","1231235451",90450832,false);
        User person11 = new User("Raj","1231235451",239020381,true);
        User person12 = new User("Clara","1231235451",192010543,false);
        User person13 = new User("Bill","1231235451",60931031,true);
        User person14 = new User("Gin","1231235451",813902930,true);
        User person15 = new User("Karl","1231235451",523019084,false);
        User person16 = new User("Chris","1231235451",703482042,false);
        User person17 = new User("Jimmy","1231235451",510293103,true);
        User person18 = new User("Rin","1231235451",301408204,false);
        User person19 = new User("Tom","1231235451",23019231,true);
        User person20 = new User("Arthur","1231235451",403810312,false);
        dbHandler.addUser(person1);
        dbHandler.addUser(person2);
        dbHandler.addUser(person3);
        dbHandler.addUser(person4);
        dbHandler.addUser(person5);
        dbHandler.addUser(person6);
        dbHandler.addUser(person7);
        dbHandler.addUser(person8);
        dbHandler.addUser(person9);
        dbHandler.addUser(person10);
        dbHandler.addUser(person11);
        dbHandler.addUser(person12);
        dbHandler.addUser(person13);
        dbHandler.addUser(person14);
        dbHandler.addUser(person15);
        dbHandler.addUser(person16);
        dbHandler.addUser(person17);
        dbHandler.addUser(person18);
        dbHandler.addUser(person19);
        dbHandler.addUser(person20);




        ArrayList<User> userList = dbHandler.getUser();

        RecyclerView rv = findViewById(R.id.recycleview);
        BrandsAdapter mAdapter = new BrandsAdapter(userList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(mLayoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(mAdapter);
    }

    public class BrandViewHolder extends RecyclerView.ViewHolder {
        TextView nametxt;
        TextView destxt;
        ImageView imagep;
        public BrandViewHolder(View itemView) {
            super(itemView);
            AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
            builder.setTitle("Profile");
            builder.setMessage("NameID");
            builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Bundle extras = new Bundle();
                    extras.putString("uname",nametxt.getText().toString());
                    extras.putString("des",destxt.getText().toString());
                    extras.putBoolean("fol",f);
                    extras.putInt("id",id);
                    String mes = Boolean.toString(f);
                    Toast.makeText(getApplicationContext(), mes, Toast.LENGTH_SHORT).show();
                    Intent activityName = new Intent(ListActivity.this, MainActivity.class);
                    activityName.putExtras(extras);
                    startActivity(activityName);
                }
            });
            builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            nametxt = itemView.findViewById(R.id.textView4);
            destxt = itemView.findViewById(R.id.textView5);


            imagep = itemView.findViewById((R.id.imageView2));
            imagep.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        }
    }

    public class BrandsAdapter extends RecyclerView.Adapter<BrandViewHolder> {
        ArrayList<User> data;
        int lastdigit;

        public BrandsAdapter(ArrayList<User> input){
            data = input;
        }

        @Override
        public int getItemViewType(int position) { // our logic to decide what view to use
            User u = data.get(position);
            lastdigit = u.getId() % 10;
            return lastdigit;
        }
        public BrandViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View item;
            if (viewType == 7){ // viewType is a value returned by getItemViewType above
                item = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.digit7,
                        parent,
                        false);
            }
            else{
                item = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.userrow,
                        parent,
                        false);
            }
            return new BrandViewHolder(item);

        }
        public void onBindViewHolder(BrandViewHolder holder, int position){
//            User u = data.get(position);
//            holder.nametxt.setText(u.getName() + " " +  u.getId());
//            holder.destxt.setText(u.getDescription());
//            f = u.getFollowed();
//            lastdigit = u.getId() % 10;

            User u = data.get(position);
            switch (holder.getItemViewType()) {
                case 7:
                    BrandViewHolder viewHolder0 = (BrandViewHolder)holder;
                    viewHolder0.nametxt.setText(u.getName() + " " +  u.getId());
                    viewHolder0.destxt.setText(u.getDescription());
                    f = u.getFollowed();
                    id = u.getId();
                    name = u.getName();
                    break;

                default:
                    BrandViewHolder viewHolder2 = (BrandViewHolder)holder;
                    viewHolder2.nametxt.setText(u.getName() + " " +  u.getId());
                    viewHolder2.destxt.setText(u.getDescription());
                    f = u.getFollowed();
                    id = u.getId();
                    name = u.getName();
                    break;
            }
        }
        public int getItemCount(){
            return data.size();
        }

    }
}


