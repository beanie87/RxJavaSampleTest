package com.sumin.rxjavasample;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

public class Rx3Activity extends AppCompatActivity {
    private TextView mTestTx;
    private EditText mEdit;
    private TextView mTestTx2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx3);

        mTestTx = findViewById(R.id.mTestTx);
        mEdit = findViewById(R.id.mEdit);
        mTestTx2 = findViewById(R.id.mTestTx2);

        Observable<String> myObservable = Observable.create(
                new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(final @NonNull ObservableEmitter<String> emitter) throws Throwable {
                        mEdit.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {

                            }

                            @Override
                            public void afterTextChanged(final Editable s) {
                                emitter.onNext(s.toString());
                            }
                        });
                    }
        });

        Observer<String> sub = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("isDisposed : " + d.isDisposed());
            }

            @Override
            public void onNext(String s) {
                mTestTx.setText(s);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };
        myObservable.subscribe(sub);

        Observable<String> justMyObservable =
                Observable.just("Just Rx3 System out Message");

        justMyObservable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                mTestTx2.setText(s);
            }
        });
    }
}
