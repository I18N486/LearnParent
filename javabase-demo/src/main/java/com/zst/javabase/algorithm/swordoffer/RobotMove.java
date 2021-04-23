package com.zst.javabase.algorithm.swordoffer;

/**
 * @Author stzhang
 * @Description
 * @Date 2021/4/6 14:01
 **/
public class RobotMove {
    public static void main(String[] args) {
        System.out.println(movingCount(2, 3, 1));
        System.out.println(movingCount(3, 1, 0));
        System.out.println(movingCount(16, 8, 4));
    }

    public static int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        return dfs(0,0,0,0,m,n,k,visited);
    }

    private static int dfs(int i, int j, int si, int sj, int m, int n, int k, boolean[][] visited) {
        if (i >= m || j >= n ||  k < si+sj || visited[i][j]) return 0;
        visited[i][j] = true;
        return 1+dfs(i+1,j,(i+1)%10 != 0?si+1:si-8,sj,m,n,k,visited)
                +dfs(i,j+1,si,(j+1)%10 != 0?sj+1:sj-8,m,n,k,visited);
    }

}
