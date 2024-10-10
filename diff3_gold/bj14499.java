/* 14499. 주사위 굴리기 (삼성 SW 역량테스트 기출) */

import java.io.*;
import java.util.*;

enum Direction {
    left,
    right,
    up,
    down
};

public class Main{
    static final Direction[] DIRECTIONS = new Direction[]{
        Direction.right, Direction.left, Direction.up, Direction.down
    };
    
    static public void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        int maxRow = Integer.parseInt(st.nextToken()), maxCol = Integer.parseInt(st.nextToken()),
        currentRow = Integer.parseInt(st.nextToken()), currentCol = Integer.parseInt(st.nextToken()),
        commandsCount = Integer.parseInt(st.nextToken());
        
        int[] dice = new int[6]; // 각 주사위에 적힌 수
        int[][] map = new int[maxRow][maxCol];
        for (int r = 0; r < maxRow; r++)
        {
            input = br.readLine();
            st = new StringTokenizer(input);
            
            for (int c = 0; c < maxCol; c++)
            {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 각 명령을 수행하기 시작한다
        input = br.readLine();
        st = new StringTokenizer(input);
        
        ExecuteCommand:
        for (int command = 0; command < commandsCount; command++)
        {
            int directionIndex = Integer.parseInt(st.nextToken()) - 1;
            Direction dir = DIRECTIONS[directionIndex];
            
            // 주사위 갱신(이동 불가능 시 continue)
            switch(dir)
            {
                case right:
                    if (currentCol + 1 >= maxCol) continue ExecuteCommand;
                    currentCol++;
                    break;
                    
                case left:
                    if (currentCol - 1 < 0) continue ExecuteCommand;
                    currentCol--;
                    break;
                    
                case up:
                    if (currentRow - 1 < 0) continue ExecuteCommand;
                    currentRow--;
                    break;
                    
                case down:
                    if (currentRow + 1 >= maxRow) continue ExecuteCommand;
                    currentRow++;
                    break;
            }
            rollDice(dice, dir); // 위치 이동 후, 그 방향으로 주사위 굴리기
            
            // 칸과 주사위의 수 갱신
            if (map[currentRow][currentCol] == 0)
            {
                map[currentRow][currentCol] = dice[5]; // 바닥면의 수 복사
            }
            else
            {
                dice[5] = map[currentRow][currentCol];
                map[currentRow][currentCol] = 0;
            }
            
            // 주사위 상단에 쓰여있는 수 출력
            bw.write(String.valueOf(dice[0]));
            bw.newLine();
        }
        
        bw.flush();
    }
    
    // 주사위를 특정 방향으로 굴려 주사위 수를 갱신하는 함수
    static void rollDice(int[] dice, Direction dir)
    {
        int temp;
        
        switch(dir){
            case right:
                temp = dice[3];
                dice[3] = dice[5];
                dice[5] = dice[2];
                dice[2] = dice[0];
                dice[0] = temp;
                return;
                
            case left:
                temp = dice[0];
                dice[0] = dice[2];
                dice[2] = dice[5];
                dice[5] = dice[3];
                dice[3] = temp;
                return;
                
            case down:
                temp = dice[1];
                dice[1] = dice[5];
                dice[5] = dice[4];
                dice[4] = dice[0];
                dice[0] = temp;
                return;
                
            case up:
                temp = dice[0];
                dice[0] = dice[4];
                dice[4] = dice[5];
                dice[5] = dice[1];
                dice[1] = temp;
                return;
        }
    }
}