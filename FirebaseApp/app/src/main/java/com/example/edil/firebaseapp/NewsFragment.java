package com.example.edil.firebaseapp;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.android.volley.VolleyLog.TAG;
import static com.android.volley.VolleyLog.d;
import static com.android.volley.VolleyLog.v;

public class NewsFragment extends Fragment {

    public NewsFragment(){
        
    }

//    private TextView getMessage;
//    private String data;
    ListView newsList;
    String[] title;
    String[] content;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference docRef = db.collection("Rooms").document("1");
    CollectionReference colRef = db.collection("users");



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news,container,false);
        Context context =getActivity();

        newsList = (ListView) view.findViewById(R.id.newslist);
        Resources resources = getResources();
        title = resources.getStringArray(R.array.news_title);
        content = resources.getStringArray(R.array.news_conteng);
        NewsAdapter newsAdapter =new NewsAdapter(context,title,content);
        newsList.setAdapter(newsAdapter);
//                getMessage = (TextView) view.findViewById(R.id.newsView);
//
//        db.collection("Rooms")
//                .whereEqualTo("id", 1)
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                getMessage.setText(document.get("description").toString());
//                                Log.d(TAG, document.getId() + " => " + document.getData());
//                            }
//                        } else {
//                            Log.d(TAG, "Error getting documents: ", task.getException());
//                        }
//                    }
//                });



        return view;
    }

}
