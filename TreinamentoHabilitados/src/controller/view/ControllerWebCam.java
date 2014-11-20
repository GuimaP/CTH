package controller.view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.bridj.jawt.JAWT.GetComponent_callback;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;

/**
 * This is controller for WebCamPreview FXML.
 * 
 * @author Rakesh Bhatt (rakeshbhatt10)
 */
public class ControllerWebCam implements Initializable {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private FlowPane fpBottomPane;

	@FXML
	private ComboBox<WebCamInfo> cbCameraOptions;

	@FXML
	private Button btnTakePicture;

	@FXML
	private Font x1;

	@FXML
	private Button btnSair;

	@FXML
	private ImageView imgWebCamCapturedImage;

	@FXML
	private BorderPane bpWebCamPaneHolder;

	
	
	@FXML
	Button btnStartCamera;

	@FXML
	Button btnStopCamera;

	@FXML
	Button btnDisposeCamera;

	private ImageView imgLoad = new ImageView();

	private BufferedImage grabbedImage;
	
	private static String nomeDaClasseQueEstaChamando;

	// private static String diretorioParaSalvar;
	// private static String nomeArquivo;
	//
	// private static String caminhoDaImagem;

	/**
	 * @param Nome
	 *            do arquivo a ser salvo
	 * @desc Passa o nome da foto que vai ser tirada.
	 */
	public static void defineValores(String nomeDaClasse) {
		nomeDaClasseQueEstaChamando = nomeDaClasse;
		// String sep = File.separator;
		// String directoryHome = System.getProperty("user.home");
		// nomeArquivo = name;
		// diretorioParaSalvar = directoryHome + sep + "CTH" + sep + "fotos" +
		// sep+name+".png";
	}

	/*
	 * public static String getPath() { // Caso j� tenha um Caminho para imagem
	 * salva ele retorna o path if (!caminhoDaImagem.isEmpty() ||
	 * caminhoDaImagem != null) { return caminhoDaImagem; } // Se n�o houver
	 * Caminho setado, retorna a imagem padr�o return ControllerWebCam.class
	 * .getResource("/resources/imgs/noImage.png").toString(); }
	 */

	private class WebCamInfo {

		private String webCamName;
		private int webCamIndex;

		public String getWebCamName() {
			return webCamName;
		}

		public void setWebCamName(String webCamName) {
			this.webCamName = webCamName;
		}

		public int getWebCamIndex() {
			return webCamIndex;
		}

		public void setWebCamIndex(int webCamIndex) {
			this.webCamIndex = webCamIndex;
		}

		@Override
		public String toString() {
			return webCamName;
		}
	}
	
	@FXML
	void closeWindowCamera(){
		closeCamera();
		btnSair.getScene().getWindow().hide();
	}

	private Webcam selWebCam = null;
	private boolean stopCamera = false;
	private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<Image>();
	protected static FlowPane barraInferior;
	protected static Button btTirarFoto;
	private String cameraListPromptText = "Choose Camera";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Ao iniciar a aplica��o, eu desabilito as op��es de sair e tirar foto.
		barraInferior = fpBottomPane;
		btTirarFoto = btnTakePicture;

		btTirarFoto.setDisable(true);
		
