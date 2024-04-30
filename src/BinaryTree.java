import java.util.*; 
import java.io.*;

public class BinaryTree {
   public BinaryNode tree_root;
   public Scanner scanner;

   public BinaryTree() {
      tree_root = new BinaryNode("book");
      scanner = new Scanner(System.in);
   }

   //Feed the bot with the users input
   public void QuestionFileScanner(Scanner input) {
      while(input.hasNextLine()) {
         tree_root = ReadLine(input);
      }
   }

   //Reads line of the file
   private BinaryNode ReadLine(Scanner input) {
      String response_type = input.nextLine();
      String user_data = input.nextLine();

      BinaryNode root = new BinaryNode(user_data);

      if (response_type.contains("Q:")) {
         root.yes_node = ReadLine(input);
         root.no_node = ReadLine(input);
      }
      return root;
   }

   //Runs at the end when the game is finish to update the tree.
   public void UpdateFile(PrintStream output) {
      if (output == null) {
         throw new IllegalArgumentException();
      }
      UpdateTree(tree_root, output);
      System.out.println("Updating file" + tree_root);
   }

   private void UpdateTree(BinaryNode rootOfTree, PrintStream output) {
      if (CheckAnswer(rootOfTree)) {
         output.println("A:");
         output.println(rootOfTree.data);
      } else {
         output.println("Q:");
         output.println(rootOfTree.data);
         UpdateTree(rootOfTree.yes_node, output);
         UpdateTree(rootOfTree.no_node, output);
      }
   }

   public boolean GotYesFromUser(String prompt) {
      System.out.print(prompt + " (y/n)? ");
      String response = scanner.nextLine().trim().toLowerCase();

      while (!response.equals("y") && !response.equals("n")) { //Makes sure the user inputs a valid response.
         System.out.println("Please answer y or n.");
         System.out.print(prompt + " (y/n)? ");
         response = scanner.nextLine().trim().toLowerCase();
      }

      return response.equals("y");
   }

   public void AskQuestions() {
      tree_root = AskQuestions(tree_root);
   }

   private boolean CheckAnswer(BinaryNode node) {
      return (node.yes_node == null || node.no_node == null);
   }

   private BinaryNode AskQuestions(BinaryNode current_node) {
      if (CheckAnswer(current_node)) {
         //Checks if the bot got a correct answer if yes then the bot wins
         if (GotYesFromUser("Is your object " + current_node.data +"?")) {
            System.out.println("Easyyyyyy!");

            //Else the bot loses and asks for new data input so that it can learn and add it to the questions file
         } else {
            System.out.print("What is the name of your object? ");

            //Gets the users input based on the user_question.
            BinaryNode user_answer = new BinaryNode(scanner.nextLine());

            System.out.println("Please enter a yes/no user_question that makes our objects different");

            String user_question = scanner.nextLine();

            //Asks the user for the user_answer to their user_question
            if (GotYesFromUser("What is the user_answer for your given object?")) {
               current_node = new BinaryNode(user_question, user_answer, current_node);
            } else {
               current_node = new BinaryNode(user_question, current_node, user_answer);
            }
         }

      } else {
         if (GotYesFromUser(current_node.data)) {
            current_node.yes_node = AskQuestions(current_node.yes_node);
         } else {
            current_node.no_node = AskQuestions(current_node.no_node);
         }   
      } 
      return current_node;
   }
}












