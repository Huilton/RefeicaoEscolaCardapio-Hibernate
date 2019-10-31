package controller;

import util.Util;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @author Huilton
 * @version 1.1
 */
public class HomeEditController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void handleCadRefeicao() {
        try {
            openWindow("/view/RefeicaoEdit.fxml", "Cadastro e Manutenções de Refeição");
        } catch (Exception e) {
            Util.msgDialog(e.getMessage());
        }
    }

    @FXML
    private void handleDiagrama() {
        try {
            openWindow("/view/DiagramaClasse.fxml", "Diagrama ER");
        } catch (Exception e) {
            Util.msgDialog(e.getMessage());
        }
    }

    @FXML
    private void handleScript() {
        try {
            openWindow("/view/ScriptEdit.fxml", "Script de Banco");
        } catch (Exception e) {
            Util.msgDialog(e.getMessage());
        }
    }

    private void openWindow(String path, String title) throws Exception {
        Parent root1 = FXMLLoader.load(getClass().getResource(path));
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/iconeFaQi.png")));
        stage.show();
    }

    @FXML
    private void handleCadPessoa(ActionEvent event) {
        try {
            openWindow("/view/PessoaEdit.fxml", "Cadastro e Manutenções de Pessoa");
        } catch (Exception e) {
            Util.msgDialog(e.getMessage());
        }
    }

    @FXML
    private void handleFotoCardapio(ActionEvent event) {
        try {
            openWindow("/view/RefeicaoEdit.fxml", "Cadastro e Manutenções de Refeição");
        } catch (Exception e) {
            Util.msgDialog(e.getMessage());
        }
    }

    private void handleCadEmail(ActionEvent event) {
        try {
            openWindow("/view/EmailEdit.fxml", "Cadastro e Manutenções de Email");
        } catch (Exception e) {
            Util.msgDialog(e.getMessage());
        }
    }

    @FXML
    private void handleCadInstituicao(ActionEvent event) {
        try {
            openWindow("/view/InstituicaoEdit.fxml", "Cadastro e Manutenções de Instituição");
        } catch (Exception e) {
            Util.msgDialog(e.getMessage());
        }
    }

    @FXML
    private void handleCadMenu(ActionEvent event) {
         try {
            openWindow("/view/MenuEdit.fxml", "Cadastro e Manutenções de Menu");
        } catch (Exception e) {
            Util.msgDialog(e.getMessage());
        }
    }
}
