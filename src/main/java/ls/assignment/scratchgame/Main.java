package ls.assignment.scratchgame;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.FileReader;
import java.io.IOException;

import static ls.assignment.scratchgame.Matrix.generateMatrix;

/**
 * Main class for the Scratch Game application.
 * This class handles the game initialization, configuration loading, and game execution.
 * @author LOTHFY ( LS )
 */
public class Main {
    /**
     * Main method that serves as the entry point for the Scratch Game application.
     * It parses command line arguments, loads the game configuration, and executes the game.
     * 
     * @param args Command line arguments:
     *             --config <path> : Path to the configuration file
     *             --betting-amount <amount> : Amount to bet in the game
     */
    public static void main(String[] args) {
        // Initialize variables for the config and betting amount
        String configFilePath = null;
        int betAmount = 0;

        // Parse the arguments
        for(int i = 0; i < args.length; i++) {
            if("--config".equals(args[i])) {
                if(i + 1 < args.length) {
                    configFilePath = args[i + 1];
                    i++; // Skip the next argument, as it is the file path
                } else {
                    System.out.println("Error: Missing value for --config");
                    return;
                }
            } else if("--betting-amount".equals(args[i])) {
                if(i + 1 < args.length) {
                    try {
                        betAmount = Integer.parseInt(args[i + 1]);
                        i++; // Skip the next argument, as it is the betting amount
                    } catch(NumberFormatException e) {
                        System.out.println("Error: Invalid bet amount format.");
                        return;
                    }
                } else {
                    System.out.println("Error: Missing value for --betting-amount");
                    return;
                }
            }
        }

        // Check if config file and bet amount are provided
        if(configFilePath == null) {
            System.out.println("Error: Config file is required");
            return;
        }
        if(betAmount <= 0) {
            System.out.println("Error: Invalid or missing bet amount");
            return;
        }

        // Load and parse the config file
        try {
            // Read the config file as a string
            FileReader reader = new FileReader(configFilePath);
            Gson gson = new Gson();

            // Parse the JSON content
            GameConfiguration config = gson.fromJson(reader, GameConfiguration.class);
            reader.close(); // Close the file reader

            Matrix matrix = generateMatrix(config);
            System.out.println(matrix);

            Matrix.EvaluationResult result = matrix.evaluateRules(config, betAmount);
            System.out.println("Reward : You win  => " + result.reward + " coins");

        } catch(IOException e) {
            System.out.println("Error: Failed to read the config file.");
        } catch(JsonSyntaxException e) {
            System.out.println("Error: Invalid JSON syntax in the config file.");
        }
    }
}

