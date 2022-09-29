package net.mov51.helpers;

public class Filter {
    public String placeHolder;
    public String secret;
    public String filePath;
    public int lineNumber;

    public void printOut(){
        System.out.println(this.placeHolder);
        System.out.println(this.secret);
        System.out.println(this.filePath);
        System.out.println(this.lineNumber);
        System.out.println("---");
    }

    public int getAdjustedLine(){
        return this.lineNumber - 1;
    }

}

