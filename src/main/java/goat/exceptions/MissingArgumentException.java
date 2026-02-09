package goat.exceptions;

/**
 * Exception thrown to indicate argument that follows a command is missing
 */
public class MissingArgumentException extends Exception {
    public MissingArgumentException(String command) {
        super("Error: The command \"" + command + "\" requires an argument");
    }
}
