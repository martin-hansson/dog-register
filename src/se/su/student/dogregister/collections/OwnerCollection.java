/**
 * @author Martin Hansson, maha6445.
 */

package se.su.student.dogregister.collections;
import se.su.student.dogregister.data.Owner;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Collection of owners.
 * As of assignment instructions,
 * not allowed to use any collection except array.
 * Except for return value of getOwners() which returns a List.
 */
public class OwnerCollection {

    // Initialize the array with zero objects.
    private Owner[] ownerArray = new Owner[0];

    /**
     * Add a new owner to the array if it's not present.
     * Increase the array size by one, and add the new owner to
     * the end of the array. This way no values are null.
     *
     * @param owner the owner to add.
     * @return true if the owner was added, false otherwise.
     */
    public boolean addOwner(Owner owner) {
        if (containsOwner(owner)) return false;

        increaseArrayLength();
        ownerArray[ownerArray.length - 1] = owner;
        return true;
    }

    /**
     * Remove an owner if its present in the array.
     * If the owner owns any dogs, it's not remove from the array.
     * Reduces the array size by one, and removes the
     * owner at the index. Owners with higher indexes
     * than the removed owners are shifted one index down.
     *
     * @param name the name of the owner to remove.
     * @return true if it was removed, false otherwise.
     */
    public boolean removeOwner(String name) {
        int ownerIndex = getOwnerIndex(name);
        if (ownerIndex == -1) return false;

        // If the owner has any dogs, we shouldn't remove it from the array.
        if (!getOwner(name).getDogs().isEmpty()) return false;

        reduceArrayLength(ownerIndex);
        return true;
    }

    /**
     * Remove an owner if its present in the array.
     * Reduces the array size by one, and removes the
     * owner at the index. Owners with higher indexes
     * than the removed owners are shifted one index down.
     *
     * @param owner the owner to remove.
     * @return true if it was removed, false otherwise.
     */
    public boolean removeOwner(Owner owner) {
        return removeOwner(owner.getName());
    }

    /**
     * Checks if an owner is present in the array.
     *
     * @param name the name of the owner to find.
     * @return true if the owner was found, false otherwise.
     */
    public boolean containsOwner(String name) {
        return getOwner(name) != null;
    }

    /**
     * Checks if an owner is present in the array.
     *
     * @param owner the owner to find.
     * @return true if the owner was found, false otherwise.
     */
    public boolean containsOwner(Owner owner) {
        return containsOwner(owner.getName());
    }

    /**
     * Gets an owner with the provided name from the array.
     *
     * @param name the name of the owner to find.
     * @return the owner if it was found, null otherwise.
     */
    public Owner getOwner(String name) {
        int ownerIndex = getOwnerIndex(name);
        if (ownerIndex == -1) return null;

        return ownerArray[ownerIndex];
    }

    /**
     * Gets all the owners in the array.
     *
     * @return a list copy of the array sorted by name lexicographically.
     */
    public ArrayList<Owner> getOwners() {
        sortArray();
        return new ArrayList<>(Arrays.asList(ownerArray));
    }

    /**
     * Helper function that sorts the array by name lexicographically.
     */
    private void sortArray() {
        for (int i = 0; i < ownerArray.length; i++) {
            int smallestIndex = i;
            for (int j = i; j < ownerArray.length; j++) {
                if (ownerArray[smallestIndex].compareTo(ownerArray[j]) > 0) smallestIndex = j;
            }

            if (ownerArray[smallestIndex].compareTo(ownerArray[i]) != 0) {
                Owner temp = ownerArray[i];
                ownerArray[i] = ownerArray[smallestIndex];
                ownerArray[smallestIndex] = temp;
            }
        }
    }

    /**
     * Helper method to find the index for the owner with the provided name.
     *
     * @param name the name of the owner to find.
     * @return the index of the owner if found, -1 otherwise.
     */
    private int getOwnerIndex(String name) {
        for (int i = 0; i < ownerArray.length; i++) {
            if (ownerArray[i].getName().equalsIgnoreCase(name)) return i;
        }

        return -1;
    }

    /**
     * Helper method that increases the array size by one
     * and copies the old array into the new one.
     * Overwrites the old array with the new array.
     */
    private void increaseArrayLength() {
        Owner[] newOwnerArray = new Owner[ownerArray.length + 1];
        System.arraycopy(ownerArray, 0, newOwnerArray, 0, ownerArray.length);
        ownerArray = newOwnerArray;
    }

    /**
     * Helper method that reduces the array size by one.
     * Copies the old array up to the index of the owner to remove exclusive,
     * then copies the rest of the array from the owner index plus one.
     * Overwrites the old array with the new array.
     *
     * @param ownerIndex the index of the owner to remove.
     */
    private void reduceArrayLength(int ownerIndex) {
        Owner[] newOwnerArray = new Owner[ownerArray.length - 1];
        System.arraycopy(ownerArray, 0, newOwnerArray, 0, ownerIndex);
        System.arraycopy(ownerArray, ownerIndex + 1, newOwnerArray, ownerIndex, newOwnerArray.length - ownerIndex);
        ownerArray = newOwnerArray;
    }
}
