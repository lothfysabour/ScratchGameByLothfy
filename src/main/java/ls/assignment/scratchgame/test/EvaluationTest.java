package ls.assignment.scratchgame.test;

import com.google.gson.Gson;
import ls.assignment.scratchgame.GameConfiguration;
import ls.assignment.scratchgame.Matrix;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * EvaluationTest.java
 * This class contains unit tests for the Matrix class and its evaluation rules.
 * It uses a JSON configuration file to set up the game rules and symbols.
 * @author LOTHFY ( LS ) 
 **/
public class EvaluationTest {
  public static GameConfiguration config;

  /**
   * Main method that runs all the test cases for the Matrix evaluation rules.
   * Loads the configuration from a JSON file and executes all test methods.
   * @param args Command line arguments (not used)
   * @throws IOException If there is an error reading the configuration file
   */
  public static void main(String[] args) throws IOException {
    // Load configuration from the JSON file
    FileReader reader = new FileReader("config.json");
    Gson gson = new Gson();
    config = gson.fromJson(reader, GameConfiguration.class);
    reader.close();

    // Run the tests
    testSameSymbol3Times();
    testSameSymbol4Times();
    testSameSymbol5Times();
    testSameSymbol6Times();
    testSameSymbol7Times();
    testSameSymbol8Times();
    testSameSymbol9Times();
    testSameSymbolsHorizontally();
    testSameSymbolsVertically();
    testSameSymbolsDiagonallyLeftToRight();
    testSameSymbolsDiagonallyRightToLeft();
  }

  /**
   * Tests the same_symbol_3_times winning combination rule.
   * Verifies that the correct reward is calculated when 3 identical symbols appear.
   */
  public static void testSameSymbol3Times() {
    System.out.println("Running test: testSameSymbol3Times");

    String[][] matrix = {
            {"A", "A", "A"},
            {"B", "C", "D"},
            {"F", "E", "F"}
    };

    int bettingAmount = 100;

    Matrix matrixObj = new Matrix(matrix, 3, 3);
    System.out.println(matrixObj.toString());
    Matrix.EvaluationResult result = matrixObj.evaluateRules(config, bettingAmount);

    // Expected: A triggers same_symbol_3_times (x1) and same_symbols_horizontally (x2)
    double expectedReward = 100 * 5 * 1 * 2; // 1000.0
    Map<String, List<String>> expectedCombinations = Map.of(
            "A", Arrays.asList("same_symbol_3_times", "same_symbols_horizontally")
    );

    System.out.println("Calculated reward for same_symbol_3_times: " + result.reward);
    assert Math.abs(result.reward - expectedReward) < 0.01 : "Test failed! Expected reward: 1000, but got: " + result.reward;
    assert result.appliedWinningCombinations.equals(expectedCombinations) :
            "Test failed! Expected combinations: " + expectedCombinations + ", but got: " + result.appliedWinningCombinations;
    assert result.appliedBonusSymbol == null : "Test failed! Expected no bonus, but got: " + result.appliedBonusSymbol;

    System.out.println("Test passed for testSameSymbol3Times!");
  }

  /**
   * Tests the same_symbol_4_times winning combination rule.
   * Verifies that the correct reward is calculated when 4 identical symbols appear.
   */
  public static void testSameSymbol4Times() {
    System.out.println("Running test: testSameSymbol4Times");

    String[][] matrix = {
            {"A", "A", "A"},
            {"A", "C", "D"},
            {"F", "E", "F"}
    };

    int bettingAmount = 100;

    Matrix matrixObj = new Matrix(matrix, 3, 3);
    System.out.println(matrixObj.toString());
    Matrix.EvaluationResult result = matrixObj.evaluateRules(config, bettingAmount);

    // Expected: A triggers same_symbol_4_times (x1.5) and same_symbols_horizontally (x2)
    double expectedReward = 100 * 5 * 1.5 * 2; // 1500.0
    Map<String, List<String>> expectedCombinations = Map.of(
            "A", Arrays.asList("same_symbol_4_times", "same_symbols_horizontally")
    );

    System.out.println("Calculated reward for same_symbol_4_times: " + result.reward);
    assert Math.abs(result.reward - expectedReward) < 0.01 : "Test failed! Expected reward: 1500, but got: " + result.reward;
    assert result.appliedWinningCombinations.equals(expectedCombinations) :
            "Test failed! Expected combinations: " + expectedCombinations + ", but got: " + result.appliedWinningCombinations;
    assert result.appliedBonusSymbol == null : "Test failed! Expected no bonus, but got: " + result.appliedBonusSymbol;

    System.out.println("Test passed for testSameSymbol4Times!");
  }

