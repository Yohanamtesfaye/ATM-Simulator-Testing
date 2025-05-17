package com.abeto.javafx;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MainController extends Account implements Initializable {
    public Button SignIn;
    public Label visionATM;
    public Scene scene;
    public Parent root;
    public Stage stage;
    @FXML
    TextField userName,userPassword;
    @FXML
    Label fieldStatus;
    public static String userType;
    public String user;
    public int password;
public UserController controller;

    @FXML
    public void adminLogin(ActionEvent event) throws IOException {
        userType="admin";
        System.out.println("Admin Tries to Log in");
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("signin-scene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public  void userLogin(ActionEvent event) throws IOException {
        System.out.println("Users Tries to Log in");
        userType="user";
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("signin-scene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void signIn(ActionEvent event)  {
        try{
            System.out.println(userType);
            user = userName.getText();
            password = Integer.parseInt(userPassword.getText());
            fieldStatus.setText("");
            if (userType.contains("admin")){
                adminAuth(event,user,password);
               }
            else if (userType.contains("user")){
                userAuth(event,user,password);
            }
        }catch (NumberFormatException n){
            System.out.println(n.getMessage());
            fieldStatus.setText("Wrong password");
            fieldStatus.setTextFill(Color.RED);

        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    public void goBack(MouseEvent event) throws IOException {
        userName.setText("");
        password = 0;
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main-scene.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public  void userAuth(ActionEvent event,String user,int password) throws IOException {
        boolean isUser = false;

        for (int i = 0; i < userDetail.size(); i++) {
            
            // if (true) {
            if (userDetail.get(i).getName().contains(user) && userDetail.get(i).getPin() == password) {
                isUser = true;
                if (controller ==null){
                    FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("user-home-scene.fxml")));

                    root = loader.load();
                    controller = (UserController) loader.getController();
                    UserController.UserAccount = userDetail.get(i);
                    controller.setWelcomeLabel();
                }

                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
        if (!isUser){
            fieldStatus.setText("Wrong password");
            fieldStatus.setTextFill(Color.RED);
        }

    }
    @FXML
    public  void adminAuth(ActionEvent event,String user,int password) throws IOException {
        if (user.contains("v") && password == 1){
            root =  FXMLLoader.load(Objects.requireNonNull(getClass().getResource("admin-home-scene.fxml")));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    else {
        fieldStatus.setText("Wrong password");
        fieldStatus.setTextFill(Color.RED);
    }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FadeTransition transition2 = new FadeTransition();
        transition2.setNode(visionATM);
        transition2.setDuration(Duration.millis(3000));
        transition2.setCycleCount(3);
        transition2.setFromValue(0);
        transition2.setToValue(1);
        transition2.setAutoReverse(true);
        transition2.play();
        ScaleTransition transition3 = new ScaleTransition();
        transition3.setNode(visionATM);
        transition3.setDuration(Duration.millis(3000));
        transition3.setCycleCount(2);
        transition3.setByY(0.5);
        transition3.setByX(0.5);
        transition3.setByZ(1);
        transition3.setAutoReverse(true);
        transition3.play();
try{
        BufferedReader reader = new BufferedReader(new FileReader("user-data.txt"));
        File file = new File("user-data.txt");
        Scanner data = new Scanner(reader);
        if (data.hasNextLine()) {
            while (data.hasNext()) {
                System.out.println("hello");

//                System.out.println(data.nextLine());
                String name = data.next();
                long acc = Long.parseLong(data.next().trim());
                int pin = Integer.parseInt(data.next().trim());
                double balance = Double.parseDouble(data.next().trim());
                Account account = new Account(name,pin,acc, (Double) balance);
                System.out.println(account);
                userDetail.add(account);
            }
        } else System.err.println("No User is found");
        reader.close();
        data.close();
    if (file.delete()){
        System.out.println("file is removed");
    }
    else{
        System.out.println("go");
    }
    }catch (FileNotFoundException e) {
    System.out.println(e.getMessage());
} catch (Exception e) {
    System.err.println("Something went wrong on File Write!!");
    System.out.println(e.getMessage());
}

//        Account account = new Account("John",1111,1111,600);
//        Account account1 = new Account("Yared",2222,2222,800);
//        Account account2 = new Account("Mike",3333,3333,400);
//        Account account3 = new Account("Maru",4444,4444,600);
//        Account account4 = new Account("Lidiya",1555,1555,2200);
//        Account account5 = new Account("Meron",1332,1332,100);
//        Account account6 = new Account("b",1,1012,300);
//        if (userDetail.isEmpty()) {
//            userDetail.add(account);
//            userDetail.add(account1);
//            userDetail.add(account2);
//            userDetail.add(account3);
//            userDetail.add(account4);
//            userDetail.add(account5);
//            userDetail.add(account6);
//        }

    }

}