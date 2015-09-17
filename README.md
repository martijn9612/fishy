# fishy
To run the Fishy game, you need to add the Slick2D library to your project path.

The following steps were taken to include Slick2D into Eclipse:

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

[Intellij steps]
- Files>Project Structure>Modules
- Click the green plus button>JARs or Directories
- Add the JAR files from the libs folder of the project
- Close the Project structure window
- Right click on the pom.xml>maven>Reimport

- Run Main.main();
- Edit run configurations in the upper right corner
- Add "-Djava.library.path=libs/native-windows" in the VM options
- Click OK and you are ready to go

[![Build Status](https://travis-ci.org/martijn9612/fishy.svg?branch=development)](https://travis-ci.org/martijn9612/fishy)
[![Coverage Status](https://coveralls.io/repos/martijn9612/fishy/badge.svg?branch=development&service=github)](https://coveralls.io/github/martijn9612/fishy?branch=development)