  /**
   * Tests the same_symbol_5_times winning combination rule.
   * Verifies that the correct reward is calculated when 5 identical symbols appear.
   */
  public static void testSameSymbol5Times() {
    System.out.println("Running test: testSameSymbol5Times");

    String[][] matrix = {
            {"A", "A", "A"},
            {"A", "A", "D"},
            {"F", "E", "F"}
    };

    int bettingAmount = 100;

    Matrix matrixObj = new Matrix(matrix, 3, 3);
    System.out.println(matrixObj.toString());
    Matrix.EvaluationResult result = matrixObj.evaluateRules(config, bettingAmount);

    // Expected: A triggers same_symbol_5_times (x2) and same_symbols_horizontally (x2)
    double expectedReward = 100 * 5 * 2 * 2; // 2000.0
    Map<String, List<String>> expectedCombinations = Map.of(
            "A", Arrays.asList("same_symbol_5_times", "same_symbols_horizontally")
    );

    System.out.println("Calculated reward for same_symbol_5_times: " + result.reward);
    assert Math.abs(result.reward - expectedReward) < 0.01 : "Test failed! Expected reward: 2000, but got: " + result.reward;
    assert result.appliedWinningCombinations.equals(expectedCombinations) :
            "Test failed! Expected combinations: " + expectedCombinations + ", but got: " + result.appliedWinningCombinations;
    assert result.appliedBonusSymbol == null : "Test failed! Expected no bonus, but got: " + result.appliedBonusSymbol;

    System.out.println("Test passed for testSameSymbol5Times!");
  }

  /**
   * Tests the same_symbol_6_times winning combination rule.
   * Verifies that the correct reward is calculated when 6 identical symbols appear.
   */
  public static void testSameSymbol6Times() {
    System.out.println("Running test: testSameSymbol6Times");

    String[][] matrix = {
            {"A", "A", "A"},
            {"A", "A", "A"},
            {"F", "E", "F"}
    };

    int bettingAmount = 100;

    Matrix matrixObj = new Matrix(matrix, 3, 3);
    System.out.println(matrixObj.toString());
    Matrix.EvaluationResult result = matrixObj.evaluateRules(config, bettingAmount);

    // Expected: A triggers same_symbol_6_times (x3) and two same_symbols_horizontally (x2 * x2)
    double expectedReward = 100 * 5 * 3 * 2 * 2; // 6000.0
    Map<String, List<String>> expectedCombinations = Map.of(
            "A", Arrays.asList("same_symbol_6_times", "same_symbols_horizontally")
    );

    System.out.println("Calculated reward for same_symbol_6_times: " + result.reward);
    assert Math.abs(result.reward - expectedReward) < 0.01 : "Test failed! Expected reward: 6000, but got: " + result.reward;
    assert result.appliedWinningCombinations.equals(expectedCombinations) :
            "Test failed! Expected combinations: " + expectedCombinations + ", but got: " + result.appliedWinningCombinations;
    assert result.appliedBonusSymbol == null : "Test failed! Expected no bonus, but got: " + result.appliedBonusSymbol;

    System.out.println("Test passed for testSameSymbol6Times!");
  }

