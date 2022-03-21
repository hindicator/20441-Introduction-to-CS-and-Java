public class Recursion {
    
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
