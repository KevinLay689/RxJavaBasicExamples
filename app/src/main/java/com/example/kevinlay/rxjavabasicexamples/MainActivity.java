package com.example.kevinlay.rxjavabasicexamples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.kevinlay.rxjavabasicexamples.networking.github.GithubModel;
import com.example.kevinlay.rxjavabasicexamples.networking.github.GithubNetwork;
import com.example.kevinlay.rxjavabasicexamples.observables.CompletableClass;
import com.example.kevinlay.rxjavabasicexamples.observables.MaybeClass;
import com.example.kevinlay.rxjavabasicexamples.observables.SingleClass;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

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

    private void log(String msg) {
        Log.i("RxJavaStuff", msg);
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

            case "RxJava":
                GithubNetwork githubNetwork = new GithubNetwork();
                CompositeDisposable disposable = new CompositeDisposable();
                disposable.add(githubNetwork.getInfo("kevinlay689")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(items -> {
                                showToast("Number of repos: " + items.size());
                                for(GithubModel user: items) {
                                    log(user.getName() + "id is:" + user.getId());
                                }
                            }, error -> log(error.toString())));
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
