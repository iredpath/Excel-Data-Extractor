package edu.neu.EDE.gui;

import java.io.File;
import java.io.IOException;

/**
 * Created by Ian on 3/24/15.
 */
public class GuiController {

    private GuiView view;

    public static void main(String[] args) {
        GuiController controller = new GuiController();
        controller.initialize();

    }

    void initialize() {
        view = new GuiView(new GuiModel());
        view.initialize();
    }

}
