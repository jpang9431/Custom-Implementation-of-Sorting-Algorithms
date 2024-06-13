import java.util.List;
import java.util.ArrayList;
import java.util.Random;

class Sort<T extends Comparable<T>> {
    public static final Random rand = new Random();

    // Note that this is max and min inclusive
    public static ArrayList<Integer> generateRandomNums(int max, int min, int size) {
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            nums.add(rand.nextInt(min, max + 1));
        }
        return nums;
    }

    private void swap(List<T> list, int index1, int index2) {
        T data = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, data);
    }

    public void bubbleSort(ArrayList<T> list) {
        boolean didSwap = true;
        while (didSwap) {
            didSwap = false;
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                    swap(list, i, i + 1);
                    didSwap = true;
                }
            }
        }
    }

    public void insertionSort(ArrayList<T> list) {
        for (int i = 0; i < list.size(); i++) {
            int minIndex = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).compareTo(list.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            swap(list, i, minIndex);
        }
    }

    public void treeSort(ArrayList<T> list) {
        MyBinarySearchTree<T> tree = new MyBinarySearchTree<>();
        for (T data : list) {
            tree.add(data);
        }
        list.clear();
        tree.addAll(list);
    }

    public void mergeSort(ArrayList<T> list) {
        int size = list.size() / 2;
        if (list.size() > 1) {
            ArrayList<T> leftList = new ArrayList<>();
            ArrayList<T> rightList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                leftList.add(list.get(i));
            }
            for (int j = size; j < list.size(); j++) {
                rightList.add(list.get(j));
            }

            mergeSort(leftList);
            mergeSort(rightList);
            merge(leftList, rightList, list);
        }
    }

    public void quickSort(ArrayList<T> list) {
        quickSort(list, 0, list.size() - 1);
    }

    private int pivot(ArrayList<T> list, int leftBound, int rightBound) {
        int pivotPlace = leftBound;
        T pivot = list.get(rightBound);
        for (int i = leftBound; i < rightBound; i++) {
            if (list.get(i).compareTo(pivot) <= 0) {
                swap(list, i, pivotPlace);
                pivotPlace++;
            }
        }
        swap(list, pivotPlace, rightBound);
        return pivotPlace;
    }

    public void quickSort(ArrayList<T> list, int leftBound, int rightBound) {
        if (leftBound < rightBound) {
            int pivot = pivot(list, leftBound, rightBound);
            quickSort(list, leftBound, pivot - 1);
            quickSort(list, pivot + 1, rightBound);
        }
    }

    private void merge(ArrayList<T> leftList, ArrayList<T> rightList, ArrayList<T> list) {
        int leftIndex = 0;
        int rightIndex = 0;
        int index = 0;
        while (leftIndex < leftList.size() && rightIndex < rightList.size()) {
            if (leftList.get(leftIndex).compareTo(rightList.get(rightIndex)) <= 0) {
                list.set(index, leftList.get(leftIndex));
                leftIndex++;
            } else {
                list.set(index, rightList.get(rightIndex));
                rightIndex++;
            }
            index++;
        }
        while (leftIndex < leftList.size()) {
            list.set(index, leftList.get(leftIndex));
            leftIndex++;
            index++;
        }
        while (rightIndex < rightList.size()) {
            list.set(index, rightList.get(rightIndex));
            rightIndex++;
            index++;
        }
    }

    private int maxNum(ArrayList<Integer> list){
        int max = Integer.MIN_VALUE;
        for(int i=0; i<list.size(); i++){
            if (list.get(i)>max){
                max = list.get(i);
            }
        }
        return max;
    }

    private void modifiedInsertionSort(ArrayList<Integer> list, int divisor) {
        for (int i = 0; i < list.size(); i++) {
            int minIndex = i;
            int minNum = (list.get(i)/divisor)%10;
            for (int j = i + 1; j < list.size(); j++) {
                if ((list.get(j)/divisor)%10<minNum) {
                    minIndex = j;
                    minNum = (list.get(j)/divisor)%10;
                }
            }
            Integer temp = list.get(i);
            list.set(i, list.get(minIndex));
            list.set(minIndex, temp);
        }
    }

    public void radixSort(ArrayList<Integer> list){
        int numDigits = (int) (Math.log10(maxNum(list))+1);
        for(int i=0; i<numDigits; i++){
            modifiedInsertionSort(list, (int) Math.pow(10, i));
        }
    }



    public static void main(String[] args) {
        ArrayList<Integer> nums = generateRandomNums(9, 0, 15);
        Sort<Integer> sort = new Sort<>();
        sort.bubbleSort(nums);
        System.out.println(nums);
        nums = generateRandomNums(9, 0, 15);
        sort.insertionSort(nums);
        System.out.println(nums);
        nums = generateRandomNums(9, 0, 15);
        sort.treeSort(nums);
        System.out.println(nums);
        nums = generateRandomNums(9, 0, 15);
        sort.mergeSort(nums);
        System.out.println(nums);
        nums = generateRandomNums(9, 0, 15);
        sort.quickSort(nums);
        System.out.println(nums);
        nums = generateRandomNums(1000, 0, 15);
        sort.radixSort(nums);
        System.out.println(nums);

    }
}