package com.sumin.rxjavasample;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//RxJava2
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class Rx2Activity extends AppCompatActivity {
    private TextView mTestTx;
    private EditText mEdit;
    private TextView mTestTx2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx2);
        //RxJava2
        // View init
        mEdit = findViewById(R.id.mEdit);
        mTestTx = findViewById(R.id.mTestTx);
        mTestTx2 = findViewById(R.id.mTestTx2);

        // EditText Event
        Observable<String> myObservable = Observable.create(
                new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
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
                }
        );

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("isDisposed : " + d.isDisposed());
            }

            @Override
            public void onNext(String s) {
                mTestTx.setText(s);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };
        myObservable.subscribe(observer);

/*
        Observable<String> myObservable = Observable.create(
                new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                        emitter.onNext("Rx2 System out Message");
                        emitter.onComplete();
                    }
                }
        );

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("isDisposed : " + d.isDisposed());
            }

            @Override
            public void onNext(String s) {
                mTestTx.setText(s);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };
        myObservable.subscribe(observer);*/

        // Just
        Observable<String> justMyObservable =
                Observable.just("Just Rx2 System out Message");

        justMyObservable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) {
                mTestTx2.setText(s);
            }
        });
    }
}
