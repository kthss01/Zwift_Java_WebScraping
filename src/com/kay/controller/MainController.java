package com.kay.controller;

import com.kay.model.dao.SeleniumScraper;
import com.kay.model.vo.Activity;
import com.kay.model.vo.ActivityRide;
import com.kay.model.vo.ActivityRun;
import com.kay.model.vo.Profile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    public Label rideDistance;
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
    public ListView<String> listView;

    private SeleniumScraper scraper;

    private Map<String, Image> imageMap = new LinkedHashMap<>();
    private Map<String, Background> backgroundMap = new LinkedHashMap<>();

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
        name.setText("");
        startZwift.setText("0.0.0");
        lastZwift.setText("0.0.0");
        activitiesCount.setText("0");
        rideCount.setText("0");
        runCount.setText("0");
    }

    private void setActivityList() {

        for (Activity activity : scraper.getActivities()) {
            StringBuilder sb = new StringBuilder();
//            if (activity instanceof ActivityRide)
//                sb.append("Ride] ");
//            else
//                sb.append("Run ] ");

            sb.append("[").append(activity.getDate().substring(2))
                    .append("] ").append(activity.getName());

            if (activity instanceof  ActivityRide) {
                imageMap.put(sb.toString(), new Image("./images/ride.png", 30, 30, false,true));
                backgroundMap.put(sb.toString(), new Background(new BackgroundFill(Color.rgb(244,240,230,1), null, null)));
            } else {
                imageMap.put(sb.toString(), new Image("./images/run.png", 30, 30, false,true));
                backgroundMap.put(sb.toString(), new Background(new BackgroundFill(Color.rgb(206,239,228,1), null, null)));
            }

            listView.getItems().add(sb.toString());
        }

        listView.setCellFactory(param -> new ActivityCell());
    }

    private class ActivityCell extends ListCell<String> {
        private ImageView imageView = new ImageView();

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                imageView.setImage(null);

                setGraphic(null);
                setText(null);
            } else {
                imageView.setImage(imageMap.get(item));

                setText(item);
                setGraphic(imageView);

                setBackground(backgroundMap.get(item));
                setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            }
        }
    }

    private void setActivity(Activity activity) {
        // img
        activityImg.setImage(new Image(activity.getImage()));

        activityImg.setOnMouseClicked(event -> {
            try {
                Desktop.getDesktop().browse(new URI(activity.getUrl()));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        });

        title.setText(activity.getName());
        rideon.setText(activity.getRideon());
        date.setText(activity.getDate());
        distance.setText(activity.getDistance());
        time.setText(activity.getTime());
        if (activity instanceof ActivityRide) {
            etcInfo.setText("Elevation");
            etcInfoValue.setText(((ActivityRide) activity).getElevation());
        } else {
            etcInfo.setText("Pace");
            etcInfoValue.setText(((ActivityRun) activity).getPace());
        }
        calories.setText(activity.getCalories());

        // tooltip
        title.setTooltip(new Tooltip(activity.getName()));
    }

    private void setProfile() {
        Profile profile = scraper.getProfile();

        // ride
        rideLevel.setText(profile.getRideLevel());
        rideExpProgressBar.setProgress(Double.parseDouble(profile.getRideLevelExp()) / 100);
        rideDistance.setText(profile.getRideDistance());
        rideTime.setText(profile.getRideTime());
        rideElevation.setText(profile.getRideElevation());
        rideCalories.setText(profile.getRideCalories());

        // run
        runLevel.setText(profile.getRunLevel());
        runExpProgressBar.setProgress(Double.parseDouble(profile.getRunLevelExp()) / 100);
        runDistance.setText(profile.getRunDistance());
        runTime.setText(profile.getRunTime());
        runCalories.setText(profile.getRunCalories());

        drops.setText(profile.getDrops());

        // profile

        // img
        userImg.setImage(new Image(profile.getIcon()));

        name.setText(profile.getName());
        startZwift.setText(scraper.getStartZwift());
        lastZwift.setText(scraper.getLastZwift());
        activitiesCount.setText(scraper.getActivitiesCount());
        rideCount.setText(scraper.getRideCount());
        runCount.setText(scraper.getRunCount());
    }

    public void loadBtn(ActionEvent actionEvent) throws IOException {
//        System.out.println("Button clicked");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/login.fxml"));
        Parent parent = fxmlLoader.load();
        LoginViewController loginViewController = fxmlLoader.getController();

        loginViewController.setUserInfo(userInfo);

        Scene scene = new Scene(parent);
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

//        System.out.println("test");
//        System.out.println(userInfo);

        scraper = new SeleniumScraper(userInfo.get("email"), userInfo.get("password"));
        if (!loginViewController.isReadZwift())
            scraper.zwiftScarping();
        else
            scraper.loadZwift();

        scraper.changeDateFormat();

//        System.out.println("show profile, activities");
//        System.out.println(scraper.getProfile());
//        System.out.println(scraper.getActivities());

        setProfile();
        setActivityList();
        setActivity(scraper.getActivities().get(0));
    }


    public void listItemClicked(MouseEvent mouseEvent) {
        int index = listView.getSelectionModel().getSelectedIndex();
        if (index <= -1)
            index = 0;
        Activity activity = scraper.getActivities().get(index);
        setActivity(activity);
    }
}
