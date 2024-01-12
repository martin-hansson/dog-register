/**
 * @author Martin Hansson, maha6445.
 */

package se.su.student.dogregister.comparators;
import java.util.Comparator;
import se.su.student.dogregister.data.Dog;

/**
 * Comparator for dog tail lengths.
 */
public class DogTailComparator implements Comparator<Dog> {

    /**
     * Compares the tail lengths of two dogs.
     *
     * @param dog the first dog to be compared.
     * @param otherDog the second dog to be compared.
     * @return -1 if the first dog has a shorter tail,
     * 1 if the second dog has a shorter tail or 0 if they
     * are the same length.
     */
    public int compare(Dog dog, Dog otherDog) {
        // If the first dog has a shorter tail, return -1.
        if (dog.getTailLength() < otherDog.getTailLength()) return -1;

        // If the second dog has a short tail, return 1.
        else if (dog.getTailLength() > otherDog.getTailLength()) return 1;

        // If the dogs have the same tail length, return 0.
        else return 0;
    }
}
