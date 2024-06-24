//package com.willwinder.plugintest;
//
//import javax.swing.SwingUtilities;
//
//public class ProgressBarUpdater extends pluginTestTopComponent implements Runnable{
//    
//    private int progressBarNum;
//    
//    @Override
//    public void run(){
//        int progress = 0;
//        ProgressBarUpdater updater = new ProgressBarUpdater();
//        updater.consoleSetText("\nThread created");
//        
//        while (progress < 100){
//            try {
//                updater.progressBarSet(progressBarNum, progress);
//                updater.consoleSetText("\n" + progressBarNum + "\n" + progress);
//                Thread.sleep(50);
//            } catch (InterruptedException ex) {}
//            progress = (int)((((float)backend.getNumCompletedRows())/((float)backend.getNumRows())) * 100);
//        }
//        updater.progressBarSet(progressBarNum, 100);
//    }
//    
//    public void startProgressBarThread(ProgressBarUpdater progressBarUpdater, int input){
//        progressBarNum = input;
//        Thread progressBarThread = new Thread(progressBarUpdater);
//        progressBarThread.start();
//    }
    
//    public void progressBarSet(int progressBarNum,int progress){
//        textArea.append("progressBarSet method called");
//        switch (progressBarNum){
//            case 0 -> {
//                SwingUtilities.invokeLater(() -> {
//                    cyanProgress.setValue(progress);
//                    cyanProgress.setString("Cyan : " + progress + "%");
//                });
//                consoleSetText("cyan set to " + progress);
//            }
//            case 1 -> {
//                SwingUtilities.invokeLater(() -> {
//                    magentaProgress.setValue(progress);
//                    magentaProgress.setString("Magenta : " + progress + "%");
//                });
//                consoleSetText("magenta set to " + progress);
//            }
//            case 2 -> {
//                SwingUtilities.invokeLater(() -> {
//                    yellowProgress.setValue(progress);
//                    yellowProgress.setString("Yellow : " + progress + "%");
//                });
//                consoleSetText("yellow set to " + progress);
//            }
//            case 3 -> {
//                SwingUtilities.invokeLater(() -> {
//                    blackProgress.setValue(progress);
//                    blackProgress.setString("Black : " + progress + "%");
//                });
//                consoleSetText("black set to " + progress);
//            }
//        }
//    }
//}
