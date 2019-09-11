package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static ru.job4j.list.CycleAlgorithm.hasCycle;

public class CycleAlgorithmTest {
    CycleAlgorithm.Node<Integer> first = new CycleAlgorithm.Node<>(1);
    CycleAlgorithm.Node<Integer> second = new CycleAlgorithm.Node<>(2);
    CycleAlgorithm.Node<Integer> third = new CycleAlgorithm.Node<>(3);
    CycleAlgorithm.Node<Integer> fourth = new CycleAlgorithm.Node<>(4);
    CycleAlgorithm.Node<Integer> fifth = new CycleAlgorithm.Node<>(5);
    CycleAlgorithm.Node<Integer> sixth = new CycleAlgorithm.Node<>(6);
    CycleAlgorithm.Node<Integer> seventh = new CycleAlgorithm.Node<>(7);
    CycleAlgorithm.Node<Integer> eighth = new CycleAlgorithm.Node<>(8);
    CycleAlgorithm.Node<Integer> ninth = new CycleAlgorithm.Node<>(9);
    CycleAlgorithm.Node<Integer> tenth = new CycleAlgorithm.Node<>(10);

    @Test
    public void whenLoopedAroundResultTrue() {
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = first;
        assertThat(hasCycle(first), is(true));
    }

    @Test
    public void whenLoopedFromTheMiddleResultTrueWith4Elements() {
        first.next = second;
        second.next = third;
        third.next = second;
        fourth.next = null;
        assertThat(hasCycle(first), is(true));
    }

    @Test
    public void whenLoopedFromTheMiddleResultTrueWith10Elements() {
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = sixth;
        sixth.next = seventh;
        seventh.next = eighth;
        eighth.next = third;
        ninth.next = tenth;
        tenth.next = null;
        assertThat(hasCycle(first), is(true));
    }

    @Test
    public void whenListIsNotLoopedResultFalse() {
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = sixth;
        sixth.next = seventh;
        seventh.next = eighth;
        eighth.next = ninth;
        ninth.next = tenth;
        tenth.next = null;
        assertThat(hasCycle(first), is(false));
    }
}