package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

public class Solution {
    public static void main(String[] args) {
        int[][] matrix = new int[][]
                {
                        {1, 1, 1, 1},
                        {1, 0, 0, 1},
                        {1, 0, 1, 1},
                        {1, 1, 1, 1}
                };
        Solution solution = new Solution();
        System.out.println(solution.maxSquare(matrix));
    }

    public static int maxSquare(int[][] matrix) {
        int side = 0;

        for (int iy = 0; iy < matrix.length; iy++) {
            for (int ix = 0; ix < matrix[0].length; ix++) {
                if (ix*iy == 0) continue;

                if(matrix[iy][ix] == 1)
                    matrix[iy][ix] = Math.min(matrix[iy-1][ix],Math.min(matrix[iy][ix-1],matrix[iy-1][ix-1])) + 1;

                if(matrix[iy][ix] > side)
                    side = matrix[iy][ix];
            }
        }

        return side*side;
    }
}
