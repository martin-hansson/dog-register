/**
 * @author Martin Hansson, maha6445.
 */

package se.su.student.dogregister;
import se.su.student.dogregister.collections.DogCollection;
import se.su.student.dogregister.collections.OwnerCollection;
import se.su.student.dogregister.data.Dog;
import se.su.student.dogregister.data.Owner;
import se.su.student.dogregister.input.InputReader;
import se.su.student.dogregister.util.Utilities;
import java.util.List;

/**
 * Dog register that has a collection for dogs and owners.
 * Reads commands from the console and executes the commands
 * until an exit command is provided.
 */
public class DogRegister {
    private static final String EXIT_COMMAND = "exit";
    private final DogCollection dogCollection = new DogCollection();
    private final OwnerCollection ownerCollection = new OwnerCollection();
    private final InputReader inputReader = new InputReader();

    public static void main(String[] args) {
        new DogRegister().start();
    }

    private void start() {
        initialize();
        runCommandLoop();
        shutDown();
    }

    /**
     * Prints a welcome message when starting
     * and the available commands.
     */
    private void initialize() {
        System.out.println("Welcome to the dog register!");
        printCommands();
    }

    /**
     * Prints the available commands.
     */
    private void printCommands() {
        System.out.println("""
				The following commands are available:
				* Register new dog
				* Remove dog
				* Register new owner
				* Remove owner
				* List dogs
				* List owners
				* Increase age
				* Give dog to owner
				* Remove dog from owner
				* Exit
				""");
    }

    /**
     * Reads and executes commands while the exit command isn't provided.
     */
    public void runCommandLoop() {
        String command;
        do {
            command = inputReader.readString("Command").toLowerCase();
            executeCommand(command);
        } while (!command.equals(EXIT_COMMAND));
    }

    /**
     * Print a message when shutting down the program.
     */
    public void shutDown() {
        System.out.println("Dog register shutting down");
    }

    /**
     * Checks the command and executes if it's a valid command.
     *
     * @param command the command to execute.
     */
    public void executeCommand(String command) {
        switch (command) {
            case "register new dog", "rnd" -> registerNewDog();
            case "remove dog", "rd" -> removeDog();
            case "register new owner", "rno" -> registerNewOwner();
            case "remove owner", "ro" -> removeOwner();
            case "list dogs", "ld" -> listDogs();
            case "list owners", "lo" -> listOwners();
            case "increase age", "ia" -> increaseAge();
            case "give dog to owner", "gdto" -> giveDogToOwner();
            case "remove dog from owner", "rdfo" -> removeDogFromOwner();
            case "exit" -> {}
            default -> System.out.println("Error: Invalid command.");
        }
    }

    /**
     * Command "register new dog" or "rnd".
     * Asks for a name, breed, age, weight and then creates a new dog and adds it to the register.
     * If the dog already exists then it won't be added.
     * Will keep asking for a name and breed until a string
     * that's not blank and not empty has been provided.
     */
    private void registerNewDog() {
        String name = Utilities.toTitleCase(validateInputString("Enter dog name"));

        if (dogCollection.containsDog(name)) {
            System.out.printf("Error: %s is already registered.%n", name);
            return;
        }

        String breed = Utilities.toTitleCase(validateInputString("Enter dog breed"));
        int age = inputReader.readInt("Enter dog age");
        int weight = inputReader.readInt("Enter dog weight");

        dogCollection.addDog(new Dog(name, breed, age, weight));
        System.out.printf("%s has been added to the register.%n", name);
    }

    /**
     * Command "remove dog" or "rd".
     * Asks for the name of the dog to remove, removes the dogs owner
     * and removes the dog from the collection.
     * The dog won't be removed if the collection is empty
     * or the dog isn't present in the collection.
     */
    private void removeDog() {
        if (dogCollection.getDogs().isEmpty()) {
            System.out.println("Error: No dogs in the register.");
            return;
        }

        String name = Utilities.toTitleCase(validateInputString("Enter dog name"));
        Dog dog = dogCollection.getDog(name);

        if (dog == null) {
            System.out.printf("Error: %s is not registered.%n", name);
            return;
        }

        dog.setOwner(null);
        dogCollection.removeDog(dog);

        System.out.printf("%s has been removed from the register.%n", name);
    }

    /**
     * Command "register new owner" or "rno".
     * Asks for a name and then creates a new owner and adds it to the register.
     * If the owner already exists then it won't be added.
     * Will keep asking for a name until a string that's not blank and not
     * empty has been provided.
     */
    private void registerNewOwner() {
        String name = Utilities.toTitleCase(validateInputString("Enter owner name"));
        if (ownerCollection.containsOwner(name)) {
            System.out.printf("Error: %s is already registered.%n", name);
            return;
        }

        ownerCollection.addOwner(new Owner(name));
        System.out.printf("%s has been added to the register.%n", name);
    }

    /**
     * Command "remove owner" or "ro".
     * Asks for the name of the owner to remove, removes the owners dogs
     * and removes the owner from the collection.
     * The owner won't be removed if the collection is empty
     * or the owner isn't present in the collection.
     */
    private void removeOwner() {
        if (ownerCollection.getOwners().isEmpty()) {
            System.out.println("Error: No owners in register.");
            return;
        }

        String name = Utilities.toTitleCase(validateInputString("Enter owner name"));
        Owner owner = ownerCollection.getOwner(name);

        if (owner == null) {
            System.out.printf("Error: %s is not registered.%n", name);
            return;
        }

        for (Dog dog : owner.getDogs()) {
            dog.setOwner(null);
            dogCollection.removeDog(dog);
        }

        ownerCollection.removeOwner(owner);
        System.out.printf("%s has been removed from the register.%n", name);
    }