  /**
   * Tests the same_symbol_7_times winning combination rule.
   * Verifies that the correct reward is calculated when 7 identical symbols appear.
   */
  public static void testSameSymbol7Times() {
    System.out.println("Running test: testSameSymbol7Times");

    String[][] matrix = {
            {"A", "A", "A"},
            {"A", "A", "A"},
            {"A", "F", "E"}
    };

    int bettingAmount = 100;

    Matrix matrixObj = new Matrix(matrix, 3, 3);
    System.out.println(matrixObj.toString());
    Matrix.EvaluationResult result = matrixObj.evaluateRules(config, bettingAmount);

    // Expected: A triggers same_symbol_7_times (x5), two same_symbols_horizontally (x2 * x2), same_symbols_vertically (x2)
    double expectedReward = 100 * 5 * 5 * 2 * 2 * 2; // 10000.0
    Map<String, List<String>> expectedCombinations = Map.of(
            "A", Arrays.asList("same_symbol_7_times", "same_symbols_horizontally", "same_symbols_vertically")
    );

    System.out.println("Calculated reward for same_symbol_7_times: " + result.reward);
    assert Math.abs(result.reward - expectedReward) < 0.01 : "Test failed! Expected reward: 10000, but got: " + result.reward;
    assert result.appliedWinningCombinations.equals(expectedCombinations) :
            "Test failed! Expected combinations: " + expectedCombinations + ", but got: " + result.appliedWinningCombinations;
    assert result.appliedBonusSymbol == null : "Test failed! Expected no bonus, but got: " + result.appliedBonusSymbol;

    System.out.println("Test passed for testSameSymbol7Times!");
  }

  /**
   * Tests the same_symbol_8_times winning combination rule.
   * Verifies that the correct reward is calculated when 8 identical symbols appear.
   */
  public static void testSameSymbol8Times() {
    System.out.println("Running test: testSameSymbol8Times");

    String[][] matrix = {
            {"A", "A", "A"},
            {"A", "A", "A"},
            {"A", "A", "F"}
    };

    int bettingAmount = 100;

    Matrix matrixObj = new Matrix(matrix, 3, 3);
    System.out.println(matrixObj.toString());
    Matrix.EvaluationResult result = matrixObj.evaluateRules(config, bettingAmount);

    // Expected: A triggers same_symbol_8_times (x10), two same_symbols_horizontally (x2 * x2), two same_symbols_vertically (x2 * x2)
    double expectedReward = 100 * 5 * 10 * 2 * 2 * 2 * 2; // 40000.0
    Map<String, List<String>> expectedCombinations = Map.of(
            "A", Arrays.asList("same_symbol_8_times", "same_symbols_horizontally", "same_symbols_vertically")
    );

    System.out.println("Calculated reward for same_symbol_8_times: " + result.reward);
    assert Math.abs(result.reward - expectedReward) < 0.01 : "Test failed! Expected reward: 40000, but got: " + result.reward;
    assert result.appliedWinningCombinations.equals(expectedCombinations) :
            "Test failed! Expected combinations: " + expectedCombinations + ", but got: " + result.appliedWinningCombinations;
    assert result.appliedBonusSymbol == null : "Test failed! Expected no bonus, but got: " + result.appliedBonusSymbol;

    System.out.println("Test passed for testSameSymbol8Times!");
  }

  /**
   * Tests the same_symbol_9_times winning combination rule.
   * Verifies that the correct reward is calculated when 9 identical symbols appear.
   */
  public static void testSameSymbol9Times() {
    System.out.println("Running test: testSameSymbol9Times");

    String[][] matrix = {
            {"A", "A", "A"},
            {"A", "A", "A"},
            {"A", "A", "A"}
    };

    int bettingAmount = 100;

    Matrix matrixObj = new Matrix(matrix, 3, 3);
    System.out.println(matrixObj.toString());
    Matrix.EvaluationResult result = matrixObj.evaluateRules(config, bettingAmount);

    // Expected: A triggers same_symbol_9_times (x20), three same_symbols_horizontally (x2 * x2 * x2),
    // three same_symbols_vertically (x2 * x2 * x2), two diagonals (x5 * x5)
    double expectedReward = 100 * 5 * 20 * 2 * 2 * 2 * 2 * 2 * 2 * 5 * 5; // 800000.0
    Map<String, List<String>> expectedCombinations = Map.of(
            "A", Arrays.asList("same_symbol_9_times", "same_symbols_horizontally", "same_symbols_vertically",
                    "same_symbols_diagonally_left_to_right", "same_symbols_diagonally_right_to_left")
    );

    System.out.println("Calculated reward for same_symbol_9_times: " + result.reward);
    assert Math.abs(result.reward - expectedReward) < 0.01 : "Test failed! Expected reward: 800000, but got: " + result.reward;
    assert result.appliedWinningCombinations.equals(expectedCombinations) :
            "Test failed! Expected combinations: " + expectedCombinations + ", but got: " + result.appliedWinningCombinations;
    assert result.appliedBonusSymbol == null : "Test failed! Expected no bonus, but got: " + result.appliedBonusSymbol;

    System.out.println("Test passed for testSameSymbol9Times!");
  }

