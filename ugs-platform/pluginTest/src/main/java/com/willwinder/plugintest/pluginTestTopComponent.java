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
import com.willwinder.universalgcodesender.listeners.MessageListener;
import com.willwinder.universalgcodesender.listeners.MessageType;
import com.willwinder.universalgcodesender.model.Alarm;
import com.willwinder.universalgcodesender.model.Position;
import com.willwinder.universalgcodesender.types.GcodeCommand;
import com.willwinder.universalgcodesender.services.MessageService;
import com.willwinder.universalgcodesender.connection.JSerialCommConnection;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import org.openide.util.Exceptions;
import org.json.JSONObject;

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
    "CTL_pluginTestAction=MultiCNC",
    "CTL_pluginTestTopComponent=MultiCNC Window",
    "HINT_pluginTestTopComponent=This is a MultiCNC window"
})
public class pluginTestTopComponent extends TopComponent
    implements UGSEventListener, ControllerListener, MessageListener{
    
    private final Settings settings;
    public final BackendAPI backend;
    private ColorFile cFile;
    private LaserFile lFile;
    private DrillFile dFile;
    private LastFilePathHelper lastFilePathHelper;
    public JSerialCommConnection conn;
    public MessageService msgSrv;
    
    private int currentFileIndex;
    private boolean filler;
    private boolean isProcessing = false;
    private boolean paused = false;
    private final int PREVIEW_WIDTH = 230;
    private final int PREVIEW_HEIGHT = 230;
    private java.io.File lastPath;
    private Folder.Bounds cBounds;
    private Folder.Bounds lBounds;
    private Folder.Bounds dBounds;
    private String[] gcodeBounds = new String[5];
    private int X_LIMITS = 999; //Change to actual x limits of machine
    private int Y_LIMITS = 999; //Change to actual y limits of machine
    private boolean isOutOfBounds = false;
    private Map<String, String> grblSettings;
    
    private int selectedTab = 0;

    public pluginTestTopComponent() {
        initComponents();
        setName(Bundle.CTL_pluginTestTopComponent());
        setToolTipText(Bundle.HINT_pluginTestTopComponent());
        
        settings = CentralLookup.getDefault().lookup(Settings.class);
        backend = CentralLookup.getDefault().lookup(BackendAPI.class);
        backend.getController().addListener(this);
        backend.addUGSEventListener(this);
        backend.addMessageListener(this);
        
        cFile = new ColorFile();
        lFile = new LaserFile();
        dFile = new DrillFile();
        lastFilePathHelper = new LastFilePathHelper();
        conn = new JSerialCommConnection();
        msgSrv = new MessageService();
        grblSettings = new HashMap<>();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new javax.swing.JTabbedPane();
        drawingPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        magentaProgress = new javax.swing.JProgressBar();
        blackProgress = new javax.swing.JProgressBar();
        yellowProgress = new javax.swing.JProgressBar();
        cyanProgress = new javax.swing.JProgressBar();
        colorStatusText = new javax.swing.JTextField();
        colorUploadButton = new javax.swing.JButton();
        colorRunButton = new javax.swing.JButton();
        colorSvgButton = new javax.swing.JButton();
        colorPreviewButton = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        colorPreviewLabel = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cyanCheckBox = new javax.swing.JCheckBox();
        magentaCheckBox = new javax.swing.JCheckBox();
        yellowCheckBox = new javax.swing.JCheckBox();
        blackCheckBox = new javax.swing.JCheckBox();
        showLimitButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        laserPanel = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
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
        laserPreviewLabel = new javax.swing.JLabel();
        drillingPanel = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
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
        settingsPanel = new javax.swing.JPanel();

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

        drawingPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        magentaProgress.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        magentaProgress.setString(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.magentaProgress.string")); // NOI18N
        magentaProgress.setStringPainted(true);
        jPanel5.add(magentaProgress, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 89, 200, 20));

        blackProgress.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        blackProgress.setString(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.blackProgress.string")); // NOI18N
        blackProgress.setStringPainted(true);
        jPanel5.add(blackProgress, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 153, 200, 20));

        yellowProgress.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        yellowProgress.setString(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.yellowProgress.string")); // NOI18N
        yellowProgress.setStringPainted(true);
        jPanel5.add(yellowProgress, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 121, 200, 20));

        cyanProgress.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cyanProgress.setString(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.cyanProgress.string")); // NOI18N
        cyanProgress.setStringPainted(true);
        jPanel5.add(cyanProgress, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 57, 200, 20));
        cyanProgress.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.cyanProgress.AccessibleContext.accessibleName")); // NOI18N

        colorStatusText.setEditable(false);
        colorStatusText.setForeground(new java.awt.Color(0, 0, 0));
        colorStatusText.setText(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.colorStatusText.text")); // NOI18N
        colorStatusText.setFocusable(false);
        jPanel5.add(colorStatusText, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 11, 200, -1));

        org.openide.awt.Mnemonics.setLocalizedText(colorUploadButton, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.colorUploadButton.text")); // NOI18N
        colorUploadButton.setToolTipText(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.colorUploadButton.toolTipText")); // NOI18N
        colorUploadButton.setFocusable(false);
        colorUploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorUploadButtonActionPerformed(evt);
            }
        });
        jPanel5.add(colorUploadButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 186, -1, -1));

        org.openide.awt.Mnemonics.setLocalizedText(colorRunButton, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.colorRunButton.text")); // NOI18N
        colorRunButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorRunButtonActionPerformed(evt);
            }
        });
        jPanel5.add(colorRunButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 227, 89, -1));

        org.openide.awt.Mnemonics.setLocalizedText(colorSvgButton, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.colorSvgButton.text")); // NOI18N
        colorSvgButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorSvgButtonActionPerformed(evt);
            }
        });
        jPanel5.add(colorSvgButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 186, 88, -1));

        org.openide.awt.Mnemonics.setLocalizedText(colorPreviewButton, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.colorPreviewButton.text")); // NOI18N
        colorPreviewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorPreviewButtonActionPerformed(evt);
            }
        });
        jPanel5.add(colorPreviewButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 227, 89, 23));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setPreferredSize(new java.awt.Dimension(240, 240));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        colorPreviewLabel.setForeground(new java.awt.Color(0, 0, 0));
        org.openide.awt.Mnemonics.setLocalizedText(colorPreviewLabel, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.colorPreviewLabel.text")); // NOI18N
        colorPreviewLabel.setToolTipText(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.colorPreviewLabel.toolTipText")); // NOI18N
        colorPreviewLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        colorPreviewLabel.setHorizontalTextPosition(JLabel.CENTER);
        colorPreviewLabel.setVerticalTextPosition(JLabel.BOTTOM);
        jPanel6.add(colorPreviewLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 7, 244, 250));

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(331, 6, 251, 260));

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.jLabel1.text")); // NOI18N
        jPanel7.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 24, -1, -1));

        cyanCheckBox.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(cyanCheckBox, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.cyanCheckBox.text")); // NOI18N
        cyanCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cyanCheckBoxActionPerformed(evt);
            }
        });
        jPanel7.add(cyanCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 57, 70, -1));

        magentaCheckBox.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(magentaCheckBox, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.magentaCheckBox.text")); // NOI18N
        magentaCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                magentaCheckBoxActionPerformed(evt);
            }
        });
        jPanel7.add(magentaCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 89, -1, -1));

        yellowCheckBox.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(yellowCheckBox, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.yellowCheckBox.text")); // NOI18N
        yellowCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yellowCheckBoxActionPerformed(evt);
            }
        });
        jPanel7.add(yellowCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 121, 70, -1));

        blackCheckBox.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(blackCheckBox, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.blackCheckBox.text")); // NOI18N
        blackCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blackCheckBoxActionPerformed(evt);
            }
        });
        jPanel7.add(blackCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 153, 70, -1));

        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 6, 77, 190));

        org.openide.awt.Mnemonics.setLocalizedText(showLimitButton, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.showLimitButton.text")); // NOI18N
        showLimitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showLimitButtonActionPerformed(evt);
            }
        });
        jPanel3.add(showLimitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, -1, -1));

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, -1, -1));

        drawingPanel.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 11, 588, 440));

        tabbedPane.addTab(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.drawingPanel.TabConstraints.tabTitle"), drawingPanel); // NOI18N

        laserPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setPreferredSize(new java.awt.Dimension(588, 443));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        laserStatusText.setEditable(false);
        laserStatusText.setForeground(new java.awt.Color(0, 0, 0));
        laserStatusText.setText(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.laserStatusText.text")); // NOI18N
        laserStatusText.setFocusable(false);
        jPanel15.add(laserStatusText, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 11, 290, 22));

        laserProgress.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        laserProgress.setName(""); // NOI18N
        laserProgress.setString(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.string")); // NOI18N
        laserProgress.setStringPainted(true);
        jPanel15.add(laserProgress, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 57, 290, 27));

        org.openide.awt.Mnemonics.setLocalizedText(laserRunButton, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.laserRunButton.text")); // NOI18N
        laserRunButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laserRunButtonActionPerformed(evt);
            }
        });
        jPanel15.add(laserRunButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 214, 89, 23));

        org.openide.awt.Mnemonics.setLocalizedText(laserSvgButton, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.laserSvgButton.text")); // NOI18N
        laserSvgButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laserSvgButtonActionPerformed(evt);
            }
        });
        jPanel15.add(laserSvgButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 173, 88, -1));

        org.openide.awt.Mnemonics.setLocalizedText(laserUploadButton, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.laserUploadButton.text")); // NOI18N
        laserUploadButton.setToolTipText(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.laserUploadButton.toolTipText")); // NOI18N
        laserUploadButton.setFocusable(false);
        laserUploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laserUploadButtonActionPerformed(evt);
            }
        });
        jPanel15.add(laserUploadButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 173, -1, -1));

        powerSlider.setValue(100);
        powerSlider.setEnabled(false);
        powerSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                powerSliderStateChanged(evt);
            }
        });
        jPanel15.add(powerSlider, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 135, 290, 20));

        org.openide.awt.Mnemonics.setLocalizedText(laserPreviewButton, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.laserPreviewButton.text")); // NOI18N
        laserPreviewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laserPreviewButtonActionPerformed(evt);
            }
        });
        jPanel15.add(laserPreviewButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(181, 214, 89, -1));

        org.openide.awt.Mnemonics.setLocalizedText(laserPowerCheckBox, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.laserPowerCheckBox.text")); // NOI18N
        laserPowerCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                laserPowerCheckBoxActionPerformed(evt);
            }
        });
        jPanel15.add(laserPowerCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 109, -1, -1));

        jPanel12.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 302, 277));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel16.setPreferredSize(new java.awt.Dimension(240, 240));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        laserPreviewLabel.setForeground(new java.awt.Color(0, 0, 0));
        org.openide.awt.Mnemonics.setLocalizedText(laserPreviewLabel, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.laserPreviewLabel.text")); // NOI18N
        laserPreviewLabel.setToolTipText(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.laserPreviewLabel.toolTipText")); // NOI18N
        laserPreviewLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        laserPreviewLabel.setHorizontalTextPosition(JLabel.CENTER);
        colorPreviewLabel.setVerticalTextPosition(JLabel.BOTTOM);
        jPanel16.add(laserPreviewLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 7, 244, 250));

        jPanel12.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(331, 6, 251, 260));

        laserPanel.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 11, 588, 443));

        tabbedPane.addTab(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.laserPanel.TabConstraints.tabTitle"), laserPanel); // NOI18N

        drillingPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        drillStatusText.setEditable(false);
        drillStatusText.setForeground(new java.awt.Color(0, 0, 0));
        drillStatusText.setText(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.drillStatusText.text")); // NOI18N
        drillStatusText.setFocusable(false);
        jPanel18.add(drillStatusText, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 11, 290, 22));

        drillProgress.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        drillProgress.setName(""); // NOI18N
        drillProgress.setString(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.drillProgress.string")); // NOI18N
        drillProgress.setStringPainted(true);
        jPanel18.add(drillProgress, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 57, 290, 27));

        org.openide.awt.Mnemonics.setLocalizedText(drillRunButton, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.drillRunButton.text")); // NOI18N
        drillRunButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drillRunButtonActionPerformed(evt);
            }
        });
        jPanel18.add(drillRunButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 214, 89, 23));

        org.openide.awt.Mnemonics.setLocalizedText(drillSvgButton, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.drillSvgButton.text")); // NOI18N
        drillSvgButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drillSvgButtonActionPerformed(evt);
            }
        });
        jPanel18.add(drillSvgButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 173, 88, -1));

        org.openide.awt.Mnemonics.setLocalizedText(drillUploadButton, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.drillUploadButton.text")); // NOI18N
        drillUploadButton.setToolTipText(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.drillUploadButton.toolTipText")); // NOI18N
        drillUploadButton.setFocusable(false);
        drillUploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drillUploadButtonActionPerformed(evt);
            }
        });
        jPanel18.add(drillUploadButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 173, -1, -1));

        speedSlider.setValue(100);
        speedSlider.setEnabled(false);
        speedSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                speedSliderStateChanged(evt);
            }
        });
        jPanel18.add(speedSlider, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 135, 290, 20));

        org.openide.awt.Mnemonics.setLocalizedText(drillPreviewButton, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.drillPreviewButton.text")); // NOI18N
        drillPreviewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drillPreviewButtonActionPerformed(evt);
            }
        });
        jPanel18.add(drillPreviewButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(181, 214, 89, -1));

        org.openide.awt.Mnemonics.setLocalizedText(drillSpeedLevel, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.drillSpeedLevel.text")); // NOI18N
        drillSpeedLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drillSpeedLevelActionPerformed(evt);
            }
        });
        jPanel18.add(drillSpeedLevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 109, -1, -1));

        jPanel17.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 302, 277));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel19.setPreferredSize(new java.awt.Dimension(240, 240));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        drillPreviewLabel.setForeground(new java.awt.Color(0, 0, 0));
        org.openide.awt.Mnemonics.setLocalizedText(drillPreviewLabel, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.drillPreviewLabel.text")); // NOI18N
        drillPreviewLabel.setToolTipText(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.drillPreviewLabel.toolTipText")); // NOI18N
        drillPreviewLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        drillPreviewLabel.setHorizontalTextPosition(JLabel.CENTER);
        colorPreviewLabel.setVerticalTextPosition(JLabel.BOTTOM);
        jPanel19.add(drillPreviewLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 7, 244, 250));

        jPanel17.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(331, 6, 251, 260));

        drillingPanel.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 11, 588, 443));

        tabbedPane.addTab(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.drillingPanel.TabConstraints.tabTitle"), drillingPanel); // NOI18N

        javax.swing.GroupLayout settingsPanelLayout = new javax.swing.GroupLayout(settingsPanel);
        settingsPanel.setLayout(settingsPanelLayout);
        settingsPanelLayout.setHorizontalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        settingsPanelLayout.setVerticalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
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
        try {
            openFileChooser();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }//GEN-LAST:event_colorUploadButtonActionPerformed
    
    private void processGcode() throws Exception{
        switch(selectedTab){
            case 0:
                backend.dispatchMessage(MessageType.INFO, cFile.colorChangeString[currentFileIndex]);
                backend.dispatchMessage(MessageType.INFO, "\n\nPen changed");
                cFile.sendGcode(cFile.gcodeFiles[currentFileIndex]);
                backend.dispatchMessage(MessageType.INFO,"\n\nFile " + cFile.gcodeFiles[currentFileIndex] + " loaded");
                break;
            case 1:
                lFile.sendGcode(overridePower(lFile.gcodeFiles));
                backend.dispatchMessage(MessageType.INFO, "\n\nFile " + lFile.gcodeFiles + " loaded");
                break;
            case 2:
                dFile.sendGcode(overridePower(dFile.gcodeFiles));
                backend.dispatchMessage(MessageType.INFO, "\n\nFile " + dFile.gcodeFiles + " loaded");
                break;
        }
        progressBarUpdater();
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
        try {
            backend.sendGcodeCommand("$$");
        } catch (Exception ex) {
            backend.dispatchMessage(MessageType.ERROR, ex.toString());
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
        backend.dispatchMessage(MessageType.INFO, "\nMagenta layer is set to " + cFile.magentaSelected.toString());
    }//GEN-LAST:event_magentaCheckBoxActionPerformed

    private void blackCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blackCheckBoxActionPerformed
        cFile.blackSelected = blackCheckBox.isSelected();
        backend.dispatchMessage(MessageType.INFO,"\nBlack layer is set to " + cFile.blackSelected.toString());
    }//GEN-LAST:event_blackCheckBoxActionPerformed

    private void cyanCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cyanCheckBoxActionPerformed
        cFile.cyanSelected = cyanCheckBox.isSelected();
        backend.dispatchMessage(MessageType.INFO,"\nCyan layer is set to " + cFile.cyanSelected.toString());
    }//GEN-LAST:event_cyanCheckBoxActionPerformed

    private void yellowCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yellowCheckBoxActionPerformed
        cFile.yellowSelected = yellowCheckBox.isSelected();
        backend.dispatchMessage(MessageType.INFO,"\nYellow layer is set to " + cFile.yellowSelected.toString());
    }//GEN-LAST:event_yellowCheckBoxActionPerformed

    private void tabbedPanePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tabbedPanePropertyChange
        filler = true;
    }//GEN-LAST:event_tabbedPanePropertyChange

    private void laserUploadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laserUploadButtonActionPerformed
        try {
            openFileChooser();
        } catch (IOException ex) {
            backend.dispatchMessage(MessageType.ERROR,ex.toString());
            backend.dispatchMessage(MessageType.ERROR,"\nError occurred while uploading file");
        }
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
        try {
            openFileChooser();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }//GEN-LAST:event_drillUploadButtonActionPerformed

    private void laserPreviewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_laserPreviewButtonActionPerformed
        try {
            previewGcode();
        } catch (Exception ex) {
            backend.dispatchMessage(MessageType.ERROR,"\nError previewing gcode\n" + ex);
        }
    }//GEN-LAST:event_laserPreviewButtonActionPerformed

    private void drillPreviewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drillPreviewButtonActionPerformed
        try {
            previewGcode();
        } catch (Exception ex) {
            backend.dispatchMessage(MessageType.ERROR,"\nError previewing gcode\n" + ex);
        }
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

    private void colorPreviewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorPreviewButtonActionPerformed
        try {
            previewGcode();
        } catch (Exception ex) {
            backend.dispatchMessage(MessageType.ERROR,"\nError previewing gcode\n" + ex);
        }
    }//GEN-LAST:event_colorPreviewButtonActionPerformed

    private void showLimitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showLimitButtonActionPerformed
        try {
            StringBuilder gcode = new StringBuilder();
            gcode.append("G90\n");
            
            gcode.append("G0 X").append(cBounds.minX)
                    .append(" Y").append(cBounds.minY).append("\n");
            
            gcode.append("G1 F3000 X").append(cBounds.maxX)
                    .append(" Y").append(cBounds.minY).append("\n");
            
            gcode.append("G1 X").append(cBounds.maxX)
                    .append(" Y").append(cBounds.maxY).append("\n");
            
            gcode.append("G1 X").append(cBounds.minX)
                    .append(" Y").append(cBounds.maxY).append("\n");
            
            gcode.append("G1 X").append(cBounds.minX)
                    .append(" Y").append(cBounds.minY).append("\n");
            
            gcode.append("G4 P0.5\n");
            
            backend.sendGcodeCommand(gcode.toString());
        } catch (Exception ex) {
            backend.dispatchMessage(MessageType.ERROR,"Unable to show limits");
        }
    }//GEN-LAST:event_showLimitButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            backend.dispatchMessage(MessageType.INFO,"\n[JSON:{\"move\":45}]");
            backend.dispatchMessage(MessageType.INFO, ("\n" + grblSettings.toString()));
            backend.dispatchMessage(MessageType.INFO, grblSettings.get("$32"));
        } catch (Exception ex) {
            backend.dispatchMessage(MessageType.ERROR, ex.toString());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void powerSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_powerSliderStateChanged
//        if (!powerSlider.getValueIsAdjusting()) {
            backend.dispatchMessage(MessageType.INFO, "\nLaser power changed to " + String.valueOf(powerSlider.getValue() * 10));
//        }
    }//GEN-LAST:event_powerSliderStateChanged

    private void speedSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_speedSliderStateChanged
