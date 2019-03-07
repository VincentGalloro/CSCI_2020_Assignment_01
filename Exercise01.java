
import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Exercise01 extends Application{

    public void start(Stage primaryStage) throws Exception {
        HBox pane = new HBox();
    
        //the path to the card images (relative path)
        String path = "file:Cards/";
        
        //store the numbers 1-54 in the array
        ArrayList<Integer> cardNums = new ArrayList<>();
        for(int i = 0; i < 54; i++){ cardNums.add(i+1); }
        //shuffle the array, the first three values will be our distinct card numbers
        Collections.shuffle(cardNums);
        
        //create our views
        ImageView[] views = new ImageView[3];
        for(int i = 0; i < views.length; i++){ 
            views[i] = new ImageView(); 
            
            //use the numbers from our shuffled cardNums array to find the corresponding image
            views[i].setImage(new Image(path + cardNums.get(i) + ".png"));
        }
        
        //add our image views to the pane
        pane.getChildren().addAll(views);
        pane.setSpacing(10);
        
        //show the scene
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }
    
    public static void main(String[] args){
        launch(args);
    }
}
