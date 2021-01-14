package kp673.simpletodo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> items;

    Button addBtn;
    EditText edItem;
    RecyclerView view;
    ItemsAdapter itemsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addBtn = findViewById(R.id.addBtn);
        edItem = findViewById(R.id.edItem);
        view = findViewById(R.id.view);

        items= new ArrayList<>();
        items.add("Buy Milk");
        items.add("Go to Gym");
        items.add("Do my proj");


        ItemsAdapter.OnLongClickListener onLongClickListener = new ItemsAdapter.OnLongClickListener(){
            @Override
            public void onItemLongCLicked(int pos) {
                items.remove(pos);
                itemsAdapter.notifyItemRemoved(pos);
                Toast.makeText(getApplicationContext(), "Item was removed", Toast.LENGTH_SHORT).show();
            }
        };
        ItemsAdapter itemsAdapter= new ItemsAdapter(items, onLongClickListener);

        view.setAdapter(itemsAdapter);
        view.setLayoutManager(new LinearLayoutManager(this));

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem = edItem.getText().toString();
                items.add(newItem);
                itemsAdapter.notifyItemInserted(items.size()-1);
                edItem.setText("");
                Toast.makeText(getApplicationContext(), "Item was added", Toast.LENGTH_SHORT).show();
            }
        });
    }
}