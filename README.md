# 2017-newcomen [![Build Status](https://travis-ci.org/frc2879/2017-newcomen.svg?branch=master)](https://travis-ci.org/frc2879/2017-newcomen)
FRC Team 2879 robot code for the 2017 Steamworks competition, code-named "Newcomen"

To set up this module in your development environment, follow these steps:

1. Clone the repository  
2. Run `gradlew eclipse` for Eclipse, or `gradlew idea` for IntelliJ (Linux/Mac users should use `./gradlew` instead of `gradlew`.)  
3. Edit the `build.gradle` file to reflect your desired configuration (e.g. changing the team number)  

To build this module, simply run `gradlew build`.
To deploy this module to your Robot, simply run `gradlew deploy`.
If you haven't already, you can deploy [Toast](https://github.com/Open-RIO/ToastAPI) to your Robot by running `gradlew toastDeploy`.
