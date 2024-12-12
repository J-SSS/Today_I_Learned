class Solution {
    public int solution(int[][] board) {
        int answer = 0;
        int[] dx = {0,1,-1,0,-1,1,1,-1};
        int[] dy = {1,0,0,-1,-1,1,-1,1};
        for(int i = 0 ; i < board.length ; i++){
            for(int j = 0 ; j < board[i].length ; j++){
                if(board[i][j] == 0) {
                    boolean isSafe = true;
                    oct:
                    for(int k = 0 ; k < 8 ; k++){
                        if(i+dx[k] > -1 && i+dx[k] < board.length
                           && j+dy[k] > -1 && j+dy[k] < board[i].length
                           && board[i+dx[k]][j+dy[k]] == 1){
                            isSafe = false;
                            break oct;
                        }
                    }
                    if(isSafe) {
                        answer++;
                    }
                }
            }
        }
        return answer;
    }
}