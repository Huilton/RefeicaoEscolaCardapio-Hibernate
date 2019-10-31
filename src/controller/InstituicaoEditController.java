/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bo.InstituicaoBo;
import bo.PessoaBo;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Instituicao;
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
public class InstituicaoEditController implements Initializable {

    private List<Instituicao> lista;
    private Instituicao obj = new Instituicao();
    private InstituicaoBo bo;
    @FXML
    private TableView<Instituicao> tblInstituicao;
    @FXML
    private TableColumn<Instituicao, Integer> tcIdInstituicao;
    @FXML
    private TableColumn<Instituicao, String> tcNome;
    @FXML
    private TableColumn<Instituicao, String> tcTelefone;
    @FXML
    private TableColumn<Instituicao, String> tcEmail;
    @FXML
    private TableColumn<Instituicao, String> tcEmailPessoa;
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
    private ComboBox<Pessoa> cbxPessoa;
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

        PessoaBo pessoaBo = new PessoaBo();

        try {
            cbxPessoa.getItems().setAll(pessoaBo.findAll());
        } catch (Exception ex) {
            Util.msgDialog(ex.getMessage());
        }
        this.bo = new InstituicaoBo();

        atualizarTabela();

        tcIdInstituicao.setCellValueFactory(new PropertyValueFactory<>("idInstituicao"));
        tcNome.setCellValueFactory(new PropertyValueFactory<>("nomeInst"));
        tcTelefone.setCellValueFactory(new PropertyValueFactory<>("telefoneInst"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("emailInst"));
        tcEmailPessoa.setCellValueFactory(new PropertyValueFactory<>("pessoa"));

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
        cbxPessoa.getSelectionModel().clearSelection();
    }

    public void execute(Global.Acao acao, String method) throws Exception {
        switch (acao) {
            case Save:
                validation(acao);
                obj.setNomeInst(txtNome.getText());
                obj.setEmailInst(txtEmail.getText());
                obj.setTelefoneInst(txtTelefone.getText());
                obj.setPessoa((Pessoa) cbxPessoa.getSelectionModel().getSelectedItem());
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
                    txtNome.getStyleClass().add("campo_invalido");
                    txtNome.requestFocus();
                    throw new Exception("Preencha o Nome!");
                } else {
                    txtNome.getStyleClass().removeAll(Collections.singleton("campo_invalido"));
                }
                if (txtTelefone.getText().trim().isEmpty()) {
                    txtTelefone.requestFocus();
                    throw new Exception("Preencha o Telefone!");
                }
                if (txtEmail.getText().trim().isEmpty()) {
                    txtEmail.requestFocus();
                    throw new Exception("Preencha o E-mail!");
                }
                if (!ValidaEmail.validar(txtEmail.getText())) {
                    txtEmail.requestFocus();
                    throw new Exception("Formato de E-mail Invalido!");
                }
                if (cbxPessoa.getSelectionModel().isEmpty()) {
                    cbxPessoa.requestFocus();
                    throw new Exception("Selecione uma pessoa");
                }
                break;
            case Delete:
                if (!Numeric.isNumeric(txtCodigo.getText())) {
                    throw new Exception("Selecione um dado na tabela que deseja excluir.");
                }
                break;

            default:
                throw new Exception("Instituicao.Validation, ação inesperada.");
        }
    }

    private void atualizarTabela() {
        try {
            tblInstituicao.getItems().clear();
            tblInstituicao.getItems().setAll(this.bo.findAll());
        } catch (Exception ex) {
            Util.msgDialog(ex.getMessage());
        }

    }

    @FXML
    private void handleTblInstituicaoClicked(MouseEvent event) {
        obj = tblInstituicao.getSelectionModel().getSelectedItem();
        txtCodigo.setVisible(true);
        lblCodigo.setVisible(true);
        if (obj != null) {
            txtCodigo.setText(String.valueOf(obj.getIdInstituicao()));
            txtNome.setText(String.valueOf(obj.getNomeInst()));
            txtEmail.setText(String.valueOf(obj.getEmailInst()));
            txtTelefone.setText(String.valueOf(obj.getTelefoneInst()));
            //Carrega a combo de pessoa quando o a tabela rece um click
            for (Pessoa pessoa : cbxPessoa.getItems()) {
                if (pessoa.getIdPessoa() == obj.getPessoa().getIdPessoa()) {
                    cbxPessoa.getSelectionModel().select(pessoa);
                    break;
                }
            }
            //Fim Carrega a combo de pessoa quando o a tabela rece um click
        }
    }

    @FXML
    private void handleBtnLimpar(ActionEvent event) {
        clear();
    }

}
