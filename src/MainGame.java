import java.io.*;
import java.util.*;

public class MainGame {
    //Sets the file to read questions from
    public static final String questions_file = "src/Questions.txt";

    public static void main(String[] args) throws FileNotFoundException {
        //Initializes the Binary Tree
        BinaryTree tree_questions = new BinaryTree();

        do {
            //Creates a scanner in the file
            tree_questions.QuestionFileScanner(new Scanner(new File(questions_file)));
            System.out.println("Welcome to Question Guezzer! Think of an object to be guessed.");

            //Starts by asking the initial question.
            tree_questions.AskQuestions();

            //Keeps running the questions game until the game has ended
        } while (tree_questions.GotYesFromUser("Do you want to go again?"));
        tree_questions.UpdateFile(new PrintStream((questions_file)));
    }
}