public class Parser {
    private final String command;
    private final String arguments;

    public Parser(String userInput) {
        String[] parts = userInput.split("\\s+", 2); //split into command and arguments
        this.command = parts[0].toLowerCase();
        this.arguments = parts.length > 1 ? parts[1] : "";
    }

    public String getCommand() {
        return command;
    }

    public String getArguments() {
        return arguments;
    }

    //require arguments helper function
    public static void requireArgs(String args, String command) throws MissingArgumentException {
        if (args.isEmpty()) throw new MissingArgumentException(command);
    }
}
