package seedu.parser;

import seedu.command.AddContactCommand;
import seedu.command.EditContactCommand;
import seedu.command.DeleteContactCommand;
import seedu.command.Command;
import seedu.command.ViewCommand;
import seedu.command.FailedCommand;
import seedu.command.ExitCommand;
import seedu.command.ListContactsCommand;

import seedu.contact.DetailType;
import seedu.exception.InvalidFlagException;
import seedu.exception.MissingArgException;
import seedu.exception.MissingDetailException;

import java.util.Arrays;

public class MainParser {
    private static final String ADD_CONTACT_COMD = "add";
    private static final String EDIT_CONTACT_COMD = "edit";
    private static final String DELETE_CONTACT_COMD = "rm";
    private static final String EXIT_COMD = "exit";
    private static final String VIEW_COMD = "view";
    private static final String LIST_COMD = "list";

    private static final int COMD_WORD_INDEX = 0;
    private static final int EDIT_USER_INDEX = 1;
    private static final int ISOLATE_COMD_WORD = 2;
    private static final int ISOLATE_USER_INPUT = 2;
    public static final int NUMBER_OF_EDIT_DETAILS = 3;
    public static final int NAME_INDEX = 0;

    private ContactParser contactParser;
    private AddContactParser addContactParser = new AddContactParser();
    private EditContactParser editContactParser = new EditContactParser();

    public Command parseCommand(String userInput) {
        String commandType = getCommandWord(userInput);
        Command command;
        switch (commandType) {
        case ADD_CONTACT_COMD:
            command = parseAddContact(userInput);
            break;
        case EDIT_CONTACT_COMD:
            command = parseEditContact(userInput);
            break;
        case DELETE_CONTACT_COMD:
            command = parseDeleteContact(userInput);
            break;
        case EXIT_COMD:
            command = new ExitCommand();
            break;
        case VIEW_COMD:
            command = parseViewContact(userInput);
            break;
        case LIST_COMD:
            command = new ListContactsCommand();
            break;
        default:
            command = new FailedCommand(FailedCommandType.GENERAL);
        }
        return command;
    }

    public String getCommandWord(String userInput) {
        String[] destructuredInputs = userInput.split(" ", ISOLATE_COMD_WORD);
        return destructuredInputs[COMD_WORD_INDEX];
    }

    private Command parseAddContact(String userInput) {
        contactParser = addContactParser;
        try {
            String[] details = contactParser.parseContactDetails(userInput);
            //check if name is specified in input
            if (details[NAME_INDEX] == null) {
                throw new MissingDetailException();
            }
            return new AddContactCommand(details[DetailType.NAME.getIndex()], details[DetailType.GITHUB.getIndex()]);
        } catch (InvalidFlagException e) {
            return new FailedCommand(FailedCommandType.INVALID_FLAG);
        } catch (MissingArgException e) {
            return new FailedCommand(FailedCommandType.MISSING_ARG);
        } catch (MissingDetailException e) {
            return new FailedCommand(FailedCommandType.MISSING_DETAIL);
        }
    }

    private Command parseEditContact(String userInput) { // userInput is raw user input
        contactParser = editContactParser;
        try {
            // split into array of size 3 with command, index and details
            String[] details = userInput.split(" ", NUMBER_OF_EDIT_DETAILS);
            if (details[ISOLATE_USER_INPUT].trim().equalsIgnoreCase("")) {
                throw new MissingDetailException();
            }
            int userIndex = Integer.parseInt(details[EDIT_USER_INDEX]);
            String[] userDetails = editContactParser.parseContactDetails(details[ISOLATE_USER_INPUT]);
            return new EditContactCommand(userDetails, userIndex);
        } catch (InvalidFlagException e) {
            return new FailedCommand(FailedCommandType.INVALID_FLAG);
        } catch (IndexOutOfBoundsException | MissingDetailException e) {
            return new FailedCommand(FailedCommandType.MISSING_ARG);
        } catch (NumberFormatException e) {
            return new FailedCommand(FailedCommandType.INVALID_INDEX);
        }
    }

    private Command parseViewContact(String userInput) {
        String[] destructuredInputs = userInput.split(" ", ISOLATE_COMD_WORD);
        if (destructuredInputs.length == 1) {
            return new FailedCommand(FailedCommandType.MISSING_ARG);
        }
        if (destructuredInputs.length > 2) {
            return new FailedCommand(FailedCommandType.INVALID_FORMAT);
        }
        try {
            return new ViewCommand(Integer.parseInt(destructuredInputs[1]));
        } catch (NumberFormatException e) {
            return new FailedCommand(FailedCommandType.INVALID_NUM);
        }
    }

    private Command parseDeleteContact(String userInput) {
        String[] destructuredInputs = userInput.split(" ");
        if (destructuredInputs.length == 1) {
            return new FailedCommand(FailedCommandType.MISSING_ARG);
        }
        if (destructuredInputs.length > 2) {
            return new FailedCommand(FailedCommandType.INVALID_FORMAT);
        }
        try {
            int deletedIndex = Integer.parseInt(destructuredInputs[1]);
            return new DeleteContactCommand(deletedIndex);
        } catch (NumberFormatException e) {
            return new FailedCommand(FailedCommandType.INVALID_NUM);
        }
    }

    // Will create a function to parse details soon, hardcoding for V0.1 (7/10/21
    // version)
    // private String parseDetail(String contactParameters, String tag) {
    //
    // }

}
