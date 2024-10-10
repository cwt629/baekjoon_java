/* 14501. 퇴사(삼성 SW 역량테스트 기출) */

import java.io.*;
import java.util.*;

class Counsel {
    int day;
    int profit;
    
    public Counsel(int day, int profit){
        this.day = day;
        this.profit = profit;
    }
}

public class Main{
    static public void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int N = Integer.parseInt(br.readLine());
        int[] time = new int[N];
        int[] profit = new int[N];
        
        for (int i = 0; i < N; i++)
        {
            String input = br.readLine();
            StringTokenizer st = new StringTokenizer(input);
            time[i] = Integer.parseInt(st.nextToken());
            profit[i] = Integer.parseInt(st.nextToken());
        }
        
        // DFS 방식으로, 모든 가능한 조합을 탐색한다
        int answer = 0;
        Stack<Counsel> dfsStack = new Stack<>();
        dfsStack.push(new Counsel(0, 0));
        
        while (!dfsStack.isEmpty())
        {
            Counsel current = dfsStack.pop();
            // 이미 N일을 넘게 된 경우, 갱신하며 종료
            if (current.day >= N){
                if (answer < current.profit) answer = current.profit;
                continue;
            }
            
            // 1. 오늘 일자에 잡힌 상담 진행
            if (current.day + time[current.day] <= N){
                dfsStack.push(new Counsel(current.day + time[current.day], current.profit + profit[current.day]));
            }
            
            // 2. 오늘 일자 패스
            dfsStack.push(new Counsel(current.day + 1, current.profit));
        }
        
        bw.write(String.valueOf(answer));
        bw.flush();
    }
}