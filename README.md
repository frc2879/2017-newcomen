# 2017-newcomen [![Build Status](https://travis-ci.org/frc2879/2017-newcomen.svg?branch=master)](https://travis-ci.org/frc2879/2017-newcomen)
FRC Team 2879 robot code for the 2017 Steamworks competition, code-named "Newcomen"

To set up this module in your development environment, follow these steps:

1. Clone the repository  
2. Run `gradlew eclipse` for Eclipse, or `gradlew idea` for IntelliJ (Linux/Mac users should use `./gradlew` instead of `gradlew`.)  
3. Edit the `build.gradle` file to reflect your desired configuration (e.g. changing the team number)  

## Build Commands
- ```gradlew build``` will build your Robot Code
- ```gradlew build deploy``` will build and deploy your code to the RoboRIO

While at competition and connected to the robot, run with the `--offline` flag. e.g. `./gradlew build deploy --offline`