
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise04 extends Application{
    
    public void start(Stage primaryStage) throws Exception {
        VBox pane = new VBox();
        
        //create the bottom portion of the scene with the path field and view button
        TextField pathField = new TextField();
        pathField.setPrefWidth(400);
        Button viewButton = new Button("View");
        HBox searchBar = new HBox();
        searchBar.setPadding(new Insets(5));
        searchBar.getChildren().addAll(new Label("Filename: "), pathField, viewButton);
       
        //create the histogram
        Pane histogramPane = new Pane();
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        chart.setLegendVisible(false);

        //create all the bars and set all initial values to 0
        XYChart.Series series = new XYChart.Series();
        for (int i = 0; i < 26; i++){
            series.getData().add(new XYChart.Data(""+(char)(i+65), 0));
        }

        chart.getData().addAll(series);
        chart.setCategoryGap(1);
        
        histogramPane.getChildren().addAll(chart);
        
        //add functionality when the view button is clicked
        viewButton.setOnAction(e -> {
            //count the characters in the file
            int[] charCounts = loadFile(pathField.getText());
            //if the file was found, update the chart
            if(charCounts != null){
                updateChart(histogramPane, charCounts);
            }
        });
        //add functionality when the enter button is clicked on the path field
        pathField.setOnKeyPressed(e -> {
            //if the enter key was pressed
            if(e.getCode() == KeyCode.ENTER){
                //count the characters in the file
                int[] charCounts = loadFile(pathField.getText());
                //if the file was found, update the chart
                if(charCounts != null){
                    updateChart(histogramPane, charCounts);
                }
            }
        });
        
        pane.getChildren().addAll(histogramPane, searchBar);

        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }
    
    //plot the character counts
    public void updateChart(Pane histogramPane, int[] charCounts){
        //reset the chart
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> chart = new BarChart<>(xAxis, yAxis);
        chart.setLegendVisible(false);

        //reset the series
        XYChart.Series series = new XYChart.Series();
        for(int i = 0; i < 26; i++){
            //create the labels using the ascii codes, and plot the character counts
            series.getData().add(new XYChart.Data<>(""+(char)(i+65), charCounts[i]));
        }
        
        chart.getData().add(series);
        chart.setCategoryGap(1);
        
        //add the updated chart to the histogram pane
        histogramPane.getChildren().clear();
        histogramPane.getChildren().add(chart);
    }
    
    //read the data from the file and return the character counts
    public int[] loadFile(String path){
        int[] charCounts = new int[26];
            
        try {
            //read thefile one byte at a time
            FileInputStream fin = new FileInputStream(new File(path));
            int i;
            //keep looping until the end of file
            while((i = fin.read()) != -1){
                //if the character is an upper case letter, add it to the counts
                if(i >= 65 && i < 91){ charCounts[i-65]++; }
                //if the character is a lower case letter, add it to the counts
                if(i >= 97 && i < 123){ charCounts[i-97]++; }
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("FILE DOES NOT EXIST");
            return null;
        } catch (IOException ex) {
            System.out.println("SOMETHING WENT WRONG WHILE READING");
            return null;
        }
            
        return charCounts;
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
