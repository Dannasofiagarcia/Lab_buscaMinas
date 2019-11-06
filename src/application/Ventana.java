package application;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import modelo.*;
import java.util.List;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Ventana extends Application {

	private static final int TILE_SIZE = 25;
	private static final int LARGO = 600;
	private static final int ANCHO = 400;
	
	private static final int LARGO_PR = 200;
	private static final int ANCHO_PR = 200;
	
	private static final int LARGO_IN = 400;
	private static final int ANCHO_IN = 400;

	private static final int X_TILES_PR = 8;
	private static final int Y_TILES_PR = 8;
	private static final int X_TILES_IN = 16;
	private static final int Y_TILES_IN = 16;
	private static final int X_TILES_EX = 30;
	private static final int Y_TILES_EX = 16;

	private Tile[][] gridExperto = new Tile[X_TILES_EX][Y_TILES_EX];
	private Tile[][] gridPrincipiante = new Tile[X_TILES_PR][Y_TILES_PR];
	private Tile[][] gridIntermedio = new Tile[X_TILES_IN][Y_TILES_IN];
	
	private int[][] casillasP = new int[X_TILES_PR][Y_TILES_PR];
	private int[][] casillasI = new int[X_TILES_IN][Y_TILES_IN];
	private int[][] casillasE = new int[X_TILES_EX][Y_TILES_EX];
	private Scene scene1;
	private Scene scene2;
	private Scene scene3;
	private Buscaminas buscaminas;
	

	private Parent createContentExperto() {
		Pane root = new Pane();
		root.setPrefSize(600, 400);
		
		Button boton = new Button();
		boton.setText("?");
		boton.setFont(Font.font("Bebas", 20));
		boton.setPrefSize(43, 39);
		boton.setLayoutX(30);
		boton.setLayoutY(-45);
		boton.setOnMouseClicked(e -> resolverE());
		root.getChildren().add(boton);
		

		// Genera las minas

		int minas = 0;

		while (minas < 99) {

			int f = (int) (Math.random() * X_TILES_EX);
			int c = (int) (Math.random() * Y_TILES_EX);

			// Si es 100 es porque tiene una mina
			if (casillasE[f][c] != 100) {

				casillasE[f][c] = 100;
				minas++;
			}
		}

		for (int y = 0; y < Y_TILES_EX; y++) {
			for (int x = 0; x < X_TILES_EX; x++) {
				int contador = 0;
				int vertical = 0;
				int horizontal = 0;
				int xTemp = x;
				int yTemp = y;

				for (int i = xTemp - 1; vertical < 3 && i < casillasE.length; i++) {
					for (int j = yTemp - 1; horizontal < 3; j++) {
						if ((i >= 0 && j >= 0) && (j < casillasE[i].length)) {
							if (casillasE[i][j] == 100) {
								contador++;
							}
						}
						horizontal++;
					}
					vertical++;
					horizontal = 0;
				}
				if (casillasE[x][y] != 100)
					casillasE[x][y] = contador;
			}
		}

		// Genera las casillasE del juego

		for (int y = 0; y < Y_TILES_EX; y++) {
			for (int x = 0; x < X_TILES_EX; x++) {
				Tile tile = new Tile(x, y, casillasE[x][y]);

				gridExperto[x][y] = tile;
				root.getChildren().add(tile);
			}
		}

		root.setLayoutY(50);
		return root;
	}
	
	//*****************************************************************************************************
	//*****************************************************************************************************
	//*****************************************************************************************************
	//*****************************************************************************************************
	//*****************************************************************************************************

	private Parent createContentPrincipiante() {
		Pane root1 = new Pane();
		root1.setPrefSize(LARGO_PR, ANCHO_PR);
		
		Button boton = new Button();
		boton.setText("?");
		boton.setFont(Font.font("Bebas", 20));
		boton.setPrefSize(43, 39);
		boton.setLayoutX(30);
		boton.setLayoutY(-45);
		boton.setOnMouseClicked(e -> resolverP());
		root1.getChildren().add(boton);

		// Genera las minas

		int minas = 0;

		while (minas < 10) {

			int f = (int) (Math.random() * X_TILES_PR);
			int c = (int) (Math.random() * Y_TILES_PR);

			// Si es 100 es porque tiene una mina
			if (casillasP[f][c] != 100) {

				casillasP[f][c] = 100;
				minas++;
			}
		}

		for (int y = 0; y < Y_TILES_PR; y++) {
			for (int x = 0; x < X_TILES_PR; x++) {
				int contador = 0;
				int vertical = 0;
				int horizontal = 0;
				int xTemp = x;
				int yTemp = y;

				for (int i = xTemp - 1; vertical < 3 && i < casillasP.length; i++) {
					for (int j = yTemp - 1; horizontal < 3; j++) {
						if ((i >= 0 && j >= 0) && (j < casillasP[i].length)) {
							if (casillasP[i][j] == 100) {
								contador++;
							}
						}
						horizontal++;
					}
					vertical++;
					horizontal = 0;
				}
				if (casillasP[x][y] != 100)
					casillasP[x][y] = contador;
			}
		}

		// Genera las casillas del juego

		for (int y = 0; y < Y_TILES_PR; y++) {
			for (int x = 0; x < X_TILES_PR; x++) {
				Tile tile = new Tile(x, y, casillasP[x][y]);

				gridPrincipiante[x][y] = tile;
				root1.getChildren().add(tile);
			}
		}

		root1.setLayoutY(50);
		return root1;
	}
	//**********************************************************************************************************
	//**********************************************************************************************************
	//**********************************************************************************************************
	//**********************************************************************************************************
	//**********************************************************************************************************
	
	private Parent createContentIntermedio() {
		Pane root2 = new Pane();
		root2.setPrefSize(LARGO_IN, ANCHO_IN);
		
		Button boton = new Button();
		boton.setText("?");
		boton.setFont(Font.font("Bebas", 20));
		boton.setPrefSize(43, 39);
		boton.setLayoutX(30);
		boton.setLayoutY(-45);
		boton.setOnMouseClicked(e -> resolverI());
		root2.getChildren().add(boton);

		// Genera las minas

		int minas = 0;

		while (minas < 40) {

			int f = (int) (Math.random() * X_TILES_IN);
			int c = (int) (Math.random() * Y_TILES_IN);

			// Si es 100 es porque tiene una mina
			if (casillasI[f][c] != 100) {

				casillasI[f][c] = 100;
				minas++;
			}
		}

		for (int y = 0; y < Y_TILES_IN; y++) {
			for (int x = 0; x < X_TILES_IN; x++) {
				int contador = 0;
				int vertical = 0;
				int horizontal = 0;
				int xTemp = x;
				int yTemp = y;

				for (int i = xTemp - 1; vertical < 3 && i < casillasI.length; i++) {
					for (int j = yTemp - 1; horizontal < 3; j++) {
						if ((i >= 0 && j >= 0) && (j < casillasI[i].length)) {
							if (casillasI[i][j] == 100) {
								contador++;
							}
						}
						horizontal++;
					}
					vertical++;
					horizontal = 0;
				}
				if (casillasI[x][y] != 100)
					casillasI[x][y] = contador;
			}
		}

		// Genera las casillas del juego

		for (int y = 0; y < Y_TILES_IN; y++) {
			for (int x = 0; x < X_TILES_IN; x++) {
				Tile tile = new Tile(x, y, casillasI[x][y]);

				gridIntermedio[x][y] = tile;
				root2.getChildren().add(tile);
			}
		}

		root2.setLayoutY(50);
		return root2;
	}
	
	//---------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------------------------------
	
	

	private class Tile extends StackPane {
		private int x, y, value;
		private boolean hasBomb;
		private boolean isOpen = false;

		private Rectangle border = new Rectangle(TILE_SIZE - 1, TILE_SIZE - 1);
		private Text text = new Text();

		public Tile(int x, int y, int value) {
			this.x = x;
			this.y = y;
			this.value = value;

			border.setStroke(Color.GHOSTWHITE);
			border.setFill(Color.DARKGRAY);

			text.setFont(Font.font("Book Antiqua"));
			text.setFont(Font.font(20));
			// text.setText(hasBomb ? "X" : "");
			text.setVisible(false);

			if (value == 1) {
				text.setText(value + "");
				text.setFill(Color.ROYALBLUE);
			}

			if (value == 2) {
				text.setText(value + "");
				text.setFill(Color.DARKGREEN);
			}
			
			if (value == 3) {
				text.setText(value + "");
				text.setFill(Color.RED);
			}
			
			if (value == 4) {
				text.setText(value + "");
				text.setFill(Color.DARKBLUE);
			}
			
			if (value == 5) {
				text.setText(value + "");
				text.setFill(Color.DARKRED);
			}
			
			if (value == 6) {
				text.setText(value + "");
				text.setFill(Color.STEELBLUE);
			}
			
			if (value == 7) {
				text.setText(value + "");
				text.setFill(Color.BLACK);
			}
			
			if (value == 8) {
				text.setText(value + "");
				text.setFill(Color.GRAY);
			}
			
			if(value == 100) {
				text.setText("*");
				text.setFill(Color.BLACK);
			}

			getChildren().addAll(border, text);

			setTranslateX(x * TILE_SIZE);
			setTranslateY(y * TILE_SIZE);

			setOnMouseClicked(e -> open());
		}

		public void open() {
			if (isOpen)
				return;

			if (value == 100) {
		
				Alert perdio = new Alert(Alert.AlertType.WARNING);
				perdio.setHeaderText(null);
				perdio.setTitle("Perdió");
				perdio.setContentText("¡Ha abierto una mina!");
				perdio.showAndWait();
				perdio.close();

				scene1.setRoot(botones());
				return;
			}

			isOpen = true;
			text.setVisible(true);
			border.setFill(Color.LIGHTGRAY);
			border.setStroke(null);
	
			if (text.getText().isEmpty()) {
				// getNeighbors(this).forEach(Tile::open);
			}
		}
	
	}
	
	public Parent botones() {
		Pane b = new Pane();
		b.setPrefSize(358, 402);
		//b.autosize();
	
		Label label = new Label();
		label.setText("Buscaminas");
		label.setFont(Font.font("Coolvetica Rg", 56));
		label.setVisible(true);
		label.setLayoutX(38.5);
		label.setLayoutY(14);
		b.getChildren().add(label);
		
		Label label2 = new Label();
		label2.setText("Seleccione el nivel");
		label2.setFont(Font.font("Arial Nova Cond Light", 22));
		label2.setLayoutX(85);
		label2.setLayoutY(90);
		b.getChildren().add(label2);
		
		Button principiante = new Button();
		principiante.setPrefSize(173, 39);
		principiante.setText("Principiante");
		principiante.setFont(Font.font("Bebas", 23));
		principiante.setLayoutX(100);
		principiante.setLayoutY(140);
		
		principiante.setOnMouseClicked(e -> scene1.setRoot(createContentPrincipiante()));
		b.getChildren().add(principiante);
		
		Button intermedio = new Button();
		intermedio.setPrefSize(173, 39);
		intermedio.setText("Intermedio");
		intermedio.setFont(Font.font("Bebas", 23));
		intermedio.setLayoutX(100);
		intermedio.setLayoutY(210);
		
		intermedio.setOnMouseClicked(e -> scene1.setRoot(createContentIntermedio()));
		b.getChildren().add(intermedio);
		
		Button experto = new Button();
		experto.setPrefSize(173, 39);
		experto.setText("Experto");
		experto.setFont(Font.font("Bebas", 23));
		experto.setLayoutX(100);
		experto.setLayoutY(280);
		
		experto.setOnMouseClicked(e -> scene1.setRoot(createContentExperto()));
		b.getChildren().add(experto);
	
		return b;
	}
	
	public void resolverP() {
		for (int y = 0; y < Y_TILES_PR; y++) {
			for (int x = 0; x < X_TILES_PR; x++) {
				gridPrincipiante[x][y].isOpen = true;
				gridPrincipiante[x][y].text.setVisible(true);
				gridPrincipiante[x][y].border.setFill(Color.LIGHTGRAY);
				gridPrincipiante[x][y].border.setStroke(null);
			}
		}
	}
	
	public void resolverE() {
		for (int y = 0; y < Y_TILES_EX; y++) {
			for (int x = 0; x < X_TILES_EX; x++) {
				gridExperto[x][y].isOpen = true;
				gridExperto[x][y].text.setVisible(true);
				gridExperto[x][y].border.setFill(Color.LIGHTGRAY);
				gridExperto[x][y].border.setStroke(null);
			}
		}
	}
	
	public void resolverI() {
		for (int y = 0; y < Y_TILES_IN; y++) {
			for (int x = 0; x < X_TILES_IN; x++) {
				gridIntermedio[x][y].isOpen = true;
				gridIntermedio[x][y].text.setVisible(true);
				gridIntermedio[x][y].border.setFill(Color.LIGHTGRAY);
				gridIntermedio[x][y].border.setStroke(null);
			}
		}
	}

		public static void main(String[] args) {
			launch(args);
		}

		@Override
		public void start(Stage stage) throws Exception {
			scene1 = new Scene(botones());

			stage.setScene(scene1);
			stage.show();
		}
}