//        if (!powerSlider.getValueIsAdjusting()) {
            backend.dispatchMessage(MessageType.INFO, "\nDrill power changed to " + String.valueOf(speedSlider.getValue() * Integer.parseInt(grblSettings.get("$30"))));
//        }
    }//GEN-LAST:event_speedSliderStateChanged
  
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
    
    private void openFileChooser() throws IOException{
        if(backend.isIdle() && isProcessing == false){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            lastPath = lastFilePathHelper.loadLastFilePath();
            if(lastPath != null){
                fileChooser.setCurrentDirectory(lastPath);
            }
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                switch (selectedTab){
                    case 0:
                        cFile.setup(fileChooser.getSelectedFile());
                        lastFilePathHelper.saveLastFilePath(cFile.getParentFile());
                        setStatusText("Files Uploaded");
                        try {
                            cFile.scaleImage(fileChooser.getSelectedFile(), PREVIEW_WIDTH, PREVIEW_HEIGHT);
                        } catch (IOException ex) {
                            backend.dispatchMessage(MessageType.ERROR,"\nError trying to scale image");
                        }
                        colorPreviewLabel.setIcon(cFile.getScaledImage());
                        colorPreviewLabel.setText("Preview");
                        cBounds = cFile.getGcodeBounds(cFile.gcodeFiles);
                        gcodeBounds = cBounds.getPreviewGcode();
                        isOutOfBounds = checkOutOfBounds(cBounds.maxX, cBounds.maxY);
                        break;
                    
                    case 1:
                        lFile.setup(fileChooser.getSelectedFile());
                        lastFilePathHelper.saveLastFilePath(lFile.getParentFile());
                        setStatusText("Files Uploaded");
                        try {
                            lFile.scaleImage(fileChooser.getSelectedFile(), PREVIEW_WIDTH, PREVIEW_HEIGHT);
                        } catch (IOException ex) {
                            backend.dispatchMessage(MessageType.ERROR,"\nError trying to scale image");
                        }
                        backend.dispatchMessage(MessageType.INFO, "3");
                        laserPreviewLabel.setIcon(lFile.getScaledImage());
                        laserPreviewLabel.setText("Preview");
                        lBounds = Folder.getGcodeBounds(lFile.gcodeFiles);
                        gcodeBounds = lBounds.getPreviewGcode();
                        isOutOfBounds = checkOutOfBounds(lBounds.maxX, lBounds.maxY);
                        break;
                    
                    case 2:
                        dFile.setup(fileChooser.getSelectedFile());
                        lastFilePathHelper.saveLastFilePath(dFile.getParentFile());
                        setStatusText("Files Uploaded");
                        try {
                            dFile.scaleImage(fileChooser.getSelectedFile(), PREVIEW_WIDTH, PREVIEW_HEIGHT);
                        } catch (IOException ex) {
                            backend.dispatchMessage(MessageType.ERROR,"\nError trying to scale image");
                        }
                        drillPreviewLabel.setIcon(dFile.getScaledImage());
                        drillPreviewLabel.setText("Preview");
                        dBounds = Folder.getGcodeBounds(dFile.gcodeFiles);
                        gcodeBounds = dBounds.getPreviewGcode();
                        isOutOfBounds = checkOutOfBounds(dBounds.maxX, dBounds.maxY);
                        break;
                        
                }
                if (isOutOfBounds){
                    backend.dispatchMessage(MessageType.ERROR,"\n\n *** Gcode is out of bounds *** ");
                }
            }else {
                backend.dispatchMessage(MessageType.INFO,"\n\nFile chooser cancelled");
            }
        }else{
            backend.dispatchMessage(MessageType.ERROR,"\n\nUnable to upload file");
        }
    }
    
    private void openWeb(){
        try {
            Desktop.getDesktop().browse(new URI("https://starfish-app-8zwaq.ondigitalocean.app"));
        } catch (URISyntaxException | IOException ex) {}
    }
    
    private void runFile(){
        if (!isOutOfBounds){
            switch (selectedTab){
                case 0:
                    if(backend.isIdle() && isProcessing == false && !cFile.isEmptyGcodeFiles()){
                        setSelectedFiles();
                        if((cFile.cyanSelected || cFile.magentaSelected || cFile.yellowSelected || cFile.blackSelected) == true){
                                isProcessing = true;
                                setCurrentFileIndex();
                                setup();
                                tabbedPane.setEnabled(false);
                                setColorCheckBoxEnabled(false);
                                try {
                                    processGcode();
                                } catch (Exception ex) {
                                    backend.dispatchMessage(MessageType.ERROR,"\nError occurred trying to process Gcode");
                                    currentFileIndex = 3;
                                    isProcessing = false;
                                }
                        }else {
                            backend.dispatchMessage(MessageType.ERROR,"\n\nUnable to run\nPlease make sure to select a layer");
                        }
                    }else{
                        backend.dispatchMessage(MessageType.ERROR,"\n\nUnable to run\nPlease make sure the machine is in idle mode and folder is loaded");
                    }
                    break;

                case 1:
                    if(backend.isIdle() && isProcessing == false && !lFile.isEmptyGcodeFiles()){
                        isProcessing = true;
                        tabbedPane.setEnabled(false);
                        try {
                            processGcode();
                        } catch (Exception ex) {
                            backend.dispatchMessage(MessageType.ERROR,"\nError occurred trying to process Gcode");
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
                            backend.dispatchMessage(MessageType.ERROR,"\nError occurred trying to process Gcode");
                            isProcessing = false;
                        }
                    }
                    break;
            }
        } else {
            backend.dispatchMessage(MessageType.ERROR,"\n\n *** Gcode is out of bounds *** ");
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
    
    private void previewGcode() throws Exception{
   
        //check if running only this command works before un-commenting
//        backend.performHomingCycle();
        if(backend.isIdle() == true && isProcessing == false){
            for(int i = 0; i < 5; i++){
                backend.dispatchMessage(MessageType.INFO,"\n" + gcodeBounds[i]);
                backend.sendGcodeCommand(gcodeBounds[i]);
            }
        } else{
            backend.dispatchMessage(MessageType.ERROR,"\nCurrently unable to preview file");
        }
    }
    
    private boolean checkOutOfBounds(float xBounds, float yBounds){
        if (xBounds > X_LIMITS || yBounds > Y_LIMITS){
            return true;
        }
        return false;
    }
    
    private String overridePower(String line) {
        if (selectedTab == 1 && laserPowerCheckBox.isSelected()) {
            if (line.contains("S")) {
                return line.replaceAll("S\\d+", "S" + (powerSlider.getValue() * 10));
            }
        } else if (selectedTab == 2 && drillSpeedLevel.isSelected()) {
            if (line.contains("S")) {
                return line.replaceAll("S\\d+", "S" + (speedSlider.getValue() * Integer.parseInt(grblSettings.get("$30"))));
            }
        }
        return line;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox blackCheckBox;
    public javax.swing.JProgressBar blackProgress;
    private javax.swing.JButton colorPreviewButton;
    private javax.swing.JLabel colorPreviewLabel;
    private javax.swing.JButton colorRunButton;
    private javax.swing.JTextField colorStatusText;
    private javax.swing.JButton colorSvgButton;
    private javax.swing.JButton colorUploadButton;
    private javax.swing.JCheckBox cyanCheckBox;
    public javax.swing.JProgressBar cyanProgress;
    public javax.swing.JPanel drawingPanel;
    private javax.swing.JButton drillPreviewButton;
    private javax.swing.JLabel drillPreviewLabel;
    public javax.swing.JProgressBar drillProgress;
    private javax.swing.JButton drillRunButton;
    private javax.swing.JCheckBox drillSpeedLevel;
    private javax.swing.JTextField drillStatusText;
    private javax.swing.JButton drillSvgButton;
    private javax.swing.JButton drillUploadButton;
    private javax.swing.JPanel drillingPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel12;
    public javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    public javax.swing.JPanel jPanel17;
    public javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel laserPanel;
    private javax.swing.JCheckBox laserPowerCheckBox;
    private javax.swing.JButton laserPreviewButton;
    private javax.swing.JLabel laserPreviewLabel;
    public javax.swing.JProgressBar laserProgress;
    private javax.swing.JButton laserRunButton;
    private javax.swing.JTextField laserStatusText;
    private javax.swing.JButton laserSvgButton;
    private javax.swing.JButton laserUploadButton;
    private javax.swing.JCheckBox magentaCheckBox;
    public javax.swing.JProgressBar magentaProgress;
    private javax.swing.JSlider powerSlider;
    private javax.swing.JPanel settingsPanel;
    private javax.swing.JButton showLimitButton;
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
        if(isProcessing == true && selectedTab == 0){
            switch (currentFileIndex) {
                case 0 -> setStatusText("Drawing Cyan");
                case 1 -> setStatusText("Drawing Magenta");
                case 2 -> setStatusText("Drawing Yellow");
                case 3 -> setStatusText("Drawing Black");
            }
        }else if(isProcessing == true){
            setStatusText("Running");
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
        if(isProcessing == true && selectedTab == 0){
            if((cFile.cyanSelected || cFile.magentaSelected || cFile.yellowSelected || cFile.blackSelected) == true){
                backend.dispatchMessage(MessageType.INFO,"\nFinished drawing layer " + cFile.gcodeFiles[currentFileIndex] + "\nSending next file");
                setCurrentFileIndex();
                try {
                    backend.performHomingCycle();
                    processGcode();
                } catch (Exception ex) {
                    backend.dispatchMessage(MessageType.ERROR, ex.toString());
                }
            }else{
                backend.dispatchMessage(MessageType.INFO,"\n\n *** Finished drawing the last file ***");
                isProcessing = false;
                setStatusText("Idle");
                resetLayerSelected();
                setColorCheckBoxEnabled(true);
                tabbedPane.setEnabled(true);
            }
        }else if(isProcessing == true){
            backend.dispatchMessage(MessageType.INFO,"\n\n *** Finished running ***");
            isProcessing = false;
            setStatusText("Idle");
            tabbedPane.setEnabled(true);
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
    public void commandSkipped(GcodeCommand command) {}

    @Override
    public void commandSent(GcodeCommand command) {}

    @Override
    public void commandComplete(GcodeCommand command) {}

    @Override
    public void probeCoordinates(Position p) {}

    @Override
    public void statusStringListener(ControllerStatus status) {}

    @Override
     public void onMessage(MessageType messageType, String message) {
         if (message.startsWith("[JSON:")){
//             backend.dispatchMessage(MessageType.INFO,"\nReceived Message:\n" + message);
             String jsonString = message.substring(6, message.length() - 1); // remove [JSON: and ]
             JSONObject json = new JSONObject(jsonString);
 
             if (json.has("mode")) {
                 String mode = json.getString("mode");
 
//                 backend.dispatchMessage(MessageType.INFO,"\nParsed Mode: " + mode);
                 
                 if (null != mode)switch (mode) {
                     case "drawing" -> selectedTab = 0;
                     case "laser" -> selectedTab = 1;
                     case "spindle" -> selectedTab = 2;
                     default -> {
                     }
                 }
                 if (selectedTab == 1) {
                     try {
                         backend.sendGcodeCommand("$32=1");
                     } catch (Exception ex) {
                         backend.dispatchMessage(MessageType.ERROR, ex.toString());
                     }
                 } else {
                     try {
                         backend.sendGcodeCommand("$32=0");
                     } catch (Exception ex) {
                         backend.dispatchMessage(MessageType.ERROR, ex.toString());
                     }
                 }
                 tabbedPane.setSelectedIndex(selectedTab);
            }
        }else if (message.startsWith("$")) {
            String[] parts = message.split("=");

            if (parts.length == 2) {
                String key = parts[0].trim();
                String rawValue = parts[1].trim();

                String[] valueParts = rawValue.split("[\\s(]+");
                if (valueParts.length > 0) {
                    String value = valueParts[0];
                    grblSettings.put(key, value);
                }
            }
        }
    }
}
