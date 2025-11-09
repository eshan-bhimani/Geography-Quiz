package edu.uga.cs1302.quiz;

import java.util.*;
import java.io.*;
import org.apache.commons.csv.*;

public class CountryCollection {
        // List to store all Country objects
        private ArrayList<Country> countries;

        // Constructor that laods countrie from a CSV file
        public CountryCollection(String csvPath) throws IOException {
                countries = new ArrayList<>();
                // Open the CSV file for reading
                try (Reader reader = new FileReader(csvPath)) {
                        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);
                        for (CSVRecord record : records) {
                                String country = record.get(0).trim();
                                String continent = record.get(1).trim();
                                countries.add(new Country(country, continent));
                        }
                }
        }

        public Country get(int index) {
                return countries.get(index);
        }

        public int size() {
                return countries.size();
        }

        public List<Country> getAll() {
                return countries;
        }
}
