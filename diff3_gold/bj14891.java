/* 14891. 톱니바퀴 (삼성 SW 역량테스트 기출) */

import java.io.*;
import java.util.*;

enum Rotation {
    clockwise,
    counterclockwise,
    none
};

public class Main
{
    static final int GEAR_QUANTITY = 4;
    
    static public void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] gears = new String[GEAR_QUANTITY];
        for (int i = 0; i < GEAR_QUANTITY; i++)
        {
            gears[i] = br.readLine();
        }
        
        Rotation[] rotations = new Rotation[GEAR_QUANTITY];
        Arrays.fill(rotations, Rotation.none);
        
        int K = Integer.parseInt(br.readLine());
        for (int move = 0; move < K; move++)
        {
            String input = br.readLine();
            StringTokenizer st = new StringTokenizer(input);
            int chosenGear = Integer.parseInt(st.nextToken()) - 1; // 인덱스
            rotations[chosenGear] = (st.nextToken().equals("1"))? Rotation.clockwise : Rotation.counterclockwise;
            
            // 왼쪽으로 회전 방향 전파
            for (int g = chosenGear; g > 0; g--)
            {
                // 현재 기어의 9시 방향과 이전 기어의 3시 방향 비교
                if (gears[g].charAt(6) == gears[g - 1].charAt(2)) break;
                rotations[g - 1] = getOppositeRotation(rotations[g]);
            }
            
            // 오른쪽으로 회전 방향 전파
            for (int g = chosenGear; g < GEAR_QUANTITY - 1; g++)
            {
                // 현재 기어의 3시 방향과 다음 기어의 9시 방향 비교
                if (gears[g].charAt(2) == gears[g + 1].charAt(6)) break;
                rotations[g + 1] = getOppositeRotation(rotations[g]);
            }
            
            // 각 톱니바퀴 회전
            for (int i = 0; i < GEAR_QUANTITY; i++)
            {
                gears[i] = getRotatedGear(gears[i], rotations[i]);
            }
            
            // 회전 배열 초기화
            Arrays.fill(rotations, Rotation.none);
        }
        
        // 점수의 합 구하기
        int answer = 0;
        int score = 1;
        for (int i = 0; i < GEAR_QUANTITY; i++)
        {
            if (gears[i].charAt(0) == '1')
                answer += score;
            score *= 2;
        }
        
        bw.write(String.valueOf(answer));
        bw.flush();
    }
    
    // 특정 회전 방향의 반대 방향을 반환하는 함수
    static Rotation getOppositeRotation(Rotation dir){
        switch(dir){
            case clockwise:
                return Rotation.counterclockwise;
                
            case counterclockwise:
                return Rotation.clockwise;
                
            default:
                return Rotation.none;
        }
    }
    
    // 특정 톱니바퀴를 시계/반시계로 회전한 결과를 반환하는 함수
    static String getRotatedGear(String gear, Rotation dir){
        switch(dir){
            case clockwise:
                return gear.charAt(gear.length() - 1) + gear.substring(0, gear.length() - 1);
                
            case counterclockwise:
                return gear.substring(1) + gear.charAt(0);
                
            default:
                return gear;
        }
    }
}