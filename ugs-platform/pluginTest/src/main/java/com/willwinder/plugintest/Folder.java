package com.willwinder.plugintest;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
    
    public static class Bounds {
        public float minX, minY, maxX, maxY;
        public Bounds(float minX, float minY, float maxX, float maxY) {
            this.minX = minX;
            this.minY = minY;
            this.maxX = maxX;
            this.maxY = maxY;
        }
        
        public String[] getPreviewGcode(){
            String[] previewGcodeCommands = {
                "G0 X" + minX + " Y" + minY + ";",
                "G0 X" + maxX + " Y" + minY + ";",
                "G0 X" + maxX + " Y" + maxY + ";",
                "G0 X" + minX + " Y" + maxY + ";",
                "G0 X" + minX + " Y" + minY + ";"
            };
            return previewGcodeCommands;
        }
        
        @Override
        public String toString() {
            return "Bounds: [minX=" + minX + ", minY=" + minY + ", maxX=" + maxX + ", maxY=" + maxY + "]";
        }
    }
    
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
    
    public static Bounds getGcodeBounds(String gcodeFileString) throws IOException {
        float minX = Float.MAX_VALUE, minY = Float.MAX_VALUE;
        float maxX = Float.MIN_VALUE, maxY = Float.MIN_VALUE;
        java.io.File gcodeFile = new java.io.File(gcodeFileString);

        try (BufferedReader reader = new BufferedReader(new FileReader(gcodeFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("G0") || line.startsWith("G1")) {
                    Float xValue = extractCoordinate(line, 'X');
                    Float yValue = extractCoordinate(line, 'Y');

                    if ((xValue != null) && (xValue != 0)) {
                        minX = Math.min(minX, xValue);
                        maxX = Math.max(maxX, xValue);
                    }
                    if ((yValue != null) && (yValue != 0)) {
                        minY = Math.min(minY, yValue);
                        maxY = Math.max(maxY, yValue);
                    }
                }
            }
        }
        
        return new Bounds(minX, minY, maxX, maxY);
    }

    protected static Float extractCoordinate(String line, char axis) {
        int index = line.indexOf(axis);
        if (index != -1) {
            int start = index + 1;
            int end = start;
            while (end < line.length() && (Character.isDigit(line.charAt(end)) || line.charAt(end) == '.' || line.charAt(end) == '-')) {
                end++;
            }
            return Float.parseFloat(line.substring(start, end));
        }
        return null;
    }
}
