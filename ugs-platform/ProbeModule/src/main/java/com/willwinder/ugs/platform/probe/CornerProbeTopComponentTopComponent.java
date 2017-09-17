/*
    Copyright 2017 Will Winder

    This file is part of Universal Gcode Sender (UGS).

    UGS is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    UGS is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with UGS.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.willwinder.ugs.platform.probe;

import com.willwinder.ugs.nbm.visualizer.shared.IRendererNotifier;
import com.willwinder.ugs.nbm.visualizer.shared.RenderableUtils;
import com.willwinder.ugs.nbp.lib.lookup.CentralLookup;
import com.willwinder.ugs.nbp.lib.services.LocalizingService;
import com.willwinder.ugs.platform.probe.AbstractProbeService.ProbeContext;
import com.willwinder.ugs.platform.probe.renderable.ProbePathPreview;
import com.willwinder.universalgcodesender.listeners.UGSEventListener;
import com.willwinder.universalgcodesender.model.BackendAPI;
import com.willwinder.universalgcodesender.model.UGSEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import net.miginfocom.swing.MigLayout;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.windows.TopComponent;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//com.willwinder.ugs.platform.probe//CornerProbeTopComponent//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "CornerProbeTopComponentTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(
        category = "Window",
        id = "com.willwinder.ugs.platform.probe.CornerProbeTopComponentTopComponent")
@ActionReference(path = LocalizingService.PLUGIN_WINDOW)
@TopComponent.OpenActionRegistration(
        displayName = "Corner Probe",
        preferredID = "CornerProbeTopComponentTopComponent"
)
public final class CornerProbeTopComponentTopComponent extends TopComponent implements UGSEventListener {
    private ProbePathPreview preview = null;

    private static final String INSIDE_IMG = "images/inside.png";
    private static final String OUTSIDE_IMG = "images/outside.png";
    private static final String xPosition = "X Position:";
    private static final String yPosition = "Y Position:";

    private SpinnerNumberModel outsideXModel;
    private SpinnerNumberModel outsideYModel;
    private SpinnerNumberModel insideXModel;
    private SpinnerNumberModel insideYModel;

    private final JLabel insideImageLabel = new JLabel("", JLabel.CENTER);
    private final JLabel outsideImageLabel = new JLabel("", JLabel.CENTER);

    private final JButton measureInside = new JButton("Measure Inside Corner");
    private final JButton measureOutside = new JButton("Measure Outside Corner");

    private final JButton settings1 = new JButton("Settings");
    private final JButton settings2 = new JButton("Settings");

    private final ImageIcon insideImage;
    private final ImageIcon outsideImage;

    private final ProbeService2 ps2;
    private final BackendAPI backend;

    JSpinner ixp = new JSpinner();
    public CornerProbeTopComponentTopComponent() {
        setName("Corner Probe");

        backend = CentralLookup.getDefault().lookup(BackendAPI.class);
        backend.addUGSEventListener(this);

        insideImage = new ImageIcon(this.getClass().getClassLoader().getResource(INSIDE_IMG));
        outsideImage = new ImageIcon(this.getClass().getClassLoader().getResource(OUTSIDE_IMG));

        ps2 = new ProbeService2(backend);

        // Initialize spinner models
        // TODO: Initialize the number.
        outsideXModel = new SpinnerNumberModel(10., 0., 1000000., 0.1);
        outsideYModel = new SpinnerNumberModel(10., 0., 1000000., 0.1);
        insideXModel = new SpinnerNumberModel(10., 0., 1000000., 0.1);
        insideYModel = new SpinnerNumberModel(10., 0., 1000000., 0.1);

        measureOutside.addActionListener((e) -> {
            ProbeContext pc = new AbstractProbeService.ProbeContext(
                1, backend.getMachinePosition(),
                get(outsideXModel), get(outsideYModel), 100., 1);
                ps2.performOutsideCornerProbe(pc);
            });

        measureInside.addActionListener((e) -> {
            ProbeContext pc = new AbstractProbeService.ProbeContext(
                1, backend.getMachinePosition(),
                get(insideXModel), get(insideYModel), 100., 1);
                ps2.performInsideCornerProbe(pc);
            });

        initComponents();
        updateControls();
    }

    public void updateControls() {
        boolean enabled = backend.isIdle();
        this.measureInside.setEnabled(enabled);
        this.measureOutside.setEnabled(enabled);
    }

    @Override
    public void UGSEvent(UGSEvent evt) {
        if (evt.isStateChangeEvent()) {
            updateControls();
        }
    }

    // deal with casting the spinner model to a double.
    private static double get(SpinnerNumberModel model) {
        return (double) model.getValue();
    }

    private void initComponents() {
        setMinimumSize(new Dimension(500, 230));
        setPreferredSize(new Dimension(500, 240));

        JPanel inside = new JPanel(new MigLayout("fill, wrap 2"));
        inside.setBorder(BorderFactory.createTitledBorder("inside"));
        inside.add(new JLabel(xPosition));
        inside.add(new JSpinner(insideXModel), "growx");
        inside.add(new JLabel(yPosition));
        inside.add(new JSpinner(insideYModel), "growx");
        inside.add(insideImageLabel, "span 2, growx");
        inside.add(measureInside, "span 2, growx");
        inside.add(settings1, "span2, growx");

        JPanel outside = new JPanel(new MigLayout("fill, wrap 2"));
        outside.setBorder(BorderFactory.createTitledBorder("outside"));
        outside.add(new JLabel(xPosition));
        outside.add(new JSpinner(outsideXModel), "growx");
        outside.add(new JLabel(yPosition));
        outside.add(new JSpinner(outsideYModel), "growx");
        outside.add(outsideImageLabel, "span 2, growx");
        outside.add(measureOutside, "span 2, growx");
        outside.add(settings2, "span 2, growx");
        
        JPanel group = new JPanel(new MigLayout("fill"));
        group.add(inside, "growx");
        group.add(outside, "growx");

        this.setLayout(new BorderLayout());
        this.add(group);
    }

    // Resize the images when the bounds change.
    @Override
    public void setBounds(int x, int y, int width, int height) {
        System.out.println((width-x) +", "+(height-y));
        int w = (int) (((width-80) / 2) * 0.85);
        insideImageLabel.setIcon(
                new ImageIcon(
                        insideImage.getImage().getScaledInstance(w, w/2, Image.SCALE_SMOOTH)));
        outsideImageLabel.setIcon(
                new ImageIcon(
                        outsideImage.getImage().getScaledInstance(w, w/2, Image.SCALE_SMOOTH)));
        super.setBounds(x, y, width, height);
    }

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
        if (this.preview == null) {
            //IRendererNotifier notifier = Lookup.getDefault().lookup(IRendererNotifier.class);
            this.preview = new ProbePathPreview("Preview");
                    //Localization.getString("platform.visualizer.renderable.autolevel-preview"));
            RenderableUtils.registerRenderable(this.preview);
        }
    }

    @Override
    public void componentClosed() {
        RenderableUtils.removeRenderable(this.preview);
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
}