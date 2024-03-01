
package org.example.Controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.example.Entities.User;

import java.io.IOException;

public class CaptchaVerificationController {


    @FXML
    private TextField captchaCodeField;
    @FXML
    private Button verifyButton;
    @FXML
    private Label errorLabel;
    @FXML
    private Label captchaCodeLabel;
    private SignInController signInController;
    public void setSignInController(SignInController signInController) {
        this.signInController = signInController;
    }
    private static final String HCAPTCHA_SECRET_KEY = "b9414817-7abe-4952-9af1-837d708fdf8c";
    private static final String HCAPTCHA_API_URL = "https://hcaptcha.com/siteverify";
    //private static final String EASYCAPTCHA_API_URL = "https://hcaptcha.com/siteverify";
    //private static final String EASYCAPTCHA_API_URL = "https://api.easycaptcha.io/verify";


    @FXML
    public void initialize() {
        // Generate and display Captcha code on initialization
        generateCaptchaCode();
    }

    private void generateCaptchaCode() {
        OkHttpClient client = new OkHttpClient();
        Gson gson = new GsonBuilder().create();

        RequestBody formBody = new okhttp3.FormBody.Builder()
                .add("key", HCAPTCHA_SECRET_KEY)
                .build();

        Request request = new Request.Builder()
                .url(HCAPTCHA_API_URL)
                .post(formBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            EasyCaptchaResponse easyCaptchaResponse = gson.fromJson(response.body().string(), EasyCaptchaResponse.class);
            if (easyCaptchaResponse.success) {
                captchaCodeLabel.setText(easyCaptchaResponse.text);
            } else {
                errorLabel.setText("Failed to generate Captcha code. Please try again.");
            }
        } catch (IOException e) {
            errorLabel.setText("Failed to generate Captcha code. Please try again.");
        }
    }

    public boolean verifyCaptcha() {
        return verifyCaptcha(captchaCodeField, errorLabel);
    }
    @FXML
    private void verifyCaptchaAndProceed(ActionEvent event) throws IOException {
        if (verifyCaptcha()) {
        User user = signInController.getUserForCaptchaVerification();
        signInController.proceedAfterCaptchaVerification(user);
    } else {
        errorLabel.setVisible(true);
    }}
    public static boolean verifyCaptcha(TextField captchaCodeField, Label errorLabel) {
        OkHttpClient client = new OkHttpClient();
        Gson gson = new GsonBuilder().create();

        RequestBody formBody = new okhttp3.FormBody.Builder()
                .add("response", captchaCodeField.getText())
                .add("secret", HCAPTCHA_SECRET_KEY)
                .build();

        Request request = new Request.Builder()
                .url(HCAPTCHA_API_URL)
                .post(formBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            EasyCaptchaResponse easyCaptchaResponse = gson.fromJson(response.body().string(), EasyCaptchaResponse.class);
            if (easyCaptchaResponse.success) {
                return true;
            } else {
                errorLabel.setText("Captcha verification failed.");
                return false;
            }
        } catch (IOException e) {
            errorLabel.setText("Captcha verification failed. Please try again.");
            return false;
        }
    }

    public static class EasyCaptchaResponse {
        public boolean success;
        public String text;
    }


    public TextField getCaptchaCodeField() {
        return captchaCodeField;
    }

    public Label getErrorLabel() {
        return errorLabel;
    }
}

