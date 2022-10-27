# smart-garden

## Tools and Components

### Moisture Sensor

**Specifications**

- Brand: Waveshare;
- Detection depth: 38mm
- Power: 2.0V ~ 5.0V
- Dimension: 20.0mm * 51.0mm
- Mounting holes size**: 2.0mm

Output voltage boosts along with the soil moisture level increases.

---

### Wi-Fi module

WiFi module is based on ESP8266.

**Specifications**
- Brand: Espressif Systems;
- Supply voltage: 3.3V;
- Current in transmission mode: up to 215mA;
- Current during reception: up to 62mA;
- Protocol: 802.11 b/g/n;
- Power in 802.11b mode: +20.5dBm;
- Two outputs SDIO;
- Energy saving and sleep modes to save energy;
- Built-in microcontroller;
- Management by means of AT-commands;
- Operating temperature range: from -40 to +125 degrees Celsius;
- The maximum communication distance is 100 meters.

Useful AT commands

- AT+GMR: return version info.
- AT+RST: restart module.
- AT+CWJAP="SSID","pwd": connect to an AP.
- AT+CWLAP: List available APs.
- AT+CIFSR: get local IP address.
- AT+CWMODE?: query the Wi-Fi mode of ESP32. When working in station mode, the module is acting as a Wi-Fi enabled device connected to an existing Wi-Fi network. When working in Soft AP mode, the module is acting as host of a Wi-Fi network, to which other devices can connect. 1 = Station mode (client); 2 = AP mode (host); 3 = AP + Station mode.
Here's the [list of AT commands](https://docs.espressif.com/projects/esp-at/en/latest/esp32/AT_Command_Set/Wi-Fi_AT_Commands.html).

Sending and receiving data

For testing, we are going to use [Thing Speak](https://thingspeak.com/) API.

Send Data to the server

- AT+CIPMUX=0: command configures the device for a single or multi IP connection. Value can be '0' (single IP) or '1' (multi IP).
- AT+CIPSTART="TCP","api.thingspeak.com",80: command starts a TCP or UDP connection. Parameters: 0..7 - connection number; "TCP" or "UDP"; remote server IP address; remote server port; remote domain name.
- AT+CIPSEND=74: command is used to send the data over the TCP or UDP connection. It should be the length of the following request + 2.
- GET https://api.thingspeak.com/update?api_key=94QWFOP77TOLIIZJ&field1=15
- AT+CIPCLOSE: command closes the connection.

Receive Data from the server

- AT+CIPMUX=0
- AT+CIPSTART="TCP","api.thingspeak.com",80
- AT+CIPSEND=95
- GET https://api.thingspeak.com/channels/1908887/feeds.json?api_key=SJ7A4MBZIJCCDCFQ&results=3

---
### BME280 Environmental Sensor Module

The BME280 is as combined digital humidity, pressure and temperature sensor.

It supports both I2C and SPI interfaces and also is compatible with 3.3V/5V voltage levels.

**Specifications**

| Parameter | Description |
| ----------- | ----------- |
| Operating voltage | 3.3V/5V |
| Communication interface | I2C/SPI |
| Temperature sensing | -40~85°C (resolution 0.01°C, accuracy ±1°C) |
| Humidity sensing | 0~100%RH (resolution 0.008%RH, accuracy ±3%RH, response time 1s, delay ≤2%RH) |
| Barometric pressure sensing | 300~1100 hPa (resolution 0.18Pa, accuracy ±1hPa) |
| Power consumption | <0.1mA@1Hz (H,P,T) |
| Dimension | 27mm x 20mm |
| Mounting hole size | 2.0mm |

**I2C interface**

| Function pin | Arduino interface | Description |
| ----------- | ----------- | ----------- |
| VCC | 3.3V/5V | Power input |
| GND | GND | Ground |
| SDA | A4 | I2C data line |
| SCL | A5 | I2C clock line |
| ADDR | NC/GND | Address chip select (default is high): When the voltage is high, the address is 0 x 77; When the voltage is low, the address is: 0 x 76. |
| CS | NC | NC |

**SPI interface**

| Function pin | Arduino interface | Description |
| ----------- | ----------- | ----------- |
| VCC | 3.3V/5V | 3.3V Power input |
| GND | GND | Ground |
| MOSI | D11 | SPI data input |
| SCK | D13 | SPI clock input |
| MISO | D12 | SPI data output |
| CS | D10 | SPI Chip select, active when voltage is low |

The default drive of the BME280 module is I2C, and the default I2C device address is 0 x 77.

To get readings from the BME280 sensor module we are going to use the [Adafruit_BME280 library](https://github.com/adafruit/Adafruit_BME280_Library). The library can be installed in the Arduino IDE (search request is "Adafruit BME280").

To use the BME280 library, we also need to install the [Adafruit_Sensor library](https://github.com/adafruit/Adafruit_Sensor). The library can be installed in the Arduino IDE (search request is "Adafruit Unified Sensor").

