package ls.assignment.scratchgame;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;

/**
 * GameConfiguration class representing the configuration of the Scratch Game.
 * Contains all the game settings, symbols, probabilities, and winning combinations.
 * @author LOTHFY ( LS )
 */
public class GameConfiguration {
  private int columns;
  private int rows;
  private Map<String, Symbol> symbols;
  private Probabilities probabilities;
  @SerializedName("win_combinations")
  private Map<String, WinCombination> winCombinations;

  /**
   * Gets the number of columns in the game matrix.
   * @return The number of columns
   */
  public int getColumns() {
    return columns;
  }

  /**
   * Sets the number of columns in the game matrix.
   * @param columns The number of columns to set
   */
  public void setColumns(int columns) {
    this.columns = columns;
  }

  /**
   * Gets the number of rows in the game matrix.
   * @return The number of rows
   */
  public int getRows() {
    return rows;
  }

  /**
   * Sets the number of rows in the game matrix.
   * @param rows The number of rows to set
   */
  public void setRows(int rows) {
    this.rows = rows;
  }

  /**
   * Gets the map of game symbols and their properties.
   * @return The map of symbols
   */
  public Map<String, Symbol> getSymbols() {
    return symbols;
  }

  /**
   * Sets the map of game symbols and their properties.
   * @param symbols The map of symbols to set
   */
  public void setSymbols(Map<String, Symbol> symbols) {
    this.symbols = symbols;
  }

  /**
   * Gets the probabilities configuration for the game.
   * @return The probabilities configuration
   */
  public Probabilities getProbabilities() {
    return probabilities;
  }

  /**
   * Sets the probabilities configuration for the game.
   * @param probabilities The probabilities configuration to set
   */
  public void setProbabilities(Probabilities probabilities) {
    this.probabilities = probabilities;
  }

  /**
   * Gets the map of winning combinations and their properties.
   * @return The map of winning combinations
   */
  public Map<String, WinCombination> getWinCombinations() {
    return winCombinations;
  }

  /**
   * Sets the map of winning combinations and their properties.
   * @param winCombinations The map of winning combinations to set
   */
  public void setWinCombinations(Map<String, WinCombination> winCombinations) {
    this.winCombinations = winCombinations;
  }

  /**
   * Returns a string representation of the game configuration.
   * @return A string containing the configuration details
   */
  @Override
  public String toString() {
    return "Config{" +
            "columns=" + columns +
            ", rows=" + rows +
            ", symbols=" + symbols +
            ", probabilities=" + probabilities +
            ", winCombinations=" + winCombinations +
            '}';
  }
}

/**
 * Symbol class representing a game symbol and its properties.
 */
class Symbol {
  @SerializedName("reward_multiplier")
  private Double rewardMultiplier;
  private String type;
  private String impact;
  private Integer extra;

  /**
   * Gets the reward multiplier for this symbol.
   * @return The reward multiplier
   */
  public Double getRewardMultiplier() {
    return rewardMultiplier;
  }

  /**
   * Sets the reward multiplier for this symbol.
   * @param rewardMultiplier The reward multiplier to set
   */
  public void setRewardMultiplier(Double rewardMultiplier) {
    this.rewardMultiplier = rewardMultiplier;
  }

  /**
   * Gets the type of this symbol (standard or bonus).
   * @return The symbol type
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the type of this symbol.
   * @param type The symbol type to set
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Gets the impact type of this symbol.
   * @return The impact type
   */
  public String getImpact() {
    return impact;
  }

  /**
   * Sets the impact type of this symbol.
   * @param impact The impact type to set
   */
  public void setImpact(String impact) {
    this.impact = impact;
  }

  /**
   * Gets the extra value for this symbol.
   * @return The extra value
   */
  public Integer getExtra() {
    return extra;
  }

  /**
   * Sets the extra value for this symbol.
   * @param extra The extra value to set
   */
  public void setExtra(Integer extra) {
    this.extra = extra;
  }

  /**
   * Returns a string representation of the symbol.
   * @return A string containing the symbol details
   */
  @Override
  public String toString() {
    return "Symbol{" +
            "rewardMultiplier=" + rewardMultiplier +
            ", type='" + type + '\'' +
            ", impact='" + impact + '\'' +
            ", extra=" + extra +
            '}';
  }
}

/**
 * Probabilities class representing the probability configuration for the game.
 */
class Probabilities {
  @SerializedName("standard_symbols")
  private List<StandardSymbol> standardSymbols;
  @SerializedName("bonus_symbols")
  private BonusSymbols bonusSymbols;

  /**
   * Gets the list of standard symbols and their probabilities.
   * @return The list of standard symbols
   */
  public List<StandardSymbol> getStandardSymbols() {
    return standardSymbols;
  }

  /**
   * Sets the list of standard symbols and their probabilities.
   * @param standardSymbols The list of standard symbols to set
   */
  public void setStandardSymbols(List<StandardSymbol> standardSymbols) {
    this.standardSymbols = standardSymbols;
  }

