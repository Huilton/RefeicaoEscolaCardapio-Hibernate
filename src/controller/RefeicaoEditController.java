/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bo.FotoCardapioBo;
import bo.InstituicaoBo;
import bo.RefeicaoBo;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import model.FotoCardapio;
import util.Global;
import util.Numeric;
import util.Util;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import model.Instituicao;
import model.Refeicao;
import util.EnvioEmail;

/**
 * FXML Controller class
 *
 * @author Huilton
 */
public class RefeicaoEditController implements Initializable {

    private List<FotoCardapio> listaFoto;
    private List<FotoCardapio> lista;
    File[] arquivo;
    String caminhoImagem = null;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private FotoCardapio obj = new FotoCardapio();
    private EnvioEmail envioEmail = new EnvioEmail();
    private Refeicao objR = new Refeicao();

    private RefeicaoBo boR = new RefeicaoBo();
    private FotoCardapioBo bo = new FotoCardapioBo();

    @FXML
    private TableColumn<Refeicao, Integer> tcCodigo;
    @FXML
    private TableColumn<Refeicao, String> tcDescricao;
    @FXML
    private TableColumn<Refeicao, String> tcTipoRef;
    @FXML
    private TableColumn<Refeicao, String> tcNomeRefeicao;
    @FXML
    private TableColumn<Refeicao, String> tcDataRefeicao;
    @FXML
    private TextField txtCodigo;
    @FXML
    private ComboBox<String> cbxTipoRefeicao;
    @FXML
    private TextField txtNomeRefeicao;
    @FXML
    private Label lblCodigo;
    @FXML
    private DatePicker txtData;
    @FXML
    private ComboBox<Instituicao> cbxInstituicao;
    @FXML
    private TextField txtDescricao;
    @FXML
    private TableView<Refeicao> tblRefeicao;
    @FXML
    private ImageView imgFoto;
    @FXML
    private Button btnExcluir;
    @FXML
    private VBox vBoxLabels;
    @FXML
    private VBox vBoxTxts;
    @FXML
    private Button btnFoto;
    @FXML
    private Button btnLimpar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        txtCodigo.setVisible(false);
        lblCodigo.setVisible(false);
        this.bo = new FotoCardapioBo();
        InstituicaoBo instituicaoBo = new InstituicaoBo();

        try {
            cbxInstituicao.getItems().setAll(instituicaoBo.findAll());
        } catch (Exception ex) {
            Util.msgDialog(ex.getMessage());
        }
        atualizarTabela();
        cbxTipoRefeicao.getItems().addAll("Selecione..", "Almoço", "Café da Manha", "Café da Tarde", "Janta", "Sobremesas");
        cbxTipoRefeicao.getSelectionModel().select(0);

