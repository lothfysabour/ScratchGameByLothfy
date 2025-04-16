# Scratch Game

A Java-based scratch game implementation that simulates a slot machine game with configurable rules and winning combinations.

## Project Description

This project implements a scratch game where players can bet and win based on symbol combinations. The game features:
- Configurable game board (matrix)
- Different types of symbols (standard and bonus)
- Various winning combinations
- Probability-based symbol generation
- Reward calculation with multipliers

## Prerequisites

- Java 17 or higher
- Maven 3.8 or higher

## Dependencies

The project uses the following main dependencies:
- Gson 2.10.1 (for JSON parsing)

## Building the Project

To build the project, run the following command in the project root directory:

```bash
mvn clean package
```

## Running the Game

To run the game, use the following command:

```bash
java -jar target/scratchgame-1.0-SNAPSHOT.jar --config <path_to_config_file> --betting-amount <amount>
```

### Parameters:
- `--config`: Path to the JSON configuration file
- `--betting-amount`: Amount to bet in the game (must be a positive integer)

### Example:
```bash
java -jar target/scratchgame-1.0-SNAPSHOT.jar --config config.json --betting-amount 100
```

## Configuration File

The game requires a JSON configuration file that defines:
- Matrix dimensions (rows and columns)
- Symbols and their properties
- Winning combinations
- Probability settings

Example configuration structure:
```json
{
  "columns": 3,
  "rows": 3,
  "symbols": {
    "A": {
      "reward_multiplier": 5,
      "type": "standard"
    }
  },
  "probabilities": {
    "standard_symbols": [...],
    "bonus_symbols": {...}
  },
  "win_combinations": {
    "same_symbol_3_times": {
      "reward_multiplier": 1,
      "when": "same_symbols",
      "count": 3
    }
  }
}
```

## Project Structure

- `src/main/java/ls/assignment/scratchgame/`
  - `Main.java`: Entry point of the application
  - `Matrix.java`: Handles game board generation and evaluation
  - `GameConfiguration.java`: Configuration data structures
- `src/test/java/ls/assignment/scratchgame/test/`
  - `EvaluationTest.java`: Unit tests for game evaluation

## Author

- LOTHFY ( LS )

## License

This project is licensed under the MIT License. 