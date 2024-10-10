/* 백준 1004번: 어린 왕자 */

import java.io.*;
import java.util.*;

class Planet {
    int centerX;
    int centerY;
    int radius;
    
    public Planet(int x, int y, int r){
        centerX = x;
        centerY = y;
        radius = r;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int T = Integer.parseInt(br.readLine());
        for (int test = 0; test < T; test++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int result = 0; // 진입/이탈 횟수
            
            // 시작 x와 y, 목표 x와 y 불러오기
            int startX = Integer.parseInt(st1.nextToken()),
            startY = Integer.parseInt(st1.nextToken()),
            endX = Integer.parseInt(st1.nextToken()),
            endY = Integer.parseInt(st1.nextToken());
            
            // 행성계의 개수 n
            int n = Integer.parseInt(br.readLine());
            Planet[] planets = new Planet[n];
            for (int i = 0; i < n; i++){
                StringTokenizer stPlanet = new StringTokenizer(br.readLine());
                // 중점의 x, y좌표, 반지름 받아옴
                int cx = Integer.parseInt(stPlanet.nextToken()),
                cy = Integer.parseInt(stPlanet.nextToken()),
                r = Integer.parseInt(stPlanet.nextToken());
                
                planets[i] = new Planet(cx, cy, r);
            }
            
            // 저장된 각 행성에 대해, 출발점과 도착점이 원의 내부인지 아닌지 확인
            for (Planet p: planets){
                boolean startInPlanet = isInsideCircle(startX, startY, p),
                goalInPlanet = isInsideCircle(endX, endY, p);
                
                // 두 값이 서로 다르면, 행성계를 진입/이탈해야 한다
                if (startInPlanet ^ goalInPlanet) result++;
            }
            
            // 계산된 값 출력
            bw.write(String.valueOf(result));
            bw.newLine();
        }
        
        bw.flush();
    }
    
    // 특정 좌표가 특정 행성의 내부에 있는지 반환하는 함수
    static boolean isInsideCircle(int x, int y, Planet p){
        // 행성의 중심과 특정 좌표 사이의 거리 계산(루트 계산을 스킵하기 위해 제곱형)
        double distanceSquare = Math.pow(p.centerX - x, 2) + Math.pow(p.centerY - y, 2);
        
        // 그 거리가 반지름의 제곱 미만이면 내부, 그렇지 않으면 외부(걸치진 않음)
        return (distanceSquare < Math.pow(p.radius, 2));
    }
}