package com.willwinder.plugintest;

import com.willwinder.ugs.nbp.lib.lookup.CentralLookup;
import com.willwinder.universalgcodesender.listeners.UGSEventListener;
import com.willwinder.universalgcodesender.model.BackendAPI;
import com.willwinder.universalgcodesender.model.UGSEvent;
import com.willwinder.universalgcodesender.utils.Settings;
import javax.swing.JFileChooser;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import com.willwinder.universalgcodesender.listeners.ControllerListener;
import com.willwinder.universalgcodesender.listeners.ControllerStatus;
import com.willwinder.universalgcodesender.model.Alarm;
import com.willwinder.universalgcodesender.model.Position;
import com.willwinder.universalgcodesender.types.GcodeCommand;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

@ConvertAsProperties(
        dtd = "-//com.willwinder.plugintest//pluginTest//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "pluginTestTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "com.willwinder.plugintest.pluginTestTopComponent")
@ActionReference(path = "Menu/Window/Plugins" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_pluginTestAction",
        preferredID = "pluginTestTopComponent"
)
@Messages({
    "CTL_pluginTestAction=pluginTest",
    "CTL_pluginTestTopComponent=pluginTest Window",
    "HINT_pluginTestTopComponent=This is a pluginTest window"
})
public class pluginTestTopComponent extends TopComponent 
    implements UGSEventListener, ControllerListener{
    
    private final Settings settings;
    public final BackendAPI backend;
    private ColorFile cFile;
    private LaserFile lFile;
    private DrillFile dFile;
    
    private int currentFileIndex;
    private boolean filler;
    private boolean isProcessing = false;
    private boolean paused = false;
    private java.io.File lastPath;
    private final int PREVIEW_WIDTH = 230;
    private final int PREVIEW_HEIGHT = 230;
    
    private int selectedTab = 0;

    public pluginTestTopComponent() {
        initComponents();
        setName(Bundle.CTL_pluginTestTopComponent());
        setToolTipText(Bundle.HINT_pluginTestTopComponent());
        
        settings = CentralLookup.getDefault().lookup(Settings.class);
        backend = CentralLookup.getDefault().lookup(BackendAPI.class);
        backend.getController().addListener(this);
        backend.addUGSEventListener(this);
        
        cFile = new ColorFile();
        lFile = new LaserFile();
        dFile = new DrillFile();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();
        drawingPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        colorTextArea = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        magentaProgress = new javax.swing.JProgressBar();
        blackProgress = new javax.swing.JProgressBar();
        yellowProgress = new javax.swing.JProgressBar();
        cyanProgress = new javax.swing.JProgressBar();
        colorStatusText = new javax.swing.JTextField();
        colorUploadButton = new javax.swing.JButton();
        colorRunButton = new javax.swing.JButton();
        colorSvgButton = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        colorPreviewLabel = new javax.swing.JLabel();
        colorTimeRemainText = new javax.swing.JLabel();
        colorDurationText = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cyanCheckBox = new javax.swing.JCheckBox();
        magentaCheckBox = new javax.swing.JCheckBox();
        yellowCheckBox = new javax.swing.JCheckBox();
        blackCheckBox = new javax.swing.JCheckBox();
        laserPanel = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        laserTextArea = new javax.swing.JTextArea();
        jPanel15 = new javax.swing.JPanel();
        laserStatusText = new javax.swing.JTextField();
        laserProgress = new javax.swing.JProgressBar();
        laserRunButton = new javax.swing.JButton();
        laserSvgButton = new javax.swing.JButton();
        laserUploadButton = new javax.swing.JButton();
        powerSlider = new javax.swing.JSlider();
        laserPreviewButton = new javax.swing.JButton();
        laserPowerCheckBox = new javax.swing.JCheckBox();
        jPanel16 = new javax.swing.JPanel();
        laserTimeRemainText = new javax.swing.JLabel();
        laserDurationText = new javax.swing.JLabel();
        laserPreviewLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        drillingPanel = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        drillTextArea = new javax.swing.JTextArea();
        jPanel18 = new javax.swing.JPanel();
        drillStatusText = new javax.swing.JTextField();
        drillProgress = new javax.swing.JProgressBar();
        drillRunButton = new javax.swing.JButton();
        drillSvgButton = new javax.swing.JButton();
        drillUploadButton = new javax.swing.JButton();
        speedSlider = new javax.swing.JSlider();
        drillPreviewButton = new javax.swing.JButton();
        drillSpeedLevel = new javax.swing.JCheckBox();
        jPanel19 = new javax.swing.JPanel();
        drillPreviewLabel = new javax.swing.JLabel();
        drillTimeRemainText = new javax.swing.JLabel();
        drillDurationText = new javax.swing.JLabel();
        settingsPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        colorChangeTable = new javax.swing.JTable();
        changeCommandCheckBox = new javax.swing.JCheckBox();

        tabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabbedPaneStateChanged(evt);
            }
        });
        tabbedPane.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tabbedPanePropertyChange(evt);
            }
        });

        colorTextArea.setEditable(false);
        colorTextArea.setColumns(20);
        colorTextArea.setRows(5);
        colorTextArea.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane1.setViewportView(colorTextArea);

        magentaProgress.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        magentaProgress.setString(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.magentaProgress.string")); // NOI18N
        magentaProgress.setStringPainted(true);

        blackProgress.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        blackProgress.setString(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.blackProgress.string")); // NOI18N
        blackProgress.setStringPainted(true);

        yellowProgress.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        yellowProgress.setString(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.yellowProgress.string")); // NOI18N
        yellowProgress.setStringPainted(true);

        cyanProgress.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cyanProgress.setString(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.cyanProgress.string")); // NOI18N
        cyanProgress.setStringPainted(true);

        colorStatusText.setEditable(false);
        colorStatusText.setForeground(new java.awt.Color(0, 0, 0));
        colorStatusText.setText(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.colorStatusText.text")); // NOI18N
        colorStatusText.setFocusable(false);

        org.openide.awt.Mnemonics.setLocalizedText(colorUploadButton, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.colorUploadButton.text")); // NOI18N
        colorUploadButton.setToolTipText(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.colorUploadButton.toolTipText")); // NOI18N
        colorUploadButton.setFocusable(false);
        colorUploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorUploadButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(colorRunButton, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.colorRunButton.text")); // NOI18N
        colorRunButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorRunButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(colorSvgButton, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.colorSvgButton.text")); // NOI18N
        colorSvgButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorSvgButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(colorStatusText)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(magentaProgress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(blackProgress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(yellowProgress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cyanProgress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(colorUploadButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(colorRunButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(colorSvgButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(colorStatusText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(cyanProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(magentaProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(yellowProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(blackProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(colorUploadButton)
                    .addComponent(colorSvgButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(colorRunButton)
                .addContainerGap())
        );

        cyanProgress.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.cyanProgress.AccessibleContext.accessibleName")); // NOI18N

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setPreferredSize(new java.awt.Dimension(240, 240));

        colorPreviewLabel.setForeground(new java.awt.Color(0, 0, 0));
        org.openide.awt.Mnemonics.setLocalizedText(colorPreviewLabel, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.colorPreviewLabel.text")); // NOI18N
        colorPreviewLabel.setToolTipText(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.colorPreviewLabel.toolTipText")); // NOI18N
        colorPreviewLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        colorPreviewLabel.setHorizontalTextPosition(JLabel.CENTER);
        colorPreviewLabel.setVerticalTextPosition(JLabel.BOTTOM);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(colorPreviewLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(colorPreviewLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        org.openide.awt.Mnemonics.setLocalizedText(colorTimeRemainText, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.colorTimeRemainText.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(colorDurationText, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.colorDurationText.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.jLabel1.text")); // NOI18N

        cyanCheckBox.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(cyanCheckBox, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.cyanCheckBox.text")); // NOI18N
        cyanCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cyanCheckBoxActionPerformed(evt);
            }
        });

        magentaCheckBox.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(magentaCheckBox, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.magentaCheckBox.text")); // NOI18N
        magentaCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                magentaCheckBoxActionPerformed(evt);
            }
        });

        yellowCheckBox.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(yellowCheckBox, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.yellowCheckBox.text")); // NOI18N
        yellowCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yellowCheckBoxActionPerformed(evt);
            }
        });

        blackCheckBox.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(blackCheckBox, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.blackCheckBox.text")); // NOI18N
        blackCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blackCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(yellowCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(blackCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(magentaCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cyanCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cyanCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(magentaCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(yellowCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(blackCheckBox)
                .addGap(68, 68, 68))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(colorDurationText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(colorTimeRemainText, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 251, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(36, 36, 36))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(colorDurationText, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(colorTimeRemainText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout drawingPanelLayout = new javax.swing.GroupLayout(drawingPanel);
        drawingPanel.setLayout(drawingPanelLayout);
        drawingPanelLayout.setHorizontalGroup(
            drawingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, drawingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        drawingPanelLayout.setVerticalGroup(
            drawingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, drawingPanelLayout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabbedPane.addTab(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.drawingPanel.TabConstraints.tabTitle"), drawingPanel); // NOI18N

        laserTextArea.setEditable(false);
        laserTextArea.setColumns(20);
        laserTextArea.setRows(5);
        laserTextArea.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane5.setViewportView(laserTextArea);

        laserStatusText.setEditable(false);
        laserStatusText.setForeground(new java.awt.Color(0, 0, 0));
        laserStatusText.setText(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.laserStatusText.text")); // NOI18N
        laserStatusText.setFocusable(false);

        laserProgress.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        laserProgress.setName(""); // NOI18N
        laserProgress.setString(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.string")); // NOI18N
        laserProgress.setStringPainted(true);

        org.openide.awt.Mnemonics.setLocalizedText(laserRunButton, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.laserRunButton.text")); // NOI18N
        laserRunButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laserRunButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(laserSvgButton, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.laserSvgButton.text")); // NOI18N
        laserSvgButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laserSvgButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(laserUploadButton, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.laserUploadButton.text")); // NOI18N
        laserUploadButton.setToolTipText(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.laserUploadButton.toolTipText")); // NOI18N
        laserUploadButton.setFocusable(false);
        laserUploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laserUploadButtonActionPerformed(evt);
            }
        });

        powerSlider.setValue(100);
        powerSlider.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(laserPreviewButton, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.laserPreviewButton.text")); // NOI18N
        laserPreviewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laserPreviewButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(laserPowerCheckBox, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.laserPowerCheckBox.text")); // NOI18N
        laserPowerCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laserPowerCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(laserStatusText)
                    .addComponent(laserProgress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(powerSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(laserRunButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(laserPreviewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(laserUploadButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(laserSvgButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26)))
                .addContainerGap())
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(laserPowerCheckBox)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(laserStatusText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(laserProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(laserPowerCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(powerSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(laserUploadButton)
                    .addComponent(laserSvgButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(laserPreviewButton)
                    .addComponent(laserRunButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel16.setPreferredSize(new java.awt.Dimension(240, 240));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 257, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 252, Short.MAX_VALUE)
        );

        org.openide.awt.Mnemonics.setLocalizedText(laserTimeRemainText, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.laserTimeRemainText.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(laserDurationText, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.laserDurationText.text")); // NOI18N

        laserPreviewLabel.setForeground(new java.awt.Color(0, 0, 0));
        org.openide.awt.Mnemonics.setLocalizedText(laserPreviewLabel, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.laserPreviewLabel.text")); // NOI18N
        laserPreviewLabel.setToolTipText(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.laserPreviewLabel.toolTipText")); // NOI18N
        laserPreviewLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        laserPreviewLabel.setHorizontalTextPosition(JLabel.CENTER);
        colorPreviewLabel.setVerticalTextPosition(JLabel.BOTTOM);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.jLabel3.text")); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(laserDurationText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(laserTimeRemainText, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(laserPreviewLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(laserPreviewLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(11, 11, 11))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(laserDurationText, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(laserTimeRemainText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout laserPanelLayout = new javax.swing.GroupLayout(laserPanel);
        laserPanel.setLayout(laserPanelLayout);
        laserPanelLayout.setHorizontalGroup(
            laserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(laserPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        laserPanelLayout.setVerticalGroup(
            laserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(laserPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPane.addTab(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.laserPanel.TabConstraints.tabTitle"), laserPanel); // NOI18N

        drillTextArea.setEditable(false);
        drillTextArea.setColumns(20);
        drillTextArea.setRows(5);
        drillTextArea.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane6.setViewportView(drillTextArea);

        drillStatusText.setEditable(false);
        drillStatusText.setForeground(new java.awt.Color(0, 0, 0));
        drillStatusText.setText(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.drillStatusText.text")); // NOI18N
        drillStatusText.setFocusable(false);

        drillProgress.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        drillProgress.setName(""); // NOI18N
        drillProgress.setString(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.drillProgress.string")); // NOI18N
        drillProgress.setStringPainted(true);

        org.openide.awt.Mnemonics.setLocalizedText(drillRunButton, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.drillRunButton.text")); // NOI18N
        drillRunButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drillRunButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(drillSvgButton, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.drillSvgButton.text")); // NOI18N
        drillSvgButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drillSvgButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(drillUploadButton, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.drillUploadButton.text")); // NOI18N
        drillUploadButton.setToolTipText(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.drillUploadButton.toolTipText")); // NOI18N
        drillUploadButton.setFocusable(false);
        drillUploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drillUploadButtonActionPerformed(evt);
            }
        });

        speedSlider.setValue(100);
        speedSlider.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(drillPreviewButton, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.drillPreviewButton.text")); // NOI18N
        drillPreviewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drillPreviewButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(drillSpeedLevel, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.drillSpeedLevel.text")); // NOI18N
        drillSpeedLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drillSpeedLevelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(drillStatusText)
                    .addComponent(drillProgress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(drillRunButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                        .addComponent(drillPreviewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(drillUploadButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(drillSvgButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29))
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(drillSpeedLevel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel18Layout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addComponent(speedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(14, Short.MAX_VALUE)))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(drillStatusText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(drillProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(drillSpeedLevel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(drillSvgButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(drillUploadButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(drillRunButton)
                    .addComponent(drillPreviewButton))
                .addGap(20, 20, 20))
            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel18Layout.createSequentialGroup()
                    .addGap(138, 138, 138)
                    .addComponent(speedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(116, Short.MAX_VALUE)))
        );

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel19.setPreferredSize(new java.awt.Dimension(240, 240));

        drillPreviewLabel.setForeground(new java.awt.Color(0, 0, 0));
        org.openide.awt.Mnemonics.setLocalizedText(drillPreviewLabel, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.drillPreviewLabel.text")); // NOI18N
        drillPreviewLabel.setToolTipText(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.drillPreviewLabel.toolTipText")); // NOI18N
        drillPreviewLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        drillPreviewLabel.setHorizontalTextPosition(JLabel.CENTER);
        colorPreviewLabel.setVerticalTextPosition(JLabel.BOTTOM);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(drillPreviewLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(drillPreviewLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(drillTimeRemainText, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.drillTimeRemainText.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(drillDurationText, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.drillDurationText.text")); // NOI18N

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(drillDurationText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(drillTimeRemainText, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(drillDurationText, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(drillTimeRemainText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout drillingPanelLayout = new javax.swing.GroupLayout(drillingPanel);
        drillingPanel.setLayout(drillingPanelLayout);
        drillingPanelLayout.setHorizontalGroup(
            drillingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, drillingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        drillingPanelLayout.setVerticalGroup(
            drillingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(drillingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        tabbedPane.addTab(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.drillingPanel.TabConstraints.tabTitle"), drillingPanel); // NOI18N

        colorChangeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Cyan", ""},
                {"Magenta", null},
                {"Yellow", null},
                {"Black", null}
            },
            new String [] {
                "Color", "Command"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(colorChangeTable);
        if (colorChangeTable.getColumnModel().getColumnCount() > 0) {
            colorChangeTable.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.colorChangeTable.columnModel.title0")); // NOI18N
            colorChangeTable.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.colorChangeTable.columnModel.title1")); // NOI18N
        }

        org.openide.awt.Mnemonics.setLocalizedText(changeCommandCheckBox, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.changeCommandCheckBox.text")); // NOI18N
        changeCommandCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeCommandCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout settingsPanelLayout = new javax.swing.GroupLayout(settingsPanel);
        settingsPanel.setLayout(settingsPanelLayout);
        settingsPanelLayout.setHorizontalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(changeCommandCheckBox)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        settingsPanelLayout.setVerticalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(changeCommandCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(278, Short.MAX_VALUE))
        );

        tabbedPane.addTab(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.settingsPanel.TabConstraints.tabTitle"), settingsPanel); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 458, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void colorUploadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorUploadButtonActionPerformed
        openFileChooser(0);
    }//GEN-LAST:event_colorUploadButtonActionPerformed
    
    private void processGcode() throws Exception{
        switch(selectedTab){
            case 0:
                backend.sendGcodeCommand(cFile.colorChangeCommand[currentFileIndex]);
                consoleSetText("\n\nPen changed");
                cFile.sendGcode(cFile.gcodeFiles[currentFileIndex]);
                progressBarUpdater();
                consoleSetText("\n\nFile " + cFile.gcodeFiles[currentFileIndex] + " loaded");
                break;
            case 1:
                break;
            case 2:
                break;
        }
    }
    
    public void consoleSetText(String message){
        switch (selectedTab){
            case 0 -> SwingUtilities.invokeLater(() -> colorTextArea.append(message));
            case 1 -> SwingUtilities.invokeLater(() -> laserTextArea.append(message));
            case 2 -> SwingUtilities.invokeLater(() -> drillTextArea.append(message));
        }
    }
    
    private void progressBarUpdater(){
        switch(selectedTab){
            case 0:
                Thread colorProgressThread = new Thread(){
                @Override
                    public void run(){
                        int progress = 0;
                        while (progress < 100){
                            progressBarSet(currentFileIndex, progress);
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException ex) {}
                            progress = (int)((((float)backend.getNumCompletedRows())/((float)backend.getNumRows())) * 100);
                        }
                        progressBarSet(currentFileIndex, 100);
                    }
                };
                colorProgressThread.start();
                break;
            case 1:
                Thread laserProgressThread = new Thread(){
                @Override
                    public void run(){
                        int progress = 0;
                        while (progress < 100){
                            progressBarSet(4, progress);
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException ex) {}
                            progress = (int)((((float)backend.getNumCompletedRows())/((float)backend.getNumRows())) * 100);
                        }
                        progressBarSet(4, 100);
                    }
                };
                laserProgressThread.start();
                break;
            case 2:
                Thread drillProgressThread = new Thread(){
                @Override
                    public void run(){
                        int progress = 0;
                        while (progress < 100){
                            progressBarSet(5, progress);
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException ex) {}
                            progress = (int)((((float)backend.getNumCompletedRows())/((float)backend.getNumRows())) * 100);
                        }
                        progressBarSet(5, 100);
                    }
                };
                drillProgressThread.start();
                break;
        }
    }
    
    public void progressBarSet(int progressBarNum,int progress){
        switch (progressBarNum){
            case 0 -> {
                SwingUtilities.invokeLater(() -> {
                    cyanProgress.setValue(progress);
                    cyanProgress.setString("Cyan : " + progress + "%");
                });
            }
            case 1 -> {
                SwingUtilities.invokeLater(() -> {
                    magentaProgress.setValue(progress);
                    magentaProgress.setString("Magenta : " + progress + "%");
                });
            }
            case 2 -> {
                SwingUtilities.invokeLater(() -> {
                    yellowProgress.setValue(progress);
                    yellowProgress.setString("Yellow : " + progress + "%");
                });
            }
            case 3 -> {
                SwingUtilities.invokeLater(() -> {
                    blackProgress.setValue(progress);
                    blackProgress.setString("Black : " + progress + "%");
                });
            }
            case 4 -> {
                SwingUtilities.invokeLater(() -> {
                    laserProgress.setValue(progress);
                    laserProgress.setString("Progress : " + progress + "%");
                });
            }
            case 5 -> {
                SwingUtilities.invokeLater(() -> {
                    drillProgress.setValue(progress);
                    drillProgress.setString("Progress : " + progress + "%");
                });
            }
        }
    }
     
    public void setup(){
        switch(selectedTab){
            case 0:
                for(int i = 0; i <= 3; i++){
                    progressBarSet(i, 0);
                }
                break;
            case 1:
                laserProgress.setValue(0);
                laserProgress.setString("Progress : 0%");
                break;
            case 2:
                drillProgress.setValue(0);
                drillProgress.setString("Progress : 0%");
                break;
        }
    }
    
    private void setStatusText(String status){
        switch (selectedTab){
            case 0:
                colorStatusText.setText("Status : " + status);
                break;
            case 1:
                laserStatusText.setText("Status : " + status);
                break;
            case 2:
                drillStatusText.setText("Status : " + status);
        }
    }
    
    private void colorSvgButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorSvgButtonActionPerformed
        openWeb();
    }//GEN-LAST:event_colorSvgButtonActionPerformed

    private void colorRunButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorRunButtonActionPerformed
        runFile();
    }//GEN-LAST:event_colorRunButtonActionPerformed

    private void tabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabbedPaneStateChanged
        selectedTab = tabbedPane.getSelectedIndex();
    }//GEN-LAST:event_tabbedPaneStateChanged

    private void magentaCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_magentaCheckBoxActionPerformed
        cFile.magentaSelected = magentaCheckBox.isSelected();
        consoleSetText("\nMagenta layer is set to " + cFile.magentaSelected.toString());
    }//GEN-LAST:event_magentaCheckBoxActionPerformed

    private void blackCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blackCheckBoxActionPerformed
        cFile.blackSelected = blackCheckBox.isSelected();
        consoleSetText("\nBlack layer is set to " + cFile.blackSelected.toString());
    }//GEN-LAST:event_blackCheckBoxActionPerformed

    private void cyanCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cyanCheckBoxActionPerformed
        cFile.cyanSelected = cyanCheckBox.isSelected();
        consoleSetText("\nCyan layer is set to " + cFile.cyanSelected.toString());
    }//GEN-LAST:event_cyanCheckBoxActionPerformed

    private void yellowCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yellowCheckBoxActionPerformed
        cFile.yellowSelected = yellowCheckBox.isSelected();
        consoleSetText("\nYellow layer is set to " + cFile.yellowSelected.toString());
    }//GEN-LAST:event_yellowCheckBoxActionPerformed

    private void tabbedPanePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tabbedPanePropertyChange
        filler = true;
    }//GEN-LAST:event_tabbedPanePropertyChange

    private void laserUploadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laserUploadButtonActionPerformed
        openFileChooser(1);
    }//GEN-LAST:event_laserUploadButtonActionPerformed

    private void laserSvgButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laserSvgButtonActionPerformed
        openWeb();
    }//GEN-LAST:event_laserSvgButtonActionPerformed

    private void laserRunButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laserRunButtonActionPerformed
        runFile();
    }//GEN-LAST:event_laserRunButtonActionPerformed

    private void drillRunButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drillRunButtonActionPerformed
        runFile();
    }//GEN-LAST:event_drillRunButtonActionPerformed

    private void drillSvgButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drillSvgButtonActionPerformed
        openWeb();
    }//GEN-LAST:event_drillSvgButtonActionPerformed

    private void drillUploadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drillUploadButtonActionPerformed
        openFileChooser(2);
    }//GEN-LAST:event_drillUploadButtonActionPerformed

    private void laserPreviewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laserPreviewButtonActionPerformed
        filler = true;
    }//GEN-LAST:event_laserPreviewButtonActionPerformed

    private void drillPreviewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drillPreviewButtonActionPerformed
        filler = true;
    }//GEN-LAST:event_drillPreviewButtonActionPerformed

    private void laserPowerCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laserPowerCheckBoxActionPerformed
        if (laserPowerCheckBox.isSelected()){
            powerSlider.setEnabled(true);
        } else {
            powerSlider.setEnabled(false);
        }
    }//GEN-LAST:event_laserPowerCheckBoxActionPerformed

    private void drillSpeedLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drillSpeedLevelActionPerformed
        if (drillSpeedLevel.isSelected()){
            speedSlider.setEnabled(true);
        } else {
            speedSlider.setEnabled(false);
        }
    }//GEN-LAST:event_drillSpeedLevelActionPerformed

    private void changeCommandCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeCommandCheckBoxActionPerformed
        if (changeCommandCheckBox.isSelected()){
            colorChangeTable.setEnabled(true);
        } else {
            colorChangeTable.setEnabled(false);
        }
    }//GEN-LAST:event_changeCommandCheckBoxActionPerformed
    
    private boolean checkColorChangeCommand(){
        for(int i = 0; i <= 3; i++){
            String colorChangeCommand = colorChangeTable.getValueAt(i, 1) + "";
            if((!cFile.gcodeIsValid(colorChangeCommand)) && changeCommandCheckBox.isSelected() == true){
                consoleSetText("\n\nUnable to run\nPlease make sure pen change command is valid ");
                return false;
            }
            cFile.colorChangeCommand[i] = colorChangeCommand;
        }
        return true;
    }
    
    private void setSelectedFiles(){
        cFile.magentaSelected = magentaCheckBox.isSelected();
        cFile.blackSelected = blackCheckBox.isSelected();
        cFile.cyanSelected = cyanCheckBox.isSelected();
        cFile.yellowSelected = yellowCheckBox.isSelected();
    }
    
    private void setCurrentFileIndex(){
        if(cFile.cyanSelected){
            currentFileIndex = 0;
            cFile.cyanSelected = false;
        }else if(cFile.magentaSelected){
            currentFileIndex = 1;
            cFile.magentaSelected = false;
        }else if(cFile.yellowSelected){
            currentFileIndex = 2;
            cFile.yellowSelected = false;
        }else if(cFile.blackSelected){
            currentFileIndex = 3;
            cFile.blackSelected = false;
        }
    }
    
    private void setColorCheckBoxEnabled(boolean enable){
        cyanCheckBox.setEnabled(enable);
        magentaCheckBox.setEnabled(enable);
        yellowCheckBox.setEnabled(enable);
        blackCheckBox.setEnabled(enable);
    }
    
    private void setColorCheckBoxSelected(boolean selected){
        cyanCheckBox.setSelected(selected);
        magentaCheckBox.setSelected(selected);
        yellowCheckBox.setSelected(selected);
        blackCheckBox.setSelected(selected);
    }
    
    private void openFileChooser(int mode){
        if(backend.isIdle() && isProcessing == false){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if(lastPath != null){
                fileChooser.setCurrentDirectory(lastPath);
            }
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                
                //Initialize ColorFile
                switch (mode){
                    case 0:
                        cFile.setup(fileChooser.getSelectedFile());
                        lastPath = cFile.getParentFile();
                        setStatusText("Files Uploaded");
                        try {
                            cFile.scaleImage(fileChooser.getSelectedFile(), PREVIEW_WIDTH, PREVIEW_HEIGHT);
                        } catch (IOException ex) {
                            consoleSetText("\nError trying to scale image");
                        }
                        colorPreviewLabel.setIcon(cFile.getScaledImage());
                        colorPreviewLabel.setText("Preview");
                        break;
                    
                    case 1:
                        lFile.setup(fileChooser.getSelectedFile());
                        lastPath = lFile.getParentFile();
                        setStatusText("Files Uploaded");
                        try {
                            lFile.scaleImage(fileChooser.getSelectedFile(), PREVIEW_WIDTH, PREVIEW_HEIGHT);
                        } catch (IOException ex) {
                            consoleSetText("\nError trying to scale image");
                        }
                        laserPreviewLabel.setIcon(lFile.getScaledImage());
                        laserPreviewLabel.setText("Preview");
                        break;
                    
                    case 2:
                        dFile.setup(fileChooser.getSelectedFile());
                        lastPath = dFile.getParentFile();
                        setStatusText("Files Uploaded");
                        try {
                            dFile.scaleImage(fileChooser.getSelectedFile(), PREVIEW_WIDTH, PREVIEW_HEIGHT);
                        } catch (IOException ex) {
                            consoleSetText("\nError trying to scale image");
                        }
                        drillPreviewLabel.setIcon(dFile.getScaledImage());
                        drillPreviewLabel.setText("Preview");
                        break;
                    
                }
            }else {
                consoleSetText("\n\nFile chooser canceled");
            }
        }else{
            consoleSetText("\n\nUnable to upload file");
        }
    }
    
    private void openWeb(){
        try {
            Desktop.getDesktop().browse(new URI("https://starfish-app-8zwaq.ondigitalocean.app"));
        } catch (URISyntaxException | IOException ex) {}
    }
    
    private void runFile(){
        switch (selectedTab){
            case 0:
                if(backend.isIdle() && isProcessing == false && !cFile.isEmptyGcodeFiles()){
                    setSelectedFiles();
                    if((cFile.cyanSelected || cFile.magentaSelected || cFile.yellowSelected || cFile.blackSelected) == true){
                        if(checkColorChangeCommand()){
                            isProcessing = true;
                            setCurrentFileIndex();
                            setup();
                            tabbedPane.setEnabled(false);
                            setColorCheckBoxEnabled(false);
                            try {
                                processGcode();
                            } catch (Exception ex) {
                                consoleSetText("Error occurred trying to draw Gcode");
                                currentFileIndex = 3;
                                isProcessing = false;
                            }
                        }else{
                            consoleSetText("\n\nUnable to run\nPlease make sure to enter valid pen color change commands in the settings tabbbbb");
                        }
                    }else {
                        consoleSetText("\n\nUnable to run\nPlease make sure to select a layer");
                    }
                }else{
                    consoleSetText("\n\nUnable to run\nPlease make sure the machine is in idle mode and folder is loaded");
                }
                break;
               
            case 1:
                if(backend.isIdle() && isProcessing == false && !lFile.isEmptyGcodeFiles()){
                    isProcessing = true;
                    tabbedPane.setEnabled(false);
                    try {
                        processGcode();
                    } catch (Exception ex) {
                        consoleSetText("Error occurred trying to draw Gcode");
                        isProcessing = false;
                    }
                }
                break;
            case 2:
                if(backend.isIdle() && isProcessing == false && !dFile.isEmptyGcodeFiles()){
                    isProcessing = true;
                    tabbedPane.setEnabled(false);
                    try {
                        processGcode();
                    } catch (Exception ex) {
                        consoleSetText("Error occurred trying to draw Gcode");
                        isProcessing = false;
                    }
                }
                break;
        }
    }
    
    private void setAllStatusText(String status){
        colorStatusText.setText("Status : " + status);
        laserStatusText.setText("Status : " + status);
        drillStatusText.setText("Status : " + status);
    }
    
    private void resetAllPreviewLabel(){
        colorPreviewLabel.setIcon(null);
        colorPreviewLabel.setText("");
        laserPreviewLabel.setIcon(null);
        laserPreviewLabel.setText("");
        drillPreviewLabel.setIcon(null);
        drillPreviewLabel.setText("");
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox blackCheckBox;
    public javax.swing.JProgressBar blackProgress;
    private javax.swing.JCheckBox changeCommandCheckBox;
    private javax.swing.JTable colorChangeTable;
    private javax.swing.JLabel colorDurationText;
    private javax.swing.JLabel colorPreviewLabel;
    private javax.swing.JButton colorRunButton;
    private javax.swing.JTextField colorStatusText;
    private javax.swing.JButton colorSvgButton;
    public javax.swing.JTextArea colorTextArea;
    private javax.swing.JLabel colorTimeRemainText;
    private javax.swing.JButton colorUploadButton;
    private javax.swing.JCheckBox cyanCheckBox;
    public javax.swing.JProgressBar cyanProgress;
    public javax.swing.JPanel drawingPanel;
    private javax.swing.JLabel drillDurationText;
    private javax.swing.JButton drillPreviewButton;
    private javax.swing.JLabel drillPreviewLabel;
    public javax.swing.JProgressBar drillProgress;
    private javax.swing.JButton drillRunButton;
    private javax.swing.JCheckBox drillSpeedLevel;
    private javax.swing.JTextField drillStatusText;
    private javax.swing.JButton drillSvgButton;
    public javax.swing.JTextArea drillTextArea;
    private javax.swing.JLabel drillTimeRemainText;
    private javax.swing.JButton drillUploadButton;
    private javax.swing.JPanel drillingPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel12;
    public javax.swing.JPanel jPanel14;
    public javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    public javax.swing.JPanel jPanel17;
    public javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    public javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JScrollPane jScrollPane5;
    public javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel laserDurationText;
    private javax.swing.JPanel laserPanel;
    private javax.swing.JCheckBox laserPowerCheckBox;
    private javax.swing.JButton laserPreviewButton;
    private javax.swing.JLabel laserPreviewLabel;
    public javax.swing.JProgressBar laserProgress;
    private javax.swing.JButton laserRunButton;
    private javax.swing.JTextField laserStatusText;
    private javax.swing.JButton laserSvgButton;
    public javax.swing.JTextArea laserTextArea;
    private javax.swing.JLabel laserTimeRemainText;
    private javax.swing.JButton laserUploadButton;
    private javax.swing.JCheckBox magentaCheckBox;
    public javax.swing.JProgressBar magentaProgress;
    private javax.swing.JSlider powerSlider;
    private javax.swing.JPanel settingsPanel;
    private javax.swing.JSlider speedSlider;
    public javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JCheckBox yellowCheckBox;
    public javax.swing.JProgressBar yellowProgress;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        tabbedPane.setEnabled(true);
        setAllStatusText("Idle");
        setup();
        isProcessing = false;
    }

    @Override
    public void componentClosed() {
        setAllStatusText("Idle");
        setup();
        isProcessing = false;
        try {
            backend.unsetGcodeFile();
        } catch (Exception ex) {}
        resetAllPreviewLabel();
        setColorCheckBoxEnabled(true);
        setColorCheckBoxSelected(true);
        resetLayerSelected();
        cFile.emptyGcodeFiles();
        lFile.emptyGcodeFiles();
        dFile.emptyGcodeFiles();
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
    }
    
    private void resetLayerSelected(){
        cFile.cyanSelected = cyanCheckBox.isSelected();
        cFile.magentaSelected = magentaCheckBox.isSelected();
        cFile.yellowSelected = yellowCheckBox.isSelected();
        cFile.blackSelected = blackCheckBox.isSelected();
    }

    @Override
    public void UGSEvent(UGSEvent event) {
        
    }

    @Override
    public void streamCanceled() {
        if(isProcessing == true){
            isProcessing = false;
            setStatusText("Idle");
        }
    }

    @Override
    public void streamStarted() {
        if(isProcessing == true){
            switch (currentFileIndex) {
                case 0 -> setStatusText("Drawing Cyan");
                case 1 -> setStatusText("Drawing Magenta");
                case 2 -> setStatusText("Drawing Yellow");
                case 3 -> setStatusText("Drawing Black");
            }
        }
    }

    @Override
    public void streamPaused() {
        if(isProcessing == true){
            isProcessing = false;
            paused = true;
        }
    }

    @Override
    public void streamResumed() {
        if(paused == true){
            isProcessing = true;
            paused = false;
        }
    }

    @Override
    public void streamComplete() {
        if(isProcessing == true){
            if((cFile.cyanSelected || cFile.magentaSelected || cFile.yellowSelected || cFile.blackSelected) == true){
                consoleSetText("\nFinished drawing layer " + cFile.gcodeFiles[currentFileIndex] + "\nSending next file");
                setCurrentFileIndex();
                try {
                    processGcode();
                } catch (Exception ex) {}
            }else{
                consoleSetText("\n\n *** Finished drawing the last file ***");
                isProcessing = false;
                setStatusText("Idle");
                resetLayerSelected();
                setColorCheckBoxEnabled(true);
                tabbedPane.setEnabled(true);
            }
        }
    }

    @Override
    public void receivedAlarm(Alarm alarm) {
        if(isProcessing == true){
            isProcessing = false;
            paused = true;
        }
    }

    @Override
    public void commandSkipped(GcodeCommand command) {
        filler = true;
    }

    @Override
    public void commandSent(GcodeCommand command) {
        filler = true;
    }

    @Override
    public void commandComplete(GcodeCommand command) {
        filler = true;
    }

    @Override
    public void probeCoordinates(Position p) {
        filler = true;
    }

    @Override
    public void statusStringListener(ControllerStatus status) {
        filler = true;
    }
}
