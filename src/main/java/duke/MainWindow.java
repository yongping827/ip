package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import duke.command.Command;
import duke.command.ExitCommand;

import java.util.concurrent.TimeUnit;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Stage stage;

    private final Image USER_IMAGE = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image DUKE_IMAGE = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    @FXML
    public void setStage(Stage s) {
        stage = s;
    }

    @FXML
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Closes the stage.
     */
    void exitStage() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ignored) {
        }
        stage.close();
    }

    /**
     * Creates the initial dialog box displaying the start-up message.
     */
    @FXML
    void handleStartUp() {
        String startupMessage = duke.getStartupMessage();
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(startupMessage, DUKE_IMAGE));
    }

    /**
     * Handles the user input. If the user input is parsed into an ExitCommand, the stage is exit. Otherwise, it is
     * handled normally in the method handleGeneralUserInput.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Command command = Parser.parse(input);
        handleGeneralUserInput(input);
        if (command instanceof ExitCommand) {
            exitStage();
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleGeneralUserInput(String input) {
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, USER_IMAGE),
                DialogBox.getDukeDialog(response, DUKE_IMAGE)
        );
        userInput.clear();
    }
}
