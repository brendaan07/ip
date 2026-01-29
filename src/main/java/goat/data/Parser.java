package goat.data;

import goat.exceptions.MissingArgumentException;

/**
 * Parser handles the user input given to the chatbot.
 * It splits the input into command and arguments
 */
public class Parser {
    private final String command;
    private final String arguments;

    /**
     * Constructs a Parser.
     *
     * The input is split into a command (the first word) and arguments (the rest of the input).
     *
     * @param userInput the  string input from the user
     */
    public Parser(String userInput) {
        String[] parts = userInput.split("\\s+", 2); //split into command and arguments
        this.command = parts[0].toLowerCase();
        this.arguments = parts.length > 1 ? parts[1] : "";
    }

    /**
     * Returns the command from the user input.
     *
     * @return the command string
     */
    public String getCommand() {
        return command;
    }

    /**
     * Returns the arguments associated with the command.
     *
     * @return the arguments string, or an empty string if none are provided
     */
    public String getArguments() {
        return arguments;
    }

    /**
     * Checks if the arguments associated with the command are provided.
     *
     * @param args
     * @param command
     * @throws MissingArgumentException
     */
    public static void requireArgs(String args, String command) throws MissingArgumentException {
        if (args.isEmpty()) throw new MissingArgumentException(command);
    }
}
