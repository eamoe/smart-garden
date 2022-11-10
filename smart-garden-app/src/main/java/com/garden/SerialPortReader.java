package com.garden;

import com.fazecast.jSerialComm.SerialPort;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class SerialPortReader {
    static final String PORT_DESCRIPTOR = "/dev/cu.usbmodem101"; // Replace with a correct port
    static final int BAUD_RATE = 9600; // Replace with the used baud rate

    public static void main(String[] args) throws InterruptedException {

        LocalStorageMap localStorage = new LocalStorageMap();

        DataController controller = new DataController(localStorage);
        SerialPort serialPort = SerialPortService.getSerialPort(PORT_DESCRIPTOR, BAUD_RATE);
        serialPort.addDataListener(controller);

        while (true) {
            Thread.sleep(25000);
            Map<Long, String> dataMap = localStorage.getStorageMap();
            if (!dataMap.isEmpty()) {
                SortedSet<Long> keys = new TreeSet<>(dataMap.keySet());
                for (Long key : keys) {
                    String value = dataMap.get(key);
                    Date date = new Date(key);
                    DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                    formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
                    String dateFormatted = formatter.format(date);
                    System.out.println(dateFormatted + "   " + value);
                    dataMap.remove(key);
                }
            }
        }

    }
}