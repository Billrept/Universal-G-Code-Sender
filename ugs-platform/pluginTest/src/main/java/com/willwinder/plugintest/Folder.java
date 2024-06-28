package com.willwinder.plugintest;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public abstract class Folder {
    public String folderName;
    public java.io.File folderPath;
    public ImageIcon imgFile;
    public final Pattern GCODE_PATTERN = Pattern.compile("^[GM]\\d+(\\.\\d+)?(\\s+[XYZFIJPRS]\\d+(\\.\\d+)?)*");
    
    public void scaleImage(java.io.File folder,int previewWidth, int previewHeight) throws IOException{
        FilenameFilter pngFilter = (File dir, String name1) -> name1.endsWith(".png");
        String imgPath = (Arrays.toString(folder.listFiles(pngFilter))).replace("[", "").replace("]","");
        BufferedImage img = null;
        img = ImageIO.read(new File(imgPath));
        Image scaledImg = img.getScaledInstance(previewWidth, previewHeight, Image.SCALE_SMOOTH);
        imgFile = new ImageIcon(scaledImg);
    }
    
    public ImageIcon getScaledImage(){
        return this.imgFile;
    }
    
    public boolean gcodeIsValid(String gcode){
        String[] commands = gcode.split(";");
        for (String command : commands) {
            command = command.trim();
            if ((command == null) || command.isEmpty() || !GCODE_PATTERN.matcher(command).matches()) {
                return false;
            }
        }
        return true;
    }
    
    public abstract void emptyGcodeFiles();
    
    public abstract boolean isEmptyGcodeFiles();
    
    public void setFolderName(String folderName){
        this.folderName = folderName;
    }
    
    public String getFolderName(){
        return this.folderName;
    }
    
    public void setFolderPath(java.io.File folderPath){
        this.folderPath = folderPath;
    }
    
    public java.io.File getFolderPath(){
        return this.folderPath;
    }
    
    public java.io.File getParentFile(){
        return this.folderPath.getParentFile();
    }
}
