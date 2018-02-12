# Home Management System

## About

The project includes a web & mobile client to interact with gathered sensor and camera
data from Raspberry PI devices. 

This is achieved through a scalable web API, a web & android client and raspberry pi scripts for different features such as camera data. 

Features include: 
* Real time sensor data, camera data
* Motion detection,control power sockets 
* Define settings for the alarm to trigger
* Receive notifications if the hardware was being tampered with etc. 

Operations can be performed by either "Admin Accounts" for a group of "standard users" using the web application or "self monitoring users" using the android application.

## Technologies

### Android

| **Tech** | **Description** |
|----------|-----------------|
| Java | Programming language used for the android implementation. |
| Gradle | Android dependency management and build scripts. |
| GSON | Used to parse JSON to Java Objects for the android app |
| Android DDP | Client interface to the DDP protocol for android |

### Raspberry PI

| **Tech** | **Description** |
|----------|-----------------|
| DDP | Node DDP Client Library |
| RPI GPIO | GPIO library |
| Energenie | Library for the energenie board |
| ref | Allows integration with C library |
| sensor.so | Sensor binary library |
| mag3110_caliberate.py | Vendor script for calibrating the mag3110 sensor on the sensor board |

### Web Application

| **Tech** | **Description** |
|----------|-----------------|
| MeteorJS | Application framework. Provides backend and frontend boilerplate |
| Blaze | Templating language used for web views |
| Bootstrap | CSS framework for web views |
| LESS | Stylesheet preprocessor |

## Setup / Installation

### Raspberry PI

1. Setup NodeJS & NPM on target device
2. (npm install) Install dependencies for the sub-modules: rpi_breadboard, rpi_camera, rpi_mems_sensor
3. (npm install) Install dependencies for the parent app: rasbpi-app.
4. Depending on the individual hardware setup for the PI, scripts can be run independently (View package.json "scripts")
5. The various device scripts present allow for quick running mirroring the hardware setups during the projects development.

### Webapp

1. Run start.bat/start.sh depending on machine OS.

### Android

1. Standard gradle / android studio workflow.

## License

Apache License v2.0 © [Kyle Williamson ](https://github.com/kyledmw)
