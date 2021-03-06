package com.algorithm.eggdroppingpuzzlefinal;

import java.util.Scanner;

/**
 * Created by Jin Hee Lee on 2016-11-24.
 */


public class EggDropDynamicProgramming {

    static int COMP_TIME_DP = 0;

    //n은 달걀의 수, k는 건물의 층 수 이다.
    static int max(int a, int b) {

        return (a > b) ? a : b;
    }

    static int EggDropDynamicProgramming(int n, int k) {
        /*
         * @ trials[n][k]
         *  n개의 계란으로 k층인 건물에서 낙하를 시도했을때
         *  최악의 경우 일때, 가장 적은 시도로 답을 찾아 낼 때, 그 시도의 횟수.
         *
         * @ nextPositionArr[n][k][]
         *  trials[n][k]를 계산 하는 과정에서
         *  nextPositionArr[n][k][0 또는 1] 은 각각
         *  Max 연산 시, 선택 될 다음 trials[n'][k']의
         *  축소 될 문제의 달걀 수 n' /축소 될 문제의 건물 층 k'
         *  nextPositionArr[n][k][2 또는 3] 은
         *  Max 연산 시, 선택 되지 못한 trials이다.
         */
        int trials[][] = new int[n + 1][k + 1];
        int i, j, x, p;
        int mResult;
        int mNextPosition[] = new int[4];
        int nextPositionArr[][][] = new int[n + 1][k + 1][4];

        /*
         * 건물의 층수 k가 1 이라면
         * 단 한번의 시도만 하면 될것이고
         * 건물의 층수 k가 0 이라면
         * 0번의 시도만 하면 될 것이다.
         * 이는 특이 케이스 이기 때문에 미리 배열에 저장 해둔다.
         */
        for (i = 1; i <= n; i++) {
            trials[i][1] = 1;
            trials[i][0] = 0;
        }

        /*
         * 달걀의 갯수 n이 1개라면
         * 그땐 건물의 층수 k까지 1부터 낙하 해야하므로
         * 시도 횟수는 건물의 층수 k와 같다
         */
        for (j = 1; j <= k; j++) {
            trials[1][j] = j;
            nextPositionArr[1][j][0] = 1;
            nextPositionArr[1][j][1] = j - 1;
        }

        /*
         * A.
         * 1. 만일 달걀이 X층에서 깨진 경우
         * => X-1층 건물에서 n-1개의 달걀의 문제로 축소 시켜 풀 수 있다.
         *
         * 2. 만일 달걀이 X층에서 깨지지 않은 경우
         * => K-X 층 건물에서 n개의 달걀의 문제로 축소 시켜 풀 수 있다.

         * B.
         * 1. 최악의 경우에서의 시도의 횟수를 최소화 할 방법을 찾는 문제이다.
         * 2. 그렇기 때문에 우린 위의 두가지경우 (A.1, A.2 ) 중
         *    최악의 경우를 선택한다. 즉 두 경우 중 max 값을 선택한다.
         */

        //Dynamic Programming을 위해, 작은 수치부터 차례차례 계산해나간다.
        for (i = 2; i <= n; i++) {
            //달걀의 갯수는 2개,3개,...., n 순으로 증가한다.
            for (j = 2; j <= k; j++) {
                //건물의 층수는 2층, 3층, ... , k 순으로 증가한다.
                trials[i][j] = Integer.MAX_VALUE; //초기화
                for (x = 1; x <= j; x++) {
                    COMP_TIME_DP++; // Computation Time 분석을 위한 변수(계산 횟수를 측정한다)
                    // 달걀은 x층에서 낙하 시도 시, 깨지거나 깨지지 않는다.
                    // 최악의 경우를 고려하므로 두 가지 경우 중 시도가 더 필요 한 값(Max)을 선택한다.(재귀를 통해 찾아냄)
                    // x층에서 달걀 낙하 시도 한번 했이므로 1을 먼저 더한다.
                    // 그리고 위에서 결정된 Max 값을 결과 값에 더한다
                    mResult = 1 + max(trials[i - 1][x - 1], trials[i][j - x]);
                    if (trials[i - 1][x - 1] > trials[i][j - x]) {
                        //임시변수 mNextPosition은 이후 최소의 시도횟수가 결정되는 x층의 NextPosition에 들어 갈 것이다
                        mNextPosition[0] = (i - 1);
                        mNextPosition[1] = (x - 1);
                        mNextPosition[2] = i;
                        mNextPosition[3] = j - x;
                    } else {
                        mNextPosition[0] = i;
                        mNextPosition[1] = j - x;
                        mNextPosition[2] = (i - 1);
                        mNextPosition[3] = (x - 1);

                    }
                    // 모든 경우(x=1~j) 에 대한 최댓값(최악의 경우)들중 가장 작은것(가장 낮은 시도 횟수)
                    // 을 최종 trials[i][j] 값으로 결정한 뒤 삽입한다. (formulaArray또한 마찬가지)
                    if (mResult <= trials[i][j]) {
                        trials[i][j] = mResult;
                        for (p = 0; p < mNextPosition.length; p++)
                            nextPositionArr[i][j][p] = mNextPosition[p];
                    }
                }
            }
        }


        //아래는 Dynamic Programming 을 구현 하였음을 알 수 있는 Table을 출력하는 함수
        //trials[n][k] 값 배열 내에서 값을 꺼내서 바로바로 계산 할 수 있다.
        //D.P 기법을 통해 같은 값을 중복하여 계산 할 필요가 없기 때문에 계산이 빠르다.
        System.out.printf("\n Egg   ||  ");
        for (i = 1; i <= n; i++) {
            System.out.printf("%3d    ", i);
        }
        System.out.printf("\n Floor ||");
        for (i = 1; i <= n; i++) {
            System.out.printf("------");
        }
        System.out.printf("\n");

        for (j = 1; j <= k; j++) {
            System.out.printf("%3d    ||  ", j);
            for (i = 1; i <= n; i++) {
                System.out.printf("%3d    ", trials[i][j]);
            }
            System.out.printf("\n");
        }


        //위와 같은 다이나믹 프로그래밍이 어떤 과정을 통해 이루어 졌는지
        //미리 계산 했던 과정을 nextPostionArr를 통해 어떠한 순서로 각 층을 순회하는지 본다.
        System.out.println("[달걀 = " + n + " 개][건물 높이 = " + k + "층] 에서 최소한의 시도 횟수는?(= trials[" + n + "][" + k + "])\n");

        i = trials[n][k];
        int e = n, f = k, temp;
        int floorCout = i;
        int prefloorCout = 0;
        int trialCout = 0;
        int isOneEgg = 0;
        String s = "";
        while (i != 0) {
            trialCout++;
            System.out.println("[" + trialCout + "번 째 시도] 현재 떨어 뜨린 층 <" + floorCout + "층>");
            //현재 푸는 식 : 1 + Max( 첫번째 경우, 두번째 경우), 여기서 첫번째 경우가 Optiaml Path이다.
            if (nextPositionArr[e][f][0] == 0 || nextPositionArr[e][f][1] == 0) {
                System.out.println("현재 trials : trials[" + e + "][" + f + "] = 1");

            } else {
                System.out.println("현재 trials : trials[" + e + "][" + f + "] = 1 + Max(trials[" + nextPositionArr[e][f][0] + "][" + nextPositionArr[e][f][1] + "], trials[" + nextPositionArr[e][f][2] + "][" + nextPositionArr[e][f][3] + "])");
            }
            if (nextPositionArr[e][f][0] <= 1) {
                // 달걀이 깨지고, 남은 달걀이 1개가 될 경우
                isOneEgg++;
                if (isOneEgg == 1) {
                    if (trials[nextPositionArr[e][f][0]][nextPositionArr[e][f][1]] == 0) {
                        //이 것이 최상층에서 이루어 질 경우, while문을 종료한다.
                        System.out.println("[" + trialCout + "번 째 시도] " +
                                "여기에서 깨진다고 가정하여서  "
                                + (prefloorCout) + "가 깨지지 않는 가장 높은 층이 된다");
                        floorCout = prefloorCout;
                        break;
                    } else {
                        System.out.println("[" + trialCout + "번 째 시도] " +
                                floorCout +
                                "층에서 깨진 후, 한 개의 달걀만 남기때문에 "
                                + (++prefloorCout) + "부터 시작함");
                        floorCout = prefloorCout;
                    }
                } else {
                    prefloorCout = floorCout;
                    floorCout++;
                }

            } else {
                prefloorCout = floorCout;
                floorCout += trials[nextPositionArr[e][f][0]][nextPositionArr[e][f][1]];
            }

            System.out.println("************************************************************************");

            temp = e;
            e = nextPositionArr[temp][f][0];
            f = nextPositionArr[temp][f][1];
            i = trials[e][f];
            Scanner sc = new Scanner(System.in);
            s = sc.nextLine();
        }


        System.out.println("\t\t\t\t\t\t\t*RESULT*");
        System.out.println("최종적으로, 달걀이 깨지지 않는 가장 높은 층은 <" + (prefloorCout) + "> 층 입니다. (이를 위한 시도 횟수 : " + trialCout + " )");
        System.out.println("************************************************************************");
        System.out.println("<Main.java>");


        //n개의 달걀로 k층에서 실험했을 때의 최소 시도 횟수 값이 담긴 배열의 요소를 반환
        return trials[n][k];
    }

}
