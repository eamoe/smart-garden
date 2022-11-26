package com.garden.serialport;

import com.fazecast.jSerialComm.SerialPort;

public class SerialPortService {

    private SerialPortService() {

    }

    public static SerialPort getSerialPort(String portDescriptor, int baudRate) {

        SerialPort serialPort = SerialPort.getCommPort(portDescriptor);
        serialPort.setComPortParameters(baudRate, Byte.SIZE, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY);
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);

        boolean isBusy = serialPort.openPort();
        if (!isBusy) {
            throw new IllegalStateException("Cannot open port");
        }

        return serialPort;
    }
}
