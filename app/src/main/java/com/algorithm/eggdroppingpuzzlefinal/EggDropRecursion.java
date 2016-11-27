package com.algorithm.eggdroppingpuzzlefinal;

/**
 * Created by JinHee on 2016-11-26.
 */

public class EggDropRecursion {

    static int COMP_TIME_RECURSION=0;
    static int EggDropRecursion(int n, int k) {
        int x;
        if (n == 1) return k;
        if (k == 0) return 0;
        if (k == 1) return 1;

        int minimum = Integer.MAX_VALUE;
        //문제에 대한 풀이 방식은 기존과 동일하나, 축소 된 문제의 결과를 따로 메모리에 저장하지 않는다.
        for (x = 1; x <= k; x++) {
            COMP_TIME_RECURSION++;
            System.out.println("현재 trials : trials[" + n + "][" + k + "] = 1 + Max(trials[" + (n - 1) + "][" + (k - 1) + "], trials[" + n + "][" + (k - x) + "])");
            minimum = Math.min(minimum, (1 + Math.max(EggDropRecursion(n - 1, k - 1), EggDropRecursion(n, k - x))));
        }


        return minimum;
    }
}

