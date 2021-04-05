package twrap;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.awt.DisplayMode;
import java.awt.GraphicsEnvironment;
public class TerminalWrap {
    private static int monitor;
    private static int textSize;
    private static int widthWindow;
    private static int toConfigure;
    private static String fileInfo;
    private static int fontScale(int fontSize) {
        return (int) (-12.5996825 + 8.5289743*Math.log(fontSize));
    }
    public static void editSetup() {
        ConfigurationSetup.editSetup();
    }
    public static void defaultSetup() {
        ConfigurationSetup.defaultSetup();
    }
    public TerminalWrap() {
        this.fileInfo = ConfigurationSetup.getData();
        String[] split = this.fileInfo.split(" ");
        this.monitor = Integer.parseInt(split[0]);
        this.textSize = fontScale(Integer.parseInt(split[1]));
        this.toConfigure = Integer.parseInt(split[2]);
        if ((ConfigurationSetup.ifChange() && ConfigurationSetup.getToChange()) || this.toConfigure == 1) {
            ConfigurationSetup.setup();
            this.fileInfo = ConfigurationSetup.getData();
            String[] splitAgain = this.fileInfo.split(" ");
            this.monitor = Integer.parseInt(splitAgain[0]);
            this.textSize = fontScale(Integer.parseInt(splitAgain[1]));
            this.toConfigure = Integer.parseInt(splitAgain[2]);
        }
        double toleranceLevel = -13 + 6.5*Math.log(getMonitorWidth()/(2*this.textSize));
        this.widthWindow = (int) (getMonitorWidth()/(2*this.textSize) - toleranceLevel);
    }
    public static int getWidthWindow() {
        return widthWindow;
    }
    public static int getTextSize() {
        return textSize;
    }
    private static double getMonitorWidth() {
        DisplayMode dispMode = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[monitor].getDisplayMode();
        return dispMode.getWidth();
    }
    public static void printWrap(String string, boolean equal) {
        choice(string,equal);
    }
    public static void printWrap(Object[] objects, boolean equal) {
        choice(Arrays.toString(objects),equal);
    }
    public static void printWrap(Object[][] objectss, boolean equal) {
        choice(Arrays.deepToString(objectss),equal);
    }
    public static void printWrap(ArrayList<?> objects, boolean equal) {
        choice(Arrays.toString(objects.toArray(new Object[objects.size()])),equal);
    }
    public static void printWrap(int[] ints, boolean equal) {
        choice(Arrays.toString(ints),equal);
    }
    public static void printWrap(int[][] intss, boolean equal) {
        choice(Arrays.deepToString(intss),equal);
    }
    public static void printWrap(double[] doubles, boolean equal) {
        choice(Arrays.toString(doubles),equal);
    }
    public static void printWrap(double[][] doubless, boolean equal) {
        choice(Arrays.deepToString(doubless),equal);
    }
    public static void printWrap(boolean[] booleans, boolean equal) {
        choice(Arrays.toString(booleans),equal);
    }
    public static void printWrap(boolean[][] booleanss, boolean equal) {
        choice(Arrays.deepToString(booleanss),equal);
    }
    public static void printWrap(char[] chars, boolean equal) {
        choice(Arrays.toString(chars),equal);
    }
    public static void printWrap(char[][] charss, boolean equal) {
        choice(Arrays.deepToString(charss),equal);
    }
    public static void printWrap(byte[] bytes, boolean equal) {
        choice(Arrays.toString(bytes),equal);
    }
    public static void printWrap(byte[][] bytess, boolean equal) {
        choice(Arrays.deepToString(bytess),equal);
    }
    public static void printWrap(short[] shorts, boolean equal) {
        choice(Arrays.toString(shorts),equal);
    }
    public static void printWrap(short[][] shortss, boolean equal) {
        choice(Arrays.deepToString(shortss),equal);
    }
    public static void printWrap(long[] longs, boolean equal) {
        choice(Arrays.toString(longs),equal);
    }
    public static void printWrap(long[][] longss, boolean equal) {
        choice(Arrays.deepToString(longss),equal);
    }
    public static void printWrap(float[] floats, boolean equal) {
       choice(Arrays.toString(floats),equal);
    }
    public static void printWrap(float[][] floatss, boolean equal) {
        choice(Arrays.deepToString(floatss),equal);
    }
    public static void printWrap(String string) {
        choice(string,false);
    }
    public static void printWrap(Object[] objects) {
        choice(Arrays.toString(objects),false);
    }
    public static void printWrap(Object[][] objectss) {
        choice(Arrays.deepToString(objectss),false);
    }
    public static void printWrap(ArrayList<?> objects) {
        choice(Arrays.toString(objects.toArray(new Object[objects.size()])),false);
    }
    public static void printWrap(int[] ints) {
        choice(Arrays.toString(ints),false);
    }
    public static void printWrap(int[][] intss) {
        choice(Arrays.deepToString(intss),false);
    }
    public static void printWrap(double[] doubles) {
        choice(Arrays.toString(doubles),false);
    }
    public static void printWrap(double[][] doubless) {
        choice(Arrays.deepToString(doubless),false);
    }
    public static void printWrap(boolean[] booleans) {
        choice(Arrays.toString(booleans),false);
    }
    public static void printWrap(boolean[][] booleanss) {
        choice(Arrays.deepToString(booleanss),false);
    }
    public static void printWrap(char[] chars) {
        choice(Arrays.toString(chars),false);
    }
    public static void printWrap(char[][] charss) {
        choice(Arrays.deepToString(charss),false);
    }
    public static void printWrap(byte[] bytes) {
        choice(Arrays.toString(bytes),false);
    }
    public static void printWrap(byte[][] bytess) {
        choice(Arrays.deepToString(bytess),false);
    }
    public static void printWrap(short[] shorts) {
        choice(Arrays.toString(shorts),false);
    }
    public static void printWrap(short[][] shortss) {
        choice(Arrays.deepToString(shortss),false);
    }
    public static void printWrap(long[] longs) {
        choice(Arrays.toString(longs),false);
    }
    public static void printWrap(long[][] longss) {
        choice(Arrays.deepToString(longss),false);
    }
    public static void printWrap(float[] floats) {
       choice(Arrays.toString(floats),false);
    }
    public static void printWrap(float[][] floatss) {
        choice(Arrays.deepToString(floatss),false);
    }
    private static void printer(String str, int wrapIndex) {   
        String[] split = str.split(" ");
        String toPrint = "";
        int lengthOfPrint = 0;
        for (int i = 0; i < split.length; i++) {
            toPrint += split[i] + " ";
            lengthOfPrint += split[i].length() + 1;
            if (lengthOfPrint >= wrapIndex || i == split.length-1) {
                toPrint += "\n";
                lengthOfPrint = 0;
            }
        }
        System.out.print(toPrint);
    }
    private static void printerEqual(String str) {
        int wrapIndex = widthWindow-3;
        int lengthStr = str.length();
        while (lengthStr % wrapIndex > 5) {
            wrapIndex--;
        }
        printer(str,wrapIndex);
    }
    private static void choice(String str, boolean equal) {
        if (equal) {
            printerEqual(str);
        }
        else {
            printer(str,widthWindow-3);
        }
    }
}