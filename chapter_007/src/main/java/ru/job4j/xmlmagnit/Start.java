package ru.job4j.xmlmagnit;

import java.io.File;
import java.util.List;
import java.util.Objects;

/**
 * Старт программы.
 *
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 * @since 15.11.2019
 * @version 1.0
 */
public class Start {

    public static void main(String[] args) {
        String sourceXML = System.getProperty("java.io.tmpdir") + "sourceXML.xml";
        String resultXML = System.getProperty("java.io.tmpdir") + "resultXML.xml";
        String scheme = Objects.requireNonNull(
                Start.class.getClassLoader().getResource("schema.xsl")).getPath();
        Config config = new Config();
        config.init();
        List<Entry> entries = null;
        try (StoreSQL storeSQL = new StoreSQL(config)) {
            storeSQL.initConnection();
            storeSQL.generate(1000000);
            entries = storeSQL.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        new StoreXML(new File(sourceXML)).save(entries);
        new ConvertXSLT().convert(new File(sourceXML), new File(resultXML), new File(scheme));
        ParserSAX parserSAX = new ParserSAX();
        parserSAX.parse(new File(resultXML));
    }
}