package fractal;

import java.io.File;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class fractalImagesController {

    @FXML
    private Pane MainPane;

    @FXML
    private ImageView FracImage;

    @FXML
    private GridPane ColorValuesGrid;

    @FXML
    private Text HueMultText;

    @FXML
    private Text SaturationText;

    @FXML
    private Text BrightnessText;

    @FXML
    private TextField HueMultValue;

    @FXML
    private TextField SaturationValue;

    @FXML
    private TextField BrightnessValue;

    @FXML
    private Text HueOffsetText;

    @FXML
    private TextField HueOffsetValue;

    @FXML
    private GridPane ColorLabelGrid;

    @FXML
    private Text ColorLabelText;

    @FXML
    private GridPane ZoomLabelGrid;

    @FXML
    private Text ZoomLabelText;

    @FXML
    private GridPane ZoomValuesGrid;

    @FXML
    private Text ZoomXText;

    @FXML
    private Text ZoomYText;

    @FXML
    private Text ZoomScaleText;

    @FXML
    private TextField ZoomXValue;

    @FXML
    private TextField ZoomYValue;

    @FXML
    private TextField ScaleValue;

    @FXML
    private Text TitleText;

    @FXML
    private Button UpdateButton;

    @FXML
    private Button SaveButton;

    @FXML
    private Button ResetButton;

    @FXML
    private Text MaxIterationLabelText;

    @FXML
    private TextField MaxIterationValue;
    
    private FractalCreator fractalCreator;
    private boolean drawFlag = false;
    
    @FXML 
    void initialize() {
        System.out.println(this.getClass().getSimpleName() + ".initialize");
    }
    
    @FXML 
    void update() {
        
        try {
            this.fractalCreator = new FractalCreator(Integer.parseInt(MaxIterationValue.getText()), 800, 600, Float.parseFloat(HueMultValue.getText()), Float.parseFloat(HueOffsetValue.getText()), 
                                Float.parseFloat(SaturationValue.getText()), Float.parseFloat(BrightnessValue.getText()), "boh" );
            this.fractalCreator.addZoom(new Zoom(Integer.parseInt(ZoomXValue.getText()), Integer.parseInt(ZoomYValue.getText()), Float.parseFloat(ScaleValue.getText())));
        }
        catch(Exception e) {
            System.out.println("Non-numeric characters exist. Color values and zoom scale must be floats. Zoom x, Zoom y and Max iterations must be integers.");
        }
        
        this.displayImage();
        
    }
    
    public void displayImage() {
        this.fractalCreator.drawFractalBuffer();
        WritableImage image = SwingFXUtils.toFXImage(this.fractalCreator.Bitmap, null);
        FracImage.setImage(image);
        drawFlag = true;
    }
    
    @FXML
    public void save() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = 
                directoryChooser.showDialog(new Stage());

        if(selectedDirectory != null){
            if (drawFlag) {
                this.fractalCreator.saveBmp(selectedDirectory.getAbsolutePath() + "/fractal.bmp" );
            }   
        }
            
    }
    
    @FXML
    public void reset() {
        MaxIterationValue.setText("100");
        HueMultValue.setText("1.0");
        HueOffsetValue.setText("0");
        SaturationValue.setText("1.0");
        BrightnessValue.setText("1.0");
        ZoomXValue.setText("400");
        ZoomYValue.setText("300");
        ScaleValue.setText("1.0");
        FracImage.setImage(null);
    }
}