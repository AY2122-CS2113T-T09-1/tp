package seedu.ui;

public class ExceptionTextUi {
    private static final String LINE = "____________________________________________________________\n";

    // Used for print messages after user inputs
    private static void printDoubleLineMessage(String message) {
        System.out.println(LINE + message + "\n" + LINE);
    }

    // Used for system messages without user inputs
    private static void printBottomLineMessage(String message) {
        System.out.println(message + "\n" + LINE);
    }

    private static void printTopLineMessage(String message) {
        System.out.println(LINE + "\n" + message);
    }

    // Used to print a line after displayed data
    public static void printBottomLine() {
        System.out.println("\n" + LINE);
    }

    // Error Messages
    public static void fileErrorMessage(String contactFilePath) {
        String message = "ConTech is unfortunately unable to access / create a\n" + " save file at " + contactFilePath
                + ".\n" + "Please relocate the file and try again.";
        printBottomLineMessage(message);
    }

    public static void invalidCommandMessage() {
        String message = "ConTech is unable to understand your request.\n" + "Please try again with a valid command.";
        printDoubleLineMessage(message);
    }

    public static void invalidFlagMessage() {
        String message = "There appears to be a flag that is not recognised.\n"
                + "Please try again with a valid flag.\n" + "  -n NAME\n" + "  -g GITHUB\n" + "  -l LINKEDIN\n"
                + "  -te TELEGRAM\n" + "  -tw TWITTER\n" + "  -e EMAIL";
        printDoubleLineMessage(message);
    }

    public static void invalidIndexMessage() {
        String message = "Please enter a valid contact index.";
        printDoubleLineMessage(message);
    }

    public static void missingDetailMessage() {
        String message = "There are missing details.\n" + "Please remove any flags with no details, \n"
                + "and ensure that your flags used are correct:\n" + "  -n NAME\n" + "  -g GITHUB\n" + "  -l LINKEDIN\n"
                + "  -te TELEGRAM\n" + "  -tw TWITTER\n" + "  -e EMAIL";
        printDoubleLineMessage(message);
    }

    public static void missingArgMessage() {
        String message = "There seems to be missing parameters in your request.\n" + "Please specify your command.";
        printDoubleLineMessage(message);
    }

    public static void invalidNumMessage() {
        String message = "That does not seem to be a number.\n" + "Please provide a number instead.";
        printDoubleLineMessage(message);
    }

    public static void invalidFormatMessage() {
        String message = "ConTech is unable to understand your request.\n"
                + "The request has not been formatted correctly. Please try again.";
        printDoubleLineMessage(message);
    }

    public static void missingNameMessage() {
        String message = "There are missing details.\n"
                + "Please specify a name when creating a contact with the flag -n";
        printDoubleLineMessage(message);
    }

    public static void numOutOfRangeMessage(int listSize) {
        String message;
        int maxIndex = listSize - 1;
        if (listSize == 0) {
            message = "There are no contacts stored in ConTech.";
        } else if (listSize == 1) {
            message = "The number you have input is out of range.\n"
                    + "You only have 1 contact stored.";
        } else {
            message = "The number you have input is out of range.\n"
                    + "Please input a number between 0 and " + maxIndex + ".";
        }
        printDoubleLineMessage(message);
    }

    public static void corruptLineMessage(String line) {
        printBottomLineMessage("Line \"" + line + "\" is corrupted and not loaded.");
    }

    public static void forbiddenInputCommaMessage(String newUserInput) {
        String message = "Due to the storage nature of ConTech, we will remove\n"
                + "commas (\",\"), and attempt to parse it as:\n"
                + newUserInput;
        printTopLineMessage(message);
    }

    public static void forbiddenDetailMessage() {
        String message = "As one of the details to be stored is \"null\", \n"
                + "ConTech is unable to process it";
        printDoubleLineMessage(message);
    }

    public static void invalidNameInput() {
        String message = "The name is not correctly formatted,\n"
                + "Rules for name :\n"
                + "    * Uppercase and lowercase letters\n"
                + "    * Spaces\n"
                + "    * No numbers or special characters\n"
                + "    * Cannot be \"null\"";
        printDoubleLineMessage(message);
    }

    public static void invalidGithubUsernameInput() {
        String message = "The github username is not correctly formatted,\n"
                + "Rules for Github username :\n"
                + "    * Only contain alphanumeric characters or hyphens\n"
                + "    * Only lowercase allowed\n"
                + "    * Maximum 39 characters allowed\n"
                + "    * Cannot have multiple consecutive hyphens\n"
                + "    * Cannot begin or end with a hyphen\n";
        printDoubleLineMessage(message);
    }

    public static void invalidTelegramUsernameInput() {
        String message = "The telegram username is not correctly formatted,\n"
                + "Rules for Telegram username :\n"
                + "    * Uppercase and lowercase letters allowed\n"
                + "    * Numbers and underscore allowed\n"
                + "    * Length at-least 5 characters";
        printDoubleLineMessage(message);
    }

    public static void invalidTwitterUsernameInput() {
        String message = "The twitter username is not correctly formatted";
        printDoubleLineMessage(message);
    }

    public static void invalidEmailInput() {
        String message = "The email id is not correctly formatted";
        printDoubleLineMessage(message);
    }

    public static void invalidLinkedinInput() {
        String message = "The linkedin username is not correctly formatted";
        printDoubleLineMessage(message);
    }
}
