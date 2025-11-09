# Geography-Quiz
An interactive JavaFX-based quiz game that tests geographic knowledge through a clean GUI interface


🌍 Geography Quiz - JavaFX Quiz Application
A sophisticated geography quiz application built with JavaFX that challenges users to identify the continents of randomly selected countries. This project demonstrates advanced Java programming concepts including GUI development, data persistence, and object-oriented design.


🎯 Project Overview
Geography Quiz is an educational application that:

Randomly selects 6 countries from a database of 195 nations
Presents multiple-choice questions about continent locations
Tracks quiz performance with persistent storage
Provides immediate feedback on correct/incorrect answers
Maintains historical quiz results with timestamps

Key Features
Core Functionality

Dynamic Quiz Generation: Randomly selected questions ensure unique quiz experiences
Interactive GUI: Clean JavaFX interface with modal windows for focused interactions
Smart Answer Validation: Immediate feedback with correct answer display for learning
Historical Tracking: Persistent storage of quiz results sorted by date (newest to oldest)
Help System: Comprehensive in-app instructions for user guidance

Technical Highlights

CSV Data Management: Apache Commons CSV library integration for country/continent data
Binary Serialization: Efficient storage and retrieval of quiz results using Java I/O
Collection Framework: Strategic use of ArrayList and LinkedList for optimal data handling
Random Generation: Duplicate-free selection of countries and distractor continents
Modal Window Architecture: Prevents concurrent interactions for better UX

🛠️ Technical Stack
TechnologyPurposeJava 17Core programming languageJavaFXGUI framework for rich user interfacesMaven 3.8.6Build automation and dependency managementApache Commons CSV 1.14.0CSV file parsing and processingJava SerializationBinary data persistence
📁 Project Structure
project5/
├── src/
│   └── main/
│       ├── java/
│       │   └── edu/uga/cs1302/quiz/
│       │       ├── GeographyQuiz.java        # Main application class
│       │       ├── QuestionCollection.java   # Country database manager
│       │       ├── Country.java              # Country model
│       │       ├── Question.java             # Quiz question model
│       │       ├── Quiz.java                 # Quiz session manager
│       │       └── QuizResult.java           # Result tracking (Serializable)
│       └── resources/
│           └── country_continent.csv          # 195 countries dataset
├── quizzes.dat                                # Binary quiz results storage
├── pom.xml                                    # Maven configuration
└── README.txt                                 # Compilation instructions
🚀 Getting Started
Prerequisites

Java Development Kit (JDK) 17 or higher
Apache Maven 3.8.6 or higher
JavaFX SDK 17 (handled via Maven dependencies)

Installation & Running

Clone the repository

bash   git clone https://github.com/yourusername/geography-quiz.git
   cd geography-quiz

Configure Maven (if on Unix-like systems)

bash   # Add to .bash_profile or .bashrc
   export PATH=/path/to/maven/bin:$PATH
   export JAVA_HOME=/path/to/jdk-17
   source ~/.bash_profile

Verify setup

bash   mvn -version  # Should show Apache Maven 3.8.6
   java -version # Should show Java 17

Compile the project

bash   mvn clean compile

Run the application

bash   mvn javafx:run
🎮 How to Play

Start Application: Launch the main window displaying quiz rules
Begin Quiz: Click "Start New Quiz" to open the quiz window
Answer Questions: Select the correct continent from 3 radio button options
Submit Answers: Click "Submit" to check your answer and proceed
View Results: After 6 questions, see your score (e.g., "5 out of 6")
Review History: Access past quiz results sorted chronologically
Get Help: Use the help button for detailed instructions anytime

🏗️ Architecture & Design Patterns
Object-Oriented Design

Encapsulation: Private data members with public accessors
Separation of Concerns: Distinct classes for data, logic, and presentation
Serialization: QuizResult implements Serializable for persistence
Collection Generics: Type-safe ArrayList<Country> and LinkedList<Question>

Data Flow

Initialization: Load countries from CSV → Parse into ArrayList<Country>
Quiz Creation: Random selection → Generate Quiz with 6 Question objects
User Interaction: JavaFX event handlers → Update quiz state
Persistence: Serialize QuizResult → Write to quizzes.dat
Restoration: Deserialize on startup → Display in chronological order

💡 Implementation Highlights
Random Question Generation
java// Ensures no duplicate countries or continents in a single quiz
- Uses java.util.Random with ArrayList indexing
- Validates unique continent distractors (including Antarctica)
- Randomizes answer order for each question
CSV Parsing Strategy
java// Apache Commons CSV for robust data handling
- Reads from src/main/resources directory
- Handles 195 country records across 6 continents
- Type-safe conversion to Country objects
Modal Window Management
java// APPLICATION_MODAL prevents main window interaction
- Quiz window: Active quiz prevents other operations
- Results window: Historical data view in isolated context
- Help window: Instructions without state changes
📊 Data Specifications

Countries: 195 nations across 6 continents
Continents: Africa, Asia, Europe, North America, Oceania, South America
Quiz Format: 6 questions per quiz, 3 multiple-choice options each
Storage: Binary serialization in quizzes.dat
Date Tracking: java.util.Date for quiz timestamps

🔒 Design Constraints

✅ No Arrays: All collections use ArrayList or LinkedList
✅ Package Structure: All classes in edu.uga.cs1302.quiz
✅ Relative Paths: Data files use relative naming for portability
✅ Modal Windows: Enforced single-focus interaction model
✅ Persistent Storage: Quiz results survive application restarts

🎓 Learning Outcomes
This project demonstrates proficiency in:

JavaFX GUI development with scene graphs and event handling
Maven project structure and dependency management
File I/O with both text (CSV) and binary serialization
Collection framework selection and optimization
Object-oriented design with SOLID principles
Random algorithm implementation without duplicates
Error handling and data validation

📝 Future Enhancements
Potential improvements for portfolio expansion:

 Difficulty levels (more continents, timed challenges)
 Leaderboard with user authentication
 Capital cities and flags as additional quiz modes
 Data visualization of quiz performance trends
 Export results to PDF/CSV reports
 Multi-language support with resource bundles

👨‍💻 Developer
Eshan - Computer Science Student | AI & Software Engineering
University of Georgia | Class of 2027
