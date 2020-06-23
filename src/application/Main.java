package src.application;
	

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

//        			............. TODO............              		//
//        	make table componentes(length,quantity) non-moveable        //
//        	create another canvas for optimization procces    			//
//        	print error msg for negative values               			//


public class Main extends Application{

    Stage window;
    TableView<Product> table;
    TextField lengthText, quantitiyText, profileLengthtext;
    Button addButton,deleteButton,optimizeButton;
    TableColumn<Product,Double> lengthColumn;
    TableColumn<Product,Double> quantityColumn;
    
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        
        window = stage;
        window.setTitle("Deneme");
       
        //length column
        lengthColumn = new TableColumn<>("Length");
        lengthColumn.setMinWidth(200);
        lengthColumn.setCellValueFactory(new PropertyValueFactory<>("length"));

        //Quantity column
        quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(200);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        
        //Length input
        lengthText = new TextField();
        lengthText.setPromptText("LENGTH");
        
        //Quantitiy input
        quantitiyText = new TextField();
        quantitiyText.setPromptText("QUANTITY");
        
        profileLengthtext = new TextField();
        profileLengthtext.setPromptText("PROFILE LENGTH");
        
        //Buttons
        addButton = new Button("ADD");
        addButton.setOnAction(e -> addButtonHandler());
        deleteButton = new Button("DELETE");
        deleteButton.setOnAction(e -> deleteButtonHandler());
        optimizeButton = new Button("OPTIMIZE");
        optimizeButton.setOnAction(e -> optimizeButtonHandler());
        
        
        //layout for addButton deleteButton...
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10,10,10,10));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(lengthText,quantitiyText,addButton,deleteButton);
        
        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);
        
        HBox hBox2 = new HBox();
        hBox2.setPadding(new Insets(0,10,10,10));
        hBox2.setSpacing(10);
        hBox2.getChildren().addAll(region1,profileLengthtext,optimizeButton);
        
        table = new TableView<>();
        table.setEditable(true);
        table.setItems(getProduct());
        table.getColumns().addAll(lengthColumn,quantityColumn);



        VBox vBox = new VBox();
        vBox.getChildren().addAll(table,hbox,hBox2);

        
        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }
    
    public void addButtonHandler() {
        try {
            double length = Double.parseDouble(lengthText.getText());
            int quantity = Integer.parseInt(quantitiyText.getText());
            lengthText.clear();
            quantitiyText.clear();
            
            //if length or quanitity vlaues are negative
            if(length <= 0 || quantity <= 0) return;
            //          ............. TODO............         //
            //        print error msg for negative values      //
            
            ArrayList<String> t = getTableViewColumn(table,0);
            //if length value doesn't exist in table
            
            if(!t.contains(String.valueOf(length))) {
                Product p = new Product(length,quantity);
                table.getItems().add(p);
            }
            //if length value exists in table
            else {
                int index = t.indexOf(String.valueOf(length));
                Product oldP = table.getItems().get(index);
                int oldQ = oldP.getQuantity();
                Product p = new Product(length,oldQ + quantity);
                table.getItems().set(index,p);
            }
        
        }catch(NumberFormatException e) {
            // invalid inputs will be ignored.
        }    
    }
    
    public void deleteButtonHandler() {
        Product selectedItem = table.getSelectionModel().getSelectedItem();
        table.getItems().remove(selectedItem);
    }
    
    public void optimizeButtonHandler() {
        //      ............. TODO............         //
    }
    
    // function to get the values of desired column
    private static ArrayList<String> getTableViewColumn(TableView tableView,int idx) {
        ArrayList<String> values = new ArrayList<>();
        ObservableList<TableColumn> columns = tableView.getColumns();
        for (Object row : tableView.getItems()) {
                values.add((columns.get(idx).getCellObservableValue(row).getValue().toString()));
        }
        return values;
}
    
    public ObservableList<Product> getProduct() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        products.add(new Product(1020,5));
        products.add(new Product(1000,10));
        products.add(new Product(1510,2));
        products.add(new Product(1120,1));
        return products;
    }
    

}
