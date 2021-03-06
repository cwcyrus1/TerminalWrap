Project: BlueJWrap
Use:     To wrap text of Strings or Arrays in the BlueJ terminal window
Author:  Cyrus Wong, 11 March 2021
package: twrap
classes: TerminalWrap, DisplayInfo
modules: ConfigurationSetup

How it works, & Instructions:
    The package twrap does the following steps to determine the wrapping "threshold" - the display length of the string or array of objects being printed that is around half of the screen width
    - For each run, a new TerminalWrap instance is constructed, and performs the following:
      - Fetches the monitors/displays available on the device
      - Compares the data to that of the "TerminalWrapData.bisv" and "DisplayInformation.bisv" files
        - If there are any discrepancies, ConfigurationSetup is called by the TerminalWrap constructor
        - Prompts user to choose the screen/display they want the Strings to be formatted to, and requests the font size (default is 10)
          - If there is only one display, it is automatically chosen (no input required)
          - Where to see font size:
            - Inside of the Editor, Options -> Preferences
            - Inside of the Project Viewer, Tools -> Preferences
      - Overwrites the new data onto the two files, even if there are no discrepancies
      - The data fetched from "TerminalWrapData.bisv" file (after being re-written) are sent to the TerminalWrap constructor, where the values are separated and scaled according to logarithmic regression
        - Scales approximately from font size to pixel and uses that scaling to find monitor width (in pixels) to half of the width, in terms of String length
    - Uses the collected information to print and wrap text; after each call of the printWrap() method, the output automatically goes to a new line
    - If used for educational purposes, to maintain similar functionality to the printing of arrays element by element, have students append to a String what would normally be printed, and call "printWrap(String string, boolean equal)"
    
    Import Statement: ConfigurationSetup and DisplayInfo need not be imported; they are all accessed internally by TerminalWrap: "import twrap.TerminalWrap;"
    
    Download Files:
    - When the files for the package are downloaded, place them INSIDE of the folder where the java project (that you want to implement wrapping to) files are stored
    - When run, a check digit in the "TerminalWrapData.bisv" value is found, and allows for first-time setup - requests for monitor choice (if applicable) and font size
    - If there are no more hardware changes, every future run will not request for font size or display choice
      - To edit the configuration, run one of the following:
        - "TerminalWrap.editSetup()"
          - requests for monitor choice and font size again
        - "TerminalWrap.defaultSetup()"
          - sets the monitor choice to monitor 0 in the list, usually the built-in or primary display, and sets the font size to 10
    - If hardware changes are detected, the configuration setup will go again
    - For every run of the program, the data is checked and referenced
    - Information about the files:
      - "TerminalWrapData.bisv"
        - list of four integers: (1)-Monitor Choice; (2)-Unscaled Font Size; (3)-Check Value for first-time setup; (4)-Number of screens detected in the previous run
      - "DisplayInformation.bisv"
        - rows of information - one row for each display detected, records: (1)-Monitor reference number; (2)-Monitor width; (3)-Monitor height
    
    Syntax and Initialization:
    - To use, first import and initialize the following:
      - "import twrap.TerminalWrap;"
      - "TerminalWrap 'name' = new TerminalWrap()"
    - If data needs to be edited, call the class "TerminalWrap", rather than the object "'name'"; call before the object is constructed (first line in main)
    - Imports, Fields and Behavior:
      - TerminalWrap
        - import java.util.Scanner;
        - import java.util.Arrays;
        - import java.util.ArrayList;
        - import java.awt.DisplayMode;
        - import java.awt.GraphicsEnvironment;
        
        - private static int monitor                                            |choice of monitor
        - private static int textSize                                           |scaled text size        
        - private static int widthWindow                                        |scaled width of half of the monitor's width      
        - private static int toConfigure                                        |represents check digit from "TerminalWrapData.bisv"       
        - private static String fileInfo                                        |the information extracted from "TerminalWrapData.bisv"
        
        - public TerminalWrap()                                                 |calls "ConfigurationSetup.getData()"
                                                                                |assigns values to the fields, uses "fontScale(int fontSize)"
                                                                                |checks for hardware changes or first-time setup
                                                                                |- calls "ConfigurationSetup.ifChange()", "ConfigurationSetup.getToChange()", "ConfigurationSetup.setup()", and "ConfigurationSetup.getData()"
                                                                                |calls "getMonitorWidth()"
        
        - private static int fontScale(int fontSize)                            |scales the font, from the font size input by user/read from file, to the approximate pixel width        
        - public static void editSetup()                                        |calls "ConfigurationSetup.editSetup()"        
        - public static void defaultSetup()                                     |calls "ConfigurationSetup.defaultSetup()"        
        - public static int getWidthWindow()                                    |accessor for "widthWindow" field        
        - public static int getTextSize()                                       |accessor for "textSize" field        
        - private static double getMonitorWidth()                               |gets the width of the chosen display, from "monitor" field       
        - public static void printWrap(String string, boolean equal)            |calls "choice(String str, boolean equal)", after changing the first argument into its "toString()" format (for custom classes, "toString" not provided)
                                      (Object[] objects, boolean equal)         |if "equal" is not provided, default is false
                                      (Object[][] objectss, boolean equal)      |
                                      (ArrayList<?> objects, boolean equal)     |
                                      (int[] ints, boolean equal)               |
                                      (int[] intss, boolean equal)              |
                                      (double[] doubles, boolean equal)         |
                                      (double[][] doubless, boolean equal)      |
                                      (boolean[] booleans, boolean equal)       |
                                      (boolean[][] booleanss, boolean equal)    |
                                      (char[] chars, boolean equal)             |
                                      (char[][] charss, boolean equal)          |
                                      (byte[] bytes, boolean equal)             |
                                      (byte[][] bytess, boolean equal)          |
                                      (short[] shorts, boolean equal)           |
                                      (short[][] shortss, boolean equal)        |
                                      (long[] longs, boolean equal)             |
                                      (long[][] longss, boolean equal)          |
                                      (float[] floats, boolean equal)           |
                                      (float[][] floatss, boolean equal)        |
                                      (String string)                           |
                                      (Object[] objects)                        |
                                      (Object[][] objectss)                     |
                                      (ArrayList<?> objects)                    |
                                      (int[] ints)                              |
                                      (int[] intss)                             |
                                      (double[] doubles)                        |
                                      (double[][] doubless)                     |
                                      (boolean[] booleans)                      |
                                      (boolean[][] booleanss)                   |
                                      (char[] chars)                            |
                                      (char[][] charss)                         |
                                      (byte[] bytes)                            |
                                      (byte[][] bytess)                         |
                                      (short[] shorts)                          |
                                      (short[][] shortss)                       |
                                      (long[] longs)                            |
                                      (long[][] longss)                         |
                                      (float[] floats)                          |
                                      (float[][] floatss)                       |
                                      
        - private static void printer(String str, int wrapIndex)                |prints the String, wrapping once the "wrapIndex" (default "widthWindow - 3") has been reached or surpassed; automatically goes to the next line afterwards        
        - private static void printerEqual(String str)                          |calculates the wrapIndex for the String str, with a tolerance of +/- 3 tokens, to try and make the printer print nearly equal lengths for each wrapped row        
        - private static void choice(String str, boolean equal)                 |if "true" is passed for the second argument, "printerEqual(String str)" is called; else, "printer(String str, int wrapIndex)" is called
     
      - DisplayInfo
        - private int displayNumber                                             |the number corresponding to the display referenced by the instance of "DisplayInfo"        
        - private int displayWidth                                              |the width of the corresponding display (pixels)       
        - private int displayHeight                                             |the height of the corresponding display (pixels)       
        
        - public DisplayInfo(int num, int w, int h)                             |the constructor for "DisplayInfo", assigns values to the fields        
        
        - public int getDispNum()                                               |accessor for "displayNumber" field        
        - public int getDispWidth()                                             |accessor for "displayWidth" field        
        - public int getDispHeight()                                            |accessor for "displayHeight" field
        
      - ConfigurationSetup
        - import java.nio.file.Path;
        - import java.nio.file.Paths;
        - import java.nio.file.FileSystem;
        - import java.io.FileWriter;
        - import java.io.File;
        - import java.io.FileNotFoundException;
        - import java.util.Scanner;
        - import java.awt.GraphicsDevice;
        - import java.awt.GraphicsEnvironment;
        - import java.awt.DisplayMode;
        - import java.util.ArrayList;
        
        - private static final File file                                        |the path of the Java Project that uses package twrap
        - protected static final String fileStr                                 |the String expression of "file", with the package and "TerminalWrapData.bisv" path added
        - private static ArrayList<DisplayInfo> dispList                        |an ArrayList of "DisplayInfo" objects, used to cross-reference current data with previous data
        - private static boolean toCheckChange                                  |initially true, used to see if any changes should be checked for (false if "editSetup()" or "defaultSetup" are called)
        - private static int font                                               |the chosen font size, to be recorded after run of program
        - private static int monitor                                            |the chosen screen, to be recorded
        
        - public staitc boolean ifChange()                                      |compares the data collected in the two ".bisv" files from the previous runs to the data collected on TerminalWrap construction - checks if setup must occur
        - public static void setup()                                            |calls "getMonitors", requests input of font size, writes new data on the files
        - private static int monitorList()                                      |returns the number of monitors recognized
        - private static int getSetScreen()                                     |returns the monitor chosen
        - private static int getMonitors()                                      |prompts for monitor choice; if only one is found, that is automatically chosen
        - public static boolean getToChange()                                   |returns "toCheckChange"
        - public static String getData()                                        |returns the information of the file "TerminalWrapData.bisv"
        - public static void editSetup()                                        |calls "setup()", makes "toCheckChange" false
        - public static void defaultSetup()                                     |sets the monitor to the default screen and sets the font size to 10, and makes "toCheckChange" false
        
