package net.thumbtack.school.introduction;

public class FirstSteps {
    public static void main(String[] args) { }
    public int sum (int x, int y)
    {
        return x + y;
    }
    public int mul (int x, int y){
        return x * y;
    }
    public int div (int x, int y){
        return x / y;
    }
    public int mod (int x, int y){
        return x % y;
    }
    public boolean isEqual (int x, int y){
        return x == y;
    }
    public boolean isGreater (int x, int y){
        return x > y;
    }
    public boolean isInsideRect(int xLeft, int yTop, int xRight, int yBottom, int x, int y){
        return xLeft <= x && x <= xRight && yTop <= y && y <= yBottom;
    }
    public int sum(int[] array){
        int result = 0;
        for (int el : array)
            result += el;
        return result;
    }
    public int mul(int[] array){
        int result = array.length > 0 ? 1 : 0;
        for (int el : array)
            result *= el;
        return result;
    }
    public int min(int[] array){
        int result = Integer.MAX_VALUE;
        for (int el : array)
            if (el < result)
                result = el;
        return result;
    }
    public int max(int[] array){
        int result = Integer.MIN_VALUE;
        for (int el : array)
            if (el > result)
                result = el;
        return result;
    }
    public double average(int[] array){
        if (array.length == 0) return 0;
        return (double)sum(array) / array.length;
    }
    public boolean isSortedDescendant(int[] array){
        for (int i = 1; i < array.length; i++)
            if (array[i] >= array[i - 1]) return false;
        return true;
    }
    public void cube(int[]array){
        for (int i = 0; i < array.length; i++)
            array[i] = array[i] * array[i] * array[i];
    }
    public boolean find(int[]array, int value){
        for (int el : array)
            if (el == value) return true;
        return false;
    }
    public void reverse(int[]array){
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
    }
    public boolean isPalindrome(int[]array){
        for (int i = 0; i < array.length / 2; i++)
            if (array[i] != array[array.length - i - 1]) return false;
        return true;
    }
    public int sum(int[][] matrix){
        int result = 0;
        for (int i = 0; i < matrix.length; i++)
            result += sum(matrix[i]);
        return result;
    }
    public int max(int[][] matrix){
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++) {
            int stringMax = max(matrix[i]);
            if (result < stringMax)
                result = stringMax;
        }
        return result;
    }
    public int diagonalMax(int[][] matrix){
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < matrix.length; i++)
            if (matrix[i][i] > result)
                result = matrix[i][i];
        return result;
    }
    public boolean isSortedDescendant(int[][] matrix){
        for (int i = 0; i < matrix.length; i++)
            if (!isSortedDescendant(matrix[i])) return false;
        return true;
    }
}
