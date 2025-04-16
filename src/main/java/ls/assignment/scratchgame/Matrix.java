package ls.assignment.scratchgame;

import java.util.*;

/**
 * Matrix class representing the game board for the Scratch Game.
 * This class handles the generation and evaluation of the game matrix.
 * @author LOTHFY ( LS )
 */
public class Matrix {
  private final String[][] matrix;
  private final int rows;
  private final int columns;
  private final Map<String, List<String>> appliedWinningCombinations;
  private String appliedBonusSymbol;

  /**
   * Constructs a new Matrix with the specified values.
   * @param matrix The 2D array representing the game board
   * @param rows The number of rows in the matrix
   * @param columns The number of columns in the matrix
   */
  public Matrix(String[][] matrix, int rows, int columns) {
    this.matrix = matrix;
    this.rows = rows;
    this.columns = columns;
    this.appliedWinningCombinations = new HashMap<>();
    this.appliedBonusSymbol = null;
  }

  /**
   * Private constructor for creating an empty matrix.
   * @param rows The number of rows in the matrix
   * @param columns The number of columns in the matrix
   */
  private Matrix(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;
    this.matrix = new String[rows][columns];
    this.appliedWinningCombinations = new HashMap<>();
    this.appliedBonusSymbol = null;
  }

  /**
   * Generates a new matrix based on the game configuration.
   * @param config The game configuration containing probabilities and rules
   * @return A new Matrix instance populated with symbols according to the configuration
   */
  public static Matrix generateMatrix(GameConfiguration config) {
    int rows = config.getRows();
    int columns = config.getColumns();
    Matrix matrix = new Matrix(rows, columns);

    // Get probabilities from config
    Probabilities probabilities = config.getProbabilities();
    List<StandardSymbol> standardSymbols = probabilities.getStandardSymbols();
    BonusSymbols bonusSymbols = probabilities.getBonusSymbols();

    // Populate matrix
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < columns; col++) {
        // Find probabilities for this cell
        int finalRow = row;
        int finalCol = col;
        Optional<StandardSymbol> cellProb = standardSymbols.stream()
                .filter(s -> s.getRow() == finalRow && s.getColumn() == finalCol)
                .findFirst();

        if (!cellProb.isPresent()) {
          throw new IllegalStateException("No probabilities defined for cell (" + row + "," + col + ")");
        }

        Map<String, Integer> combinedProbs = new HashMap<>(cellProb.get().getSymbols());
        if (bonusSymbols != null && !bonusSymbols.getSymbols().isEmpty()) {
          combinedProbs.putAll(bonusSymbols.getSymbols());
        }

        matrix.matrix[row][col] = selectSymbol(combinedProbs);
      }
    }

    return matrix;
  }
  
  /**
   * Selects a symbol based on the provided weights.
   * @param symbolWeights A map of symbols to their probability weights
   * @return The selected symbol
   * @throws IllegalArgumentException if the total probability weight is zero
   */
  private static String selectSymbol(Map<String, Integer> symbolWeights) {
    int totalWeight = symbolWeights.values().stream().mapToInt(Integer::intValue).sum();
    if (totalWeight == 0) {
      throw new IllegalArgumentException("Total probability weight is zero");
    }
    Random rand = new Random();
    int randomValue = rand.nextInt(totalWeight);
    int currentWeight = 0;

    for (Map.Entry<String, Integer> entry : symbolWeights.entrySet()) {
      currentWeight += entry.getValue();
      if (randomValue < currentWeight) {
        return entry.getKey();
      }
    }
    return symbolWeights.keySet().iterator().next();
  }

  /**
   * Inner class representing the result of matrix evaluation.
   * Contains the calculated reward and applied winning combinations.
   */
  public static class EvaluationResult {
    public double reward;
    public Map<String, List<String>> appliedWinningCombinations;
    public String appliedBonusSymbol;

    /**
     * Constructs a new EvaluationResult.
     * @param reward The calculated reward amount
     * @param appliedWinningCombinations The winning combinations that were applied
     * @param appliedBonusSymbol The bonus symbol that was applied, if any
     */
    public EvaluationResult(double reward, Map<String, List<String>> appliedWinningCombinations, String appliedBonusSymbol) {
      this.reward = reward;
      this.appliedWinningCombinations = appliedWinningCombinations;
      this.appliedBonusSymbol = appliedBonusSymbol;
    }
  }

  /**
   * Evaluates the matrix according to the game rules and configuration.
   * @param configuration The game configuration containing winning rules and symbol properties
   * @param bettingAmount The amount bet by the player
   * @return An EvaluationResult containing the calculated reward and applied combinations
   */
  public EvaluationResult evaluateRules(GameConfiguration configuration, int bettingAmount) {
    Map<String, List<String>> appliedCombinations = new HashMap<>();
    double totalReward = 0.0;
    String appliedBonus = null;

    // Count symbols
    Map<String, Integer> symbolCounts = new HashMap<>();
    for (String[] row : matrix) {
      for (String symbol : row) {
        symbolCounts.merge(symbol, 1, Integer::sum);
      }
    }

    // Evaluate same_symbols combinations
    Map<String, String> bestSameSymbolCombo = new HashMap<>();
    for (Map.Entry<String, WinCombination> entry : configuration.getWinCombinations().entrySet()) {
      String comboName = entry.getKey();
      WinCombination combo = entry.getValue();
      if ("same_symbols".equals(combo.getWhen())) {
        int requiredCount = combo.getCount();
        for (Map.Entry<String, Integer> symbolEntry : symbolCounts.entrySet()) {
          String symbol = symbolEntry.getKey();
          int count = symbolEntry.getValue();
          if (count >= requiredCount && "standard".equals(configuration.getSymbols().get(symbol).getType())) {
            bestSameSymbolCombo.compute(symbol, (k, v) -> {
              if (v == null) return comboName;
              WinCombination existing = configuration.getWinCombinations().get(v);
              return combo.getCount() > existing.getCount() ? comboName : v;
            });
          }
        }
      }
    }

    Map<String, List<String>> linearCombos = new HashMap<>();
    for (Map.Entry<String, WinCombination> entry : configuration.getWinCombinations().entrySet()) {
      String comboName = entry.getKey();
      WinCombination combo = entry.getValue();
      if ("linear_symbols".equals(combo.getWhen()) && combo.getCoveredAreas() != null) {
        for (List<String> area : combo.getCoveredAreas()) {
          String firstSymbol = null;
          boolean allSame = true;
          for (String coord : area) {
            String[] parts = coord.split(":");
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);
            if (row >= rows || col >= columns) {
              allSame = false;
              break;
            }
            String symbol = matrix[row][col];
            if (!configuration.getSymbols().containsKey(symbol) ||
                    !"standard".equals(configuration.getSymbols().get(symbol).getType())) {
              allSame = false;
              break;
            }
            if (firstSymbol == null) {
              firstSymbol = symbol;
            } else if (!firstSymbol.equals(symbol)) {
              allSame = false;
              break;
            }
          }
          if (allSame && firstSymbol != null) {
            linearCombos.computeIfAbsent(firstSymbol, k -> new ArrayList<>()).add(comboName);
          }
        }
      }
    }

    Set<String> winningSymbols = new HashSet<>();
    winningSymbols.addAll(bestSameSymbolCombo.keySet());
    winningSymbols.addAll(linearCombos.keySet());

    for (String symbol : winningSymbols) {
      double symbolReward = bettingAmount * configuration.getSymbols().get(symbol).getRewardMultiplier();
      List<String> combosForSymbol = new ArrayList<>();

      // Apply same_symbols combination
      String sameCombo = bestSameSymbolCombo.get(symbol);
      if (sameCombo != null) {
        symbolReward *= configuration.getWinCombinations().get(sameCombo).getRewardMultiplier();
        combosForSymbol.add(sameCombo);
      }

      List<String> symbolLinearCombos = linearCombos.getOrDefault(symbol, Collections.emptyList());
      for (String linearCombo : symbolLinearCombos) {
        symbolReward *= configuration.getWinCombinations().get(linearCombo).getRewardMultiplier();
        combosForSymbol.add(linearCombo);
      }

      if (!combosForSymbol.isEmpty()) {
        totalReward += symbolReward;
        appliedCombinations.put(symbol, combosForSymbol);
      }
    }

    for (String[] row : matrix) {
      for (String symbol : row) {
        if (configuration.getSymbols().containsKey(symbol) &&
                "bonus".equals(configuration.getSymbols().get(symbol).getType()) &&
                totalReward > 0) {
          appliedBonus = symbol;
          Symbol bonusSymbol = configuration.getSymbols().get(symbol);
          if ("multiply_reward".equals(bonusSymbol.getImpact())) {
            totalReward *= bonusSymbol.getRewardMultiplier();
          } else if ("extra_bonus".equals(bonusSymbol.getImpact())) {
            totalReward += bonusSymbol.getExtra();
          }
          break;
        }
      }
      if (appliedBonus != null) break;
    }

    this.appliedWinningCombinations.putAll(appliedCombinations);
    this.appliedBonusSymbol = appliedBonus;
    return new EvaluationResult(totalReward, new HashMap<>(appliedCombinations), appliedBonus);
  }

  /**
   * Returns the game matrix.
   * @return The 2D array representing the game board
   */
  public String[][] getMatrix() {
    return matrix;
  }

  /**
   * Returns the applied winning combinations.
   * @return A map of symbols to their winning combinations
   */
  public Map<String, List<String>> getAppliedWinningCombinations() {
    return appliedWinningCombinations;
  }

  /**
   * Returns the applied bonus symbol.
   * @return The bonus symbol that was applied, or null if none
   */
  public String getAppliedBonusSymbol() {
    return appliedBonusSymbol;
  }

  /**
   * Returns a string representation of the matrix.
   * @return A string showing the matrix contents
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (String[] row : matrix) {
      sb.append(Arrays.toString(row)).append("\n");
    }
    return sb.toString();
  }
}
