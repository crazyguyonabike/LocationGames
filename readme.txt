This is a standard Maven project. To test run:

$M2_HOME/bin/mvn clean test

If the standard Grizzly port is already in use, you can change the port used with:

$M2_HOME/bin/mvn clean test -DargLine="-Djersey.test.port=7985"
