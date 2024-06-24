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
import org.openide.util.Exceptions;
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
    private ColorFile colorFile;
    
    private int currentFileIndex = 4;
    private boolean filler;
    private boolean isProcessing = false;
    private int layerMode = 0; 
    private boolean autoRunNextFile = true;
    private boolean paused = false;
    private java.io.File lastPath;
    private final int PREVIEW_WIDTH = 230;
    private final int PREVIEW_HEIGHT = 230;
    

    public pluginTestTopComponent() {
        initComponents();
        setName(Bundle.CTL_pluginTestTopComponent());
        setToolTipText(Bundle.HINT_pluginTestTopComponent());
        
        settings = CentralLookup.getDefault().lookup(Settings.class);
        backend = CentralLookup.getDefault().lookup(BackendAPI.class);
        backend.getController().addListener(this);
        backend.addUGSEventListener(this);
        
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        uploadButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        svgButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        magentaProgress = new javax.swing.JProgressBar();
        blackProgress = new javax.swing.JProgressBar();
        yellowProgress = new javax.swing.JProgressBar();
        cyanProgress = new javax.swing.JProgressBar();
        statusText = new javax.swing.JTextField();
        runButton = new javax.swing.JButton();
        layerComboBox = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        previewLabel = new javax.swing.JLabel();
        timeRemainText = new javax.swing.JLabel();
        durationText = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cyanCheckBox = new javax.swing.JCheckBox();
        magentaCheckBox = new javax.swing.JCheckBox();
        yellowCheckBox = new javax.swing.JCheckBox();
        blackCheckBox = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();

        org.openide.awt.Mnemonics.setLocalizedText(jMenu1, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.jMenu1.text")); // NOI18N
        jMenuBar1.add(jMenu1);

        org.openide.awt.Mnemonics.setLocalizedText(jMenu2, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.jMenu2.text")); // NOI18N
        jMenuBar1.add(jMenu2);

        jTabbedPane2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane2StateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(uploadButton, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.uploadButton.text")); // NOI18N
        uploadButton.setToolTipText(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.uploadButton.toolTipText")); // NOI18N
        uploadButton.setFocusable(false);
        uploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadButtonActionPerformed(evt);
            }
        });

        textArea.setEditable(false);
        textArea.setColumns(20);
        textArea.setRows(5);
        textArea.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane1.setViewportView(textArea);

        org.openide.awt.Mnemonics.setLocalizedText(svgButton, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.svgButton.text")); // NOI18N
        svgButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                svgButtonActionPerformed(evt);
            }
        });

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

        statusText.setEditable(false);
        statusText.setForeground(new java.awt.Color(0, 0, 0));
        statusText.setText(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.statusText.text")); // NOI18N
        statusText.setFocusable(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(magentaProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(blackProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(yellowProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cyanProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(statusText))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cyanProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(magentaProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(yellowProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(blackProgress, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        cyanProgress.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.cyanProgress.AccessibleContext.accessibleName")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(runButton, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.runButton.text")); // NOI18N
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });

        layerComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All layers", "Only cyan layer", "Only magenta layer", "Only yellow layer", "Only black layer", "From magenta layer", "From yellow layer" }));
        layerComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                layerComboBoxActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setPreferredSize(new java.awt.Dimension(240, 240));

        previewLabel.setForeground(new java.awt.Color(0, 0, 0));
        org.openide.awt.Mnemonics.setLocalizedText(previewLabel, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.previewLabel.text")); // NOI18N
        previewLabel.setToolTipText(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.previewLabel.toolTipText")); // NOI18N
        previewLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        previewLabel.setHorizontalTextPosition(JLabel.CENTER);
        previewLabel.setVerticalTextPosition(JLabel.BOTTOM);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(previewLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(previewLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(timeRemainText, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.timeRemainText.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(durationText, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.durationText.text")); // NOI18N

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
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(yellowCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(blackCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(magentaCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cyanCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 7, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cyanCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(magentaCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(yellowCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(blackCheckBox)
                .addContainerGap())
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
                        .addComponent(durationText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(timeRemainText, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(uploadButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(runButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(svgButton, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                    .addComponent(layerComboBox, 0, 1, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(uploadButton)
                            .addComponent(svgButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(runButton)
                            .addComponent(layerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(durationText, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timeRemainText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 405, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(236, 236, 236)
                .addComponent(jButton2)
                .addContainerGap(289, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(jButton2)
                .addContainerGap(265, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jToggleButton1, org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.jToggleButton1.text")); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addComponent(jToggleButton1)
                .addContainerGap(267, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(264, Short.MAX_VALUE)
                .addComponent(jToggleButton1)
                .addGap(140, 140, 140))
        );

        jTabbedPane2.addTab(org.openide.util.NbBundle.getMessage(pluginTestTopComponent.class, "pluginTestTopComponent.jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 342, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void uploadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadButtonActionPerformed
        if(backend.isIdle() && isProcessing == false){
            
            //Open File Chooser
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if(lastPath != null){
                fileChooser.setCurrentDirectory(lastPath);
            }
            int returnVal = fileChooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                
                //Initialize ColorFile
                colorFile = new ColorFile(fileChooser.getSelectedFile(), PREVIEW_WIDTH, PREVIEW_HEIGHT);
                lastPath = colorFile.getParentFile();
                setStatusText("Files Uploaded");
                try {
                    colorFile.scaleImage(fileChooser.getSelectedFile(), PREVIEW_WIDTH, PREVIEW_HEIGHT);
                } catch (IOException ex) {
                    consoleSetText("\nError trying to scale image");
                }
                previewLabel.setIcon(colorFile.getScaledImage());
                previewLabel.setText("Preview");
//                consoleSetText(colorFile.fileList);
            }else {
                consoleSetText("\n\nFile chooser canceled");
            }
        }else{
            consoleSetText("\n\nUnable to upload file");
        }
    }//GEN-LAST:event_uploadButtonActionPerformed
    
    private void processGcode() throws Exception{
        colorFile.sendGcode(colorFile.gcodeFiles[currentFileIndex]);
        progressBarUpdater();
        consoleSetText("\n\nFile " + colorFile.gcodeFiles[currentFileIndex] + " loaded");
    }
    
    public void consoleSetText(String message){
        SwingUtilities.invokeLater(() -> textArea.append(message));
    }
    
    private void progressBarUpdater(){
        Thread progressThread = new Thread(){
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
        progressThread.start();
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
        }
    }
     
    public void setup(){
        for(int i = 0; i <= 3; i++){
            progressBarSet(i, 0);
        }
    }
    
    private void setStatusText(String status){
        statusText.setText("Status : " + status);
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void svgButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_svgButtonActionPerformed
        try {
            Desktop.getDesktop().browse(new URI("https://starfish-app-8zwaq.ondigitalocean.app"));
        } catch (URISyntaxException | IOException ex) {}
    }//GEN-LAST:event_svgButtonActionPerformed

    private void runButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runButtonActionPerformed
        if(isProcessing == false){
            if(colorFile.gcodeFiles[3] != null){
                isProcessing = true;
                setCurrentFileIndex();
                setup();
    //            try {
    //                timeRemainUpdater();
    //            } catch (Exception ex) {
    //                textArea.append("Error occurred calling timeRemainUpdater method");
    //            }
                try {
                    processGcode();
                } catch (Exception ex) {
                    consoleSetText("Error occurred trying to draw Gcode");
                    currentFileIndex = 3;
                    isProcessing = false;
                }
            }else {
                consoleSetText("\n\nUnable to run\nPlease make sure the folder is uploaded");
            }
        }else{
            consoleSetText("\n\nUnable to run\nPlease make sure the machine is not running");
        }
    }//GEN-LAST:event_runButtonActionPerformed

    private void layerComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_layerComboBoxActionPerformed
        if(isProcessing == false){
            layerMode = layerComboBox.getSelectedIndex();
            if(layerMode >= 1 && layerMode <= 4){
                autoRunNextFile = false;
            }else{
                autoRunNextFile = true;
            }
            setCurrentFileIndex();
            consoleSetText("\n\nMachine will now draw '" + layerComboBox.getSelectedItem() + "'");
        }
    }//GEN-LAST:event_layerComboBoxActionPerformed

    private void jTabbedPane2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane2StateChanged
        
    }//GEN-LAST:event_jTabbedPane2StateChanged

    private void magentaCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_magentaCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_magentaCheckBoxActionPerformed

    private void blackCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blackCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_blackCheckBoxActionPerformed

    private void cyanCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cyanCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cyanCheckBoxActionPerformed

    private void yellowCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yellowCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yellowCheckBoxActionPerformed
    
    private void setCurrentFileIndex(){
        switch (layerMode){
            case 0 -> currentFileIndex = 0;
            case 1 -> currentFileIndex = 0;
            case 2 -> currentFileIndex = 1;
            case 3 -> currentFileIndex = 2;
            case 4 -> currentFileIndex = 3;
            case 5 -> currentFileIndex = 1;
            case 6 -> currentFileIndex = 2;
        }
    }
    
//    private void timeRemainUpdater() throws Exception{
//        
//        long totalTime = 0;
//        StringBuilder message = new StringBuilder("\nEstimated time for each layer :");
//        
//        for(int i = 0; i < gcodeFiles.length; i++){
//            backend.setGcodeFile(gcodeFiles[i]);
//            message.append("\n " + backend.getSendRemainingDuration());
//            
//            totalTime += backend.getSendRemainingDuration();
//        }
//        message.append("\n\nTotal Time : " + totalTime);
//        textArea.append(message.toString());
//        Timer time = new Timer();
//        Thread timeThread = new Thread(){
//            @Override
//            public void run(){
//                long timePassed;
//                while(isProcessing){
//                    timePassed = backend.get
//                    timeRemainText.setText("Estimated time remaining : " + );
//                    
//                    Thread.sleep(50));
//                }
//            }
//        };
//        timeThread.start();
//    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox blackCheckBox;
    public javax.swing.JProgressBar blackProgress;
    private javax.swing.JCheckBox cyanCheckBox;
    public javax.swing.JProgressBar cyanProgress;
    private javax.swing.JLabel durationText;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    public javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    public javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JComboBox<String> layerComboBox;
    private javax.swing.JCheckBox magentaCheckBox;
    public javax.swing.JProgressBar magentaProgress;
    private javax.swing.JLabel previewLabel;
    private javax.swing.JButton runButton;
    private javax.swing.JTextField statusText;
    private javax.swing.JButton svgButton;
    public javax.swing.JTextArea textArea;
    private javax.swing.JLabel timeRemainText;
    private javax.swing.JButton uploadButton;
    private javax.swing.JCheckBox yellowCheckBox;
    public javax.swing.JProgressBar yellowProgress;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        setStatusText("Idle");
        setup();
        currentFileIndex = 3;
        isProcessing = false;
    }

    @Override
    public void componentClosed() {
        setStatusText("Idle");
        setup();
        currentFileIndex = 3;
        isProcessing = false;
        try {
            backend.unsetGcodeFile();
        } catch (Exception ex) {}
        previewLabel.setIcon(null);
        previewLabel.setText("");
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
            if(currentFileIndex < 3 && autoRunNextFile == true){
                textArea.append("\nFinished drawing layer " + (currentFileIndex + 1) + "\nSending next file");
                currentFileIndex++;
                try {
                    processGcode();
                } catch (Exception ex) {
                    Exceptions.printStackTrace(ex);
                }
            }else if(currentFileIndex == 3 && autoRunNextFile == true){
                textArea.append("\n\n *** Finished drawing the last file ***");
                isProcessing = false;
                setStatusText("Idle");
            }else if(autoRunNextFile == false){
                currentFileIndex = 3;
                isProcessing = false;
                switch (layerMode){
                    case 1 -> textArea.append("\n\n *** Finished drawing cyan layer ***");
                    case 2 -> textArea.append("\n\n *** Finished drawing magenta layer ***");
                    case 3 -> textArea.append("\n\n *** Finished drawing yellow layer ***");
                    case 4 -> textArea.append("\n\n *** Finished drawing black layer ***");
                }
                setStatusText("Idle");
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
