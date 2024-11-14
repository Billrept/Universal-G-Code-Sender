package com.willwinder.plugintest;

import com.willwinder.ugs.nbp.lib.lookup.CentralLookup;
import com.willwinder.universalgcodesender.model.BackendAPI;
import java.io.File;
import java.io.FilenameFilter;

public class LaserFile extends Folder{
    
    public BackendAPI backend;
    public String gcodeFiles = new String();
    
    public void setup(java.io.File folderPath){
        backend = CentralLookup.getDefault().lookup(BackendAPI.class);
        
        this.folderPath = folderPath;
        folderName = folderPath.getName();
        
        FilenameFilter gcodeFilter = (java.io.File file, String name1) -> name1.endsWith(".gcode");
        java.io.File[] fileArr = folderPath.listFiles(gcodeFilter);
        gcodeFiles = fileArr[0].toString();
    }
    
    public void sendGcode(String file){
        try {
            backend.setGcodeFile(new File(file));
            backend.send();
        } catch (Exception ex) {}
    }
    
    @Override
    public void emptyGcodeFiles() {
        this.gcodeFiles = null;
    }

    @Override
    public boolean isEmptyGcodeFiles() {
        if (this.gcodeFiles != null){
            return false;
        }
        return true;
    }
    
}
