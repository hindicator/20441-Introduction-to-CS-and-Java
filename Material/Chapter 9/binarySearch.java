public class binarySearch{
    public static boolean search(int [] arr, int x){
        int low = 0, high = arr.length-1, mid;

        while(low <= high){
            mid = (low + high)/2;
            if(arr[mid] == x){
                return true;
            }
            if(arr[mid] < x){
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int [] arr1 = {1, 2, 3, 5, 7, 9, 10, 13, 111};
        boolean b1 = search(arr1, 13);
        System.out.println(b1);
    }
}