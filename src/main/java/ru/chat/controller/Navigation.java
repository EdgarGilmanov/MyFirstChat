package ru.chat.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Navigation {
    private final Stage stage;
    private final Scene scene;
    private List<Controller> controllers = new ArrayList<>();

    public Navigation(Stage stage) {
        this.stage = stage;
        this.scene = new Scene(new Pane(), 600, 400);
        this.stage.setScene(scene);
    }

    public Scene getScene() {
        return scene;
    }

    public Controller load(String sUrl) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(sUrl));
            Node root = fxmlLoader.load();

            Controller controller = fxmlLoader.getController();
            controller.setView(root);

            return controller;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void show(BaseController controller) {
        scene.setRoot((Parent) controller.getView());
        controllers.add(controller);
    }

    public void goBack() {
        if (controllers.size() > 1) {
            controllers.remove(controllers.get(controllers.size() - 1));
            scene.setRoot((Parent) controllers.get(controllers.size() - 1).getView());
        }
    }

    public void clearHistory() {
        while (controllers.size() > 1)
            controllers.remove(0);
    }
}