# Instalation instructions

## Windows

### Core requirements regardless of language to use

**Docker for Desktop**:

1) Download and install from https://www.docker.com/products/docker-desktop

**Java 11**

1) Download from this link: https://download.java.net/openjdk/jdk11/ri/openjdk-11+28_windows-x64_bin.zip

2) Extract the zip file into a folder, e.g. `C:\Program Files\Java\`, and it will create a jdk-11 folder (where the bin folder is a direct sub-folder). You may need Administrator privileges to extract the zip file to this location.

3) Set a PATH:

   Select Control Panel and then System.
   Click Advanced and then Environment Variables.
   Add the location of the bin folder of the JDK installation to the PATH variable in System Variables.
   The following is a typical value for the `PATH` variable: `C:\WINDOWS\system32;C:\WINDOWS;"C:\Program Files\Java\jdk-11\bin"`

4) Set JAVA_HOME:

   Under System Variables, click New.
   Enter the variable name as JAVA_HOME.
   Enter the variable value as the installation path of the JDK (without the bin sub-folder).
   Click OK.
   Click Apply Changes.

5) Configure the JDK in your IDE of choice (e.g. IntelliJ, Eclipse or VS Code)

6) You are set.

To see if it worked, open up the Command Prompt and type java -version and see if it prints your newly installed JDK.

**Maven 3**

1) Download https://apache.dattatec.com/maven/maven-3/3.8.1/binaries/apache-maven-3.8.1-bin.zip

2) Extract the zip file into a folder, e.g. C:\Program Files\maven-3.8.1\ and it will create an apache-maven-3.8.1 folder (where the bin folder is a direct sub-folder). You may need Administrator privileges to extract the zip file to this location.

3) Set a PATH:

   Select Control Panel and then System.
   Click Advanced and then Environment Variables.
   Add the location of the bin folder of the maven installation to the PATH variable in System Variables. The following is a typical value for the PATH variable: C:\WINDOWS\system32;C:\WINDOWS;"C:\Program Files\Java\apache-maven-3.8.1\bin"


To see if it worked, open up the Command Prompt and type mvn -v and see if it prints your newly installed mvn.

**Kafka**

1) Download these files https://downloads.apache.org/kafka/3.5.1/kafka_2.13-3.5.1.tgz. this is very important because for Windows, these contain all the classes already compiled (the other one doesn't)

2) Unzip the folder and put it in your home directory (`C:\Users\<CurrentUserName>`), and rename it to a simple name like kafka. This is key because the CLASSPATH internally needs to be short enough and not pass the 8100 characters for CMD in Windows 10.

3) Open the `C:\Users\<CurrentUserName>\kafka\bin\windows` directory on the command line of your choice with administrative permissions.

4) You can run any of the bat scripts that you need in the course. I suggest adding these folder to your PATH so you can run it from everywhere.


## Mac

### Core requirements regardless of language to use


**Docker for Desktop:**

1) Download and install from https://www.docker.com/products/docker-desktop

**Java 11**

1) `brew install openjdk@11`
   1') Verify:

```sh
 /usr/libexec/java_home -V
```

2) Set JAVA_HOME:
```sh
export JAVA_HOME=`/usr/libexec/java_home -v WHAT_EVER_VERSION_YOU_HAVE`
```

**Maven 3**

1) `brew install maven`

**Kafka**

1) Download: https://downloads.apache.org/kafka/3.5.1/kafka_2.13-3.5.1.tgz
2) `tar xvfz ~/Downloads/kafka_2.13-3.5.1.tgz`
3) Move that folder wherever you like :)

## Linux (Debian)

### Core requirements regardless of language to use


**Docker for Desktop:**

1) Download and install from https://docs.docker.com/desktop/linux/install/

**Java 11**

1) `sudo apt install openjdk-11-jdk`
   1a) Verify:

```sh
 /usr/libexec/java_home -V
```

2) Set JAVA_HOME:
```sh
export JAVA_HOME=`/usr/libexec/java_home -v WHAT_EVER_VERSION_YOU_HAVE`
```

**Maven 3**

1) `sudo apt-get install maven`

**Kafka**

1) Download: https://downloads.apache.org/kafka/3.5.1/kafka_2.13-3.5.1.tgz
2) `tar xvfz ~/Downloads/kafka_2.13-3.5.1.tgz`
3) Move that folder wherever you like :)
