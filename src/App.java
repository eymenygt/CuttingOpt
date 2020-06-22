package application;
	

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
import javafx.scene.layout.VBox;



public class App extends Application{

    Stage window;
    TableView<Product> table;
    TextField lengthText, quantitiyText;
    Button addButton,deleteButton;
    
    public static void main(String[] args){
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        
        window = stage;
        window.setTitle("Deneme");
       
        //length column
        TableColumn<Product,Double> lengthColumn = new TableColumn<>("Length");
        lengthColumn.setMinWidth(200);
        lengthColumn.setCellValueFactory(new PropertyValueFactory<>("length"));

        //Quantity column
        TableColumn<Product,Double> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(200);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        
        //Length input
        lengthText = new TextField();
        lengthText.setPromptText("LENGTH");
        
        //Quantitiy input
        quantitiyText = new TextField();
        quantitiyText.setPromptText("QUANTITY");
        
        //Buttons
        addButton = new Button("ADD");
        addButton.setOnAction(e -> addButtonHandler());
        deleteButton = new Button("DELETE");
        deleteButton.setOnAction(e -> deleteButtonHandler());
        
        //layout for addButton deleteButton...
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10,10,10,10));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(lengthText,quantitiyText,addButton,deleteButton);
        
        table = new TableView<>();
        table.setItems(getProduct());
        table.getColumns().addAll(lengthColumn,quantityColumn);



        VBox vBox = new VBox();
        vBox.getChildren().addAll(table,hbox);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }
    
    public void addButtonHandler() {
        try {
            double length = Double.parseDouble(lengthText.getText());
            int quantity = Integer.parseInt(quantitiyText.getText());
            Product p = new Product(length,quantity);
            table.getItems().add(p);
            lengthText.clear();
            quantitiyText.clear();
        }catch(NumberFormatException e) {
            // invalid inputs will be ignored.
        }    
    }
    
    public void deleteButtonHandler() {
        Product selectedItem = table.getSelectionModel().getSelectedItem();
        table.getItems().remove(selectedItem);
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
