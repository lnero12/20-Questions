
import java.util.*;
import java.io.*;

public class QuestionGame {
    private QuestionNode rootTree;
    private Scanner console;


    public QuestionGame(String initialObject) {
        rootTree = new QuestionNode("computer");
        console = new Scanner(System.in);
    }

    public QuestionGame(Scanner input) {
        rootTree = play(input);
    }

    private QuestionNode play(Scanner input) {
        if (!input.nextLine().equals("A:")) {
            return new QuestionNode(input.nextLine(), rootTree.back, rootTree.front);
        } else {
            return new QuestionNode(input.nextLine());
        }
    }

    public void saveQuestions(PrintStream output) {
        saveQuestions(output, rootTree);
    }

    // Uses PrintStream output & QuestionNode current as parameters
    // Stores the current tree to the output file
    private void saveQuestions(PrintStream output, QuestionNode current) {
        if (current.back != null || current.front != null) {
            output.println("Q:" + current.data);
            saveQuestions(output, current.back);
            saveQuestions(output, current.front);
        } else {
            output.println("A:" + current.data);
        }
    }

    public void askQuestions() {
        rootTree = askQuestions(rootTree);
    }

    // Uses QuestionNode current as a parameter
    // Returns a new question tree
    // Completes on game with the user
    private QuestionNode askQuestions(QuestionNode current) {
        if (current.back != null || current.front != null) {
            if (yesTo(current.data)) {
                current.back = askQuestions(current.back);
            } else {
                current.front = askQuestions(current.front);
            }
        } else {
            if (yesTo("Would your object happen to be " + current.data + "?")) {
                System.out.println("Great, I got it right!");
            } else {
                System.out.print("What is the name of your object? ");
                String answer = console.nextLine();
                System.out.println("Please give me a yes/no question that");
                System.out.println("distinguishes between your object");
                System.out.println("and mine--> ");
                String question = console.nextLine();
                if (yesTo("And what is the answer for your object?")) {
                    current = new QuestionNode(question, new QuestionNode(answer), current);
                } else {
                    current = new QuestionNode(question, current, new QuestionNode(answer));
                }
            }
        }
        return current;
    }

    // This method asks the given question until the user types “y” or “n”.
    // Returns true if “y”, false if “n”.
    public boolean yesTo(String prompt) {
        System.out.print(prompt + " (y/n)? ");
        String response = console.nextLine().trim().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Please answer y or n.");
            System.out.print(prompt + " (y/n)? ");
            response = console.nextLine().trim().toLowerCase();
        }
        return response.equals("y");
    }

    // QuestionNode class have at least one constructor used by the tree.
    // It represent a single node of the tree.
    // Stores a single node of a binary tree in a game similar to 20 questions.
    private static class QuestionNode {
        public String data; // Stores text at this node
        public QuestionNode back; // Left sub-tree
        public QuestionNode front; // Right sub-tree

        // Constructs a Question Node with text set and the subtrees set to null.
        public QuestionNode(String textSet) {
            this(textSet, null, null);
        }

        // Constructs a Question Node with text set to 'a',
        // the yes subtree set to 'yes' and the no subtree set to 'no'.
        public QuestionNode(String textSet, QuestionNode yesString, QuestionNode noString) {
            data = textSet;
            back = yesString;
            front = noString;
        }
    }
}