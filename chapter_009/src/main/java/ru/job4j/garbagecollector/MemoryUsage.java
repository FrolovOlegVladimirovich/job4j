package ru.job4j.garbagecollector;

/**
 * Демонстрация работы Garbage Collector.
 *
 *  Пустой объект без полей в 64-х разрядной системе занимает 16 байт памяти.
 *
 *  Объект User с полем String name = «test»:
 *
 *  1) new User():
 *  Заголовок: 16 байт
 *  Ссылочная переменная String: 4 байт
 *  Выравнивание для кратности 8 байт: 4 байт
 *  Итого: 24 байт
 *
 *  2) new String(«name»):
 *  Заголовок: 16 байт
 *  Ссылочная переменная byte[] value: 4 байт
 *  Примитив byte coder: 1 байт
 *  Примитив int hash: 4 байт
 *  Выравнивание для кратности 8 байт: 7 байт
 *  Итого: 32 байт
 *
 *  3) new byte[]:
 *  Заголовок: 16 байт
 *  На длину массива: 4 байт
 *  Примитивы byte: 4 байт
 *  Итого: 24 байт
 *
 *  Итого общий размер объекта new User(«test») == 80 байт
 *
 *  Программа запускает цикл, в котором создается объект new User("test") без ссылок на данный объект.
 *
 *  Максимальное количество объектов new User("test") с ключом -Xmx5m ~ 30500 шт. (до появления OutOfMemoryError:
 *  Java heap space). То есть если выделить для хранения объектов виртуальной машине 5 мегабайт,
 *  то при этом количестве объектов память будет исчерпана.
 *
 *  VM вызывает GC примерно на 4300 созданных объектах new User("test").
 */
public class MemoryUsage {
    public static void main(String[] args) {
        info();
        for (int i = 0; i < 4300; i++) {
            new User("test");
            System.out.println(i);
        }
        info();
    }

    public static void info() {
        int mb = 1024 * 1024;

        //Getting the runtime reference from system
        Runtime runtime = Runtime.getRuntime();

        System.out.println("##### Heap utilization statistics [MB] #####");

        //Print used memory
        System.out.println("Used memory: "
                + (runtime.totalMemory() - runtime.freeMemory()) / mb);

        //Print free memory
        System.out.println("Free memory: "
                + runtime.freeMemory() / mb);

        //Print total available memory
        System.out.println("Total memory: "
                + runtime.totalMemory() / mb);

        //Print maximum available memory
        System.out.println("Max memory: "
                + runtime.maxMemory() / mb);
    }
}