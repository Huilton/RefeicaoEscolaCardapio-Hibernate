/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bo.PessoaBo;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Pessoa;
import util.Global;
import util.Numeric;
import util.Util;
import util.ValidaEmail;

/**
 * FXML Controller class
 *
 * @author Aluno
 */
public class PessoaEditController implements Initializable {

    private List<Pessoa> lista;
    private Pessoa obj = new Pessoa();
    private PessoaBo bo;
    @FXML
    private TableView<Pessoa> tblPessoa;
    @FXML
    private TableColumn<Pessoa, Integer> tcIdPessoa;
    @FXML
    private TableColumn<Pessoa, String> tcNome;
    @FXML
    private TableColumn<Pessoa, String> tcTelefone;
    @FXML
    private TableColumn<Pessoa, String> tcEmail;
    @FXML
    private TextField txtCodigo;
    @FXML
    private Button btnExcluir;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTelefone;
    @FXML
    private Label lblCodigo;
    @FXML
    private Button btnLimpar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtCodigo.setVisible(false);
        lblCodigo.setVisible(false);
        this.bo = new PessoaBo();

        atualizarTabela();

        tcIdPessoa.setCellValueFactory(new PropertyValueFactory<>("idPessoa"));
        tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        // TODO
    }

    @FXML
    private void handleBtnSalvar(ActionEvent event) {
        try {
            execute(Global.Acao.Save);
            atualizarTabela();
        } catch (Exception e) {
            Util.msgDialog(e.getMessage());
        }
    }

    @FXML
    private void handleBtnExcluir(ActionEvent event) {
        try {
            execute(Global.Acao.Delete);
            atualizarTabela();
        } catch (Exception ex) {
            Util.msgDialog(ex.getMessage());
        }
    }

    public void execute(Global.Acao acao) throws Exception {
        execute(acao, null);
    }

    private void clear() {
        txtNome.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
        txtCodigo.setText("");

    }

    public void execute(Global.Acao acao, String method) throws Exception {
        switch (acao) {
            case Save:
                validation(acao);
                obj.setNome(txtNome.getText());
                obj.setEmail(txtEmail.getText());
                obj.setTelefone(txtTelefone.getText());
                this.bo.save(obj);
                Util.msgDialog("Salvo com sucesso.");
                clear();
                txtCodigo.setVisible(false);
                lblCodigo.setVisible(false);
                break;
            case Delete:
                validation(acao);
                this.bo.delete(obj);
                Util.msgDialog("Excluido com sucesso.");
                clear();
                break;
            case find:
                //find
                break;
            case findAll:
                this.lista = this.bo.findAll();
                break;
            case Execute:
                break;
            default:
                throw new Exception("Ação não identificada.");
        }
    }

    public void validation(Global.Acao acao) throws Exception {
        switch (acao) {
            case Save:
                if (txtNome.getText().trim().isEmpty()) {
                    txtNome.requestFocus();
                    throw new Exception("Preencha o Nome!");
                }
                if (txtEmail.getText().trim().isEmpty()) {
                    txtEmail.requestFocus();
                    throw new Exception("Preencha o E-mail!");
                }
                if (!ValidaEmail.validar(txtEmail.getText())) {
                    txtEmail.requestFocus();
                    throw new Exception("Formato de E-mail Invalido!");
                }
                if (txtTelefone.getText().trim().isEmpty()) {
                    txtTelefone.requestFocus();
                    throw new Exception("Preencha o Telefone!");
                }

                break;

            case Delete:
                if (!Numeric.isNumeric(txtCodigo.getText())) {
                    throw new Exception("Selecione um dado na tabela que deseja excluir.");
                }
                break;

            default:
                throw new Exception("Pessoa.Validation, ação inesperada.");
        }
    }

    private void atualizarTabela() {
        try {
            tblPessoa.getItems().clear();
            tblPessoa.getItems().setAll(this.bo.findAll());
        } catch (Exception ex) {
            Util.msgDialog(ex.getMessage());
        }

    }

    @FXML
    private void handleTblPessoaClicked(MouseEvent event) {
        obj = tblPessoa.getSelectionModel().getSelectedItem();
        txtCodigo.setVisible(true);
        lblCodigo.setVisible(true);
        if (obj != null) {
            txtCodigo.setText(String.valueOf(obj.getIdPessoa()));
            txtNome.setText(String.valueOf(obj.getNome()));
            txtEmail.setText(String.valueOf(obj.getEmail()));
            txtTelefone.setText(String.valueOf(obj.getTelefone()));
        }
    }

    @FXML
    private void handleBtnLimpar(ActionEvent event) {
        clear();
    }

}
