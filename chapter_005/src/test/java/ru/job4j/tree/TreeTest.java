package ru.job4j.tree;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Тест Tree implements SimpleTree.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 19.09.2019
 * @version 1.0
 */
public class TreeTest {
    Tree<Integer> tree;
    Tree<Integer> nullTree;

    /**
     * Создает для теста два дерева:
     * tree со значением корня-root == 1;
     * nullTree со значением корня-root == null.
     */
    @Before
    public void setUp() {
        tree = new Tree<>(1);
        nullTree = new Tree<>();

        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);

        nullTree.add(1, 2);
        nullTree.add(1, 3);
        nullTree.add(1, 4);
        nullTree.add(4, 5);
        nullTree.add(5, 6);
    }

    /**
     * Если нет child, есть parent - добавляет child в parent.
     */
    @Test
    public void whenNoChildAddChildToExistingParent() {
        List<Integer> expect = List.of(1, 2, 3, 4, 5, 6);

        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
        assertThat(tree.allElements(), is(expect));
    }

    /**
     * Дерево без элементов (root == null).
     * Если нет child, есть parent - добавляет child в parent.
     */
    @Test
    public void whenNoChildAddChildToExistingParentInsideNullTree() {

        List<Integer> expect = List.of(1, 2, 3, 4, 5, 6);

        assertThat(
                nullTree.findBy(6).isPresent(),
                is(true)
        );
        assertThat(nullTree.allElements(), is(expect));
    }

    /**
     * Если нет child, и нет parent -
     * добавляет parent в корень дерева и добавляет child в parent.
     */
    @Test
    public void whenNoChildAndNoParentAddParentAndChildToParent() {
        tree.add(8, 10);

        List<Integer> expect = List.of(1, 2, 3, 4, 8, 5, 10, 6);

        assertThat(
                tree.findBy(8).isPresent(),
                is(true)
        );
        assertThat(
                tree.findBy(10).isPresent(),
                is(true)
        );
        assertThat(tree.allElements(), is(expect));
    }

    /**
     * Дерево без элементов (root == null).
     * Если нет child, и нет parent -
     * добавляет parent в корень дерева и добавляет child в parent.
     */
    @Test
    public void whenNoChildAndNoParentAddParentAndChildToParentInsideNullTree() {
        nullTree.add(8, 10);

        List<Integer> expect = List.of(1, 2, 3, 4, 8, 5, 10, 6);

        assertThat(
                nullTree.findBy(8).isPresent(),
                is(true)
        );
        assertThat(
                nullTree.findBy(10).isPresent(),
                is(true)
        );
        assertThat(nullTree.allElements(), is(expect));
    }

    /**
     * Если есть child, и есть parent - return false.
     */
    @Test
    public void whenParentAndChildExistsResultFalse() {
        boolean result = tree.add(1, 5);

        List<Integer> expect = List.of(1, 2, 3, 4, 5, 6);

        assertThat(result, is(false));
        assertThat(tree.allElements(), is(expect));
    }

    /**
     * Дерево без элементов (root == null).
     * Если есть child, и есть parent - return false.
     */
    @Test
    public void whenParentAndChildExistsResultFalseInsideNullTree() {
        boolean result = nullTree.add(1, 4);
        List<Integer> expect = List.of(1, 2, 3, 4, 5, 6);

        assertThat(result, is(false));
        assertThat(nullTree.allElements(), is(expect));
    }

    /**
     * Если есть child, и нет parent - добавляет parent в корень дерева.
     */
    @Test
    public void whenNoParentAndChildExistAddOnlyParent() {
        tree.add(7, 5);

        List<Integer> expect = List.of(1, 2, 3, 4, 7, 5, 6);

        assertThat(
                tree.findBy(7).isPresent(),
                is(true)
        );
        assertThat(tree.allElements(), is(expect));
    }

    /**
     * Дерево без элементов (root == null).
     * Если есть child, и нет parent - добавляет parent в корень дерева.
     */
    @Test
    public void whenNoParentAndChildExistAddOnlyParentInsideNullTree() {
        nullTree.add(7, 5);

        List<Integer> expect = List.of(1, 2, 3, 4, 7, 5, 6);

        assertThat(
                nullTree.findBy(7).isPresent(),
                is(true)
        );
        assertThat(nullTree.allElements(), is(expect));
    }

    /**
     * Проверка поиска отсутствующего элемента в дереве.
     */
    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );

        assertThat(
                nullTree.findBy(7).isPresent(),
                is(false)
        );
    }

    /**
     * Поиск всех элементов в дереве.
     */
    @Test
    public void returnsAllAddedElements() {
        tree.add(7, 5);

        List<Integer> expect = List.of(1, 2, 3, 4, 7, 5, 6);

        assertThat(tree.allElements(), is(expect));
    }

    /**
     * Проверка дерева на бинарность.
     */
    @Test
    public void whenTreeNoBinaryResultFalseAndTreeBinIsBinaryResultTrue() {
        Tree<Integer> treeBin = new Tree<>(1);

        treeBin.add(1, 2);
        treeBin.add(1, 3);
        treeBin.add(3, 4);
        treeBin.add(3, 5);
        treeBin.add(2, 6);
        treeBin.add(5, 9);
        treeBin.add(5, 10);

        assertThat(tree.isBinary(), is(false));
        assertThat(treeBin.isBinary(), is(true));
    }

    /* ---------------- Тесты итератора -------------- */

    @Test(expected = NoSuchElementException.class)
    public void whenNoSuchElementException() {
        tree.add(7, 5);
        Iterator it = tree.iterator();

        it.next(); // 1
        it.next(); // 2
        it.next(); // 3
        it.next(); // 4
        it.next(); // 7
        it.next(); // 5
        it.next(); // 6
        it.next(); // NoSuchElementException
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenConcurrentModificationException() {
        tree.add(7, 5);
        Iterator it = tree.iterator();

        it.next();
        tree.add(10, 11);
        it.next();
    }

    @Test
    public void whenNoElementsResultShouldBeFalse() {
        Tree<Integer> tree = new Tree<>();
        Iterator it = tree.iterator();
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void hasNextNextSequentialInvocation() {
        tree.add(7, 5);
        Iterator it = tree.iterator();

        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(5));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void testsThatNextMethodDoesntDependsOnPriorHasNextInvocation() {
        tree.add(7, 5);
        Iterator it = tree.iterator();

        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(7));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(6));
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        tree.add(7, 5);
        Iterator it = tree.iterator();

        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(7));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(6));
    }
}