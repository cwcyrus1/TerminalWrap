package twrap;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.FileSystem;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.DisplayMode;
import java.util.ArrayList;
public class ConfigurationSetup {
    private static final File file = new File(ConfigurationSetup.class.getProtectionDomain().getCodeSource().getLocation().getPath());
    protected static final String fileStr = file.toString() + "/twrap/TerminalWrapData.bisv";
    private static ArrayList<DisplayInfo> dispList = new ArrayList<DisplayInfo>();
    private static boolean toCheckChange = true;
    private static int font = 10;
    private static int monitor = getSetScreen();
    public static boolean ifChange() {
        String fileInfo = "";
        try {
            Scanner scan = new Scanner(new File(fileStr));
            fileInfo = scan.nextLine();
        }
        catch (FileNotFoundException noFile) {
            System.out.println(noFile);
        }
        try {
            Scanner scan = new Scanner(new File(file,"/twrap/DisplayInformation.bisv"));
            while (scan.hasNextLine()) {
                String input = scan.nextLine();
                String[] splinput = input.split(" ");
                DisplayInfo d = new DisplayInfo(Integer.parseInt(splinput[0]), Integer.parseInt(splinput[1]), Integer.parseInt(splinput[2]));
                dispList.add(d);
            }
        }
        catch (FileNotFoundException noFile) {
            System.out.println(noFile);
        }
        String[] split = fileInfo.split(" ");
        GraphicsDevice[] graphDev = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        String s = "";
        for (int i = 0; i < graphDev.length; i++) {
            DisplayMode dispMode = graphDev[i].getDisplayMode();
            s += i + " " + dispMode.getWidth() + " " + dispMode.getHeight() + "\n";
        }
        String[] dispRef = s.split("\n");
        boolean dispSame = true;
        int lessSize = Math.min(dispList.size(), dispRef.length);
        for (int i = 0; i < lessSize; i++) {
            String[] dispRefAr = dispRef[i].split(" ");
            if (dispSame) {
                dispSame = (dispList.get(i).getDispWidth() == Integer.parseInt(dispRefAr[1]) && dispList.get(i).getDispHeight() == Integer.parseInt(dispRefAr[2]));
            }
        }
        if ((dispList.size() != dispRef.length) || !dispSame) {
            System.out.println("A hardware change has been detected.");
            return true;
        }
        else {
            return false;
        }
    }
    public static void setup() {
        Scanner scan = new Scanner(System.in);
        try {
            FileWriter fW = new FileWriter(fileStr, false);
            monitor = getMonitors();
            System.out.print("What is your font size? Check under Options/Prefrences: ");
            font = scan.nextInt();
            String string = monitor + " " + font + " " + 0 + " " + monitorList();
            fW.write(string);
            fW.close();
        }
        catch(Exception except) {
           System.out.println(except);
        }
        try {
            FileWriter fW = new FileWriter(file.toString() + "/twrap/DisplayInformation.bisv", false);
            GraphicsDevice[] graphDev = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
            String s = "";
            for (int i = 0; i < graphDev.length; i++) {
                DisplayMode dispMode = graphDev[i].getDisplayMode();
                s += i + " " + dispMode.getWidth() + " " + dispMode.getHeight() + "\n";
            }
            fW.write(s);
            fW.close();
        }
        catch(Exception except) {
            System.out.println(except);
        }
    }
    private static int monitorList() {
        GraphicsDevice[] graphDev = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        return graphDev.length;
    }
    private static int getSetScreen() {
        Scanner scan;
        int monitorChoice = 0;
        try {
            scan = new Scanner(new File(fileStr));
            monitorChoice = scan.nextInt();
        }
        catch (FileNotFoundException noFile) {
            System.out.println(noFile);
        }
        return monitorChoice;
    }
    private static int getMonitors() {        
        GraphicsDevice[] graphDev = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        String s = "";
        for (int i = 0; i < graphDev.length; i++) {
            DisplayMode dispMode = graphDev[i].getDisplayMode();
            s += "Display " + i + ", width: " + dispMode.getWidth() + ", height: " + dispMode.getHeight() + "\n";
        }
        System.out.println(s);
        if (graphDev.length == 1) {
            return 0;
        }
        else {
            Scanner scan = new Scanner(System.in);
            int input = -1;
            System.out.print("Which monitor would you like to use? Type the integer value: ");
            if (scan.hasNextInt()) {
                input = scan.nextInt();
                if (input >= graphDev.length) {
                    throw new IndexOutOfBoundsException("No monitor found for index " + input + "; check if it is plugged in");
                }
            }
            else {
                throw new IllegalArgumentException();
            }
            return input;
        }
    }
    public static boolean getToChange() {
        return toCheckChange;
    }
    public static String getData() {
        String fileData = "";
        try {
            Scanner scan = new Scanner(new File(fileStr));
            fileData = scan.nextLine();
        }
        catch (FileNotFoundException noFile) {
            System.out.println(noFile);
        }
        return fileData;
    }
    public static void editSetup() {
        toCheckChange = false;
        setup();
    }
    public static void defaultSetup() {
        toCheckChange = false;
        try {
            FileWriter fW = new FileWriter(fileStr, false);
            monitor = 0;
            font = 10;
            String string = monitor + " " + font + " " + 0 + " " + monitorList();
            fW.write(string);
            fW.close();
        }
        catch(Exception except) {
            System.out.println(except);
        }
    }
}