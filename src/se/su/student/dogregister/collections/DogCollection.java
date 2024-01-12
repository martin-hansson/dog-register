/**
 * @author Martin Hansson, maha6445.
 */

package se.su.student.dogregister.collections;
import java.util.ArrayList;
import java.util.stream.Collectors;
import se.su.student.dogregister.comparators.DogTailNameComparator;
import se.su.student.dogregister.comparators.DogNameComparator;
import se.su.student.dogregister.data.Dog;
import se.su.student.dogregister.sorter.DogSorter;

/**
 * Collection of dogs.
 */
public class DogCollection {
    private final ArrayList<Dog> dogList = new ArrayList<>();

    /**
     * Adds the dog to the list if it has a unique name.
     *
     * @param dog the dog to add to the list.
     * @return true if the dog was added, false otherwise.
     */
    public boolean addDog(Dog dog) {
        if (containsDog(dog)) return false;

        this.dogList.add(dog);
        return true;
    }

    /**
     * Removes the dog with the provided name if its present in the list.
     * If a dog has an owner, it's not removed from the list.
     *
     * @param name the name of the dog to remove.
     * @return true if it was removed, false otherwise.
     */
    public boolean removeDog(String name) {
        if (containsDog(name)) {
            Dog dog = getDog(name);

            // If the dog has an owner, we shouldn't remove it from the list.
            if (dog.getOwner() != null) return false;

            this.dogList.remove(dog);
            return true;
        }

        return false;
    }

    /**
     * Removes the dog if its present in the list.
     *
     * @param dog the dog to remove.
     * @return true if it was removed, false otherwise.
     */
    public boolean removeDog(Dog dog) {
        return removeDog(dog.getName());
    }

    /**
     * Checks if the dog with the provided name is present in the list.
     *
     * @param name the name of the dog to find.
     * @return true if the dog is present, false otherwise.
     */
    public boolean containsDog(String name) {
        return getDog(name) != null;
    }

    /**
     * Checks if the dog is present in the list.
     *
     * @param dog the dog to find.
     * @return true if the dog is present, false otherwise.
     */
    public boolean containsDog(Dog dog) {
        return containsDog(dog.getName());
    }

    /**
     * Gets the dog with the provided name from the list.
     *
     * @param name the name of the dog to find.
     * @return the dog if its present, null otherwise.
     */
    public Dog getDog(String name) {
        for (Dog dog : this.dogList) {
            if (dog.getName().equalsIgnoreCase(name)) return dog;
        }

        return null;
    }

    /**
     * Gets all the dogs in the list sorted by tail length and name.
     *
     * @return an unmodifiable copy of the list of dogs.
     */
    public ArrayList<Dog> getDogs() {
        DogSorter.sortDogs(new DogNameComparator(), this.dogList);
        return new ArrayList<>(this.dogList);
    }

    /**
     * Gets all the dogs in the list with a larger tail length than the provided length.
     *
     * @param minTailLength the smallest tail length to filter out.
     * @return an unmodifiable copy of the dogs with a larger tail length.
     */
    public ArrayList<Dog> getDogsWithLongerTail(double minTailLength) {
        DogSorter.sortDogs(new DogTailNameComparator(), this.dogList);
        return new ArrayList<>(this.dogList.stream().filter(dog -> dog.getTailLength() >= minTailLength).collect(Collectors.toList()));
    }

}
