package com.kay.controller;

import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Map;

public class LoginViewController {
    public TextField idField;
    public PasswordField pwdField;

    private Map<String, String> userInfo;

    public void loginBtn(MouseEvent actionEvent) {
//        System.out.println("btn clicked");
        userInfo.put("email", idField.getText());
        userInfo.put("password", pwdField.getText());
        System.out.println("login success");

        closeStage(actionEvent);
    }

    private void closeStage(MouseEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public Map<String, String> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Map<String, String> userInfo) {
        this.userInfo = userInfo;
    }
}
