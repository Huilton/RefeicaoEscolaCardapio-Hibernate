/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import util.Util;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

/**
 * @author Huilton
 * @version 1.1
 */
public class ScriptEditController implements Initializable {

    @FXML
    private TextArea txScript;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\scriptBanco\\dbrefeicao.sql"))) {
            String sCurrentLine, script = "";

            while ((sCurrentLine = br.readLine()) != null) {
                script = script + "\r\n" + sCurrentLine;
            }

            txScript.setText(script);

        } catch (IOException e) {
            Util.msgDialog(e.getMessage());
        }
    }

}
