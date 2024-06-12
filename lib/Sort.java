import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;
class Sort<T extends Comparable<T>>{
    public static final Random rand = new Random();

    public void swap(List<T> list, int index1, int index2){
        T data = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, data);
    }

    public void bubbleSort(ArrayList<T> list){
        boolean didSwap = true;
        while(didSwap){
            didSwap = false;
            for(int i=0; i<list.size()-1; i++){
                if (list.get(i).compareTo(list.get(i+1))>0){
                    swap(list, i, i+1);
                    didSwap = true;
                }
            }
        }
    }

    public void insertionSort(ArrayList<T> list){
        for(int i=0; i<list.size(); i++){
            int minIndex = i;
            for(int j=i+1; j<list.size(); j++){
                if (list.get(j).compareTo(list.get(minIndex))<0){
                    minIndex = j;
                }
            }
            swap(list, i, minIndex);
        }
    }

    public void treeSort(ArrayList<T> list){
       
    }

    //Note that this is max and min inclusive
    public static ArrayList<Integer> generateRandomNums(int max, int min, int size){
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for(int i=0; i<size; i++){
            nums.add(rand.nextInt(min, max+1));
        }
        return nums;
    }

    public static void main(String[] args){
        ArrayList<Integer> nums = generateRandomNums(10, 0, 15);
        Sort<Integer> sort = new Sort<>();
        sort.bubbleSort(nums);
        System.out.println(nums);
        nums = generateRandomNums(10, 0, 15);
        sort.insertionSort(nums);
        System.out.println(nums);
        nums = generateRandomNums(10, 0, 15);
        sort.treeSort(nums);
        System.out.println(nums);
    }
}