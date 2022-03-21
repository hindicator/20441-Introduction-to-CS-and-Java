public class Recursion {
    
    // 2022a Moed 89
    // Soon

    // 2022a Moed 67
    public static boolean isIdentity(int[][] mat, int x, int size){
        return isIdentity(mat, x, size, x, x, 1);
    }
    private static boolean isIdentity(int[][]mat, int x, int size, int i, int j, int num){
        System.out.println(mat[i][j] + " " + "- " + i + " " + j);
        
        if(num == size*size && i == j && i == x+size-1){
            return true;
        }
        if(i == j && mat[i][j] != 1){
            return false;
        }
        if(i != j && mat[i][j] != 0){
            return false;
        }
        if(j == x+size-1){
            return isIdentity(mat, x, size, i+1, x, num+1);
        }else{
            return isIdentity(mat, x, size, i, j+1, num+1);
        }
    }

    // 2021b Moed 62
    public static boolean equalSplit(int[] arr) {
        if (arr.length % 2 == 1)
            return false;
        return equalSplit(arr, 0, 0, 0, 0);
    }
    /* count = the amount of elements in the first set (sum1) */
    private static boolean equalSplit(int[] arr, int i, int sum1, int sum2, int count) {
        if (i == arr.length)
            return sum1 == sum2 && 2*count == arr.length;
        
        return equalSplit(arr, i+1, sum1 + arr[i], sum2, count+1)
            || equalSplit(arr, i+1, sum1, sum2 + arr[i], count);
    }

    // 2021b Moed 60
    public static boolean split3(int[] arr) {
        return split3(arr, 0, 0, 0, 0);
    }
    private static boolean split3(int[] a, int i, int sum1, int sum2, int sum3) {
        if (i == a.length)
            return sum1 == sum2 && sum2 == sum3;
        return split3(a, i+1, sum1 + a[i], sum2, sum3) // adding to sum1
            || split3(a, i+1, sum1, sum2 + a[i], sum3) // adding to sum2
            || split3(a, i+1, sum1, sum2, sum3 + a[i]); // adding to sum3
    }
    // 2021a Moed 85
    public static int minPrice(int[][] mat)
    {
        return minPrice(mat, 0, 1);
    }
    private static int minPrice(int[][] mat, int currentStation, int toStation)
    {
        if(currentStation == mat.length-1){
            return 0;
        }
        if(toStation == mat.length){
            return Integer.MAX_VALUE;
        }
        int price2 = minPrice(mat, currentStation, toStation+1);
        int price1 = minPrice(mat, toStation, toStation+1) + mat[currentStation][toStation];
        return Math.min(price1, price2);
    }

    // 2021a Moed 70
    public static int findMaxPrice(int[] prices, int n){
        return findMaxPrice(prices, n, 1);
    }
    private static int findMaxPrice(int[] prices, int n, int i){
        if(n < 0 || i > prices.length-1){
            return Integer.MIN_VALUE;
        }
        if(n == 0){
            return 0;
        }
        int take = prices[i] + findMaxPrice(prices, n-i, i);
        int noTake = findMaxPrice(prices, n, i+1);

        return Math.max(take, noTake);
    }
    // 2020b Moed 96
    public static int maxSumKnight(int[][] mat) {
        return maxSumKnight(mat, 0, 0, 0, mat[0][0]);
    }

    private static int maxSumKnight(int[][] mat, int row, int col, int sum, int prev) {
        if (outOfRange(mat, row, col) || mat[row][col] < 0 || Math.abs(mat[row][col] - prev) > 1)
            return 0;
        
        sum += mat[row][col];
        if (row == mat.length - 1 && col == mat[0].length - 1)
            return sum;
        
        mat[row][col] *= -1; // changing it to negative
        int u1 = maxSumKnight(mat, row - 2, col +1, sum, -mat[row][col]), u2 = maxSumKnight(mat, row - 2, col - 1, sum, -mat[row][col]),
            u3 = maxSumKnight(mat, row - 1, col + 2, sum, -mat[row][col]), u4 = maxSumKnight(mat, row - 1, col - 2, sum, -mat[row][col]);
        
        int d1 = maxSumKnight(mat, row + 2, col +1, sum, -mat[row][col]), d2 = maxSumKnight(mat, row + 2, col - 1, sum, -mat[row][col]),
            d3 = maxSumKnight(mat, row + 1, col + 2, sum, -mat[row][col]), d4 = maxSumKnight(mat, row + 1, col - 2, sum, -mat[row][col]);
        
        mat[row][col] *= -1; // changing back to positive
        return Math.max(
            Math.max(Math.max(u1, u2), Math.max(u3, u4)), 
            Math.max(Math.max(d1, d2), Math.max(d3, d4)));
        
    }
    private static boolean outOfRange(int[][] mat, int row, int col) {
        return row < 0 || col < 0 || row >= mat.length || col >= mat[0].length;
    }

    // 2020b Moed 84
    public static void findWord(char [][] arr, String word){
        int[][] mat = new int[arr.length][arr[0].length];
        findWord(arr, word, 0, 0, 0, mat);
    }
    private static void findWord(char [][] arr, String word, int index, int i, int j, int[][] mat){
        if(i==-99){
            return;
        }
        if(index+1 == word.length()){
            mat[i][j] = index+1;
            printArray(mat);
            mat[i][j] = 0;
        }
        else if(i >= 0 && i < arr.length && j >= 0 && j < arr[0].length){
            if(arr[i][j] == word.charAt(index) && mat[i][j] == 0){
                mat[i][j] = index+1;
                // Up
                if(i-1 >= 0 && arr[i-1][j] == word.charAt(index+1)){
                    findWord(arr, word, index+1, i-1, j, mat);
                }
                // Down
                if(i+1 < arr.length && arr[i+1][j] == word.charAt(index+1)){
                    findWord(arr, word, index+1, i+1, j, mat);
                }
                // Left
                if(j-1 >= 0 && arr[i][j-1] == word.charAt(index+1)){
                    findWord(arr, word, index+1, i, j-1, mat);
                }
                // Right
                if(j+1 < arr[0].length && arr[i][j+1] == word.charAt(index+1)){
                    findWord(arr, word, index+1, i, j+1, mat);
                }
                mat[i][j] = 0;
            }
            else{
                if(index != 0){
                    return;
                }
                if(j == arr[0].length-1){
                    if(i == arr.length-1){
                        System.out.println("No Such Word");
                    }
                    findWord(arr, word, 0, i+1, 0, mat);
                }
                else{
                    findWord(arr, word, 0, i, j+1, mat);
                }
            }    
        }
        
    }
    private static void printArray(int[][] mat){
        for(int i = 0; i<mat.length; i++){
            for(int j = 0; j < mat[0].length; j++){
                System.out.print(mat[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    // 2020b Moed 81
    public static int makeSum(int[] lengths, int k, int num) {
        if (lengths == null || lengths.length == 0)
            return 0;
        return makeSum(lengths, k, num, 0);
    }
    private static int makeSum(int[] lengths, int k, int num, int i) {
        if (k == 0)
            return 1;
        if (i == lengths.length || num == 0 || k < 0)
            return 0;
        return makeSum(lengths, k - lengths[i], num - 1, i) + makeSum(lengths, k, num, i + 1);
    }

    // 2020a Moed 87
    public static int totalWays(int[][] mat, int k) {
        return totalWays(mat, k, 0, 0, false, 0);
    }
    private static int totalWays(int[][] mat, int k, int row, int col, boolean right, int count) {
        if (!inRange(mat, row, col) || count > k)
            return 0;
        if (row == mat.length-1 && col == mat[0].length-1)
            return count == k ? 1 : 0;
        
        if (row == 0 && col == 0)
            return totalWays(mat, k, 1, 0, false, 0) + totalWays(mat, k, 0, 1, true, 0);
        
        return totalWays(mat, k, row+1, col, false, right ? count + 1 : count) // down
            + totalWays(mat, k, row, col+1, true, right ? count : count + 1); // right
    }
    private static boolean inRange(int[][] mat, int row, int col) {
        return row >= 0 && row < mat.length && col >= 0 && col < mat[0].length;
    }

    // 2020a Moed 85
    public static int findMaximum(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0)
            return 0;
        return findMaximum(mat, 0, 0);
    }
    private static int findMaximum(int[][] mat, int row, int col) {
        if (checkInRange(mat, row, col) || mat[row][col] == -1)
            return 0;

        return mat[row][col] +
            Math.max(findMaximum(mat, row + 1, col), // next row
                findMaximum(mat, row, row % 2 == 0 ? col + 1 : col - 1)); // next column
    }
    private static boolean checkInRange(int[][] mat, int row, int col) {
        return row < 0 || col < 0 || row >= mat.length || col >= mat[0].length;
    }
    // 2019b Moed 85
    public static int howManyPaths(int [][] mat){
        return howManyPaths(mat, 0, 0);
    }
    private static int howManyPaths(int [][] mat, int i, int j){
        if(i < 0 || i > mat.length-1 || j < 0 || 
                j > mat[0].length-1 || mat[i][j] == 0){
            return 0;
        }
        if(i == mat.length-1 && j == mat[0].length-1){
            return 1;
        }
        int k = mat[i][j];
        mat[i][j] = 0;

        int goUp = howManyPaths(mat, i-k, j);
        int goDown = howManyPaths(mat, i+k, j);
        int goRight = howManyPaths(mat, i, j+k);
        int goLeft = howManyPaths(mat, i, j-k);

        mat[i][j] = k;
        return goUp + goDown + goRight + goLeft;
    }

    // 2019b Moed 83
    public static int longestPath(int [][] mat, int x, int y){
        return longestPath(mat, x, y, 0, 0);
    }
    private static int longestPath(int [][] mat, int x, int y, int i, int j){
        
        // Stop condition
        if(i < 0 || i > mat.length-1 || j < 0 ||
                j > mat[0].length-1 || mat[i][j] == 0){
            return 0;
        }
        if(i == x-1 && j == y-1){
            return 1;
        }
        int k = mat[i][j];
        mat[i][j] = 0;

        int goUp = longestPath(mat, x, y, i-1, j);
        int goDown = longestPath(mat, x, y, i+1, j);
        int goRight = longestPath(mat, x, y, i, j+1);
        int goLeft = longestPath(mat, x, y, i, j-1);
        
        mat[i][j] = k;
        return Math.max(Math.max(goRight+1, goLeft+1),
                Math.max(goDown+1, goUp+1));
    }
    
    // 2019a Moed 85
    public static int longestPalindrome(int[] arr){
        return longestPalindrome(arr, 0, arr.length-1, 0);
    }
    private static int longestPalindrome(int[] arr, int i, int j, int sum){
        if(i<0 || i>arr.length-1 || j<0 || j>arr.length-1){
            return Integer.MIN_VALUE;
        }
        if(j < i){
            return sum;
        }
        if(j == i){
            return sum + 1;
        }
        if(arr[i] == arr[j]){
            int both = longestPalindrome(arr, i+1, j-1, sum+2);
            int one = longestPalindrome(arr, i+1, j, sum);
            int two = longestPalindrome(arr, i, j-1, sum);
            return Math.max(both, Math.max(one, two));
        }
        else{
            int one = longestPalindrome(arr, i+1, j, sum);
            int two = longestPalindrome(arr, i, j-1, sum);
            return Math.max(one, two);
        }
    }

    // 2019a Moed 84
    public static boolean sumPower3(int num){
        return sumPower3(num, 0, 0);
    }
    private static boolean sumPower3(int num, int n, int sum){
        System.out.println(sum + " " + num);
        // Bad stop condition
        if(sum > num || Math.pow(3, n-1) > num){
            return false;
        }
        if(sum==num){
            return true;
        }
        return sumPower3(num, n+1, sum+(int)Math.pow(3, n)) || 
                sumPower3(num, n+1, sum);
    }

    // 2018b Moed 83
    public static int prince(int[][] drm, int i, int j) {
        final int NO_PATH = -1;
        if (drm == null || i < 0 || j < 0 || i >= drm.length || j >= drm.length)
            return NO_PATH;
        
        int res = prince(drm, i, j, 0);
        if(res == Integer.MAX_VALUE)
            return NO_PATH;
        return res;
    }
    private static int prince(int[][] drm, int i, int j, int count) {
        final int VISITED = -10, TARGET = -1, MAX = Integer.MAX_VALUE;
        if (drm[i][j] == TARGET)
            return count + 1;
        if (drm[i][j] == VISITED)
            return MAX;

        int temp = drm[i][j];
        drm[i][j] = VISITED; // marking as visited
        count++; // counting this step
        
        int up = MAX, down = MAX, left = MAX, right = MAX;
        
        if (i > 0 && isValid(temp, drm[i-1][j]))
            up = prince(drm, i-1, j, count);
        
        if (i+1 < drm.length && isValid(temp, drm[i+1][j]))
            down = prince(drm, i+1, j, count);
        
        if (j > 0 && isValid(temp, drm[i][j-1]))
            left = prince(drm, i, j-1, count);
        
        if (j+1 < drm.length && isValid(temp, drm[i][j+1]))
            right = prince(drm, i, j+1, count);
        
        drm[i][j] = temp; // changing back to the original value
        return Math.min(Math.min(up, down), Math.min(left, right));
    }

    private static boolean isValid(int from, int to) {
        // checking if the "evil" is in the next cell or the jump is valid.
        return to == -1 || Math.abs(from - to) <= 1 || from - to == 2;
    }

    // 2018a Moed 91
    public static int cheapestRoute(int[] stations) {
        return cheapestRoute(stations, 0);
    }
    private static int cheapestRoute(int[] a, int i) {
        if (a[i] == 0)
            return Integer.MAX_VALUE;
        if (i == a.length -1)
            return a[i];
        if (i == a.length - 2)
            return a[i] + a[i+1];
        
        int temp = a[i];
        a[i] = 0;
        
        int nextOne = cheapestRoute(a, i+1), nextTwo = cheapestRoute(a, i+2),
        cheapest = temp + Math.min(nextOne, nextTwo);
        
        a[i] = temp;
        if (cheapest <= 0 || cheapest == Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        return cheapest;   
    }

    // 2018a Moed 85
    public static int longestSlope(int[][] mat, int num) {
        return longestSlope(mat, num, 0, 0);
    }
    
    // linear search in every row
    private static int longestSlope(int[][] mat, int num, int row, int col) {
        // moving to the next row if we've checked all the cells in the current row.
        if (col == mat[0].length) {
            col = 0;
            row++;
        }
        if (row == mat.length)
            return 0;
        
        int next = longestSlope(mat, num, row, col+1),
            current = longestSlope(mat, num, row, col, mat[row][col]+num);
        return Math.max(current, next);
    }
    
    // returns the longest slope that starts with mat[row][col]
    private static int longestSlope(int[][] mat, int num, int row, int col, int prev) {
        if (!checkRange(mat, row, col) || mat[row][col] == Integer.MIN_VALUE || prev - mat[row][col] != num)
            return 0;
        
        int temp = mat[row][col];
        mat[row][col] = Integer.MIN_VALUE; // handling the case that num is equal to 0.
        
        int up = longestSlope(mat, num, row-1, col, temp),
            down = longestSlope(mat, num, row+1, col, temp),
            left = longestSlope(mat, num, row, col-1, temp),
            right = longestSlope(mat, num, row, col+1, temp);
        mat[row][col] = temp; // changing back to the original value
        
        // adding 1 in order to include the current step.
        return 1 + Math.max(Math.max(up, down), Math.max(left, right));
    }
    private static boolean checkRange(int[][] mat, int row, int col) {
        return row >= 0 && row < mat.length && col >= 0 && col < mat[0].length;
    }

    // 2017b Moed 85
    public static int oneFiveSeven(int n){
        return oneFiveSeven(n, 0);
    }
    private static int oneFiveSeven(int n, int k){
        if(n<0){
            return Integer.MAX_VALUE;
        }
        if(n==0){
            return k;
        }
        return Math.min(oneFiveSeven(n-7, k+1), Math.min(oneFiveSeven(n-5, k+1), oneFiveSeven(n-1, k+1)));
    }

    // 2015b Moed 86
    public static void printAllSum(int[] a, int sum){
        printAllSum(a, sum, 0, "");
    }
    private static void printAllSum(int[] a, int sum, int i, String s){
        if(i == a.length){
            if(sum == 0){
                System.out.println(s);
            }
            return;
        }
        printAllSum(a, sum-a[i], i + 1, s + "1");
        printAllSum(a, sum, i + 1, s + "0");
    }
}
