/* 1032번: 명령 프롬프트 */

import java.io.*;
import java.util.*;

public class bj01032 {
    static public void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ArrayList<String> fileNames = new ArrayList<>();
        String answer = "";

        // 파일 이름의 개수 N 받아오기
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++){
            String currentFileName = br.readLine();
            fileNames.add(currentFileName);
        }

        for (int i = 0; i < fileNames.get(0).length(); i++){
            char currentChar = fileNames.get(0).charAt(i);
            boolean isMatched = true; // 현재 인덱스에 대해 모든 문자가 일치하는지 여부
            for (int ptr = 1; ptr < fileNames.size(); ptr++){
                if (currentChar != fileNames.get(ptr).charAt(i)){
                    isMatched = false;
                    break;
                }
            }

            answer += (isMatched? String.valueOf(currentChar) : "?");
        }

        bw.write(answer);

        bw.flush();
    }
}
