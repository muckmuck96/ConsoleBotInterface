package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.concurrent.ThreadLocalRandom;

public class Controller {
    @FXML
    private Button consoleSubmit;
    @FXML
    private TextField consoleInput;
    @FXML
    private TextArea consoleWindow;

    @FXML
    private void sendConsoleHandler(ActionEvent event) throws Exception{
        FileParser fp = new FileParser("src/sample/storage.test");
        String line = consoleInput.getText();
        consoleInput.setText("");
        consoleWindow.setText(consoleWindow.getText() + "\nDu: " + line);
        String pipetype = fp.contains(line, false, "greeting");
        String pipeline = (String)fp.get("pipelines:" + pipetype);
        if(pipeline == null){
            consoleWindow.setText(consoleWindow.getText() + "\nBot: Ich habe dich nicht verstanden!");
            return;
        }
        String[] p = pipeline.split("-");
        String[] s = p[0].split(",");
        for(int i = 0; i < s.length; i++){
            if(line.equalsIgnoreCase(s[i])){
                String[] q = p[1].split(",");
                consoleWindow.setText(consoleWindow.getText() + "\nBot: " + q[ThreadLocalRandom.current().nextInt(0, q.length)]);
            }
        }

    }
}
