# heroscribe

Changes in HeroScribeEnhanced-1.0, by 2011 Jason Allen.

Using JDK 17 and maven.

Made for home use.

## Building & running

### Maven & Java

- Amazon Corretto JDK 17 - https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/what-is-corretto-17.html
- Maven 3 - https://maven.apache.org/

### Using asdf (Linux)

The project includes a .tool-versions for asdf.
See https://asdf-vm.com/ for how to use asdf.

### Build

In the project's root folder:

    mvn clean package

This will create a `target` folder with a file called `heroscribe-bundle.zip`, which contains the HeroScribeEnhanced ready to use.

## Running HeroScribe

Unzip the bundle and run

    java -jar heroscribe.jar

### Postscript

Install postscript for high quality PDF generation.

## Links

- The original HeroScribe: <http://www.heroscribe.org/heroquest.html>

- The original HeroScribe Enhanced (site down): <http://www.propvault.com/heroscribe/>

## Legal

HeroQuest Copyright 1989, 1990 Milton Bradley Company. All Rights Reserved. Nothing on this project is intended as a challenge to the rights of the Milton Bradley Company/Hasbro, Inc. in regard to HeroQuest.