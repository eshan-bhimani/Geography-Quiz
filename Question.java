package edu.uga.cs1302.quiz;


import java.io.Serializable;
import java.util.*;

public class Question implements Serializable {
        private static final long serialVersionUID = 1L;

        // The name of the country for the question
        private String country;
        // The correct continenet for the country
        private String correctAnswer;
        private List<String> options;

        public Question(String country, String correctAnswer, List<String> allAnswers) {
                this.country = country;
                this.correctAnswer = correctAnswer;

                // Shuffle the options to randomize their order
                this.options = new ArrayList<>(allAnswers);
                Collections.shuffle(this.options);
        }

        public String getQuestionText() {
                return "What continent is " + country + " located in?";
        }
        // Returns the list of answer choices
        public List<String> getOptions() {
                return options;
        }

        public String getAnswer() {
                return correctAnswer;
        }
}
