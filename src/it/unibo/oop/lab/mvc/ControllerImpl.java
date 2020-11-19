package it.unibo.oop.lab.mvc;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ControllerImpl implements Controller {

    private final List<String> strings = new LinkedList<>();
    private String currString;

    /**
     * Sets the next string to print.
     * 
     * @param s
     *          the string to be set as next
     */
    public void setNextString(final String s) {
        this.currString = Objects.requireNonNull(s, "This method requires a non null argument");
    }

    /**
     * Get the current selected string.
     * 
     * @return a string representing the next string to print
     */
    public String getNextString() {
        return this.currString;
    }

    /**
     * Get the history of all printed strings.
     * 
     * @return a list representing the history of printed strings
     */
    public List<String> getHistory() {
        return this.strings;
    }

    /**
     * Prints the current set string.
     */
    public void printCurrentString() {
        if (this.currString == null) {
            throw new IllegalStateException("There is no set string");
        }
        strings.add(this.currString);
        System.out.println(this.currString);
    }

}
