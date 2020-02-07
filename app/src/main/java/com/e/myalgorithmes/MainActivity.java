package com.e.myalgorithmes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tree).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("algo", "test");
                AVL_Tree tree = new AVL_Tree();
                AVL_Tree.Node root = tree.insert(null, 5);
                root = tree.insert(root, 1);
                root = tree.insert(root, 3);
                root = tree.insert(root, 7);
                root = tree.insert(root, 9);
                root = tree.insert(root, 34);
                root = tree.insert(root, 12);
                root = tree.insert(root, 15);
                root = tree.insert(root, 11);
                root = tree.insert(root, 41);
                root = tree.insert(root, 42);
                root = tree.insert(root, 43);
                root = tree.insert(root, 44);
                root = tree.insert(root, 45);

                Log.d("algo", tree.toString(root));
            }
        });

        findViewById(R.id.heap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MinHeap heap = new MinHeap();
                heap.insert(6);
                heap.insert(3);
                heap.insert(1);
                heap.insert(9);
                heap.insert(5);
                heap.insert(2);
                heap.insert(8);

                Log.d("algo", heap.toString());

            }
        });

    }
}
