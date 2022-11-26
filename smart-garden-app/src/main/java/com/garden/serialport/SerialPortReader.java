package com.garden.serialport;

import com.fazecast.jSerialComm.SerialPort;
import com.garden.datastorage.LocalStorage;

public class SerialPortReader {
    static final String PORT_DESCRIPTOR = "/dev/cu.usbmodem101"; // Replace with a correct port
    static final int BAUD_RATE = 9600; // Replace with the used baud rate

    public SerialPortReader() {
    }

    public static void run(LocalStorage localStorage) {
        DataController controller = new DataController(localStorage);
        SerialPort serialPort = SerialPortService.getSerialPort(PORT_DESCRIPTOR, BAUD_RATE);
        serialPort.addDataListener(controller);
    }

    /*public static void main(String[] args) throws InterruptedException {

        LocalStorageMap localStorage = new LocalStorageMap();

        DataController controller = new DataController(localStorage);
        SerialPort serialPort = SerialPortService.getSerialPort(PORT_DESCRIPTOR, BAUD_RATE);
        serialPort.addDataListener(controller);

        while (true) {
            Thread.sleep(5000);

            Pair<Long, String> item = localStorage.getFirstItem();
            Long key = item.getKey();
            String value = item.getValue();

            if (key != 0L) {
                Date date = new Date(key);
                DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
                String dateFormatted = formatter.format(date);
                System.out.println(dateFormatted + "   " + value);

                localStorage.removeItem(key);
            }
        }

    }*/
}