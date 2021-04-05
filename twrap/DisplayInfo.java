package twrap;
public class DisplayInfo {
    private int displayNumber;
    private int displayWidth;
    private int displayHeight;
    public DisplayInfo(int num, int w, int h) {
        this.displayNumber = num;
        this.displayWidth = w;
        this.displayHeight = h;
    }
    public int getDispNum() {
        return this.displayNumber;
    }
    public int getDispWidth() {
        return this.displayWidth;
    }
    public int getDispHeight() {
        return this.displayHeight;
    }
}