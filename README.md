# fishy
To run the Fishy game, you need to add the Slick2D library to your project path.

The following steps were taken to include Slick2D into Eclipse:

[Download steps]
- Download Slick2D from http://slick.ninjacave.com/slick.zip
- Unpack the zip file to a preferred location, for example at /lib/slick
- Navigate to the 'lib' folder inside the unpacked folder
- Unpack the native-{platform}.jar file for your platform OS

[Eclipse steps]
- Open the project properties in Eclipse and navigate to "Java build Path"
- Click on "Add new library", followed by "User Library"
- Create a new user library named Slick2D and click "Next"
- Select the added "Slick2D" entry and click on "Add external JARs"
- Navigate to the location of the unpacked zip-file and open the folder 'lib'
- Select the following files: lwjgl.jar, slick.jar, jinput.jar and lwjgl_util.jar
- Select the "Native library location" in the "Slick2D" entry and click on 'edit'
- Navigate to the unpacked 'native' folder and select it
- Click on 'Apply' to update the Project Path and you should be able to use Slick2D

[![Build Status](https://travis-ci.org/martijn9612/fishy.svg?branch=development)](https://travis-ci.org/martijn9612/fishy)
[![Coverage Status](https://coveralls.io/repos/martijn9612/fishy/badge.svg?branch=development&service=github)](https://coveralls.io/github/martijn9612/fishy?branch=development)
