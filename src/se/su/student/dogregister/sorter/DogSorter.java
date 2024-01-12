/**
 * @author Martin Hansson, maha6445.
 */

package se.su.student.dogregister.sorter;
import java.util.ArrayList;
import java.util.Comparator;
import se.su.student.dogregister.data.Dog;

/**
 * Used to sort dogs in a list by using a comparator.
 */
public class DogSorter {

    /**
     * Swap the place of two dogs in the specified list.
     *
     * @param dogList the list of dogs.
     * @param dogIndex the index of the first dog to swap.
     * @param otherDogIndex the index of the second dog to swap.
     */
    private static void swapDogs(ArrayList<Dog> dogList, int dogIndex, int otherDogIndex) {
        Dog dog = dogList.get(dogIndex);
        dogList.set(dogIndex, dogList.get(otherDogIndex));
        dogList.set(otherDogIndex, dog);
    }

    /**
     * Finds the smallest dog starting from starting index
     * searching through the rest of the provided list.
     *
     * @param dogComparator the comparator to use.
     * @param dogList the list to search through.
     * @param startIndex the index to start searching at.
     * @return the index of the smallest dog.
     */
    private static int nextDog(Comparator<Dog> dogComparator, ArrayList<Dog> dogList, int startIndex) {
        int smallestIndex = startIndex;

        for (int i = startIndex + 1; i < dogList.size(); i++) {
            if (dogComparator.compare(dogList.get(smallestIndex), dogList.get(i)) > 0) smallestIndex = i;
        }

        return smallestIndex;
    }

    /**
     * Sorts the dogs in the provided list using the provided comparator.
     * Counts the number of swaps and returns them.
     *
     * @param dogComparator the comparator to use.
     * @param dogList the list to sort.
     * @return the number of swaps.
     */
    public static int sortDogs(Comparator<Dog> dogComparator, ArrayList<Dog> dogList) {
        int swapCounter = 0;

        for (int i = 0; i < dogList.size(); i++) {
            int smallestIndex = nextDog(dogComparator, dogList, i);
            if (smallestIndex != i) {
                swapDogs(dogList, smallestIndex, i);
                swapCounter++;
            }
        }

        return swapCounter;
    }
}
