/*
 * Copyright (C) 2024 User
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.willwinder.plugintest;

import com.willwinder.ugs.nbp.lib.lookup.CentralLookup;
import com.willwinder.universalgcodesender.model.BackendAPI;

public class LaserFile extends Folder{
    
    public BackendAPI backend;
    public String gcodeFiles = new String();
    
    public void setup(java.io.File folderPath){
        backend = CentralLookup.getDefault().lookup(BackendAPI.class);
        
        this.folderPath = folderPath;
        folderName = folderPath.getName();
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
