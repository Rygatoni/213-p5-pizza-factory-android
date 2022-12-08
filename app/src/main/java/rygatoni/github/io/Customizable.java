package rygatoni.github.io;

/**
 * Interface for adding basic add/remove functionalities
 * to classes.
 *
 * @author Rygl Ato
 * @author Jeffrey Mijares
 */
public interface Customizable {
    /**
     * Adds an object.
     * @param obj The object to be added.
     * @return true if the object was successfully added, false if not
     */
    boolean add(Object obj);

    /**
     * Removes an object.
     * @param obj The object to be removed.
     * @return true if the object was successfully removed, false if not
     */
    boolean remove(Object obj);
}
