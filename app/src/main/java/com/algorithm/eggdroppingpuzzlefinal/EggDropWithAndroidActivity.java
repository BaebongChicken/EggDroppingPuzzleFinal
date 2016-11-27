package com.algorithm.eggdroppingpuzzlefinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EggDropWithAndroidActivity extends AppCompatActivity {
    int trials[][];
    int n, k;
    int i, j, x, p;
    int result;
    int coef[];
    int formulaArray[][][];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egg_drop_with_android);

        bindViews();
        trials = new int[n + 1][k + 1];
        formulaArray = new int[n + 1][k + 1][4];
    }

    void bindViews() {
        coef = new int[4];

    }
}
