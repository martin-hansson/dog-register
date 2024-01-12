/**
 * @author Martin Hansson, maha6445.
 */

package se.su.student.dogregister.data;
import se.su.student.dogregister.util.Utilities;

/**
 * Data class for a dog.
 * A dog has a name, breed, age, weight, and a tail length.
 * A dog can have an owner.
 */
public class Dog {

    // A dachshund always has the tail length 3.7.
    private static final double DACHSHUND_TAIL_LENGTH = 3.7;

    // Different translations for dachshund.
    private static final String[] DACHSUND_TRANSLATIONS = {
            "tax",
            "dachshund",
            "mäyräkoira",
            "teckel"
    };

    private final String name;
    private final String breed;
    private final int weight;
    private int age;
    private Owner owner;

    public Dog(String name, String breed, int age, int weight) {
        this.name = Utilities.toTitleCase(name);
        this.breed = Utilities.toTitleCase(breed);
        this.age = age;
        this.weight = weight;
    }

    public String getName() {
        return this.name;
    }

    public String getBreed() {
        return this.breed;
    }

    public int getAge() {
        return this.age;
    }

    public int getWeight() {
        return this.weight;
    }

    public Owner getOwner() {
        return this.owner;
    }

    /**
     * Calculate the tail length for the dog.
     * Formula: tail length = age * weight / 10.
     * If the dog is a dachshund, return the constant
     * for dachshund tail length.
     *
     * @return the tail length for the dog.
     */
    public double getTailLength() {
        // If the breed is dachshund, return DACHSHUND_TAIL_LENGTH
        for (String translation : DACHSUND_TRANSLATIONS) {
            if (translation.equalsIgnoreCase(this.breed)) return DACHSHUND_TAIL_LENGTH;
        }

        // Calculate the tail length.
        return this.age * this.weight / 10.0;
    }

    /**
     * Sets the owner of the dog.
     * If owner is provided and dog has no owner,
     * sets the dogs owner to the provided owner and
     * asks the owner to add the dog to their owned dogs.
     * If the argument is null then the dog should remove its owner
     * and ask their owner to remove it from their owned dogs.
     *
     * @param owner the new owner of the dog.
     * @return true if either the owner was set or removed or false if it failed.
     */
    public boolean setOwner(Owner owner) {
        // If the argument is null, then remove owner.
        if (owner == null) return removeOwner();

        // Check if dog already has an owner.
        if (this.owner != null) return false;

        // Set dog owner to provided owner.
        this.owner = owner;

        // Check if owner owns the dog.
        if (!owner.getDogs().contains(this)) {
            // Ask the owner to remove the dog from their owned dogs.
            owner.addDog(this);
        }

        return true;
    }

    /**
     * Helper method that removes the owner from the dog.
     * If the dogs current owner has the dog among its owned dogs,
     * then ask it to remove the dog from their owned dogs.
     *
     * @return true if the owner was removed, false otherwise.
     */
    private boolean removeOwner() {
        // Check if the dog doesn't have an owner.
        if (this.owner == null) return false;

        // If the current owner owns this dog, ask it to remove the dog from their owned dogs.
        if (this.owner.getDogs().contains(this)) this.owner.removeDog(this);

        // Set the owner to null.
        this.owner = null;

        return true;
    }

    /**
     * Increase the age of the dog by one.
     * If it's higher than "Integer.MAX_VALUE", don't increase the age
     * to avoid overloading.
     */
    public void increaseAge() {
        if (this.age != Integer.MAX_VALUE) this.age++;
    }

    @Override
    public String toString() {
        if (this.owner == null) return "Dog [Name: %s, Breed: %s, Age: %s, Weight: %s, Tail length: %.2f]".formatted(this.name, this.breed, this.age, this.weight, getTailLength());
        else return "Dog [Name: %s, Breed: %s, Age: %s, Weight: %s, Tail length: %.2f, Owner: %s]".formatted(this.name, this.breed, this.age, this.weight, getTailLength(), this.owner.getName());
    }
}
