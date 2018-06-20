package com.example.kevinlay.rxjavabasicexamples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kevinlay.rxjavabasicexamples.observables.CompletableClass;
import com.example.kevinlay.rxjavabasicexamples.observables.MaybeClass;
import com.example.kevinlay.rxjavabasicexamples.observables.SingleClass;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Spinner spinner;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.rxSpinner);

        button = findViewById(R.id.goButton);
        button.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.rx_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {

    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        switch (adapterView.getItemAtPosition(i).toString()) {
            case "Single":
                SingleClass singleClass = new SingleClass();
                singleClass.getInfo().subscribe(this::showToast);
                break;
            case "Completable":
                CompletableClass completableClass = new CompletableClass();
                completableClass.getInfo()
                        .subscribe( () -> showToast("CompletableDone"),
                                    e -> showToast(e.toString()));
                break;
            case "Maybe":
                MaybeClass maybeClass = new MaybeClass("kevin2201001");
                maybeClass.getInfo().subscribe( name -> showToast(name + "done"),
                                                error -> showToast(error.toString()));
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
