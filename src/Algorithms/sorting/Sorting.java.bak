package Algorithms.Sorting;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Comparator;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;


/**
 * My implementation of the sorting algorithms learned in class
 *
 *
 * @author Tania Nasser
 * @userid tnasser3
 * @GTID 903397126
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {

        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Array and comparator"
                    + " instances must not be null.");
        }
        int i = 0;
        boolean swapped = true;
        while (i < arr.length - 1 && swapped) {
            swapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swapped = true;
                }

            }
            i++;
        }
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {

        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Array, comparator, and random"
                    + " instances must not be null");
        }

        //assume elem 0 is already sorted so start at 1
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && comparator.compare(arr[j - 1], arr[j]) > 0) {
                T temp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = temp;
                j--;
            }
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Note that there may be duplicates in the array.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {

        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Array, comparator, and random"
                    + " instances must not be null");
        }

        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (comparator.compare(arr[j], arr[minIndex])  < 0) {
                    minIndex = j;
                }
            }
            T temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }

    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Note that there may be duplicates in the array.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {

        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException("Array, comparator, and random"
                    + " instances must not be null");
        }
        quickSort(arr, 0, arr.length, comparator, rand);

    }

    /**
     * Private recursive helper method for quicksort
     *
     * @param arr the array to be sorted
     * @param left the index of the first elem
     * @param right the index of the last elem
     * @param comparator the comparator to be used to compare elems
     * @param rand the random seed used to choose the pivot
     * @param <T> quicksort uses the generic type T
     */
    private static <T> void quickSort(T[] arr, int left, int right,
                                      Comparator<T> comparator, Random rand) {

        if (right - left <= 0) {
            return;
        }
            int pivotIndex = rand.nextInt(right - left) + left;
            T pivot = arr[pivotIndex];

            T temp = arr[left];
            arr[left] = arr[pivotIndex];
            arr[pivotIndex] = temp;

            int leftIndex = left + 1;
            int rightIndex = right - 1;

            while (leftIndex <= rightIndex) {
                while (leftIndex <= rightIndex && comparator.compare(arr[leftIndex], pivot) <= 0) {
                    leftIndex++;
                }
                while (leftIndex <= rightIndex && comparator.compare(arr[rightIndex], pivot) >= 0) {
                    rightIndex--;
                }

                if (leftIndex <= rightIndex) {
                    T temp2 = arr[leftIndex];
                    arr[leftIndex] = arr[rightIndex];
                    arr[rightIndex] = temp2;

                    leftIndex++;
                    rightIndex--;
                }
            }

            T temp3 = pivot;
            arr[left] = arr[rightIndex];
            arr[rightIndex] = temp3;

            quickSort(arr, left, rightIndex, comparator, rand);
            quickSort(arr, rightIndex + 1, right, comparator, rand);


    }


    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {

        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Array, comparator, and random"
                    + " instances must not be null");
        }

        int length = arr.length;
        if (length >= 2) {
            int mid = length / 2;
            T[] left = copyArray(arr, 0, mid - 1);
            T[] right = copyArray(arr, mid, length - 1);
            mergeSort(left, comparator);
            mergeSort(right, comparator);
            merge(arr, left, right, mid, comparator);
        }
    }

    /**
     *
     * Private recursive helper method merge (for merge sort)
     *
     * @param arr the original array to be sorted
     * @param left the sorted left half array to be merged
     * @param right the sorted right half of array to be merged
     * @param mid the middle index of the array
     * @param comparator the comparator used to compare the elems
     * @param <T> merge method uses the generic type T
     */
    private static <T> void merge(T[] arr, T[] left, T[] right, int mid,
                             Comparator<T> comparator) {
        int leftIndex = 0;
        int rightIndex = 0;
        int current = 0;

        while (leftIndex < mid && rightIndex < arr.length - mid) {
            //using the <= in compare makes this mergesort implementation stable
            if (comparator.compare(left[leftIndex], right[rightIndex]) <= 0) {
                arr[current] = left[leftIndex];
                leftIndex++;
            } else {
                arr[current] = right[rightIndex];
                rightIndex++;
            }
            current++;
        }

        while (leftIndex < mid) {
            arr[current] = left[leftIndex];
            leftIndex++;
            current++;
        }

        while (rightIndex < arr.length - mid) {
            arr[current] = right[rightIndex];
            rightIndex++;
            current++;
        }
    }

    /**
     *
     * Private helper method copyArray (used by mergeSort)
     *
     * @param arr the original array to be copied
     * @param start the left most index of the new array (inclusive)
     * @param end the right most index of the new array (inclusive)
     * @param <T> copyArray uses the generic type T
     * @return the newly copied array
     */
    private static <T> T[] copyArray(T[] arr, int start, int end) {
        T[] newArr = (T[]) new Object[end - start + 1];
        int index = 0;
        for (int i = start; i <= end; i++) {
            newArr[index] = arr[i];
            index++;
        }
        return newArr;
    }



    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     */
    public static void lsdRadixSort(int[] arr) {

        if (arr == null) {
            throw new IllegalArgumentException("Array, comparator, and random"
                    + " instances must not be null");
        }
        //find the # of digits in longest int
        int iterations = findMaxDigits(arr);

        //create and initialize the buckets
        List<Queue<Integer>> buckets = new ArrayList<Queue<Integer>>();
        for (int i = 0; i < 20; i++) {
            buckets.add(new LinkedList<Integer>());
        }

        int currentElem;
        for (int i = 0; i <= iterations; i++) {
            // calculate the correct bucket for currentElem
            for (int j = 0; j < arr.length; j++) {
                currentElem = arr[j];
                int digit = currentElem;

                //divide to get the targeted digit
                for (int z = i; z > 0; z--) {
                    digit /= 10;
                }
                digit %= 10;

                //put the currentElem in the bucket
                buckets.get(digit + 9).offer(currentElem);

            }

            //dequeue the elems back into the array
            int index = 0;
            for (int bucket = 0; bucket < 20; bucket++) {
                //while bucket is not empty, put elems into array
                while (!buckets.get(bucket).isEmpty()) {
                    arr[index] = buckets.get(bucket).poll();
                    index++;
                }
            }

        }
    }

    /**
     * Private helper method to find the max number of digits for Radix Sort LSD
     *
     * @param arr the array to examine for the longest int
     * @return the number of digits of the longest int to be sorted
     */
    private static int findMaxDigits(int[] arr) {
        int maxDigits = 0;
        for (int i = 0; i < arr.length; i++) {
            int curr = Math.abs(arr[i]);
            int iterCnt = 0;
            while (curr > 0) {
                curr = curr / 10;
                iterCnt++;
            }
            if (iterCnt > maxDigits) {
                maxDigits = iterCnt;
            }
        }
        return maxDigits;
    }




