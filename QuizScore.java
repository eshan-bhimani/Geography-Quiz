package edu.uga.cs1302.quiz;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QuizScore implements Serializable{
        private static final long serialVersionUID = 1L;
        private int score;
        private Date date;

        public QuizScore(int score, Date date){
                this.score = score;
                this.date = date;
        }

        public int getScore() {
                return score;
        }

        public Date getDate() {
                return date;
        }

        @Override
        public String toString(){
                SimpleDateFormat fmt = new SimpleDateFormat("MMM dd, yyyy HH:mm");
                return "Score: " + score + "/6 - " + fmt.format(date);
        }
}
