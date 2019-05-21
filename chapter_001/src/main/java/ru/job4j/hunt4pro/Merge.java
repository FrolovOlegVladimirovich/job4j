package ru.job4j.hunt4pro;

public class Merge {
    public int[] merge(int[] left, int[] right) {
        int[] rsl = new int[left.length + right.length];
        int i = 0;
        int j = 0;
        if (rsl.length != 0) {
            for (; i < left.length; i++, j++) {
                rsl[i] = left[i];
            }
            for (int k = 0; k < right.length && j < rsl.length; k++, j++) {
                rsl[j] = right[k];
            }
            for (int k = 1; (rsl.length - k) != 1; k++) {
                for (int l = 0; l < rsl.length - k; l++) {
                    if (rsl[l] > rsl[l + 1]) {
                        int tmp = rsl[l];
                        rsl[l] = rsl[l + 1];
                        rsl[l + 1] = tmp;
                    }
                }
            }
        }
        return rsl;
    }
}
