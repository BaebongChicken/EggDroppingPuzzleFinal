package com.algorithm.eggdroppingpuzzlefinal;


import java.util.Scanner;

/**
 * Created by JinHee on 2016-11-26.
 */

public class Main {
    /* Driver program to test to pront printDups*/
    public static void main(String args[]) {

        int n = 1, k = 1;
        int r = 0;
        while (n > 0 && k > 0) {
            System.out.println("달걀의 갯수 입력 : ");
            Scanner sc = new Scanner(System.in);
            n = sc.nextInt();
            System.out.println("건물의 층수 입력 : ");
            k = sc.nextInt();
            System.out.println("Binary Search 만을 활용한 경우와 비교 하시겠습니까? Yes : 1 No : 0");
            r = sc.nextInt();
            if (n <= 0 || k <= 0) {
                System.out.println("잘못 된 입력입니다(n과 k는 양수의 정수만 가능)");
                n = 1;
                k = 1;
            } else {
                System.out.println(n + "개의 달걀을 가지고 " +
                        k + "층 건물에서 달걀이 깨지지 않는 가장 높은 층을 찾기 위한\n" +
                        "최악의 경우에서의 최소한의 시도 횟수 : "
                        + EggDropDynamicProgramming.EggDropDynamicProgramming(n, k));
                System.out.println("DP를 이용 했을 때, 계산 횟수(Computaion Time) :"
                        + EggDropDynamicProgramming.COMP_TIME_DP);
                EggDropDynamicProgramming.COMP_TIME_DP = 0;
                if (r == 1) {
                    System.out.println("********************************************************");
                    System.out.println(n + "개의 달걀을 가지고 " +
                            k + "층 건물에서 달걀이 깨지지 않는 가장 높은 층을 찾기 위한\n" +
                            "최악의 경우에서의 최소한의 시도 횟수 : "
                            + EggDropRecursion.EggDropRecursion(n, k));
                    System.out.println("BinarySearch만를 이용 했을 때, 계산 횟수(Computaion Time) :"
                            + EggDropRecursion.COMP_TIME_RECURSION);
                    EggDropRecursion.COMP_TIME_RECURSION = 0;

                }
            }
        }

    }
}
