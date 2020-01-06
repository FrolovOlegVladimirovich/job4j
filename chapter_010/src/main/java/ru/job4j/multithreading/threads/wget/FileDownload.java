package ru.job4j.multithreading.threads.wget;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * Speed limit file downloader.
 * @author Oleg Frolov (frolovolegvladimirovich@gmail.com)
 */
public class FileDownload {
    private final String url;
    private final double kByte;

    /**
     * Default constructor.
     * @param url URL.
     * @param kBytePerSecond Speed limit in kilobytes per second.
     */
    public FileDownload(String url, double kBytePerSecond) {
        this.url = url;
        this.kByte = kBytePerSecond;
    }

    /**
     * Starts program.
     */
    public void start() {
        var splitUrl = url.split("/");
        var fileName = splitUrl[splitUrl.length - 1];
        try (BufferedInputStream input = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream output = new FileOutputStream(fileName)) {
            byte[] dataBuffer = new byte[(int) (kByte * 1024)];
            int bytesRead;
            System.out.println(String.format("Downloading file \"%s\"", fileName));
            var totalTime = System.nanoTime();
            var timeNano = System.nanoTime();
            while ((bytesRead = input.read(dataBuffer, 0, (int) (kByte * 1024))) != -1) {
                timeNano = (System.nanoTime() - timeNano);
                if (timeNano < 1_000_000_000) {
                    try {
                        Thread.sleep((1_000_000_000 - timeNano) / 1_000_000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                output.write(dataBuffer, 0, bytesRead);
            }
            System.out.println(
                    String.format("Total time: %d milliseconds", (System.nanoTime() - totalTime) / 1_000_000)
            );
        } catch (UnknownHostException | MalformedURLException e) {
            System.out.println("Wrong URL. Try again.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Two arguments supposed: URL speed_limit
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Invalid number of arguments.");
        } else {
            try {
                var url = args[0];
                var speed = Double.parseDouble(args[1]);
                new FileDownload(url, speed).start();
            } catch (NumberFormatException e) {
                System.out.println("Wrong number format.");
                e.printStackTrace();
            }
        }
    }
}