package com.willwinder.plugintest;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Folder {
    public String folderName;
    public java.io.File folderPath;
    public ImageIcon imgFile;
    
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
    
    public void setFolderName(String name){
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
