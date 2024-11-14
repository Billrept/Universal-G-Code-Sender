package com.willwinder.plugintest;

import static com.willwinder.plugintest.Folder.extractCoordinate;
import com.willwinder.ugs.nbp.lib.lookup.CentralLookup;
import com.willwinder.universalgcodesender.model.BackendAPI;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ColorFile extends Folder{
    public String[] colorChangeCommand = new String[4];
    public String[] gcodeFiles = new String[4];
    public String fileList;
    public BackendAPI backend;
    public Boolean cyanSelected, magentaSelected, yellowSelected, blackSelected;

    
    public void setup(java.io.File folderPath){
        
        backend = CentralLookup.getDefault().lookup(BackendAPI.class);
        
        this.folderPath = folderPath;
        folderName = folderPath.getName();
        
        FilenameFilter gcodeFilter = (java.io.File file, String name1) -> name1.endsWith(".gcode");
        java.io.File[] fileArr = folderPath.listFiles(gcodeFilter);
        sortGcodeFiles(fileArr);
        for(int i = 0; i < 4; i++){
            gcodeFiles[i] = fileArr[i].toString();
        }
        fileList = getFileList(gcodeFiles);
    }
    
    public void sortGcodeFiles(java.io.File[] gcodeFiles){
        Map<String, Integer> orderMap = new HashMap<>();
        orderMap.put("cyan.gcode", 1);
        orderMap.put("magenta.gcode", 2);
        orderMap.put("yellow.gcode", 3);
        orderMap.put("black.gcode", 4);
        
        Comparator<java.io.File> fileComparator = (path1, path2) -> {
            String file1 = path1.getName();
            String file2 = path2.getName();
            int order1 = orderMap.get(file1);
            int order2 = orderMap.get(file2);
            return Integer.compare(order1, order2);
        };
        
        Arrays.sort(gcodeFiles, fileComparator);
    }
    
    public String getFileList(String[] gcodeFiles){
        StringBuilder fileList = new StringBuilder("\n\n *** Files Uploaded *** \n\nFiles in the selected folder:\n");
            for (String file : gcodeFiles) {
                fileList.append(file).append("\n");
            }
        fileList.append("\n Click Run to start drawing");
        return fileList.toString();
    }
    
    public void sendGcode(String file){
        try {
            backend.setGcodeFile(new File(file));
            backend.send();
        } catch (Exception ex) {}
    }
    
    public void setAllLayerSelected(Boolean selected){
        this.cyanSelected = selected;
        this.magentaSelected = selected;
        this.yellowSelected = selected;
        this.blackSelected = selected;
    }
    
    public static Bounds getGcodeBounds(String[] gcodeFileString) throws IOException {
        float minX = Float.MAX_VALUE, minY = Float.MAX_VALUE;
        float maxX = Float.MIN_VALUE, maxY = Float.MIN_VALUE;
        for (int i = 0; i < 4; i++){
            java.io.File gcodeFile = new java.io.File(gcodeFileString[i]);

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
        }
        return new Bounds(minX, minY, maxX, maxY);
    }

    @Override
    public void emptyGcodeFiles() {
        for(int i = 0; i <= gcodeFiles.length; i++){
            this.gcodeFiles[i] = null;
        }
    }

    @Override
    public boolean isEmptyGcodeFiles() {
        for (String gcodeFile : gcodeFiles) {
            if (gcodeFile != null) {
                return false;
            }
        }
        return true;
    }
}
