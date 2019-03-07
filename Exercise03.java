
import java.util.Random;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Exercise03 extends Application{
    
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new Pane();
        
        //create the circle
        Circle c = new Circle(200, 200, 100);
        c.setStroke(Color.BLACK);
        c.setFill(Color.WHITE);
        
        pane.getChildren().add(c);
        
        Random random = new Random();
        
        //create our line, dot, and label arrays
        Line[] lines = new Line[3];
        Circle[] dots = new Circle[3];
        Label[] labels = new Label[3];
        
        //create the lines
        for(int i = 0; i < lines.length; i++){
            lines[i] = new Line();
            pane.getChildren().add(lines[i]);
        }
        //create the dots
        for(int i = 0; i < dots.length; i++){   
            //calculate a random angle
            double a = random.nextDouble()*Math.PI*2;
            //set the dots position based on the angle
            dots[i] = new Circle(200 + Math.cos(a)*100, 200 + Math.sin(a)*100, 10);
            pane.getChildren().add(dots[i]);
        }
        //create the labels
        for(int i = 0; i < labels.length; i++){
            labels[i] = new Label();
            pane.getChildren().add(labels[i]);
        }
        
        for(int i = 0; i < dots.length; i++){
            Circle d = dots[i];
            
            d.setFill(Color.RED);
            d.setStroke(Color.BLACK);
            
            //when a dot is dragged, update the position and update the lines/angles
            d.setOnMouseDragged(e -> {
                //get the angle from the center of the circle to the mouse
                double angle = Math.atan2(e.getY() - 200, e.getX() - 200);
                //update the dots position
                d.setCenterX(200 + Math.cos(angle)*100);
                d.setCenterY(200 + Math.sin(angle)*100);
                //update the lines and angles
                updateLines(dots, lines, labels);
            });
        }
        
        //call update lines before we start to draw the triangle and initial angles
        updateLines(dots, lines, labels);
        
        pane.setPrefSize(400, 400);
        
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }


    public void updateLines(Circle[] dots, Line[] lines, Label[] labels){
        //loop over the dots
        for(int i = 0; i < dots.length; i++){
            //update this line to start at the current dot
            lines[i].setStartX(dots[i].getCenterX());
            lines[i].setStartY(dots[i].getCenterY());
            //update this line to end at the next dot
            lines[i].setEndX(dots[(i+1)%dots.length].getCenterX());
            lines[i].setEndY(dots[(i+1)%dots.length].getCenterY());
            
            //move the label to the dot's position
            labels[i].relocate(dots[i].getCenterX(), dots[i].getCenterY()+10);
        }
        
        //calculate the lengths a, b, and c
        double a = Math.sqrt(Math.pow(dots[2].getCenterX()-dots[1].getCenterX(), 2) +
                Math.pow(dots[2].getCenterY()-dots[1].getCenterY(), 2));
        double b = Math.sqrt(Math.pow(dots[2].getCenterX()-dots[0].getCenterX(), 2) +
                Math.pow(dots[2].getCenterY()-dots[0].getCenterY(), 2));
        double c = Math.sqrt(Math.pow(dots[0].getCenterX()-dots[1].getCenterX(), 2) +
                Math.pow(dots[0].getCenterY()-dots[1].getCenterY(), 2));
        
        //calculate the angles A, B, and C
        double A = Math.acos((a*a - b*b - c*c) / (-2 * b * c)) * (180 / Math.PI);
        double B = Math.acos((-a*a + b*b - c*c) / (-2 * a * c)) * (180 / Math.PI);
        double C = Math.acos((-a*a - b*b + c*c) / (-2 * a * b)) * (180 / Math.PI);
        
        //set the labels to display the calculated angles
        labels[0].setText(""+(int)Math.round(A));
        labels[1].setText(""+(int)Math.round(B));
        labels[2].setText(""+(int)Math.round(C));
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
