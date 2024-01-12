/**
 * @author Martin Hansson, maha6445.
 */

package se.su.student.dogregister.data;
import java.lang.Comparable;
import java.util.ArrayList;

import se.su.student.dogregister.util.Utilities;

/**
 * Data class for an owner.
 * An owner has a name.
 * An owner can own multiple dogs.
 */
public class Owner implements Comparable<Owner> {
    private final String name;
    private final ArrayList<Dog> dogs;

    public Owner(String name) {
        this.name = Utilities.toTitleCase(name);
        this.dogs = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    /**
     * Add a dog to the list of owned dogs.
     * Only adds the dog if the dog doesn't have an owner
     * and the dog isn't owned by this owner.
     * After adding the dog to the list, if the dog
     * doesn't have an owner, asks the dog to set
     * this owner as its owner.
     *
     * @param dog the dog to add to the list of owned dogs.
     * @return true if it was added, false otherwise.
     */
    public boolean addDog(Dog dog) {
        // If argument is null, then return.
        if (dog == null) return false;

        // Check if dog already has another owner.
        if (dog.getOwner() != null && dog.getOwner() != this) return false;

        // Check owner already owns the dog.
        if (this.dogs.contains(dog)) return false;

        this.dogs.add(dog);

        // If the dog doesn't have an owner.
        if (dog.getOwner() == null) {
            // Ask the dog to set its owner.
            dog.setOwner(this);
        }

        return true;
    }

    /**
     * Removes a dog from the list of owned dogs.
     * Only removes the dog if it is in the list of owned dogs.
     * If the dog has this owner as its owner, asks it to set
     * its owner to null.
     *
     * @param dog the dog to remove from the list of owned dogs.
     * @return true if the dog was removed, false otherwise.
     */
    public boolean removeDog(Dog dog) {
        // If argument is null, then return.
        if (dog == null) return false;

        // Check if owner owns the dog.
        if (!this.dogs.contains(dog)) return false;

        // Remove the dog from owned dogs.
        this.dogs.remove(dog);

        // If owner is dogs owner, then ask dog to remove owner.
        if (dog.getOwner() == this) dog.setOwner(null);

        return true;
    }

    /**
     * Returns a list of the dogs owned by this owner.
     *
     * @return an unmodifiable copy of the dogs owned by this owner.
     */
    public ArrayList<Dog> getDogs() {
        return new ArrayList<>(this.dogs);
    }

    @Override
    public String toString() {
        if (dogs.isEmpty()) return "Owner [Name: " + this.name + ", Dogs: None]";
        else {
            StringBuilder builder = new StringBuilder();
            builder.append("Owner [Name: ");
            builder.append(this.name);
            builder.append(", Dogs: ");
            builder.append(this.dogs);
            builder.append("]");

            return builder.toString();
        }
    }

    /**
     * Compares the owner to another owner.
     * Specifically compares the names lexicographically.
     *
     * @param otherOwner the object to be compared.
     * @return -1 if the owners name has a lexicographically smaller name,
     * 1 if the other owner has a lexicographically smaller name,
     * 0 if they are the same name.
     */
    @Override
    public int compareTo(Owner otherOwner) {
        return this.name.compareTo(otherOwner.getName());
    }
}
