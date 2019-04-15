//package View.Tests;
//
//import static org.testfx.api.FxAssert.verifyThat;
//import static org.testfx.matcher.control.LabeledMatchers.hasText;
//
//import Controller.Controller;
//import Model.AbstractPlayer;
//import View.AddPlayersScreen;
//import View.Main;
//import org.junit.jupiter.api.Test;
//import org.testfx.robot.Motion;
//
//
//import javafx.scene.control.Button;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//import org.testfx.api.FxToolkit;
//import org.testfx.framework.junit.ApplicationTest;
//
//
//public class AddPlayersScreenTest extends ApplicationTest{
//    @Override public void start(Stage stage) {
//        Parent sceneRoot = new AddPlayersScreen(Controller.WIDTH,Controller.HEIGHT,"GUI",,,).getScene();
//        Scene scene = new Scene(sceneRoot, 100, 100);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    @Test
//    public void should_contain_button() {
//        // expect:
//        verifyThat(".button", hasText("click me!"));
//    }
//
//    @Test public void should_click_on_button() {
//        // when:
//        clickOn(".button");
//
//        // then:
//        verifyThat(".button", hasText("clicked!"));
//    }
//}