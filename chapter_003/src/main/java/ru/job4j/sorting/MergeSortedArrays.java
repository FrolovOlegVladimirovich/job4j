package ru.job4j.sorting;

/**
 * Объединить два отсортированных массива в один.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 14.08.2019
 * @version 1.0
 */
public class MergeSortedArrays {

    int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int res = 0;
        int l = 0;
        int r = 0;
        while (res != result.length) {
            if (l < left.length && r < right.length) {
                if (left[l] <= right[r]) {
                    result[res++] = left[l++];
                } else {
                    result[res++] = right[r++];
                }
            } else {
                if (r != right.length) {
                    result[res++] = right[r++];
                } else if (l != left.length) {
                    result[res++] = left[l++];
                }
            }
        }
        return result;
    }
}
