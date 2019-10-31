package util;

/**
 * @author Huilton
 * @version 1.1
 */
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Util {

    public static void msgDialog(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sistema de Refeições");
        alert.setHeaderText("Atenção:");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static ButtonType msgDialogOption(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.YES, ButtonType.NO);
        alert.setTitle("Sistema de Refeições");
        alert.setHeaderText("Atenção:");
        return alert.showAndWait().get();
    }
}
