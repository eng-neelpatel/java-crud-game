import java.util.*;

/**
 * CRUD Game - A Java console-based game demonstrating CRUD operations
 * Create, Read, Update, and Delete game characters!
 * Author: Neel Patel
 */
public class CRUDGame {
    private static Map<Integer, Character> characters = new HashMap<>();
    private static int nextId = 1;
    private static Scanner scanner = new Scanner(System.in);
    
    // Inner class for Character
    static class Character {
        int id;
        String name;
        String characterClass;
        int level;
        int health;
        int attack;
        int defense;
        
        Character(int id, String name, String characterClass, int level) {
            this.id = id;
            this.name = name;
            this.characterClass = characterClass;
            this.level = level;
            setStats();
        }
        
        void setStats() {
            switch(characterClass.toLowerCase()) {
                case "warrior":
                    health = 100 + (level * 15);
                    attack = 20 + (level * 5);
                    defense = 15 + (level * 4);
                    break;
                case "mage":
                    health = 70 + (level * 10);
                    attack = 30 + (level * 7);
                    defense = 8 + (level * 2);
                    break;
                case "rogue":
                    health = 80 + (level * 12);
                    attack = 25 + (level * 6);
                    defense = 10 + (level * 3);
                    break;
                case "healer":
                    health = 90 + (level * 13);
                    attack = 15 + (level * 3);
                    defense = 12 + (level * 3);
                    break;
                default:
                    health = 85 + (level * 11);
                    attack = 18 + (level * 4);
                    defense = 12 + (level * 3);
            }
        }
        
        @Override
        public String toString() {
            return String.format(
                "| %-3d | %-15s | %-10s | %-5d | %-6d | %-6d | %-7d |",
                id, name, characterClass, level, health, attack, defense
            );
        }
    }
    
    public static void main(String[] args) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("       WELCOME TO THE CRUD CHARACTER MANAGER GAME!");
        System.out.println("=".repeat(60));
        System.out.println("Manage your party of heroes using CRUD operations!");
        
        // Add some starter characters
        addStarterCharacters();
        
