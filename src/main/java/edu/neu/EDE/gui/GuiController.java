package edu.neu.EDE.gui;

/**
 * GuiController - basic main class
 * serving as entry point into jar
 */
public class GuiController {
    /**
     * @author Ian Redpath
     * @version 3/24/2015
     */

    private GuiView view;

    /**
     * main method - called when application is launched
     * @param args any initializing args
     */
    public static void main(String[] args) {
        GuiController controller = new GuiController();
        controller.launch();
    }

    /**
     * launch the application
     */
    void launch() {
        view = new GuiView(new GuiModel());
        view.initialize();
    }

}
