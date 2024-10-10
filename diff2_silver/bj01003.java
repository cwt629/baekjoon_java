/* 백준 1003번: 피보나치 함수 */

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // 각 인자에 대해 0과 1이 출력된 횟수를 dp 형태로 구현
        int[][] dp = new int[41][2];
        
        // 초기값
        dp[0] = new int[]{1, 0};
        dp[1] = new int[]{0, 1};
        
        for (int i = 2; i < dp.length; i++){
            dp[i] = new int[]{dp[i - 1][0] + dp[i - 2][0], dp[i - 1][1] + dp[i - 2][1]};
        }
        
        int T = Integer.parseInt(br.readLine());
        for (int test = 0; test < T; test++){
            int n = Integer.parseInt(br.readLine());
            bw.write(dp[n][0] + " " + dp[n][1]);
            bw.newLine();
        }
        
        bw.flush();
    }
}