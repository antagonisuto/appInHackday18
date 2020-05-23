//package com.example.edil.firebaseapp;
//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.squareup.picasso.Picasso;
//
//
//public class InformationAboutRoom extends AppCompatActivity {
//    ViewHolder viewHolder = new ViewHolder();
//
//    @SuppressLint("WrongViewCast")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout_marginBottom.activity_information_about_room);
//        String tags = getIntent().getStringExtra("tags");
//        // name of the picture
//        loadImageFromUrl(tags);
//    }
//
//    private void loadImageFromUrl(String tags) {
//        Picasso.with(this).load(tags).placeholder(R.mipmap.ic_launcher) // option
//                .error(R.mipmap.ic_launcher)  //error
//                .into(viewHolder.networkImageView, new com.squareup.picasso.Callback() {
//                            @Override
//                            public void onSuccess() {
//                            }
//
//                            @Override
//                            public void onError() {
//                            }
//                        }
//                );
//    }
//    //put photo from API
//
//    //casting and objects
//    private class ViewHolder{
//        TextView starNumber,nameOfRoom,location,price;
//        ImageView networkImageView;
//    }
//    private void cassting() {
//        viewHolder.starNumber=  findViewById(R.id.Rstar);
//        viewHolder.nameOfRoom=  findViewById(R.id.NameOfConfRoom);
//        viewHolder.location = findViewById(R.id.locationConfRoom);
//        viewHolder.price = findViewById(R.id.pricePerHour);
//    }
//
//}
