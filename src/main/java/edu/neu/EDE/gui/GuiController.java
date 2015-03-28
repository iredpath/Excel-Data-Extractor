package edu.neu.EDE.gui;

/**
 * Created by Ian on 3/24/15.
 */
public class GuiController {

    private GuiView view;

    public static void main(String[] args) {
        GuiController controller = new GuiController();
        controller.launch();
    }

    void launch() {
        view = new GuiView(new GuiModel());
        view.initialize();
    }

}
