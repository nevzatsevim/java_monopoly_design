package view;

import Engine.Notifications.Notification;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * This class is used to display the information of an action.
 * Displays a new string when any change happens
 * @author Nevzat Sevim, Cemal Yagcioglu
 */

public class feedbackUI extends VBox {

  private display myDisplay;
  private TextArea textArea;
  private Label label;

  public feedbackUI(display newDisplay) {

    this.myDisplay = newDisplay;
    this.textArea = new TextArea();
    this.textArea.setWrapText(true);
    this.label = new Label("Notifications");

    int height = (2 * myDisplay.getHeight()) / 10;
    int width = (3 * myDisplay.getWidth()) / 10;

    this.setMinSize(width, height);
    this.setMaxSize(width, height);
    this.setAlignment(Pos.CENTER);

    this.getChildren().addAll(label, textArea);
  }

  public TextArea getTextArea() {
    return textArea;
  }

  public void updateFeedBackUI() {
    this.getTextArea().setText("");
    List<String> actionsInfo = Notification.getNotifications();
    for (String info : actionsInfo) {
      this.getTextArea().appendText(info);
    }
    this.getTextArea().appendText("");
  }


  /**
   * Toggles the Class to dark mode
   * @param dark & alt: two strings that define Style preferences
   */

    public void toggleDark(String dark, String alt){

        this.setStyle(dark);
        label.setStyle(alt);
        textArea.setStyle(dark);

        Region content = (Region) textArea.lookup(".content");
        content.setStyle(dark);
    }

}