  /**
   * Tests the same_symbols_horizontally winning combination rule.
   * Verifies that the correct reward is calculated when identical symbols appear in a horizontal line.
   */
  public static void testSameSymbolsHorizontally() {
    System.out.println("Running test: testSameSymbolsHorizontally");

    String[][] matrix = {
            {"A", "A", "A"},
            {"B", "B", "B"},
            {"C", "C", "C"}
    };

    int bettingAmount = 100;

    Matrix matrixObj = new Matrix(matrix, 3, 3);
    System.out.println(matrixObj.toString());
    Matrix.EvaluationResult result = matrixObj.evaluateRules(config, bettingAmount);

    // Expected:
    // A: same_symbol_3_times (x1), same_symbols_horizontally (x2) = 100 * 5 * 1 * 2 = 1000
    // B: same_symbol_3_times (x1), same_symbols_horizontally (x2) = 100 * 3 * 1 * 2 = 600
    // C: same_symbol_3_times (x1), same_symbols_horizontally (x2) = 100 * 2.5 * 1 * 2 = 500
    double expectedReward = 1000 + 600 + 500; // 2100.0
    Map<String, List<String>> expectedCombinations = Map.of(
            "A", Arrays.asList("same_symbol_3_times", "same_symbols_horizontally"),
            "B", Arrays.asList("same_symbol_3_times", "same_symbols_horizontally"),
            "C", Arrays.asList("same_symbol_3_times", "same_symbols_horizontally")
    );

    System.out.println("Calculated reward for same_symbols_horizontally: " + result.reward);
    assert Math.abs(result.reward - expectedReward) < 0.01 : "Test failed! Expected reward: 2100, but got: " + result.reward;
    assert result.appliedWinningCombinations.equals(expectedCombinations) :
            "Test failed! Expected combinations: " + expectedCombinations + ", but got: " + result.appliedWinningCombinations;
    assert result.appliedBonusSymbol == null : "Test failed! Expected no bonus, but got: " + result.appliedBonusSymbol;

    System.out.println("Test passed for testSameSymbolsHorizontally!");
  }

  /**
   * Tests the same_symbols_vertically winning combination rule.
   * Verifies that the correct reward is calculated when identical symbols appear in a vertical line.
   */
  public static void testSameSymbolsVertically() {
    System.out.println("Running test: testSameSymbolsVertically");

    String[][] matrix = {
            {"A", "B", "C"},
            {"A", "B", "C"},
            {"A", "B", "C"}
    };

    int bettingAmount = 100;

    Matrix matrixObj = new Matrix(matrix, 3, 3);
    System.out.println(matrixObj.toString());
    Matrix.EvaluationResult result = matrixObj.evaluateRules(config, bettingAmount);

    // Expected:
    // A: same_symbol_3_times (x1), same_symbols_vertically (x2) = 100 * 5 * 1 * 2 = 1000
    // B: same_symbol_3_times (x1), same_symbols_vertically (x2) = 100 * 3 * 1 * 2 = 600
    // C: same_symbol_3_times (x1), same_symbols_vertically (x2) = 100 * 2.5 * 1 * 2 = 500
    double expectedReward = 1000 + 600 + 500; // 2100.0
    Map<String, List<String>> expectedCombinations = Map.of(
            "A", Arrays.asList("same_symbol_3_times", "same_symbols_vertically"),
            "B", Arrays.asList("same_symbol_3_times", "same_symbols_vertically"),
            "C", Arrays.asList("same_symbol_3_times", "same_symbols_vertically")
    );

    System.out.println("Calculated reward for same_symbols_vertically: " + result.reward);
    assert Math.abs(result.reward - expectedReward) < 0.01 : "Test failed! Expected reward: 2100, but got: " + result.reward;
    assert result.appliedWinningCombinations.equals(expectedCombinations) :
            "Test failed! Expected combinations: " + expectedCombinations + ", but got: " + result.appliedWinningCombinations;
    assert result.appliedBonusSymbol == null : "Test failed! Expected no bonus, but got: " + result.appliedBonusSymbol;

    System.out.println("Test passed for testSameSymbolsVertically!");
  }

