package seedu.ui;

import seedu.contact.Contact;

import java.util.Scanner;

public class TextUi {
    private static final String LOGO = "   _____         _______        _     \n"
            + "  / ____|       |__   __|      | |    \n" + " | |     ___  _ __ | | ___  ___| |__  \n"
            + " | |    / _ \\| '_ \\| |/ _ \\/ __| '_ \\ \n" + " | |___| (_) | | | | |  __/ (__| | | |\n"
            + "  \\_____\\___/|_| |_|_|\\___|\\___|_| |_|\n" + "                                      \n";

    private static final String LINE = "____________________________________________________________\n";

    private final Scanner scanner;

    public TextUi() {
        scanner = new Scanner(System.in);
        initMessage();
    }

    public String getUserInput() {
        return scanner.nextLine().trim();
    }

    // Used for print messages after user inputs
    private static void printDoubleLineMessage(String message) {
        System.out.println(LINE + message + "\n" + LINE);
    }

    // Used for system messages without user inputs
    private static void printBottomLineMessage(String message) {
        System.out.println(message + "\n" + LINE);
    }

    // Used to print a line after displayed data
    public static void printBottomLine() {
        System.out.println("\n" + LINE);
    }

    private static void initMessage() {
        printDoubleLineMessage(LOGO);
        System.out.println("Welcome to ConTech, your personal contact tracker.\n" + LINE);
    }

    public static void createNewContactFileMessage(String contactFilePath) {
        String message = "As ConTech is unable to find your saved data, \n" + " it has created a new one for you at: \n"
                + contactFilePath;
        printBottomLineMessage(message);
    }

    public static void addContactMessage(String contactName, int listSize) {
        String message = "ConTech has added the specified contact: " + contactName + "\n" + "You now have " + listSize
                + " contact(s).";
        printDoubleLineMessage(message);
    }

    public static void editContactMessage(String contactName) {
        String message = "ConTech has edited the specified contact: " + contactName;
        printDoubleLineMessage(message);
    }

    public static void viewContactMessage(Contact viewingContact, int index) {
        String message = index + ". " + "Name: " + viewingContact.getName() + " GitHub: " + viewingContact.getGithub();
        printDoubleLineMessage(message);
    }

    public static void printContactWithIndex(int index, String contactName) {
        System.out.println(index + ". " + contactName);
    }

    public static void contactsListMessage(int listSize) {
        String message = "ConTech has " + listSize + " contacts stored.\n" + "Here's the list :";
        printDoubleLineMessage(message);
    }

    public static void contactsEmptyListMessage() {
        String message = "You have not stored any contacts in ConTech";
        printDoubleLineMessage(message);
    }

    public static void deleteContactMessage(String contactName, int listSize) {
        String message = "ConTech has removed the specified contact: " + contactName + "\n" + "You now have " + listSize
                + " contact(s).";
        printDoubleLineMessage(message);
    }

    public static void exitMessage() {
        String message = "ConTech will now shutdown.\n" + "We hope you have enjoyed using it.";
        printDoubleLineMessage(message);
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
                + "Please try again with a valid flag.\n" + "  -n NAME\n" + "  -g GITHUB";
        printDoubleLineMessage(message);
    }

    public static void invalidIndexMessage() {
        String message = "Please enter a valid contact index.";
        printDoubleLineMessage(message);
    }

    public static void missingDetailMessage() {
        String message = "There are missing details.\n" + "Please remove any flags with no details";
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
}
