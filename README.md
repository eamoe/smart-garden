# smart-garden

### Moisture Sensor
#### Specifications:
- Brand: Waveshare;
- Detection depth: 38mm
- Power: 2.0V ~ 5.0V
- Dimension: 20.0mm * 51.0mm
- Mounting holes size**: 2.0mm

Output voltage boosts along with the soil moisture level increases.

### Wi-Fi module
WiFi module is based on ESP8266.
#### Specifications:
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

#### Useful AT commands
- AT+GMR: return version info.
- AT+RST: restart module.
- AT+CWJAP="SSID","pwd": connect to an AP.
- AT+CWLAP: List available APs.
- AT+CIFSR: get local IP address.
- AT+CWMODE?: query the Wi-Fi mode of ESP32. When working in station mode, the module is acting as a Wi-Fi enabled device connected to an existing Wi-Fi network. When working in Soft AP mode, the module is acting as host of a Wi-Fi network, to which other devices can connect. 1 = Station mode (client); 2 = AP mode (host); 3 = AP + Station mode.
Here's the [list of AT commands](https://docs.espressif.com/projects/esp-at/en/latest/esp32/AT_Command_Set/Wi-Fi_AT_Commands.html).

#### Sending and receiving data
For testing, we are going to use [Thing Speak](https://thingspeak.com/) API.

#### Send Data to the server
- AT+CIPMUX=0: command configures the device for a single or multi IP connection. Value can be '0' (single IP) or '1' (multi IP).
- AT+CIPSTART="TCP","api.thingspeak.com",80: command starts a TCP or UDP connection. Parameters: 0..7 - connection number; "TCP" or "UDP"; remote server IP address; remote server port; remote domain name.
- AT+CIPSEND=74: command is used to send the data over the TCP or UDP connection. It should be the length of the following request + 2.
- GET https://api.thingspeak.com/update?api_key=94QWFOP77TOLIIZJ&field1=15
- AT+CIPCLOSE: command closes the connection.

#### Receive Data from the server
- AT+CIPMUX=0
- AT+CIPSTART="TCP","api.thingspeak.com",80
- AT+CIPSEND=95
- GET https://api.thingspeak.com/channels/1908887/feeds.json?api_key=SJ7A4MBZIJCCDCFQ&results=3

