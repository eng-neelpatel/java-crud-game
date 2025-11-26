# Java CRUD Game

A fun Java console-based game demonstrating **CRUD (Create, Read, Update, Delete)** operations with a character management system.

## Features

- **CREATE** - Add new characters to your party
- **READ** - View all characters or individual character details
- **UPDATE** - Modify character attributes (name, class, level)
- **DELETE** - Remove characters from your party
- **BATTLE** - Simulate battles between your characters!

## Character Classes

| Class | Health | Attack | Defense |
|-------|--------|--------|----------|
| Warrior | High | Medium | High |
| Mage | Low | High | Low |
| Rogue | Medium | Medium-High | Medium |
| Healer | Medium-High | Low | Medium |

## How to Run

### Prerequisites
- Java JDK 14 or higher (uses switch expressions)

### Compile and Run
```bash
# Compile the game
javac CRUDGame.java

# Run the game
java CRUDGame
```

## Gameplay

1. The game starts with 3 pre-made characters
2. Use the menu to Create, Read, Update, or Delete characters
3. Try the Battle Simulator to pit your characters against each other!

## Sample Output

```
============================================================
       WELCOME TO THE CRUD CHARACTER MANAGER GAME!
============================================================
Manage your party of heroes using CRUD operations!

----------------------------------------
           MAIN MENU
----------------------------------------
1. CREATE - Add a new character
2. READ   - View all characters
3. READ   - View single character
4. UPDATE - Modify a character
5. DELETE - Remove a character
6. BATTLE - Simulate a battle
7. EXIT   - Quit the game
----------------------------------------
```

## Technologies Used

- Java 14+
- Collections Framework (HashMap)
- Object-Oriented Programming
- Console I/O

## Author

**Neel Patel** - Software Engineering Student

## License

This project is open source and available for learning purposes.
