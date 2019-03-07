
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Exercise02 extends Application{

    public void start(Stage primaryStage) throws Exception {
        GridPane pane = new GridPane();
        
        //create the 4 labels for our fields
        pane.add(new Label("Investment Amount"), 0, 0);
        pane.add(new Label("Years"), 0, 1);
        pane.add(new Label("Annual Interest Rate"), 0, 2);
        pane.add(new Label("Future Value"), 0, 3);
        
        //create the four text fields
        TextField investmentAmount = new TextField();
        TextField yearsField = new TextField();
        TextField annualInterestRate = new TextField();
        TextField futureValue = new TextField();
        
        //set all of the text to right justified in our fields
        investmentAmount.setAlignment(Pos.CENTER_RIGHT);
        yearsField.setAlignment(Pos.CENTER_RIGHT);
        annualInterestRate.setAlignment(Pos.CENTER_RIGHT);
        futureValue.setAlignment(Pos.CENTER_RIGHT);
        
        //disable the future value field since it only shows output
        futureValue.setDisable(true);
        
        //add our textfields to the pane
        pane.add(investmentAmount, 1, 0);
        pane.add(yearsField, 1, 1);
        pane.add(annualInterestRate, 1, 2);
        pane.add(futureValue, 1, 3);
        
        Button calculateButton = new Button("Calculate");
        calculateButton.setOnMouseClicked(e ->{
            //parse the doubles out of all our text fields
            double investment = Double.parseDouble(investmentAmount.getText());
            double years = Double.parseDouble(yearsField.getText());
            double interest = Double.parseDouble(annualInterestRate.getText())*0.01;
            
            //apply the formula
            double future = Math.round(investment * Math.pow((1 + interest/12), years*12)*100)/100d;
            
            //put the result into our future value text field
            futureValue.setText(""+future);
        });
        
        //add the calculate button to the pane and set the padding
        pane.add(calculateButton, 1, 5);
        pane.setPadding(new Insets(10));
        
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
