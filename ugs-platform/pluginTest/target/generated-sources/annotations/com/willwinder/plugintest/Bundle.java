package com.willwinder.plugintest;
/** Localizable strings for {@link com.willwinder.plugintest}. */
class Bundle {
    /**
     * @return <i>windowClass</i>
     * @see windowClassTopComponent
     */
    static String CTL_windowClassAction() {
        return org.openide.util.NbBundle.getMessage(Bundle.class, "CTL_windowClassAction");
    }
    /**
     * @return <i>windowClass Window</i>
     * @see windowClassTopComponent
     */
    static String CTL_windowClassTopComponent() {
        return org.openide.util.NbBundle.getMessage(Bundle.class, "CTL_windowClassTopComponent");
    }
    /**
     * @return <i>This is a windowClass window</i>
     * @see windowClassTopComponent
     */
    static String HINT_windowClassTopComponent() {
        return org.openide.util.NbBundle.getMessage(Bundle.class, "HINT_windowClassTopComponent");
    }
    private Bundle() {}
}
