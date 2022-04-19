package br.edu.femass.biblioteca.gui;

import br.edu.femass.biblioteca.dao.EmprestimoDao;
import br.edu.femass.biblioteca.dao.LivroDao;
import br.edu.femass.biblioteca.dao.UsuarioDao;
import br.edu.femass.biblioteca.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BibliotecaController implements Initializable {
    private LivroDao livroDao = new LivroDao();
    private UsuarioDao usuarioDao = new UsuarioDao();
    private EmprestimoDao emprestimoDao = new EmprestimoDao();

    @FXML
    private ListView<Livro> LstLiv;
    private void limparLivTela(){
        TxtLivNome.setText("");
        TxtLivEdicao.setText("");
        CboLivGenero.setValue(null);
        TxtLivAutorNome.setText("");
        TxtLivAutorSobrenome.setText("");
    }
    private void habilitarLivCampo(Boolean incluir) {
        TxtLivNome.setDisable(!incluir);
        TxtLivEdicao.setDisable(!incluir);
        CboLivGenero.setDisable(!incluir);
        TxtLivAutorNome.setDisable(!incluir);
        TxtLivAutorSobrenome.setDisable(!incluir);
        BtnLivGravar.setDisable(!incluir);
        BtnLivCancelar.setDisable(!incluir);
        BtnLivIncluir.setDisable(incluir);
        BtnLivExcluir.setDisable(incluir);
        BtnLivAddCpy.setDisable(incluir);
        BtnLivRemCpy.setDisable(incluir);
    }
    private void exibirLivro(){
        Livro livro = LstLiv.getSelectionModel().getSelectedItem();
        if (livro==null) return;
        TxtLivNome.setText(livro.getNome());
        TxtLivEdicao.setText(livro.getEdicao());
        CboLivGenero.setValue(livro.getGenero());
        TxtLivAutorNome.setText(livro.getAutorNome());
        TxtLivAutorSobrenome.setText(livro.getAutorSobrenome());

    }
    @FXML
    private void LstLiv_KeyPressed(KeyEvent evento){ exibirLivro();}
    @FXML
    private void LstLiv_MouseClicked(MouseEvent evento){ exibirLivro();}
    @FXML
    private Button BtnLivIncluir;
    @FXML
    protected void BtnLivIncluir_Action(ActionEvent evento) {
        habilitarLivCampo(true);
        limparLivTela();
        TxtLivNome.requestFocus();
    }
    @FXML
    private Button BtnLivExcluir;
    @FXML
    protected void BtnLivExcluir_Action(ActionEvent evento) {
        Livro livro = LstLiv.getSelectionModel().getSelectedItem();
        if (livro == null) return;
        try{
            livroDao.excluir(livro);
        } catch (Exception e){
            e.printStackTrace();
        }
        atualizarLivLista();
    }
    @FXML
    private Button BtnLivCancelar;
    @FXML
    protected void BtnLivCancelar_Action(ActionEvent evento) {
        limparLivTela();
        habilitarLivCampo(false);
    }
    @FXML
    private Button BtnLivGravar;
    @FXML
    protected void BtnLivGravar_Action(ActionEvent evento) {
        Livro livro = new Livro(TxtLivNome.getText(), TxtLivEdicao.getText(), CboLivGenero.getValue());
        livro.addAutor(new Autor(TxtLivAutorNome.getText(), TxtLivAutorSobrenome.getText()));
        try {
            livroDao.gravar(livro);
        } catch (Exception e){
            e.printStackTrace();
        }
        atualizarLivLista();
        habilitarLivCampo(false);
        limparLivTela();
    }
    @FXML
    private Button BtnLivAddCpy;
    @FXML
    private void BtnLivAddCpy_Action(ActionEvent evento){
        Livro livro = LstLiv.getSelectionModel().getSelectedItem();
        livro.addCopia(new Copia());
        if (livro == null) return;
        try{
            livroDao.salvar();
        } catch (Exception e){
            e.printStackTrace();
        }
        atualizarLivLista();
    }
    @FXML
    private Button BtnLivRemCpy;
    @FXML
    private void BtnLivRemCpy_Action(ActionEvent evento){
        Livro livro = LstLiv.getSelectionModel().getSelectedItem();
        livro.removerCopia();
        if (livro == null) return;
        try{
            livroDao.salvar();
        } catch (Exception e){
            e.printStackTrace();
        }
        atualizarLivLista();
    }
    @FXML
    private TextField TxtLivNome;
    @FXML
    private TextField TxtLivEdicao;
    @FXML
    private TextField TxtLivAutorNome;
    @FXML
    private TextField TxtLivAutorSobrenome;
    @FXML
    private ComboBox<GeneroLivro> CboLivGenero;


    @FXML
    private ListView<Usuario> LstUsu;
    private void limparUsuTela(){
        TxtUsuAlunoNome.setText("");
        TxtUsuAlunoMatricula.setText("");
        TxtUsuProfessorNome.setText("");
    }
    private void habilitarUsuCampo(Boolean incluir) {
        TxtUsuAlunoNome.setDisable(!incluir);
        TxtUsuAlunoMatricula.setDisable(!incluir);
        TxtUsuProfessorNome.setDisable(!incluir);
        BtnUsuAlunoGravar.setDisable(!incluir);
        BtnUsuAlunoCancelar.setDisable(!incluir);
        BtnUsuProfessorGravar.setDisable(!incluir);
        BtnUsuProfessorCancelar.setDisable(!incluir);
        BtnUsuIncluir.setDisable(incluir);
        BtnUsuExcluir.setDisable(incluir);
    }
    private void exibirUsuario(){
        Usuario usuario = LstUsu.getSelectionModel().getSelectedItem();
        if (usuario == null) return;
        TxtUsuAlunoNome.setText(usuario.getNome());
        TxtUsuProfessorNome.setText(usuario.getNome());
    }
    @FXML
    private void LstUsu_KeyPressed(KeyEvent evento){exibirUsuario();}
    @FXML
    private void LstUsu_MouseClicked(MouseEvent evento){exibirUsuario();}
    @FXML
    private Button BtnUsuIncluir;
    @FXML
    protected void BtnUsuIncluir_Action(ActionEvent evento) {
        habilitarUsuCampo(true);
        limparUsuTela();
        TxtUsuAlunoNome.requestFocus();
    }
    @FXML
    private Button BtnUsuExcluir;

    @FXML
    protected void BtnUsuExcluir_Action(ActionEvent evento) {
        Usuario usuario = LstUsu.getSelectionModel().getSelectedItem();
        if (usuario == null) return;
        try{
            usuarioDao.excluir(usuario);
        } catch (Exception e){
            e.printStackTrace();
        }
        atualizarUsuLista();
    }


    @FXML
    private Button BtnUsuAlunoCancelar;

    @FXML
    protected void BtnUsuAlunoCancelar_Action(ActionEvent evento) {
        limparUsuTela();
        habilitarUsuCampo(false);
    }

    @FXML
    private Button BtnUsuAlunoGravar;

    @FXML
    protected void BtnUsuAlunoGravar_Action(ActionEvent evento) {
        Usuario usuario = new Aluno(TxtUsuAlunoNome.getText(), Integer.parseInt(TxtUsuAlunoMatricula.getText()));
        try {
            usuarioDao.gravar(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
        atualizarUsuLista();
        habilitarUsuCampo(false);
        limparUsuTela();
    }
    @FXML
    private TextField TxtUsuAlunoNome;
    @FXML
    private TextField TxtUsuAlunoMatricula;


    @FXML
    private Button BtnUsuProfessorCancelar;

    @FXML
    protected void BtnUsuProfessorCancelar_Action(ActionEvent evento) {
        limparUsuTela();
        habilitarUsuCampo(false);
    }
    @FXML
    private Button BtnUsuProfessorGravar;

    @FXML
    protected void BtnUsuProfessorGravar_Action(ActionEvent evento) {
        Usuario usuario = new Professor(TxtUsuProfessorNome.getText());
        try {
            usuarioDao.gravar(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
        atualizarUsuLista();
        habilitarUsuCampo(false);
        limparUsuTela();
    }
    @FXML
    private TextField TxtUsuProfessorNome;


    @FXML
    private ListView<Emprestimo> LstEmp;
    @FXML
    private ListView<Usuario> LstUsu1;
    @FXML
    private ListView<Livro> LstLiv1;

    private void habilitarEmpCampo(Boolean incluir) {
        TxtEmpUsuario.setDisable(!incluir);
        TxtEmpLivro.setDisable(!incluir);
        BtnEmpGravar.setDisable(!incluir);
        BtnEmpCancelar.setDisable(!incluir);
        LstUsu1.setDisable(!incluir);
        LstLiv1.setDisable(!incluir);
        BtnEmpIncluir.setDisable(incluir);
        BtnEmpExcluir.setDisable(incluir);
    }

    private void exibirEmpUsuario(){
        Usuario usuario = LstUsu1.getSelectionModel().getSelectedItem();
        if (usuario == null) return;
        TxtEmpUsuario.setText(usuario.getNome());
    }

    private void exibirEmpLivro(){
        Livro livro = LstLiv1.getSelectionModel().getSelectedItem();
        if (livro == null) return;
        TxtEmpLivro.setText(livro.getNome());
    }
    @FXML
    private void LstUsu1_KeyPressed(KeyEvent evento){exibirEmpUsuario();}
    @FXML
    private void LstUsu1_MouseClicked(MouseEvent evento){exibirEmpUsuario();}
    @FXML
    private void LstLiv1_KeyPressed(KeyEvent evento){exibirEmpLivro();}
    @FXML
    private void LstLiv1_MouseClicked(MouseEvent evento){exibirEmpLivro();}
    @FXML
    private Button BtnEmpIncluir;

    @FXML
    protected void BtnEmpIncluir_Action(ActionEvent evento) {
        habilitarEmpCampo(true);
        limparLivTela();
    }

    @FXML
    private Button BtnEmpExcluir;

    @FXML
    protected void BtnEmpExcluir_Action(ActionEvent evento) {
        System.out.println("Excluir Empréstimo");
    }

    @FXML
    private Button BtnEmpCancelar;

    @FXML
    protected void BtnEmpCancelar_Action(ActionEvent evento) {
        habilitarEmpCampo(false);
    }

    @FXML
    private Button BtnEmpGravar;

    @FXML
    protected void BtnEmpGravar_Action(ActionEvent evento) {
        Emprestimo emprestimo = new Emprestimo(LstUsu1.getSelectionModel().getSelectedItem(),
                LstLiv1.getSelectionModel().getSelectedItem());
        try {
            emprestimoDao.gravar(emprestimo);
        } catch (Exception e){
            e.printStackTrace();
        }
        atualizarEmpLista();
        habilitarEmpCampo(false);
    }
    @FXML
    private TextField TxtEmpUsuario;
    @FXML
    private TextField TxtEmpLivro;

    //Initialize -------------------------------------------------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<GeneroLivro> generos = FXCollections.observableArrayList(GeneroLivro.values());
        CboLivGenero.setItems(generos);
        atualizarLivLista();
        atualizarUsuLista();
        atualizarEmpLista();
    }
    private void atualizarLivLista() {
        List<Livro> livros = null;
        try {
            livros = livroDao.listar();
        } catch (Exception e) {
            livros = new ArrayList<Livro>();
        }
        ObservableList<Livro> livros1 = FXCollections.observableArrayList(livros);
        LstLiv.setItems(livros1);
        LstLiv1.setItems(livros1);
    }
    private void atualizarUsuLista(){
        List<Usuario> usuarios = null;
        try {
            usuarios = usuarioDao.listar();
        } catch (Exception e) {
            usuarios = new ArrayList<Usuario>();
        }
        ObservableList<Usuario> usuarios1 = FXCollections.observableArrayList(usuarios);
        LstUsu.setItems(usuarios1);
        LstUsu1.setItems(usuarios1);
    }
    private void atualizarEmpLista() {
        List<Emprestimo> emprestimos = null;
        try {
            emprestimos = emprestimoDao.listar();
        } catch (Exception e) {
            emprestimos = new ArrayList<Emprestimo>();
        }
        ObservableList<Emprestimo> emprestimos1 = FXCollections.observableArrayList(emprestimos);
        LstEmp.setItems(emprestimos1);
    }
}