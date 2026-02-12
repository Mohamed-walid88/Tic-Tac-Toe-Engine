# 🎮 Tic Tac Toe – Java Engine

A modular and extensible Tic Tac Toe engine built in **Java**, featuring an AI opponent powered by the **Minimax algorithm** with multiple difficulty levels.

This project focuses on clean architecture, game state management, and AI decision-making logic.

---

## 🚀 Features

- ♟️ Complete Tic Tac Toe game logic
- 🤖 AI opponent with Minimax algorithm
- 🎯 Multiple difficulty levels:
  - Easy (Random moves)
  - Medium (Mixed strategy)
  - Hard (Optimal Minimax – Unbeatable)
- 🧠 Efficient state evaluation
- 🗂️ Modular class design
- 🔄 Game state tracking

---

## 🏗️ Project Architecture

The engine is built using a structured, object-oriented approach:

- **GameState** → Represents the current state of the game (Win, Tie, Ongoing).
- **StateChecker** → Evaluates the board and determines the game result.
- **ComputerPlayer** → Implements AI logic using Minimax.
- **DIFFICULTY** → Enum defining AI difficulty levels.

---

## 📂 Project Structure

```text
TicTacToe-Java/
│
├── ComputerPlayer.java # AI logic (Minimax + difficulty)
├── GameState.java # Game state enum
├── StateChecker.java # Board evaluation logic
├── DIFFICULTY.java # Difficulty levels enum
```
---

## 🧠 How the AI Works

The AI uses the **Minimax algorithm**, a recursive decision-making algorithm commonly used in two-player zero-sum games.

### Evaluation Rules:
- +Score → AI win
- -Score → Player win
- 0 → Tie

On **Hard difficulty**, the AI explores all possible game states and always chooses the optimal move, making it mathematically unbeatable.

---## 🛠️ Technologies Used

- **Java**
- Object-Oriented Programming (OOP)
- Minimax Algorithm
- Recursion
- HashMap (Memoization / Transposition table optimization)
- Enum-based state modeling

---

## 🧠 AI & Algorithm Details

The AI is implemented using the **Minimax algorithm**, a recursive decision-making algorithm used in deterministic two-player zero-sum games.

### Evaluation Strategy

- Positive score → AI win  
- Negative score → Player win  
- Zero → Draw  

The algorithm simulates all possible future moves and chooses the optimal one.

### Difficulty Levels

- **Easy** → Random valid moves  
- **Medium** → Mix of random and optimal moves  
- **Hard** → Full Minimax (Unbeatable)

On Hard difficulty, the AI guarantees:
- It never loses  
- It always forces at least a draw  

---

## 📊 Time & Space Complexity

For a standard 3×3 Tic Tac Toe board:

- Worst-case time complexity: **O(9!)**
- Optimized with memoization: Significantly reduced
- Space complexity: **O(depth of recursion)**

Because the game state space is small, full Minimax is computationally feasible.

---

## 🎯 Learning Outcomes

This project demonstrates:

- Applying recursion in practical problem-solving
- Designing clean OOP architecture
- Implementing AI decision-making systems
- Managing game states using enums
- Separating engine logic from UI
- Performance optimization using memoization

---

## 🔮 Future Improvements

- Alpha-Beta pruning optimization
- Move ordering heuristics
- GUI implementation (Swing or JavaFX)
- Web integration (Engine + Frontend)
- Multiplayer support
- Generalization to NxN boards

---

## 👨‍💻 Author

**Mohammed Walid**

Computer Science student focused on algorithms, problem-solving, and game engine logic.

---

## 📜 License

This project is open-source and intended for educational and learning purposes.
