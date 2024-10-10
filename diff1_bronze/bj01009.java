/* 백준 1009번: 분산처리 */

import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++){
            String input = br.readLine();
            StringTokenizer st = new StringTokenizer(input);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int result = 1;
            for (int j = 0; j < b; j++){
                result = (result * a) % 10; // 매번 10에 대한 나머지만 구해준다
            }
            if (result == 0) result = 10; // 나머지가 0인 경우는 10으로 변환
            
            bw.write(String.valueOf(result));
            bw.newLine();
        }
        
        bw.flush();
    }
}