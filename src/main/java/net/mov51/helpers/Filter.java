package net.mov51.helpers;

import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.constructor.Constructor;

import javax.swing.plaf.PanelUI;

public class Filter {
    public String placeHolder;
    public int lineNumber;
    public String filePath;
    public String secret;

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public void setPlaceHolder(String placeHolder) {
        this.placeHolder = placeHolder;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}

