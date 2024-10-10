import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int answer = 0;
        
        int n = Integer.parseInt(br.readLine());
        
        Integer[] A = new Integer[n], B = new Integer[n];
        // A 데이터 읽어오기
        String inputA = br.readLine();
        StringTokenizer stA = new StringTokenizer(inputA);
        for (int i = 0; i < n; i++)
            A[i] = Integer.parseInt(stA.nextToken());
        
        // B 데이터 읽어오기
        String inputB = br.readLine();
        StringTokenizer stB = new StringTokenizer(inputB);
        for (int i = 0; i < n; i++)
            B[i] = Integer.parseInt(stB.nextToken());
        
        // A는 오름차순, B는 내림차순으로 정렬한다
        Arrays.sort(A, (a, b) -> (a - b));
        Arrays.sort(B, (a, b) -> (b - a));
        
        // 앞에서부터 서로 곱하여 더한다(B의 큰 값과 A의 작은 값끼리 곱하게 됨)
        for (int i = 0; i < n; i++){
            answer += A[i] * B[i];
        }
        bw.write(String.valueOf(answer));
        
        bw.flush();
    }
}