  /**
   * Gets the bonus symbols configuration.
   * @return The bonus symbols configuration
   */
  public BonusSymbols getBonusSymbols() {
    return bonusSymbols;
  }

  /**
   * Sets the bonus symbols configuration.
   * @param bonusSymbols The bonus symbols configuration to set
   */
  public void setBonusSymbols(BonusSymbols bonusSymbols) {
    this.bonusSymbols = bonusSymbols;
  }

  /**
   * Returns a string representation of the probabilities.
   * @return A string containing the probability details
   */
  @Override
  public String toString() {
    return "Probabilities{" +
            "standardSymbols=" + standardSymbols +
            ", bonusSymbols=" + bonusSymbols +
            '}';
  }
}

/**
 * StandardSymbol class representing a standard symbol in the game.
 */
class StandardSymbol {
  private int column;
  private int row;
  private Map<String, Integer> symbols;

  /**
   * Gets the column position of this symbol.
   * @return The column position
   */
  public int getColumn() {
    return column;
  }

  /**
   * Sets the column position of this symbol.
   * @param column The column position to set
   */
  public void setColumn(int column) {
    this.column = column;
  }

  /**
   * Gets the row position of this symbol.
   * @return The row position
   */
  public int getRow() {
    return row;
  }

  /**
   * Sets the row position of this symbol.
   * @param row The row position to set
   */
  public void setRow(int row) {
    this.row = row;
  }

  /**
   * Gets the map of symbols and their weights for this position.
   * @return The map of symbols and weights
   */
  public Map<String, Integer> getSymbols() {
    return symbols;
  }

  /**
   * Sets the map of symbols and their weights for this position.
   * @param symbols The map of symbols and weights to set
   */
  public void setSymbols(Map<String, Integer> symbols) {
    this.symbols = symbols;
  }

  /**
   * Returns a string representation of the standard symbol.
   * @return A string containing the symbol details
   */
  @Override
  public String toString() {
    return "StandardSymbol{" +
            "column=" + column +
            ", row=" + row +
            ", symbols=" + symbols +
            '}';
  }
}

/**
 * BonusSymbols class representing the bonus symbols configuration.
 */
class BonusSymbols {
  private Map<String, Integer> symbols;

  /**
   * Gets the map of bonus symbols and their weights.
   * @return The map of bonus symbols and weights
   */
  public Map<String, Integer> getSymbols() {
    return symbols;
  }

  /**
   * Sets the map of bonus symbols and their weights.
   * @param symbols The map of bonus symbols and weights to set
   */
  public void setSymbols(Map<String, Integer> symbols) {
    this.symbols = symbols;
  }

  /**
   * Returns a string representation of the bonus symbols.
   * @return A string containing the bonus symbols details
   */
  @Override
  public String toString() {
    return "BonusSymbols{" +
            "symbols=" + symbols +
            '}';
  }
}

/**
 * WinCombination class representing a winning combination in the game.
 */
class WinCombination {
  @SerializedName("reward_multiplier")
  private Double rewardMultiplier;
  private String when;
  private Integer count;
  private String group;
  @SerializedName("covered_areas")
  private List<List<String>> coveredAreas;

  /**
   * Gets the reward multiplier for this winning combination.
   * @return The reward multiplier
   */
  public Double getRewardMultiplier() {
    return rewardMultiplier;
  }

  /**
   * Sets the reward multiplier for this winning combination.
   * @param rewardMultiplier The reward multiplier to set
   */
  public void setRewardMultiplier(Double rewardMultiplier) {
    this.rewardMultiplier = rewardMultiplier;
  }

  /**
   * Gets the condition for when this combination applies.
   * @return The condition string
   */
  public String getWhen() {
    return when;
  }

  /**
   * Sets the condition for when this combination applies.
   * @param when The condition string to set
   */
  public void setWhen(String when) {
    this.when = when;
  }

  /**
   * Gets the count required for this combination.
   * @return The required count
   */
  public Integer getCount() {
    return count;
  }

  /**
   * Sets the count required for this combination.
   * @param count The required count to set
   */
  public void setCount(Integer count) {
    this.count = count;
  }

  /**
   * Gets the group this combination belongs to.
   * @return The group name
   */
  public String getGroup() {
    return group;
  }

  /**
   * Sets the group this combination belongs to.
   * @param group The group name to set
   */
  public void setGroup(String group) {
    this.group = group;
  }

  /**
   * Gets the covered areas for this combination.
   * @return The list of covered areas
   */
  public List<List<String>> getCoveredAreas() {
    return coveredAreas;
  }

  /**
   * Sets the covered areas for this combination.
   * @param coveredAreas The list of covered areas to set
   */
  public void setCoveredAreas(List<List<String>> coveredAreas) {
    this.coveredAreas = coveredAreas;
  }

  /**
   * Returns a string representation of the winning combination.
   * @return A string containing the combination details
   */
  @Override
  public String toString() {
    return "WinCombination{" +
            "rewardMultiplier=" + rewardMultiplier +
            ", when='" + when + '\'' +
            ", count=" + count +
            ", group='" + group + '\'' +
            ", coveredAreas=" + coveredAreas +
            '}';
  }
}
