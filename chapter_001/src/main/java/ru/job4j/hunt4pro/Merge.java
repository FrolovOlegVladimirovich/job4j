package ru.job4j.hunt4pro;

/**
 * Объединяет два отсортированных масcива в один алгоритмом сортировки слиянием.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 22.05.2019
 * @version 2.0
 *
 * i - итератор в массиве left
 * j - итератор в массиве right
 * k - итератор в массиве array
 */
public class Merge {
  public int[] merge(int[] left, int[] right) {
    int i = 0;
    int j = 0;
    int k = 0;
    int length = left.length + right.length;
    int[] array = new int[length];
    while (k < length) {
      if (i == left.length) {
        array[k++] = right[j++];
      } else if (j == right.length) {
        array[k++] = left[i++];
      } else if (left[i] <= right[j]) {
        array[k++] = left[i++];
      } else {
        array[k++] = right[j++];
      }
    }
    return array;
  }
}
