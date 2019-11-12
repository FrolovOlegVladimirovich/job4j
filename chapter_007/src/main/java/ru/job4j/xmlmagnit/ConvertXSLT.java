package ru.job4j.xmlmagnit;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

/**
 * Преобразование XML файла, полученного из StoreXML в файл другого XML формата через XSLT.
 *
 * Исходный формат:
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
 * Результат в формате:
 * <entries>
 * <entry field="значение поля field"/>
 * ...
 * <entry field="значение поля field"/>
 * </entries>
 *
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 13.11.2019
 * @version 1.0
 */
public class ConvertXSLT {

    /**
     * Читает файл source и преобразовывает его в файл destination за счет XSL схемы scheme.
     *
     * @param source Исходный XML файл.
     * @param destination Итоговый XML после преобразования.
     * @param scheme XSL схема для конвертации source XML в destination XML.
     */
    public void convert(File source, File destination, File scheme) {
        TransformerFactory factory = TransformerFactory.newInstance();
        try {
            Transformer transformer = factory.newTransformer(new StreamSource(new FileInputStream(scheme)));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
            transformer.transform(new StreamSource(new FileInputStream(source)), new StreamResult(destination));
        } catch (FileNotFoundException | TransformerException e) {
            e.printStackTrace();
        }
    }
}