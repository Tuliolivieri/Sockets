/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author tulio
 */
public class Server extends Application
{
    private static double xOffset = 0; private static double yOffset = 0;
    @Override
    public void start(Stage stage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root, Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        
        scene.setOnMousePressed(new EventHandler<MouseEvent>() { @Override public void handle(MouseEvent event) { xOffset = stage.getX() - event.getScreenX(); yOffset = stage.getY() - event.getScreenY(); } });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() { @Override public void handle(MouseEvent event) { stage.setX(event.getScreenX() + xOffset); stage.setY(event.getScreenY() + yOffset); } });
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
