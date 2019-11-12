package ru.job4j.xmlmagnit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

/**
 * Приложение парсит выходной файл из ConvertXSLT.convert
 * и выводит арифметическую сумму значений всех атрибутов field в консоль.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 15.11.2019
 * @version 1.0
 */
public class ParserSAX {
    private final List<Long> values = new ArrayList<>();

    /**
     * Парсит атрибут "field" в файле source,
     * суммирует значение всех атрибутов.
     * Результат выводит в консоль.
     *
     * @param source XML файл для парсинга.
     * @return Сумма значений всех атрибутов "field".
     */
    public Long parse(File source) {
        DefaultHandler handler = new DefaultHandler() {
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                String result = attributes.getValue("field");
                if (result != null && !result.isEmpty()) {
                    result.lines().forEach(line -> values.add(Long.parseLong(line)));
                }
            }
        };
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(source, handler);
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
        Long result = values.stream().reduce(Long::sum).get();
        System.out.printf("%s %s", "Result is:", result);
        return result;
    }
}