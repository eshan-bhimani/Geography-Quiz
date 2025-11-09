package edu.uga.cs1302.quiz;

import java.util.*;

public class Quiz {
        private List<Question> questions;
        private int score;
        private Date date;

        public Quiz(CountryCollection collection) {
                questions = new ArrayList<>();
                Set<Integer> used = new HashSet<>();
                Random rand = new Random();
                // Continues generating questions until we have 6
                while (questions.size() < 6) {
                        int index = rand.nextInt(collection.size());
                        if (used.add(index)) {
                                Country c = collection.get(index);
                                String countryName = c.getName();
                                String correctContinent = c.getContinent();

                                // Collect 3 incorrect continents with a safety limit
                                Set<String> distractors = new HashSet<>();
                                int attempts = 0;

                                while (distractors.size() < 2  && attempts < 100) {
                                        Country other = collection.get(rand.nextInt(collection.size()));
                                        String otherContinent = other.getContinent();
                                        if (!otherContinent.equals(correctContinent)) {
                                        distractors.add(otherContinent);
                                        }
                                        attempts++;
                                }
                                // If we successfully find 2 unique distractors, create a question 
                                if (distractors.size() == 2) {
                                        List<String> options = new ArrayList<>(distractors);
                                        options.add(correctContinent);
                                        Collections.shuffle(options);
                                        questions.add(new Question(countryName, correctContinent, options));
                                } else {
                                        System.out.println("Skipping: " + countryName + " (Not enough countires available)");
                                }

                        }
                }

                System.out.println("Quiz generated with " + questions.size() + " questions.");
                score = 0;
                date = new Date();
        }
        public List<Question> getQuestions() {
                return questions;
        }

        public int getScore() {
                return score;
        }

        public void incrementScore() {
                score++;
        }

        public Date getDate() {
                return date;
        }

        @Override
        public String toString() {
                StringBuilder sb = new StringBuilder("Quiz:\n");
                for (Question q : questions) {
                        sb.append(q).append("\n");
                }
                return sb.toString();
        }
}
