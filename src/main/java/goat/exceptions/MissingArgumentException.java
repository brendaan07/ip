package goat.exceptions;

public class MissingArgumentException extends Exception {
    public MissingArgumentException(String command) {
        super("Error: The command \"" + command + "\" requires an argument");
    }
}
