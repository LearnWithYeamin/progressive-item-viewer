package com.myeamin.progressiveitemviewer;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private ArrayList<HashMap<String, String>> arrayList;
    private DatabaseHelper dbHelper;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        editor = sharedPreferences.edit();

        boolean isFirstLaunch = sharedPreferences.getBoolean("FIRST_LAUNCH", true);
        if (isFirstLaunch) {
            insertItems(); // Insert data if this is the first launch

            // Update SharedPreferences to mark that the initial data has been inserted
            editor.putBoolean("FIRST_LAUNCH", false);
            editor.apply();
        }
        loadItems();


    }

    private void insertItems() {
        dbHelper.insertItem("Text1 - Item 1", "Text2 - Item 1");
        dbHelper.insertItem("Text1 - Item 2", "Text2 - Item 2");
        dbHelper.insertItem("Text1 - Item 3", "Text2 - Item 3");
        dbHelper.insertItem("Text1 - Item 4", "Text2 - Item 4");
        dbHelper.insertItem("Text1 - Item 5", "Text2 - Item 5");
        dbHelper.insertItem("Text1 - Item 6", "Text2 - Item 6");
        dbHelper.insertItem("Text1 - Item 7", "Text2 - Item 7");
        dbHelper.insertItem("Text1 - Item 8", "Text2 - Item 8");
        dbHelper.insertItem("Text1 - Item 9", "Text2 - Item 9");
        dbHelper.insertItem("Text1 - Item 10", "Text2 - Item 10");
        dbHelper.insertItem("Text1 - Item 11", "Text2 - Item 11");
        dbHelper.insertItem("Text1 - Item 12", "Text2 - Item 12");
        dbHelper.insertItem("Text1 - Item 13", "Text2 - Item 13");
        dbHelper.insertItem("Text1 - Item 14", "Text2 - Item 14");
        dbHelper.insertItem("Text1 - Item 15", "Text2 - Item 15");
        dbHelper.insertItem("Text1 - Item 16", "Text2 - Item 16");
        dbHelper.insertItem("Text1 - Item 17", "Text2 - Item 17");
        dbHelper.insertItem("Text1 - Item 18", "Text2 - Item 18");
        dbHelper.insertItem("Text1 - Item 19", "Text2 - Item 19");
        dbHelper.insertItem("Text1 - Item 20", "Text2 - Item 20");
        dbHelper.insertItem("Text1 - Item 21", "Text2 - Item 21");
        dbHelper.insertItem("Text1 - Item 22", "Text2 - Item 22");
        dbHelper.insertItem("Text1 - Item 23", "Text2 - Item 23");
        dbHelper.insertItem("Text1 - Item 24", "Text2 - Item 24");
        dbHelper.insertItem("Text1 - Item 25", "Text2 - Item 25");
        dbHelper.insertItem("Text1 - Item 26", "Text2 - Item 26");
        dbHelper.insertItem("Text1 - Item 27", "Text2 - Item 27");
        dbHelper.insertItem("Text1 - Item 28", "Text2 - Item 28");
        dbHelper.insertItem("Text1 - Item 29", "Text2 - Item 29");
        dbHelper.insertItem("Text1 - Item 30", "Text2 - Item 30");
        dbHelper.insertItem("Text1 - Item 31", "Text2 - Item 31");
        dbHelper.insertItem("Text1 - Item 32", "Text2 - Item 32");
        dbHelper.insertItem("Text1 - Item 33", "Text2 - Item 33");
        dbHelper.insertItem("Text1 - Item 34", "Text2 - Item 34");
        dbHelper.insertItem("Text1 - Item 35", "Text2 - Item 35");
        dbHelper.insertItem("Text1 - Item 36", "Text2 - Item 36");
        dbHelper.insertItem("Text1 - Item 37", "Text2 - Item 37");
        dbHelper.insertItem("Text1 - Item 38", "Text2 - Item 38");
        dbHelper.insertItem("Text1 - Item 39", "Text2 - Item 39");
        dbHelper.insertItem("Text1 - Item 40", "Text2 - Item 40");
        dbHelper.insertItem("Text1 - Item 41", "Text2 - Item 41");
        dbHelper.insertItem("Text1 - Item 42", "Text2 - Item 42");
        dbHelper.insertItem("Text1 - Item 43", "Text2 - Item 43");
        dbHelper.insertItem("Text1 - Item 44", "Text2 - Item 44");
        dbHelper.insertItem("Text1 - Item 45", "Text2 - Item 45");
    }

    private void loadItems() {

        int lastIndex = sharedPreferences.getInt("KEY_LAST_INDEX", 0); // Get last index from SharedPreferences
        int limit = 10; // Number of items to load

        // Calculate total item count
        int totalItemCount = dbHelper.getTotalItemCount();

        // Check if lastIndex exceeds total item count and reset it if necessary
        if (lastIndex >= totalItemCount) {
            lastIndex = 0; // Reset to the beginning
        }

        arrayList = dbHelper.getItems(lastIndex, limit);
        adapter = new ItemAdapter(arrayList);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        // Update lastIndex for the next load
        editor.putInt("KEY_LAST_INDEX", (lastIndex + limit) % totalItemCount); // Use modulo to wrap around
        editor.apply();
    }


    public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
        private ArrayList<HashMap<String, String>> arrayList;

        public ItemAdapter(ArrayList<HashMap<String, String>> arrayList) {
            this.arrayList = arrayList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            HashMap<String, String> mHashMap = arrayList.get(position);
            String id = mHashMap.get("id");
            String text1 = mHashMap.get("text1");
            String text2 = mHashMap.get("text2");

            holder.textViewID.setText("ID: " + id);
            holder.textView1.setText(text1);
            holder.textView2.setText(text2);
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView textViewID;
            private TextView textView1;
            private TextView textView2;

            public ViewHolder(View itemView) {
                super(itemView);
                textViewID = itemView.findViewById(R.id.item_text_view_id);
                textView1 = itemView.findViewById(R.id.item_text_view_1);
                textView2 = itemView.findViewById(R.id.item_text_view_2);
            }
        }
    }

}
