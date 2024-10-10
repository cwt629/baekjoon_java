/* 15683. 감시 (삼성 SW 역량테스트 기출) */

import java.io.*;
import java.util.*;

enum Direction{
    right,
    left,
    up,
    down
};

class CCTV {
    int row;
    int col;
    int type;
    
    public CCTV(int row, int col, int type)
    {
        this.row = row;
        this.col = col;
        this.type = type;
    }
}

public class Main{
    // 각 CCTV의 가능한 감시 방향들
    static final Direction[][][] CCTV_DIRECTIONS = new Direction[][][]{
        // type 1
        new Direction[][]{
            new Direction[]{Direction.left}, // 왼쪽만
            new Direction[]{Direction.right}, // 오른쪽만
            new Direction[]{Direction.up}, // 위쪽만
            new Direction[]{Direction.down} // 아래쪽만
        },
        // type 2
        new Direction[][]{
            new Direction[]{Direction.left, Direction.right}, // 좌, 우
            new Direction[]{Direction.up, Direction.down} // 상, 하
        },
        // type 3
        new Direction[][]{
            new Direction[]{Direction.up, Direction.right}, // 상, 우
            new Direction[]{Direction.right, Direction.down}, // 우, 하
            new Direction[]{Direction.down, Direction.left}, // 하, 좌
            new Direction[]{Direction.left, Direction.up} // 좌, 상
        },
        // type 4
        new Direction[][]{
            new Direction[]{Direction.left, Direction.up, Direction.right}, // 좌, 상, 우
            new Direction[]{Direction.up, Direction.right, Direction.down}, // 상, 우, 하
            new Direction[]{Direction.right, Direction.down, Direction.left}, // 우, 하, 좌
            new Direction[]{Direction.down, Direction.left, Direction.up} // 하, 좌, 상
        },
        // type 5
        new Direction[][]{
            new Direction[]{Direction.left, Direction.up, Direction.right, Direction.down} // 상, 하, 좌, 우
        }
    };
    
    static public void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        
        int maxRow = Integer.parseInt(st.nextToken()),
        maxCol = Integer.parseInt(st.nextToken());
        
        int[][] room = new int[maxRow][maxCol];
        ArrayList<CCTV> cctvs = new ArrayList<>(); // 각 cctv의 정보를 담을 리스트
        int answer = -1;
        
        for (int row = 0; row < maxRow; row++)
        {
            input = br.readLine();
            st = new StringTokenizer(input);
            
            for (int col = 0; col < maxCol; col++)
            {
                room[row][col] = Integer.parseInt(st.nextToken());
                // CCTV의 위치를 기억해둔다
                if (room[row][col] > 0 && room[row][col] < 6)
                {
                    cctvs.add(new CCTV(row, col, room[row][col]));
                }
            }
        }
        
        // DFS 방식으로, cctv의 모든 가능한 방향에 대해 모두 탐색한다
        Stack<ArrayList<Integer>> dfsStack = new Stack<>();
        dfsStack.push(new ArrayList<Integer>());
        while (!dfsStack.isEmpty())
        {
            ArrayList<Integer> currentIndices = dfsStack.pop();
            int listIndex = currentIndices.size();
            // 모두 탐색이 완료된 경우, 해당 인덱스들에 대하여 감시를 시작한다
            if (listIndex >= cctvs.size())
            {
                boolean[][] isSeen = new boolean[maxRow][maxCol];
                for (int cctvIndex = 0; cctvIndex < cctvs.size(); cctvIndex++)
                {
                    CCTV currentTV = cctvs.get(cctvIndex);
                    isSeen[currentTV.row][currentTV.col] = true; // 이 cctv의 위치
                    Direction[] directions = CCTV_DIRECTIONS[currentTV.type - 1][currentIndices.get(cctvIndex)];
                    
                    for (Direction dir: directions)
                    {
                        switch(dir){
                            case up:
                                for (int r = currentTV.row - 1; r >= 0 && room[r][currentTV.col] != 6; r--)
                                    isSeen[r][currentTV.col] = true;
                                break;

                            case down:
                                for (int r = currentTV.row + 1; r < maxRow && room[r][currentTV.col] != 6; r++)
                                    isSeen[r][currentTV.col] = true;
                                break;

                            case left:
                                for (int c = currentTV.col - 1; c >= 0 && room[currentTV.row][c] != 6; c--)
                                    isSeen[currentTV.row][c] = true;
                                break;

                            case right:
                                for (int c = currentTV.col + 1; c < maxCol && room[currentTV.row][c] != 6; c++)
                                    isSeen[currentTV.row][c] = true;
                                break;
                        }
                    }
                }
                
                // isSeen을 활용해 사각지대의 개수를 구한다
                int result = getUnguardedCell(isSeen, room);
                if (answer < 0 || answer > result) answer = result;
                
                continue;
            }
            
            // 인덱스를 더 추가해야 하는 경우
            CCTV currentTV = cctvs.get(listIndex);
            int possibleDirectionsCount = CCTV_DIRECTIONS[currentTV.type - 1].length;
            
            for (int dirIndex = 0; dirIndex < possibleDirectionsCount; dirIndex++)
            {
                ArrayList<Integer> newList = new ArrayList<>(currentIndices);
                newList.add(dirIndex);
                dfsStack.push(newList);
            }
        }
        
        bw.write(String.valueOf(answer));
        bw.flush();
    }
    
    // boolean[][] 배열을 바탕으로 감시 불가능한 칸의 개수를 세는 함수
    static int getUnguardedCell(boolean[][] isSeen, int[][] room)
    {
        int result = 0;
        for (int i = 0; i < isSeen.length; i++)
        {
            for (int j = 0; j < isSeen[i].length; j++)
            {
                // 벽은 제외해준다
                if (!isSeen[i][j] && room[i][j] != 6) result++;
            }
        }
        
        return result;
    }
}