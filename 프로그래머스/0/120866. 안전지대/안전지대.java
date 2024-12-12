class Solution {
    public int solution(int[][] board) {
        int answer = 0;
        int n = board.length;
        
        int[] dx = {0,1,-1,0,-1,1,1,-1};
        int[] dy = {1,0,0,-1,-1,1,-1,1};
        
        for(int i = 0 ; i < n ; i++){
            inner:
            for(int j = 0 ; j < n ; j++){
                if(board[i][j] == 0) {
                    for(int k = 0 ; k < 8 ; k++){
                        if(i+dx[k] > -1 && i+dx[k] < n // x좌표 검사
                           && j+dy[k] > -1 && j+dy[k] < n // y좌표 검사
                           && board[i+dx[k]][j+dy[k]] == 1){
                            continue inner;
                        }
                    }
                    answer++;
                }
            }
        }
        return answer;
    }
}