        //Inicio formatação data Tabela
        tcDataRefeicao.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Refeicao, String>, ObservableValue<String>>() {

            public ObservableValue<String> call(CellDataFeatures<Refeicao, String> cell) {
                final Refeicao r = cell.getValue();

                final SimpleObjectProperty<String> simpleObject = new SimpleObjectProperty<String>(dateFormat.format(r.getDataRef()));
                return simpleObject;
            }
        } );
        //Fim formatação data Tabela

        //Inicio Populando campos para edição
        tcCodigo.setCellValueFactory(new PropertyValueFactory<>("idRefeicao"));
       // tcDataRefeicao.setCellValueFactory(new PropertyValueFactory<>("dataRef"));
        tcDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        tcNomeRefeicao.setCellValueFactory(new PropertyValueFactory<>("nomeRef"));
        tcTipoRef.setCellValueFactory(new PropertyValueFactory<>("tipoRef"));
        //Fim Populando campos para edição
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
    public void handleTblRefeicaoClicked() throws Exception {
        objR = tblRefeicao.getSelectionModel().getSelectedItem();
        txtCodigo.setVisible(true);
        lblCodigo.setVisible(true);
        try {
            if (objR != null) {
                txtCodigo.setText(String.valueOf(objR.getIdRefeicao()));
                txtNomeRefeicao.setText(String.valueOf(objR.getNomeRef()));
                txtDescricao.setText(String.valueOf(objR.getDescricao()));
                cbxTipoRefeicao.getSelectionModel().select(objR.getTipoRef());
                txtData.setValue(objR.getDataRef().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                imgFoto.setImage(new Image(new ByteArrayInputStream(objR.getFotoCardapio().getFoto())));
                //Carrega a combo de instituição quando o a tabela rece um click
                for (Instituicao instituicao : cbxInstituicao.getItems()) {
                    if (instituicao.getIdInstituicao() == objR.getInstituicao().getIdInstituicao()) {
                        cbxInstituicao.getSelectionModel().select(instituicao);
                        break;
                    }
                }
                //Fim Carrega a combo de instituição quando o a tabela rece um click
            }
        } catch (Exception ex) {
            Util.msgDialog(ex.getMessage());
        }
    }

    private void clear() {
        txtCodigo.setText("");
        txtNomeRefeicao.setText("");
        txtDescricao.setText("");
        txtData.getEditor().clear();
        cbxTipoRefeicao.getSelectionModel().clearSelection();
        cbxInstituicao.getSelectionModel().clearSelection();
        arquivo = null;
        imgFoto.setImage(null);
        obj = null;
        objR = null;

    }

    @FXML
    private void handleBtnExcluir(ActionEvent event) {
        try {
            execute(Global.Acao.Delete);
            atualizarTabela();
        } catch (Exception e) {
            Util.msgDialog(e.getMessage());
        }
    }

    public void execute(Global.Acao acao) throws Exception {
        execute(acao, null);
    }

    public void execute(Global.Acao acao, String method) throws Exception {
        switch (acao) {
            case Save:
                validation(acao);
                objR.setFotoCardapio(obj);
                objR.setTipoRef(cbxTipoRefeicao.getSelectionModel().getSelectedItem());
                objR.setNomeRef(txtNomeRefeicao.getText());
                objR.setDataRef(Date.valueOf(txtData.getValue()));
                objR.setDataCadastro(Date.valueOf(LocalDate.now()));
                objR.setInstituicao((Instituicao) cbxInstituicao.getSelectionModel().getSelectedItem());
                objR.setDescricao(txtDescricao.getText());

                boR.save(objR);
                Util.msgDialog("Salvo com sucesso.");
                clear();
                txtCodigo.setVisible(false);
                lblCodigo.setVisible(false);
                caminhoImagem = null;
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
                if (txtNomeRefeicao.getText().trim().isEmpty()) {
                    txtNomeRefeicao.requestFocus();
                    throw new Exception("Preencha o Nome!");
                }
                if (txtDescricao.getText().trim().isEmpty()) {
                    txtDescricao.requestFocus();
                    throw new Exception("Preencha a descrição!");
                }
                if (cbxTipoRefeicao.getSelectionModel().getSelectedItem().equals("Selecione..")) {
                    cbxTipoRefeicao.requestFocus();
                    throw new Exception("Selecione um tipo de refeição.");
                }
                if (txtData.getEditor().getText().trim().isEmpty()) {
                    throw new Exception("Informe data da refeição.");
                }
                if (cbxInstituicao.getSelectionModel().getSelectedItem().equals("Selecione..")) {
                    cbxTipoRefeicao.requestFocus();
                    throw new Exception("Selecione uma Instituição.");
                }

                break;

            case Delete:
                if (!Numeric.isNumeric(txtCodigo.getText())) {
                    throw new Exception("Selecione um dado na tabela que deseja excluir.");
                }
                break;

            default:
                throw new Exception("Refeição.Validation, ação inesperada.");
        }
    }

    private void atualizarTabela() {
        try {
            tblRefeicao.getItems().clear();
            tblRefeicao.getItems().setAll(this.boR.findAll());

        } catch (Exception ex) {
            Util.msgDialog(ex.getMessage());
        }

    }

    @FXML
    private void handleFoto(ActionEvent event) throws Exception {
        try {

            FileChooser chooser = new FileChooser();
            File file = chooser.showOpenDialog(null);
            caminhoImagem = file.getPath();

            obj.setFoto(bo.selecioneImagem(file));//analisar a possiblidade de retirar e colocar tudo na refeição
            obj.setNomeFoto(caminhoImagem);
            imgFoto.setImage(bo.loadImage(file));
            bo.save(obj);

        } catch (Exception ex) {
            Util.msgDialog(ex.getMessage());
        }
    }

    @FXML
    private void handleBtnLimpar(ActionEvent event
    ) {
        clear();
    }
}
