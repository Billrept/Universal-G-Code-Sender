//package com.willwinder.plugintest;
//
//import java.awt.Image;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FilenameFilter;
//import java.io.IOException;
//import java.util.Arrays;
//import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;
//
//public class ScaledImage {
//    
//    public ImageIcon getScaledImage(java.io.File folder,int width, int height) throws IOException{
//        FilenameFilter pngFilter = (File dir, String name1) -> name1.endsWith(".png");
//        String imgPath = (Arrays.toString(folder.listFiles(pngFilter))).replace("[", "").replace("]","");
//        BufferedImage img = null;
//        img = ImageIO.read(new File(imgPath));
//        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
//        ImageIcon imgFile = new ImageIcon(scaledImg);
//        return imgFile;
//    }
//}
