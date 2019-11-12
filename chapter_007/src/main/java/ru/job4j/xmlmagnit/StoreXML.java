package ru.job4j.xmlmagnit;

import java.io.File;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Генерация XML из базы данных.
 *
 * Для создания XML используется технология JAXB.
 * Классы Entries, Entry описываются JavaBean.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 13.11.2019
 * @version 1.0
 */
public class StoreXML {
    private final File target;

    /**
     * Конструктор принимает XML файл, в который будут сохраняться данные.
     * @param target XML файл, в который будут сохраняться данные.
     */
    public StoreXML(File target) {
        this.target = target;
    }

    /**
     * Сохраняет данные из entryList в файл target.
     *
     * entryList упаковывается в Entries для создания следующей структуры XML:
     * <entries>
     * <entry>
     * <field>значение поля field</field>
     * </entry>
     * ...
     * <entry>
     * <field>значение поля field</field>
     * </entry>
     * </entries>
     *
     * @param entryList Список Entry со значениями, полученными из колонки "field" базы данных.
     */
    public void save(List<Entry> entryList) {
        Entries entries = new Entries(entryList);
        try {
            JAXBContext context = JAXBContext.newInstance(Entries.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(entries, target);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}