        boolean running = true;
        while(running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch(choice) {
                case 1 -> createCharacter();
                case 2 -> readCharacters();
                case 3 -> readSingleCharacter();
                case 4 -> updateCharacter();
                case 5 -> deleteCharacter();
                case 6 -> battleSimulator();
                case 7 -> {
                    System.out.println("\nThanks for playing! Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }
    
    static void displayMenu() {
        System.out.println("\n" + "-".repeat(40));
        System.out.println("           MAIN MENU");
        System.out.println("-".repeat(40));
        System.out.println("1. CREATE - Add a new character");
        System.out.println("2. READ   - View all characters");
        System.out.println("3. READ   - View single character");
        System.out.println("4. UPDATE - Modify a character");
        System.out.println("5. DELETE - Remove a character");
        System.out.println("6. BATTLE - Simulate a battle");
        System.out.println("7. EXIT   - Quit the game");
        System.out.println("-".repeat(40));
    }
    
    static void addStarterCharacters() {
        characters.put(nextId, new Character(nextId++, "Arthas", "Warrior", 5));
        characters.put(nextId, new Character(nextId++, "Gandalf", "Mage", 10));
        characters.put(nextId, new Character(nextId++, "Shadow", "Rogue", 7));
    }
    
    // CREATE Operation
    static void createCharacter() {
        System.out.println("\n>>> CREATE NEW CHARACTER <<<");
        System.out.print("Enter character name: ");
        String name = scanner.nextLine();
        
        System.out.println("Available classes: Warrior, Mage, Rogue, Healer");
        System.out.print("Enter character class: ");
        String charClass = scanner.nextLine();
        
        int level = getIntInput("Enter starting level (1-20): ");
        level = Math.max(1, Math.min(20, level));
        
        Character newChar = new Character(nextId, name, charClass, level);
        characters.put(nextId, newChar);
        
        System.out.println("\nCharacter created successfully!");
        System.out.println("ID: " + nextId + " | Name: " + name + " | Class: " + charClass + " | Level: " + level);
        nextId++;
    }
    
    // READ All Operation
    static void readCharacters() {
        System.out.println("\n>>> ALL CHARACTERS <<<");
        if(characters.isEmpty()) {
            System.out.println("No characters found! Create one first.");
            return;
        }
        
        printTableHeader();
        for(Character c : characters.values()) {
            System.out.println(c);
        }
        printTableFooter();
        System.out.println("Total characters: " + characters.size());
    }
    
    // READ Single Operation
    static void readSingleCharacter() {
        System.out.println("\n>>> VIEW CHARACTER DETAILS <<<");
        int id = getIntInput("Enter character ID: ");
        
        if(characters.containsKey(id)) {
            Character c = characters.get(id);
            System.out.println("\n--- Character Profile ---");
            System.out.println("ID: " + c.id);
            System.out.println("Name: " + c.name);
            System.out.println("Class: " + c.characterClass);
            System.out.println("Level: " + c.level);
            System.out.println("Health: " + c.health);
            System.out.println("Attack: " + c.attack);
            System.out.println("Defense: " + c.defense);
            System.out.println("Power Score: " + (c.health + c.attack + c.defense));
        } else {
            System.out.println("Character not found with ID: " + id);
        }
    }
    
    // UPDATE Operation
    static void updateCharacter() {
        System.out.println("\n>>> UPDATE CHARACTER <<<");
        int id = getIntInput("Enter character ID to update: ");
        
        if(!characters.containsKey(id)) {
            System.out.println("Character not found!");
            return;
        }
        
        Character c = characters.get(id);
        System.out.println("Current: " + c.name + " | " + c.characterClass + " | Level " + c.level);
        
        System.out.println("\nWhat do you want to update?");
        System.out.println("1. Name");
        System.out.println("2. Class");
        System.out.println("3. Level");
        System.out.println("4. All attributes");
        
        int choice = getIntInput("Choice: ");
        
        switch(choice) {
            case 1 -> {
                System.out.print("Enter new name: ");
                c.name = scanner.nextLine();
            }
            case 2 -> {
                System.out.print("Enter new class: ");
                c.characterClass = scanner.nextLine();
                c.setStats();
            }
            case 3 -> {
                c.level = getIntInput("Enter new level (1-20): ");
                c.level = Math.max(1, Math.min(20, c.level));
                c.setStats();
            }
            case 4 -> {
                System.out.print("Enter new name: ");
                c.name = scanner.nextLine();
                System.out.print("Enter new class: ");
                c.characterClass = scanner.nextLine();
                c.level = getIntInput("Enter new level (1-20): ");
                c.level = Math.max(1, Math.min(20, c.level));
                c.setStats();
            }
            default -> System.out.println("Invalid choice!");
        }
        System.out.println("Character updated successfully!");
    }
    
    // DELETE Operation
    static void deleteCharacter() {
        System.out.println("\n>>> DELETE CHARACTER <<<");
        int id = getIntInput("Enter character ID to delete: ");
        
        if(characters.containsKey(id)) {
            Character c = characters.remove(id);
            System.out.println("Character '" + c.name + "' has been deleted!");
        } else {
            System.out.println("Character not found with ID: " + id);
        }
    }
    
    // Bonus: Battle Simulator
    static void battleSimulator() {
        System.out.println("\n>>> BATTLE SIMULATOR <<<");
        if(characters.size() < 2) {
            System.out.println("Need at least 2 characters for battle!");
            return;
        }
        
        readCharacters();
        int id1 = getIntInput("Enter first fighter's ID: ");
        int id2 = getIntInput("Enter second fighter's ID: ");
        
        if(!characters.containsKey(id1) || !characters.containsKey(id2)) {
            System.out.println("Invalid character IDs!");
            return;
        }
        
        Character c1 = characters.get(id1);
        Character c2 = characters.get(id2);
        
        System.out.println("\n" + "=".repeat(40));
        System.out.println("   " + c1.name + " VS " + c2.name);
        System.out.println("=".repeat(40));
        
        int hp1 = c1.health;
        int hp2 = c2.health;
        Random rand = new Random();
        int round = 1;
        
        while(hp1 > 0 && hp2 > 0) {
            System.out.println("\nRound " + round + ":");
            
            // Character 1 attacks
            int damage1 = Math.max(1, c1.attack - c2.defense/2 + rand.nextInt(10));
            hp2 -= damage1;
            System.out.println(c1.name + " deals " + damage1 + " damage!");
            
            if(hp2 <= 0) break;
            
            // Character 2 attacks
            int damage2 = Math.max(1, c2.attack - c1.defense/2 + rand.nextInt(10));
            hp1 -= damage2;
            System.out.println(c2.name + " deals " + damage2 + " damage!");
            
            System.out.println(c1.name + ": " + Math.max(0, hp1) + " HP | " + c2.name + ": " + Math.max(0, hp2) + " HP");
            round++;
        }
        
        System.out.println("\n" + "=".repeat(40));
        String winner = hp1 > 0 ? c1.name : c2.name;
        System.out.println("    WINNER: " + winner + "!");
        System.out.println("=".repeat(40));
    }
    
    static void printTableHeader() {
        System.out.println("+" + "-".repeat(73) + "+");
        System.out.printf("| %-3s | %-15s | %-10s | %-5s | %-6s | %-6s | %-7s |%n",
            "ID", "Name", "Class", "Level", "Health", "Attack", "Defense");
        System.out.println("+" + "-".repeat(73) + "+");
    }
    
    static void printTableFooter() {
        System.out.println("+" + "-".repeat(73) + "+");
    }
    
    static int getIntInput(String prompt) {
        System.out.print(prompt);
        while(!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return value;
    }
}