    /**
     * Command "list dogs" or "ld".
     * Asks for a minimum tail length and lists all dogs with a longer tail.
     * Doesn't list the dogs if there are no dogs registered.
     */
    private void listDogs() {
        if (dogCollection.getDogs().isEmpty()) {
            System.out.println("Error: No dogs in register.");
            return;
        }

        double minTailLength = inputReader.readDouble("Enter minimum tail length");
        List<Dog> matches = dogCollection.getDogsWithLongerTail(minTailLength);

        System.out.println("Dogs in register:");
        for (Dog dog : matches) {
            System.out.println(dog);
        }
    }

    /**
     * Command "list owners" or "lo".
     * Lists all the owners in the collection.
     * Doesn't list any owners if there are no owners registered.
     */
    private void listOwners() {
        if (ownerCollection.getOwners().isEmpty()) {
            System.out.println("Error: No owners in register.");
            return;
        }

        System.out.println("Owners in register:");
        ownerCollection.getOwners().forEach(System.out::println);
    }

    /**
     * Command "increase age" or "ia".
     * Increases the age of the specified dog by one.
     * The age won't increase if there are no dogs registered or
     * the specified dog doesn't exist.
     * Will keep asking for a name until a string that's not blank and not
     * empty has been provided.
     */
    private void increaseAge() {
        if (dogCollection.getDogs().isEmpty()) {
            System.out.println("Error: No dogs in register.");
            return;
        }

        String name = Utilities.toTitleCase(validateInputString("Enter dog name"));
        Dog dog = dogCollection.getDog(name);

        if (dog == null) {
            System.out.printf("Error: %s is not registered.%n", name);
            return;
        }

        dog.increaseAge();
        System.out.printf("%s is now one year older.%n", name);
    }

    /**
     * Command "give dog to owner" or "gdto".
     * Asks for a name of a dog and an owner and sets the dogs owner
     * to that owner and adds the dog to the owners owned dogs.
     * The dog won't set the owner and the owner add the dog if
     * either collection is empty or either the dog or the owner
     * are not found in either collection.
     * Will keep asking for a name until a string that's not blank and not
     * empty has been provided.
     */
    private void giveDogToOwner() {
        // Check if either collection is empty.
        if (dogCollection.getDogs().isEmpty()) {
            System.out.println("Error: No dogs in register.");
            return;
        } else if (ownerCollection.getOwners().isEmpty()) {
            System.out.println("Error: No owners in register.");
            return;
        }

        // Get name of dog and fetch it.
        String dogName = Utilities.toTitleCase(validateInputString("Enter dog name"));
        Dog dog = dogCollection.getDog(dogName);

        // If there was no dog, print error.
        if (dog == null) {
            System.out.printf("Error: %s is not registered.%n", dogName);
            return;
        }

        // If the dog has an owner, print error.
        if (dog.getOwner() != null) {
            System.out.printf("Error: %s already has an owner.%n", dogName);
            return;
        }

        // Get the name of owner and fetch it.
        String ownerName = Utilities.toTitleCase(validateInputString("Enter owner name"));
        Owner owner = ownerCollection.getOwner(ownerName);

        // If there was no owner, print error.
        if (owner == null) {
            System.out.printf("Error: %s is not registered.%n", ownerName);
            return;
        }

        dog.setOwner(owner);
        System.out.printf("%s is now owned by %s.%n", dogName, ownerName);
    }

    /**
     * Command "remove dog from owner" or "rdfo".
     * Asks for a name of a dog sets the dogs owner
     * to null and removes the dog from the owners owned dogs.
     * The dog won't remove the owner and the owner remove the dog if
     * either collection is empty or if the dog is not found in the collection.
     * Will keep asking for a name until a string that's not blank and not
     * empty has been provided.
     */
    private void removeDogFromOwner() {
        // Check if either collection is empty.
        if (dogCollection.getDogs().isEmpty()) {
            System.out.println("Error: No dogs in register.");
            return;
        } else if (ownerCollection.getOwners().isEmpty()) {
            System.out.println("Error: No owners in register.");
            return;
        }

        // Get name of dog and fetch it.
        String dogName = Utilities.toTitleCase(validateInputString("Enter dog name"));
        Dog dog = dogCollection.getDog(dogName);

        // If there was no dog, print error.
        if (dog == null) {
            System.out.printf("Error: %s is not registered.%n", dogName);
            return;
        }

        // If the dog doesn't have an owner, print error.
        if (dog.getOwner() == null) {
            System.out.printf("Error: %s doesn't have an owner.%n", dogName);
            return;
        }

        dog.setOwner(null);
        System.out.printf("%s no longer has an owner.%n", dogName);
    }

    /**
     * Helper function that validates that the string provided
     * is not blank and not empty.
     *
     * @param prompt the prompt to print to the user.
     * @return inputted string that's not empty or blank.
     */
    private String validateInputString(String prompt) {
        String input = inputReader.readString(prompt);

        while (input.isBlank() || input.isEmpty()) {
            System.out.printf("Error: %s can't be empty.%n", prompt);
            input = inputReader.readString("Name");
        }

        return input;
    }
}