		ObservableList<WebCamInfo> options = FXCollections
				.observableArrayList();
		int webCamCounter = 0;
		for (Webcam webcam : Webcam.getWebcams()) {
			WebCamInfo webCamInfo = new WebCamInfo();
			webCamInfo.setWebCamIndex(webCamCounter);
			webCamInfo.setWebCamName(webcam.getName());
			options.add(webCamInfo);
			webCamCounter++;
		}
		cbCameraOptions.setItems(options);
		cbCameraOptions.setPromptText(cameraListPromptText);
		cbCameraOptions.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<WebCamInfo>() {

					@Override
					public void changed(
							ObservableValue<? extends WebCamInfo> arg0,
							WebCamInfo arg1, WebCamInfo arg2) {
						// Desabilito as barra inferior toda e o bt de tirar
						// foto
						barraInferior.setDisable(true);
						btTirarFoto.setDisable(true);

						// Crio uma image de load,para aguardar o carregamento
						// da webcam
						imgLoad.setImage(new Image(getClass()
								.getResourceAsStream(
										"/resources/icons/loading.gif")));
						ObjectProperty<Image> imgProperty = new SimpleObjectProperty<Image>();
						imgProperty.set(imgLoad.getImage());
						// E seto na propriede ImageView da Tela
						imgWebCamCapturedImage.imageProperty()
								.bind(imgProperty);

						// Se houve alguma Instancia aberta, ent�o feche!
						if (selWebCam != null) {
							closeCamera();
						}

						// Verifica se possui alguma web cam setada
						if (arg2 != null) {
							//
							System.out.println("WebCam Index: "
									+ arg2.getWebCamIndex() + ": WebCam Name:"
									+ arg2.getWebCamName());
							initializeWebCam(arg2.getWebCamIndex());
						}
					}
				});
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				setImageViewSize();
			}
		});

	}

	public void takePicture(ActionEvent event) {
		try {
			BufferedImage im = SwingFXUtils.fromFXImage(
					imgWebCamCapturedImage.getImage(), null);
			// ImageIO.write(
			// im,"png",new File(diretorioParaSalvar));

			ImageView viewFoto = new ImageView();
//			viewFoto.setFitWidth(150);
//			viewFoto.setFitHeight(145);
			
			//Converto o Buffered Img para Image FX
			Image imgFX = SwingFXUtils.toFXImage(im, null);
			viewFoto.setImage(imgFX);
//			viewFoto.setY(15);
			
			//Verifica qual classe esta chamando
			switch (nomeDaClasseQueEstaChamando) {
			case "Cliente":
				//Quando a foto  tirada, passa-se a instancia da Foto para outra tela
				for(int i = 0; i < ControllerTelaCliente.panelFoto.getChildren().size(); i++){
					ControllerTelaCliente.panelFoto.getChildren().remove(i);
				}
				ControllerTelaCliente.imgStaticView.setImage(imgFX);
				ControllerTelaCliente.panelFoto.getChildren().add(ControllerTelaCliente.imgStaticView);
				break;
				
			case "Instrutor":
				for(int i = 0; i < ControllerTelaInstrutor.pnStaticFoto.getChildren().size();i++){
					ControllerTelaInstrutor.pnStaticFoto.getChildren().remove(i);
				}
				ControllerTelaInstrutor.imgFoto = viewFoto;
				ControllerTelaInstrutor.imgFoto.setImage(viewFoto.getImage());
				ControllerTelaInstrutor.pnStaticFoto.getChildren().add(ControllerTelaInstrutor.imgFoto);
				break;

			default:
				break;
			}
			
			
			closeCameraAferPicture();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void setImageViewSize() {

		double height = bpWebCamPaneHolder.getHeight();
		double width = bpWebCamPaneHolder.getWidth();
		imgWebCamCapturedImage.setFitHeight(height);
		imgWebCamCapturedImage.setFitWidth(width);
		imgWebCamCapturedImage.prefHeight(height);
		imgWebCamCapturedImage.prefWidth(width);
		imgWebCamCapturedImage.setPreserveRatio(true);

	}

	protected void initializeWebCam(final int webCamIndex) {

		Task<Void> webCamIntilizer = new Task<Void>() {

			@Override
			protected Void call() throws Exception {

				if (selWebCam == null) {
					selWebCam = Webcam.getWebcams().get(webCamIndex);
					selWebCam.open();
				} else {
					closeCamera();
					selWebCam = Webcam.getWebcams().get(webCamIndex);
					selWebCam.open();
				}

				startWebCamStream();
				return null;
			}

		};

		new Thread(webCamIntilizer).start();
		fpBottomPane.setDisable(false);
		// btnStartCamera.setDisable(true);
	}

	protected void startWebCamStream() {

		stopCamera = false;
		// bpWebCamPaneHolder.getChildren().remove(imgLoad);
		// bpWebCamPaneHolder.getChildren().add(imgWebCamCapturedImage);
		// bpWebCamPaneHolder.setCenter(imgWebCamCapturedImage);
		// bpWebCamPaneHolder.centerProperty().(imgWebCamCapturedImage);

		Task<Void> task = new Task<Void>() {

			@Override
			protected Void call() throws Exception {

				while (!stopCamera) {
					try {

						if ((grabbedImage = selWebCam.getImage()) != null) {
							Thread.sleep(100);

							Platform.runLater(new Runnable() {

								@Override
								public void run() {
									final Image mainiamge = SwingFXUtils
											.toFXImage(grabbedImage, null);
									imageProperty.set(mainiamge);
									ControllerWebCam.btTirarFoto
											.setDisable(false);
								}
							});

							grabbedImage.flush();

						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				return null;
			}

		};
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
		imgWebCamCapturedImage.imageProperty().bind(imageProperty);

	}

	private void closeCamera() {
		try {
			if (selWebCam != null) {
				stopCamera = true;
				Thread.sleep(1000);
				selWebCam.close();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void closeCameraAferPicture() {
		try {
			if (selWebCam != null) {
				stopCamera = true;
				Thread.sleep(1000);
				selWebCam.close();
				btnSair.getScene().getWindow().hide();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stopCamera(ActionEvent event) {
		stopCamera = true;
		// btnStartCamera.setDisable(false);
		// btnStopCamera.setDisable(true);
	}

	public void startCamera(ActionEvent event) {
		stopCamera = false;
		startWebCamStream();
		// btnStartCamera.setDisable(true);
		// btnStopCamera.setDisable(false);
	}

	public void disposeCamera(ActionEvent event) {
		stopCamera = true;
		closeCameraAferPicture();
		// Webcam.shutdown();
		// btnStopCamera.setDisable(true);
		// btnStartCamera.setDisable(true);
	}
}