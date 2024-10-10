/* 백준 1002번: 터렛 */

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int T = Integer.parseInt(br.readLine());
        for (int test = 0; test < T; test++){
            int result;
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 각 정보를 읽어온다
            int x1 = Integer.parseInt(st.nextToken()),
            y1 = Integer.parseInt(st.nextToken()),
            r1 = Integer.parseInt(st.nextToken()),
            x2 = Integer.parseInt(st.nextToken()),
            y2 = Integer.parseInt(st.nextToken()),
            r2 = Integer.parseInt(st.nextToken());
            
            // 1. 중심이 아예 동일한 경우
            if (x1 == x2 && y1 == y2){
                result = (r1 == r2)? -1 : 0; // 반지름마저 같으면 무한대, 그렇지 않으면 0개
            }
            // 2. 중심이 다른 경우
            else {
                // 두 중심 사이의 거리를 제곱 형태로 구한다
                double distanceSquare = Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2);
                
                // 그 거리와, 두 반지름의 합의 제곱값을 비교한다
                /*
                거리 > 반지름합 : 0개
                거리 = 반지름합 : 1개
                거리 < 반지름합
                - 거리 > 반지름차 : 2개
                - 거리 = 반지름차 : 1개
                - 거리 < 반지름차 : 0개
                */
                double radiusSquare = Math.pow(r1 + r2, 2);
                if (distanceSquare > radiusSquare) result = 0;
                else if (distanceSquare == radiusSquare) result = 1;
                else {
                    double radiusGapSquare = Math.pow(r1 - r2, 2);
                    if (distanceSquare > radiusGapSquare) result = 2;
                    else if (distanceSquare == radiusGapSquare) result = 1;
                    else result = 0;
                }
            }
            
            bw.write(String.valueOf(result));
            bw.newLine();
        }
        
        bw.flush();
    }
}