//    /**
//     * Implement LSD (least significant digit) radix sort.
//     *
//     * Remember you CANNOT convert the ints to strings at any point in your
//     * code! Doing so may result in a 0 for the implementation.
//     *
//     * It should be:
//     *  stable
//     *
//     * Have a worst case running time of:
//     *  O(kn)
//     *
//     * And a best case running time of:
//     *  O(kn)
//     *
//     * Refer to the PDF for more information on LSD Radix Sort.
//     *
//     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
//     * if you wish, but it may only be used inside radix sort and any radix sort
//     * helpers. Do NOT use these classes with other sorts.
//     *
//     * @throws IllegalArgumentException if the array is null
//     * @param arr the array to be sorted
//     */
//    public static void lsdRadixSort(int[] arr) {
//
//        if (arr == null) {
//            throw new IllegalArgumentException("Array, comparator, and random"
//                    + " instances must not be null");
//        }
//        //find the # of digits in longest int
//        int iterations = findMaxDigits(arr);
//
//        //create and initialize the buckets
//        List<Queue<Integer>> buckets = new ArrayList<Queue<Integer>>();
//        for (int i = 0; i < 20; i++) {
//            buckets.add(new LinkedList<Integer>());
//        }
//
//        int currentElem;
//        for (int i = 0; i <= iterations; i++) {
//            // calculate the correct bucket for currentElem
//            for (int j = 0; j < arr.length; j++) {
//                currentElem = arr[j];
//                int digit = currentElem;
//
//                //divide to get the targeted digit
//                for (int z = i; z > 0; z--) {
//                    digit /= 10;
//                }
//                digit %= 10;
//
//                //put the currentElem in the bucket
//                if (digit == 0 && arr[j] < 0) {
//                    buckets.get(0).offer(arr[j]);
//                } else if (arr[j] < 0) {
//                    buckets.get(Math.abs(digit)).offer(currentElem);
//                } else {
//                    buckets.get(Math.abs(digit) + 10).offer(currentElem);
//                }
//
//            }
//
//            //dequeue the elems back into the array
//            int index = 0;
//            for (int bucket = 9; bucket >= 0; bucket--) {
//                //while bucket is not empty, remove the elems and put into
//                // the array
//                while (!buckets.get(bucket).isEmpty()) {
//                    arr[index] = buckets.get(bucket).poll();
//                    index++;
//                }
//            }
//            for (int bucket = 10; bucket < 20; bucket++) {
//                //while bucket is not empty, remove the elems and put into
//                // the array
//                while (!buckets.get(bucket).isEmpty()) {
//                    arr[index] = buckets.get(bucket).poll();
//                    index++;
//                }
//            }
//        }
//    }
//
//    /**
//     * Private helper method to find the max number of digits for Radix Sort LSD
//     *
//     * @param arr the array to examine for the longest int
//     * @return the number of digits of the longest int to be sorted
//     */
//    private static int findMaxDigits(int[] arr) {
//        int maxDigits = 0;
//        for (int i = 0; i < arr.length; i++) {
//            int curr = Math.abs(arr[i]);
//            int iterCnt = 0;
//            while (curr > 0) {
//                curr = curr / 10;
//                iterCnt++;
//            }
//            if (iterCnt > maxDigits) {
//                maxDigits = iterCnt;
//            }
//        }
//        return maxDigits;
//    }

}
