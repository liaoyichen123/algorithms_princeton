package module6.collinear;

import java.util.Arrays;

public class MergeSort {


    private static void sort(int[] inputArray) {
        int inputSize = inputArray.length;
        if (inputSize <= 1) return;
        int mid = inputSize / 2;
        int[] leftArray = new int[mid];
        int[] rightArray = new int[inputSize - mid];
        int rightIndex = 0;
        for (int i = 0; i < inputSize; i++) {
            if (i < mid) {
                leftArray[i] = inputArray[i];
            } else {
                rightArray[rightIndex] = inputArray[i];
                rightIndex++;
            }
        }
        sort(leftArray);
        sort(rightArray);
        merge(leftArray, rightArray, inputArray);
        System.out.println(Arrays.toString(inputArray));
    }


    private static void merge(int[] leftArray, int[] rightArray, int[] inputArray) {
        int leftLength = leftArray.length;
        int rightLength = rightArray.length;
        int leftIndex = 0;
        int rightIndex = 0;
        int inputIndex = 0;
        while (leftIndex < leftLength && rightIndex < rightLength) {
            if (leftArray[leftIndex] < rightArray[rightIndex]) {
                inputArray[inputIndex] = leftArray[leftIndex];
                leftIndex++;
                inputIndex++;
            } else {
                inputArray[inputIndex] = rightArray[rightIndex];
                rightIndex++;
                inputIndex++;
            }
        }
        while (leftIndex < leftLength) {
            inputArray[inputIndex] = leftArray[leftIndex];
            inputIndex++;
            leftIndex++;
        }
        while (rightIndex < rightLength) {
            inputArray[inputIndex] = rightArray[rightIndex];
            inputIndex++;
            rightIndex++;
        }
    }


    public static void main(String[] args) {
        int[] unsortedInts = {3,1,0,2};
        sort(unsortedInts);
    }
}

