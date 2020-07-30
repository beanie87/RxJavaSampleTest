package com.sumin.rxjavasample;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

// Rxjava1
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;

public class Rx1Activity extends AppCompatActivity {
    private TextView mTestTx;
    private EditText mEdit;
    private TextView mTestTx2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        //RxJava1
        // View init
        mEdit = findViewById(R.id.mEdit);
        mTestTx = findViewById(R.id.mTestTx);
        mTestTx2 = findViewById(R.id.mTestTx2);

        // EditText Event
        Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(final Subscriber<? super String> sub) {
                        mEdit.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {

                            }

                            @Override
                            public void afterTextChanged(final Editable s) {
                                sub.onNext(s.toString());
                            }
                        });
                    }
                }
        );

        Observer<String> mySubscriber = new Observer<String>() {
            @Override
            public void onNext(String s) {
                mTestTx.setText(s);
            }
            @Override
            public void onCompleted() {
                System.out.println("onComplete"); }
            @Override
            public void onError(Throwable e) {
                System.out.println(e.getMessage()); }
        };
        myObservable.subscribe(mySubscriber);

        /*Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext("Rx1 System out Message");
                        sub.onCompleted();
                    }
                }
        );

        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) { System.out.println(s); }
            @Override
            public void onCompleted() {
                System.out.println("onComplete"); }
            @Override
            public void onError(Throwable e) {
                System.out.println(e.getMessage()); }
        };
        myObservable.subscribe(mySubscriber);*/

        // Just
        Observable<String> justMyObservable =
                Observable.just("Just Rx1 System out Message");

        justMyObservable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                mTestTx2.setText(s);
            }
        });
    }
}
