package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class MenuPrincipal extends Application {

    private BorderPane root;
    private VBox menuBox;
    private Label rodapeLabel;

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();
        root.setPadding(new Insets(40));

        menuBox = criarMenuPrincipal();
        root.setCenter(menuBox);

        // Rodapé
        rodapeLabel = new Label("Organize, Controle e Expanda Seu Ensino com Eficiência");
        rodapeLabel.getStyleClass().add("rodape");
        root.setBottom(rodapeLabel);
        BorderPane.setAlignment(rodapeLabel, Pos.CENTER);


        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets    ().add(getClass().getResource("/gui/style.css").toExternalForm());

        primaryStage.setTitle("SGCA - Sistema de Gestão de Cursos e Alunos");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private VBox criarMenuPrincipal() {
        Label titulo = new Label("SGCA");
        titulo.getStyleClass().add("titulo");

        Label subtitulo = new Label("Sistema de Gestão de Cursos e Alunos");
        subtitulo.getStyleClass().add("subtitulo");

        Button btnAluno = criarBotao("Gerenciar Alunos");
        btnAluno.setOnAction(e -> mostrarAlunoGUI());

        Button btnCurso = criarBotao("Gerenciar Cursos");
        btnCurso.setOnAction(e -> mostrarCursoGUI());

        Button btnRelatorio = criarBotao("Gerar Relatórios");
        btnRelatorio.setOnAction(e -> mostrarRelatorioGUI());

        HBox botoesBox = new HBox(30, btnAluno, btnCurso, btnRelatorio);
        botoesBox.setAlignment(Pos.CENTER);
        botoesBox.setPadding(new Insets(30));

        VBox box = new VBox(20, titulo, subtitulo, botoesBox);
        box.setAlignment(Pos.CENTER);
        return box;
    }

    private Button criarBotao(String texto) {
        Button botao = new Button(texto);
        botao.getStyleClass().add("botao");
        botao.setEffect(new DropShadow(5, Color.GRAY));
        botao.setPrefWidth(200);
        botao.setPrefHeight(60);
        return botao;
    }

    private void mostrarAlunoGUI() {
        AlunoGUI painelAluno = new AlunoGUI();
        root.setCenter(painelAluno.getView());
        mostrarBotaoVoltar();
    }

    private void mostrarCursoGUI() {
        CursoGUI painelCurso = new CursoGUI();
        root.setCenter(painelCurso.getView());
        mostrarBotaoVoltar();
    }

    private void mostrarRelatorioGUI() {
        RelatorioGUI relatorioGUI = new RelatorioGUI();
        relatorioGUI.show();
    }

    private void mostrarBotaoVoltar() {
        Button voltar = criarBotao("⬅ Voltar ao Menu");
        voltar.setOnAction(e -> voltarAoMenu());
        root.setBottom(voltar);
        BorderPane.setAlignment(voltar, Pos.CENTER);
    }

    private void voltarAoMenu() {
        root.setCenter(menuBox);
        root.setBottom(rodapeLabel);
        BorderPane.setAlignment(rodapeLabel, Pos.CENTER);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
