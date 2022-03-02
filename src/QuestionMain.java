import com.sun.source.tree.BinaryTree;

import java.io.*;
import java.util.*;

    public class QuestionMain {
        public static void main(String[] args) throws FileNotFoundException, IOException {
            Scanner console = new Scanner(System.in);

            System.out.println("Welcome to the 20 Questions Game!");
            System.out.println();
            System.out.print("Which questions file would you like to use? ");
            String filename = console.nextLine().trim();

            /* Create the Questions File if it doesn't exist */
            File questionsFile = new File(filename);
            if (!questionsFile.exists()) {
                questionsFile.createNewFile();
            }

            Scanner questions = new Scanner(questionsFile);


          QuestionGame game;

            /* Check if the file has anything in it.  If it does, use it.  Otherwise, initialize
             * a new game. */
            if (!questions.hasNext()) {
                System.out.println("There are no objects to guess in that questions file.");
                System.out.print("Can you provide me with an initial object? ");
                String initialObject = console.nextLine().toLowerCase().trim();
                game = new QuestionGame(initialObject);
            }
            else {
                game = new QuestionGame(questions);
            }

            System.out.print("Let's play!  ");
            do {
                System.out.println("Please choose your object, and I'll start guessing.");
                System.out.println("Press Enter when you're ready to begin!");
                console.nextLine();
               // game.play(console);
                System.out.println();
                game.saveQuestions(new PrintStream(questionsFile));
                System.out.print("Do you want to play again (y/n)? ");
            } while (console.nextLine().trim().toLowerCase().startsWith("y"));
        }
    }