  /**
   * Tests the same_symbols_diagonally_left_to_right winning combination rule.
   * Verifies that the correct reward is calculated when identical symbols appear in a diagonal line from top-left to bottom-right.
   */
  public static void testSameSymbolsDiagonallyLeftToRight() {
    System.out.println("Running test: testSameSymbolsDiagonallyLeftToRight");

    String[][] matrix = {
            {"A", "B", "C"},
            {"D", "A", "E"},
            {"F", "E", "A"}
    };

    int bettingAmount = 100;

    Matrix matrixObj = new Matrix(matrix, 3, 3);
    System.out.println(matrixObj.toString());
    Matrix.EvaluationResult result = matrixObj.evaluateRules(config, bettingAmount);

    // Expected: A triggers same_symbols_diagonally_left_to_right (x5)
    double expectedReward = 100 * 5 * 5; // 2500.0
    Map<String, List<String>> expectedCombinations = Map.of(
            "A", Collections.singletonList("same_symbols_diagonally_left_to_right")
    );

    System.out.println("Calculated reward for same_symbols_diagonally_left_to_right: " + result.reward);
    assert Math.abs(result.reward - expectedReward) < 0.01 : "Test failed! Expected reward: 2500, but got: " + result.reward;
    assert result.appliedWinningCombinations.equals(expectedCombinations) :
            "Test failed! Expected combinations: " + expectedCombinations + ", but got: " + result.appliedWinningCombinations;
    assert result.appliedBonusSymbol == null : "Test failed! Expected no bonus, but got: " + result.appliedBonusSymbol;

    System.out.println("Test passed for testSameSymbolsDiagonallyLeftToRight!");
  }

  /**
   * Tests the same_symbols_diagonally_right_to_left winning combination rule.
   * Verifies that the correct reward is calculated when identical symbols appear in a diagonal line from top-right to bottom-left.
   */
  public static void testSameSymbolsDiagonallyRightToLeft() {
    System.out.println("Running test: testSameSymbolsDiagonallyRightToLeft");

    String[][] matrix = {
            {"C", "B", "A"},
            {"D", "A", "E"},
            {"A", "E", "F"}
    };

    int bettingAmount = 100;

    Matrix matrixObj = new Matrix(matrix, 3, 3);
    System.out.println(matrixObj.toString());
    Matrix.EvaluationResult result = matrixObj.evaluateRules(config, bettingAmount);

    // Expected: A triggers same_symbols_diagonally_right_to_left (x5)
    double expectedReward = 100 * 5 * 5; // 2500.0
    Map<String, List<String>> expectedCombinations = Map.of(
            "A", Collections.singletonList("same_symbols_diagonally_right_to_left")
    );

    System.out.println("Calculated reward for same_symbols_diagonally_right_to_left: " + result.reward);
    assert Math.abs(result.reward - expectedReward) < 0.01 : "Test failed! Expected reward: 2500, but got: " + result.reward;
    assert result.appliedWinningCombinations.equals(expectedCombinations) :
            "Test failed! Expected combinations: " + expectedCombinations + ", but got: " + result.appliedWinningCombinations;
    assert result.appliedBonusSymbol == null : "Test failed! Expected no bonus, but got: " + result.appliedBonusSymbol;

    System.out.println("Test passed for testSameSymbolsDiagonallyRightToLeft!");
  }
}
