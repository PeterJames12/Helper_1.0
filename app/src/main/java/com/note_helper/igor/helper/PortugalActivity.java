package com.note_helper.igor.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author Igor Gnes on 25.02.17.
 */

public class PortugalActivity extends AppCompatActivity {


    private TextView textPortugalView;
    private Button button;
    private List<String> portugalWords;
    private Random random;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_portugues_words);

        textPortugalView = (TextView) findViewById(R.id.text_portugal_words);
        button = (Button) findViewById(R.id.btn_portugal_words);

        random = new Random();
        portugalWords = new LinkedList<>();
        initializeCollections();
    }

    private void initializeCollections() {
        portugalWords.add("Sentir");
        portugalWords.add("Andar");
        portugalWords.add("Pão");
        portugalWords.add("Cabeça");
        portugalWords.add("Cabalo");
        portugalWords.add("Ter de");
        portugalWords.add("Ну і слова тут");
    }

    public void generatePortugal(View view) {
        textPortugalView.setText(portugalWords.get(random.nextInt(portugalWords.size())));
    }
}
