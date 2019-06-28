package Algorithms.Sorting;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static String[] bigSorting(String[] arr) {
        // Complete this function
        int length = arr.length;
        if (length >= 2) {
            int mid = length / 2;
            String[] left = copyArray(arr, 0, mid - 1);
            String[] right = copyArray(arr, mid, arr.length - 1);

            bigSorting(left);
            bigSorting(right);
            merge(arr, left, right, mid);
        }
        return arr;
    }

    static String[] copyArray(String[] arr, int start, int end){
        String[] newArr = new String[end - start + 1];
        int index = 0;
        for(int i=start; i <= end; i++){
            newArr[index] = arr[i];
            index++;
        }
        return newArr;
    }

    static void merge(String[] arr, String[] left, String[] right, int mid){

        int leftIndex = 0;
        int rightIndex = 0;
        int current = 0;

        while(leftIndex < mid && rightIndex < arr.length - mid) {
            if (Float.valueOf(left[leftIndex]).compareTo
                    (Float.valueOf(right[rightIndex])) < 0){
                arr[current] = left[leftIndex];
                leftIndex++;
            } else {
                arr[current] = right[rightIndex];
                rightIndex++;
            }
            current++;
        }

        while(Float.valueOf(leftIndex).compareTo(Float.valueOf(mid)) < 0){
            arr[current] = left[leftIndex];
            leftIndex++;
            current++;
        }
        while(Float.valueOf(rightIndex).compareTo(Float.valueOf(mid)) < 0){
            arr[current] = right[rightIndex];
            rightIndex++;
            current++;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] arr = new String[n];
        for(int arr_i = 0; arr_i < n; arr_i++){
            arr[arr_i] = in.next();
        }
        String[] result = bigSorting(arr);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + (i != result.length - 1 ? "\n" : ""));
        }
        System.out.println("");


        in.close();
    }
}