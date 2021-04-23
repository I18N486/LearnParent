package com.zst.javabase.algorithm.swordoffer;

/**
 * @Author stzhang
 * @Description
 * @Date 2021/4/6 9:58
 **/
public class FindPathFromMatrix {
    public static void main(String[] args) {
        char[][] board = {{'a','b','c','e'},{'s','f','c','s'},{'a','d','e','e'}};
        String word = "abcced";
        char[][] board1 = {{'c','a','a'},{'a','a','a'},{'b','c','d'}};
        String word1 = "aab";
        System.out.println(exist(board1, word1));
    }

    public static boolean existMine(char[][] board, String word){
        char[] chars = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfsMine(board,chars,i,j,0)) return true;
            }
        }
        return false;
    }

    private static boolean dfsMine(char[][] board, char[] chars, int i, int j, int k) {
        if (i<0 || i >= board.length || j <0 || j >=board[0].length || board[i][j] != chars[k]) return false;
        //找到了最后一个字符，并且最后一个字符也是匹配的，则直接返回
        if (k == chars.length -1) return true;
        board[i][j] = '\0';
        boolean res = dfsMine(board,chars,i-1,j,k+1)|| dfsMine(board,chars,i+1,j,k+1)
                ||dfsMine(board,chars,i,j-1,k+1)||dfsMine(board,chars,i,j+1,k+1);
        board[i][j]=chars[k];
        return res;
    }

    public static boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) return false;
        char[] chars = word.toCharArray();
        //第一步找到首个字符，起始
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == chars[0]){
                    //第二步，从起始字符向相邻的四周查找，找到则递归查找下一个
                    if (findAround(1,i,j,-1,-1,chars,board)) return true;
                }
            }
        }
        return false;
    }

    private static boolean findAround(int index,int i,int j,int preI,int preJ,char[] chars,char[][] board){
        if (index >= chars.length) return true;
        int nextI = i -1;
        boolean result = false;
        if (0 <= nextI && nextI < board.length && nextI != preI){
            if (board[nextI][j] == chars[index]) {
                result = findAround(++index, nextI, j, i, j, chars, board);
                if (result) return result;
            }
        }
        nextI = i+1;
        if (0 <= nextI && nextI < board.length && nextI != preI){
            if (board[nextI][j] == chars[index]) {
                result = findAround(++index, nextI, j, i, j, chars, board);
                if (result) return result;
            }
        }
        int nextJ = j-1;
        if (0 <= nextJ && nextJ < board[0].length && nextJ != preJ){
            if (board[i][nextJ] == chars[index]) {
                result = findAround(++index, i, nextJ, i, j, chars, board);
                if (result) return result;
            }
        }
        nextJ = j+1;
        if (0 <= nextJ && nextJ < board[0].length && nextJ != preJ){
            if (board[i][nextJ] == chars[index]) {
                result = findAround(++index, i, nextJ, i, j, chars, board);
                if (result) return result;
            }
        }
        return false;
    }

    /**
     * 检查字串是否可达
     * ex：{{'a','b','c','e'},{'s','f','c','s'},{'a','d','e','e'}}
     * str："abcced" 有不重复的路径组成字符，说明可达，返回true
     * @param board
     * @param word
     * @return
     */
    public boolean existPlus(char[][] board, String word) {
        char[] words = word.toCharArray();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(dfs(board, words, i, j, 0)) return true;
            }
        }
        return false;
    }
    boolean dfs(char[][] board, char[] word, int i, int j, int k) {
        if(i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k]) return false;
        if(k == word.length - 1) return true;
        board[i][j] = '\0';
        boolean res = dfs(board, word, i + 1, j, k + 1) || dfs(board, word, i - 1, j, k + 1) ||
                dfs(board, word, i, j + 1, k + 1) || dfs(board, word, i , j - 1, k + 1);
        board[i][j] = word[k];
        return res;
    }
}
