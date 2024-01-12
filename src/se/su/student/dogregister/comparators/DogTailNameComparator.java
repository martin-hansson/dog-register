/**
 * @author Martin Hansson, maha6445.
 */

package se.su.student.dogregister.comparators;
import java.util.Comparator;
import se.su.student.dogregister.data.Dog;

/**
 * Comparator for dog tail lengths and names.
 */
public class DogTailNameComparator implements Comparator<Dog> {

    /**
     * First compares the tail lengths of two dogs,
     * if they are the same, then compares the names lexicographically.
     *
     * @param dog the first dog to be compared.
     * @param otherDog the second dog to be compared.
     * @return -1 if the first dog has a short tail or lexicographically smaller name,
     * 1 if the second dog has shorter tail or a lexicographically smaller name
     * or 0 if both the tail length and the name are the same.
     */
    public int compare(Dog dog, Dog otherDog) {
        int result = new DogTailComparator().compare(dog, otherDog);
        if (result == 0) return new DogNameComparator().compare(dog, otherDog);
        else return result;
    }
}
