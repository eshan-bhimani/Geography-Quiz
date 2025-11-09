package edu.uga.cs1302.quiz;

import java.io.*;
import java.util.*;

public class QuizResult implements Serializable {
    private static final long serialVersionUID = 1L;

    // List of all past quiz scores
    private List<QuizScore> results;

    public QuizResult() {
        results = new LinkedList<>();
    }

    public void addResult(QuizScore score) {
        results.add(0, score); // newest to oldest
    }
    // Returns the list of quiz scores
    public List<QuizScore> getResults() {
         return results;
    }
    // Saves teh curretn QuizResult object to a file
    public void save(String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(this);
        }
    }

    public static QuizResult load(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (QuizResult) in.readObject();
        }
    }

    // Creates a string representation of all quiz scores
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Past Quiz Scores:\n");
        for (QuizScore score : results) {
            sb.append(score).append("\n");
        }
        return sb.toString();
    }
}
