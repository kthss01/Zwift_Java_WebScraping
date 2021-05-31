package com.kay.controller;

import com.kay.model.dao.SeleniumScraper;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    public Label rideDistance;
    public ScrollPane scrollPane;
    public Label rideLevel;
    public Label walkLevel;
    public Label rideTime;
    public Label rideCalories;
    public Label rideElevation;
    public Label runDistance;
    public Label runTime;
    public Label runCalories;
    public Label drops;
    public ImageView activityImg;
    public Label date;
    public Label title;
    public Label etcInfo;
    public Label distance;
    public Label time;
    public Label etcInfoValue;
    public Label calories;
    public Label rideon;
    public ImageView userImg;
    public Label name;
    public Label startZwift;
    public Label lastZwift;
    public Label activitiesCount;
    public Label rideCount;
    public Label runCount;

    private SeleniumScraper scraper;

    // email과 password 저장
    private Map<String, String> userInfo = new LinkedHashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void loadBtn(ActionEvent actionEvent) throws IOException {
//        System.out.println("Button clicked");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/login.fxml"));
        Parent parent = fxmlLoader.load();
        LoginViewController loginViewController = fxmlLoader.<LoginViewController>getController();
        loginViewController.setUserInfo(userInfo);

        Scene scene = new Scene(parent);
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

//        System.out.println("test");
//        System.out.println(userInfo);

        scraper = new SeleniumScraper(userInfo.get("email"), userInfo.get("password"));
        scraper.zwiftScarping();

        System.out.println("show profile, activities");
        System.out.println(scraper.getProfile());
        System.out.println(scraper.getActivities());
    }
}
