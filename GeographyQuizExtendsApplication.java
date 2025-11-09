package edu.uga.cs1302.quiz;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.stage.Modality;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import java.io.File;

public class GeographyQuiz extends Application {

        // Instance variables for quiz state    
        private CountryCollection countries;
        private Quiz quiz;
        private int currentIndex = 0;
        private int correctAnswers = 0;
        // The entry point for JavaFX GUI
        @Override
        public void start(Stage primaryStage) {
                primaryStage.setTitle("Geography Quiz");
                // Main menu buttons
                Button startQuizBtn = new Button("Start Quiz");
                Button viewResultsBtn = new Button("View Past Results");
                Button helpBtn = new Button("Help");
                Button quitBtn = new Button("Quit");
                // The Start Quiz button action
                startQuizBtn.setOnAction(e -> {
                        try {
                                countries = new CountryCollection("src/main/resources/country_continent.csv");
                                quiz = new Quiz(countries);
                                currentIndex = 0;
                                correctAnswers = 0;
                                showQuizWindow(primaryStage); // Launches the quiz window
                        } catch (IOException ex) {
                                ex.printStackTrace();
                        }
                });
                // The View Past Results button action  
                viewResultsBtn.setOnAction(e -> {
                        Stage resultStage = new Stage();
                        resultStage.initOwner(primaryStage);
                        resultStage.initModality(Modality.APPLICATION_MODAL);
                        resultStage.setTitle("Past Results");

                        VBox resultsBox = new VBox(10);
                        resultsBox.setStyle("-fx-padding: 15;");
                        ScrollPane scrollPane = new ScrollPane(resultsBox);
                        scrollPane.setFitToWidth(true);

                        try {
                                QuizResult result = QuizResult.load("quizzes.dat");
                                for (QuizScore score : result.getResults()) {
                                        Label label = new Label(score.toString());
                                        resultsBox.getChildren().add(label);
                                }
                        } catch (IOException | ClassNotFoundException ex) {
                                resultsBox.getChildren().add(new Label("No saved results or failed to load."));
                        }

                        Button closeBtn = new Button("Close");
                        closeBtn.setOnAction(ev -> resultStage.close());
                        VBox layout = new VBox(10, scrollPane, closeBtn);
                        layout.setStyle("-fx-padding: 10; -fx-alignment: center;");

                        Scene scene = new Scene(layout, 300, 300);
                        resultStage.setScene(scene);
                        resultStage.show();
                });
                // The Help button action
                helpBtn.setOnAction(e -> {
                        Stage helpStage = new Stage();
                        helpStage.initOwner(primaryStage);
                        helpStage.initModality(Modality.APPLICATION_MODAL);
                        helpStage.setTitle("Help");
                        // The message that displays for Help
                        String helpText = """
                        This is a simple Geography Quiz game.

                        Press "Start Quiz" to begin a 6-question quiz.
                        For each question, select the continent you think the country belongs to.
                        Your score will be saved after completing the quiz.
                        You can view your past scores anytime by clicking "View Past Results".
                        Press "Quit" to close the application.
                        """;

                        TextArea textArea = new TextArea(helpText);
                        textArea.setWrapText(true);
                        textArea.setEditable(false);

                        ScrollPane scrollPane = new ScrollPane(textArea);
                        scrollPane.setFitToWidth(true);

                        Button closeBtn = new Button("Close");
                        closeBtn.setOnAction(ev -> helpStage.close());

                        VBox layout = new VBox(10, scrollPane, closeBtn);
                        layout.setStyle("-fx-padding: 10; -fx-alignment: center;");

                        Scene scene = new Scene(layout, 400, 250);
                        helpStage.setScene(scene);
                        helpStage.show();



                });

                // The Quit button action
                quitBtn.setOnAction(e -> primaryStage.close());

                // The Main window layout
                VBox root = new VBox(15, startQuizBtn, viewResultsBtn, helpBtn, quitBtn);
                root.setStyle("-fx-padding: 20; -fx-alignment: center;");

                Scene scene = new Scene(root, 300, 250);
                primaryStage.setScene(scene);
                primaryStage.show();

        }
        // Opens the window and sets up the first question
        private void showQuizWindow(Stage owner) {
                Stage quizStage = new Stage();
                quizStage.initOwner(owner);
                quizStage.initModality(Modality.APPLICATION_MODAL);
                quizStage.setTitle("Take the Quiz");

                VBox layout = new VBox(10);
                layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

                Label feedbackLabel = new Label("");
                Label questionLabel = new Label();
                ToggleGroup choicesGroup = new ToggleGroup();
                VBox optionsBox = new VBox(5);

                Button submitBtn = new Button("Submit");

                layout.getChildren().addAll(feedbackLabel, questionLabel, optionsBox, submitBtn);
                Scene scene = new Scene(layout, 400, 300);
                quizStage.setScene(scene);
                quizStage.show();

                showQuestion(questionLabel, optionsBox, choicesGroup); // This loads the first question
                // The submit button logic
                submitBtn.setOnAction(e -> {
                        Toggle selected = choicesGroup.getSelectedToggle();
                        if (selected == null) return;

                        String answer = ((RadioButton) selected).getText();
                        Question current = quiz.getQuestions().get(currentIndex);
                        if (answer.equals(current.getAnswer())) {
                                feedbackLabel.setText("Correct!");
                                correctAnswers++;
                        } else {
                                feedbackLabel.setText("Incorrect. Correct answer: " + current.getAnswer());
                        }

                        currentIndex++;
                        // If more questions remain, show the next one
                        if (currentIndex < quiz.getQuestions().size()) {
                                showQuestion(questionLabel, optionsBox, choicesGroup);
                        } else {
                                QuizScore score = new QuizScore(correctAnswers, quiz.getDate());
                                try {
                                        QuizResult result;
                                        File file = new File("quizzes.dat");
                                        // Load existing results if possible
                                        if (file.exists()) {
                                                try {
                                                        result = QuizResult.load("quizzes.dat");
                                                } catch (IOException | ClassNotFoundException err) {
                                                        result = new QuizResult();
                                                }
                                        } else {
                                                result = new QuizResult();
                                        }
                                        result.addResult(score);
                                        result.save("quizzes.dat"); // Saves the updated score results                          
                                } catch (IOException ex){
                                        ex.printStackTrace();
                                }
                                // Displays the final score
                                questionLabel.setText("Final Score: " + correctAnswers + "/6");
                                optionsBox.getChildren().clear();
                                submitBtn.setDisable(true);
                        }
                });
        }
        // Shows the current quiz question and answer choices
        private void showQuestion(Label questionLabel, VBox optionsBox, ToggleGroup group) {
                optionsBox.getChildren().clear();
                group.getToggles().clear();

                Question q = quiz.getQuestions().get(currentIndex);
                questionLabel.setText((currentIndex + 1) + ". " + q.getQuestionText());

                for (String option : q.getOptions()) {
                        RadioButton rb = new RadioButton(option);
                        rb.setToggleGroup(group);
                        optionsBox.getChildren().add(rb);
                }
        }

        // The main method for JavaFX
        public static void main(String[] args) {
                launch(args);
        }
}                                  
