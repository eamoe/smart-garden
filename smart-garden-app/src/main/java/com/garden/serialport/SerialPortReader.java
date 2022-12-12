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
}