package cat.udl.eps.ed.practica1.sorting;

/**
 * This class contains methods relaytes tothe sorting of arrays of integers.
 *
 * @author jmgimeno
 */

public class IntArraySorter {

    private final int[] array;

    /**
     * Constructs an instance of the class.
     *
     * @param array the array to be sorted.
     */
    public IntArraySorter(int[] array) {
        this.array = array;
    }

    /**
     * Utility method to check is the array is sorted.
     *
     * Only to be used in tests.
     *
     * @return a boolean telling if the array is sorted or not.
     */
    public boolean isSorted() {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Swaps the values at two positions in the array.
     *
     * If either {@code i} or {@code j} are invalid positions in the array,
     * it throws {@code java.lang.ArrayIndexOutOfBoundsException}
     *
     * @param i index of one of the positions
     * @param j index of the other
     */
    public void swap(int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    /**
     * Sorts the array using the insertion sort algorithm.
     */
    public void insertionSort() {

        // Invariant: The prefix [0, end) is a sorted array
        // Decreasing: array.length - end

        // We insert element at end into this prefix
        for (int end = 1; end < array.length; end++) {

            // Invariant: arrays sorted in the ranges [0, insert)
            // and [insert, end] and all elements in [0, insert)
            // are lower than or equal to those in [insert+1, end]
            // Decreasing: insert

            for (int insert = end; insert >= 1; insert--) {
                if (array[insert - 1] > array[insert]) {
                    // When elements at insert and insert+1 are not sorted,
                    // we swap them and continue to fullfill the (internal)
                    // invariant.
                    swap(insert - 1, insert);
                } else {
                    // When elements at insert and insert+1 are sorted, the
                    // whole [insert, end] is sorted and we can safely increment
                    // end and continue to fullfill the (external) invariant.
                    break;
                }
            }
        }
    }

    /**
     * Sorts the array using the bubble sort algorithm.
     */
    public void bubbleSort() {
        for (int start = 0; start < array.length - 1; start++) {
            boolean swapped = false; //Ens indica si ha swapejat o no
            for (int i = array.length - 1; i > start; i--) {
                if (array[i] < array[i - 1]) {
                    swap(i, i - 1);
                    swapped = true;
                }
            }
            if(!swapped){
                break; //Si no ha swappejat, vol dir que ja está ordenat, trenca el bucle
            }
        }
    }

    /**
     * Sorts the array using the selection sort algorithm.
     */
    public void selectionSort() {
        for(int ini = 0; ini <= array.length-1; ini++){
            int minim = returnMin(ini, array.length);
            if(minim != ini) {
                swap(ini, minim);
            }
        }
        //throw new UnsupportedOperationException("Not implemented yet");
    }
    private int returnMin(int ini, int end){
        int minPos = ini;
        for(int i = ini+1; i < end; i++){
            if(array[i] < array[minPos]){
                minPos = i;
            }
        }
        return minPos;
    }



    /**
     * Sorts the arry using the quicksort algortithm.
     */
    public void quickSort() {
        quickSort(0, array.length);
    }

    private void quickSort(int left, int right) {
        // DO NOT MODIFY !!!!
        // 0 <= left <= right <= array.length
        if (right - left > 1) {
            int pivotPos = left + (right - left) / 2;
            int pivotValue = array[pivotPos];
            swap(left, pivotPos);
            int pos = partitionIterative(pivotValue, left + 1, right);
            swap(left, pos - 1);
            quickSort(left, pos - 1);
            quickSort(pos, right);
        }
    }

    private int partitionRecursive(int pivotValue, int inf, int sup) {
        // DO NOT USE -> create an iterative version which does the same
        // 0 <= left <= inf <= sup <= right <= v.length
        if (inf == sup) {
            return inf;
        } else if (array[inf] <= pivotValue) {
            return partitionRecursive(pivotValue, inf + 1, sup);
        } else if (array[sup - 1] > pivotValue) {
            return partitionRecursive(pivotValue, inf, sup - 1);
        } else {
            swap(inf, sup - 1);
            return partitionRecursive(pivotValue, inf + 1, sup - 1);
        }
    }

    private int partitionIterative(int pivotValue, int inf, int sup) {
        int left = inf;
        int right = sup;

        while (left < right) {
            if (array[left] <= pivotValue) { //si el valor del punter esquerra es menor o igual al pivot, està ben colocat, passem al seguent
                left++;
            } else if (array[right - 1] > pivotValue) { //si el valor del punter dret es major que el pivot, està ben colocat, passem al seguent
                right--;
            } else { //si no es cap dels anteriors casos es que estàn mal colocats, els swapejem i pasem al seguent en les dues bandes
                swap(left, right - 1);
                left++;
                right--;
            }
        }
        return left;
        //throw new UnsupportedOperationException("Not implemented yet");
    }
}
