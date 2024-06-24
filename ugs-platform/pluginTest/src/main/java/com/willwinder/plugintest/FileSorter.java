//package com.willwinder.plugintest;
//
//import java.util.Arrays;
//import java.util.Comparator;
//import java.util.HashMap;
//import java.util.Map;
//
//public class FileSorter {
//    
//        public void sortGcodeFile(java.io.File[] gcodeFiles){
//        Map<String, Integer> orderMap = new HashMap<>();
//        orderMap.put("cyan.gcode", 1);
//        orderMap.put("magenta.gcode", 2);
//        orderMap.put("yellow.gcode", 3);
//        orderMap.put("black.gcode", 4);
//        
//        Comparator<java.io.File> fileComparator = (path1, path2) -> {
//            String file1 = path1.getName();
//            String file2 = path2.getName();
//            int order1 = orderMap.get(file1);
//            int order2 = orderMap.get(file2);
//            return Integer.compare(order1, order2);
//        };
//        
//        Arrays.sort(gcodeFiles, fileComparator);
//    }
//}
