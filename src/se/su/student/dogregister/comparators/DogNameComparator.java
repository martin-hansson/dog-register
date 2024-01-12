/**
 * @author Martin Hansson, maha6445.
 */

package se.su.student.dogregister.comparators;
import java.util.Comparator;
import se.su.student.dogregister.data.Dog;

/**
 * Comparator for dog names.
 */
public class DogNameComparator implements Comparator<Dog> {

    /**
     * Compares the names of two dogs. Uses "String.compareTo"
     * to compare the names lexicographically.
     *
     * @param dog the first dog to be compared.
     * @param otherDog the second dog to be compared.
     * @return -1 if the first dog has a lexicographically smaller name,
     * 1 if the second dog has a lexicographically smaller name
     * or 0 if they are the same.
     */
    public int compare(Dog dog, Dog otherDog) {
        return dog.getName().compareTo(otherDog.getName());
    }
}
