package com.example.edil.firebaseapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class RoomsFragment extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference docRef = db.collection("Rooms").document("1");

    String[] names;
    String[] prices;
    String[] descriptions;


    public RoomsFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_menu,container,false);

        final Context context =getActivity();
        ListView roomlist = (ListView) view.findViewById(R.id.listViewMain);

        Resources res = getResources();
        names = res.getStringArray(R.array.names);
        prices = res.getStringArray(R.array.capacity);
        descriptions = res.getStringArray(R.array.locations);


        RoomAdapter adapter = new RoomAdapter(context,names,prices,descriptions);
        roomlist.setAdapter(adapter);

//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if (document.exists()) {
//                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
//                    } else {
//                        Log.d(TAG, "No such document");
//                    }
//                } else {
//                    Log.d(TAG, "get failed with ", task.getException());
//                }
//            }
//        });

        roomlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showDetailIntent = new Intent(context,QRActivity.class);
                showDetailIntent.putExtra("name",names[position]);
                startActivity(showDetailIntent);
            }
        });

        return view;
    }
}
