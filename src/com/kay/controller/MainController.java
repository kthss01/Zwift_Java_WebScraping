package com.kay.controller;

import com.kay.model.dao.SeleniumScraper;
import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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
    public Label runLevel;
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
    public ProgressBar rideExpProgressBar;
    public ProgressBar runExpProgressBar;

    private SeleniumScraper scraper;

    // email과 password 저장
    private Map<String, String> userInfo = new LinkedHashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        System.out.println("start");
        initInfo();
    }

    public void initInfo() {
        // 초기 정보들 다 기본 상태로 변경
        // Activity
        activityImg.setImage(null);

        title.setText("");
        rideon.setText("0");
        distance.setText("0 km");
        time.setText("0h 0m 0s");
        etcInfo.setText("0 m");
        calories.setText("0 cal");

        // Profile
        // ride
        rideLevel.setText("0");
        rideExpProgressBar.setProgress(0);
        rideDistance.setText("0 km");
        rideTime.setText("0d 0h 0m");
        rideElevation.setText("0 m");
        rideCalories.setText("0 cal");

        // run
        runLevel.setText("0");
        runExpProgressBar.setProgress(0);
        runDistance.setText("0 km");
        runTime.setText("0d 0h 0m");
        runCalories.setText("0 cal");

        drops.setText("0");

        userImg.setImage(null);
        startZwift.setText("0.0.0");
        lastZwift.setText("0.0.0");
        activitiesCount.setText("0");
        rideCount.setText("0");
        runCount.setText("0");

        Node node = scrollPane.getContent();
        node.setVisible(false);

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
