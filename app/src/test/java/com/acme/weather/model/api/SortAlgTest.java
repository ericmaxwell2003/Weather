package com.acme.weather.model.api;


import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class SortAlgTest {


void bubbleSort(int [] arr) {
    for(int i = 0; i < arr.length; i++) {
       for(int j = 0; j < arr.length - i - 1; j++) {
           if(arr[j] > arr[j+1]) {
               int temp = arr[j];
               arr[j] = arr[j+1];
               arr[j+1] = temp;
           }
       }
    }
}


//    void mergeResults(int[] array, int low, int middle, int high) {
//        int n1 = middle - low + 1;
//        int n2 = high - middle;
//
//        int [] left = new int[n1];
//        int [] right = new int[n2];
//
//        for(int i = 0; i < n1; ++i) {
//            left[i] = array[low + i];
//        }
//
//        for(int i = 0; i < n2; ++i) {
//            right[i] = array[middle + i + 1];
//        }
//
//        int i = 0, j = 0;
//        int k = low;
//
//        while(i < left.length && j < right.length) {
//
//            if(left[i] <= right[j]) {
//                array[k] = left[i];
//                i++;
//            } else {
//                array[k] = right[j];
//                j++;
//            }
//            k++;
//        }
//
//        while(i < left.length) {
//            array[k] = left[i];
//            i++;
//            k++;
//        }
//
//        while(j < right.length) {
//            array[k] = right[j];
//            j++;
//            k++;
//        }
//    }
//
//    void mergeSort(int arr[], int low, int high) {
//        if(low < high) {
//
//            int middle = (low + high) / 2;
//
//            mergeSort(arr, low, middle);
//            mergeSort(arr, middle + 1, high);
//
//            mergeResults(arr, low, middle, high);
//        }
//    }



//    public void mergeResults(int [] arr, int low, int middle, int range) {
//
//        int [] left = new int[middle - low + 1];
//        int [] right = new int[range - middle];
//
//        for(int i = 0; i < left.length; i++) {
//            left[i] = arr[low + i];
//        }
//
//        for(int j = 0; j < right.length; j++) {
//            right[j] = arr[middle + 1 + j];
//        }
//
//        int i = 0, j = 0;
//        int k = low;
//
//        while(i < left.length && j < right.length) {
//
//            if(left[i] <= right[j]) {
//                arr[k] = left[i];
//                i++;
//            } else {
//                arr[k] = right[j];
//                j++;
//            }
//            k++;
//        }
//
//        while(i < left.length) {
//            arr[k] = left[i];
//            i++;
//            k++;
//        }
//
//        while(j < right.length) {
//            arr[k] = right[j];
//            j++;
//            k++;
//        }
//    }
//
//
//    public void mergeSort(int [] arr, int low, int range) {
//        if(low < range) {
//
//            int middle = (low + range) / 2;
//
//            mergeSort(arr, low, middle);
//            mergeSort(arr, middle + 1, range);
//
//            mergeResults(arr, low, middle, range);
//        }
//    }


    public void mergeSort(int [] arr, int low, int range) {
        if(low < range) {
            int middle = (range + low) / 2;

            mergeSort(arr, low, middle);
            mergeSort(arr, middle + 1, range);

            mergeResults(arr, low, middle, range);
        }
    }

    private void mergeResults(int [] arr, int low, int middle, int range) {
        int [] left = new int[middle - low + 1];
        int [] right = new int[range - middle];

        for(int i = 0; i < left.length; i++) {
            left[i] = arr[i + low];
        }

        for(int j = 0; j < right.length; j++) {
            right[j] = arr[j + middle + 1];
        }

        int l = 0;
        int r = 0;
        int k = low;

        while(l < left.length && r < right.length) {

            if(left[l] <= right[r]) {
                arr[k] = left[l];
                l++;
            } else {
                arr[k] = right[r];
                r++;
            }
            k++;

        }

        while(l < left.length) {
            arr[k] = left[l];
            k++;
            l++;
        }
        while(r < right.length) {
            arr[k] = right[r];
            k++;
            r++;
        }

    }

    @Test
    public void testBubbleSort() {
        int [] sequence = {64, 34, 25, 12, 22, 11, 90};
        int [] expectedResult = {11, 12, 22, 25, 34, 64, 90};

        bubbleSort(sequence);
        assertArrayEquals(expectedResult, sequence);
    }

    @Test
    public void testSortSequence() {
        int [] sequence = {64, 34, 25, 12, 22, 11, 90};
        int [] expectedResult = {11, 12, 22, 25, 34, 64, 90};

        mergeSort(sequence, 0, sequence.length - 1);
        assertArrayEquals(expectedResult, sequence);
    }


}
