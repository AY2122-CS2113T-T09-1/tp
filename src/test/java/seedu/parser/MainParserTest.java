package seedu.parser;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import seedu.command.AddContactCommand;
import seedu.command.Command;
import seedu.command.DeleteContactCommand;
import seedu.command.FailedCommand;
import seedu.command.ViewCommand;
import seedu.contact.ContactList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MainParserTest {
    private MainParser mainParser;
    private String testUserInput;
    private int testIndex; // For commands that require index

    @BeforeEach
    public void setUp() {
        mainParser = new MainParser();
    }

    /**
     * Returns type of Command the parseCommand returns. This is required because most of the MainParser's
     * internal parsing methods are private.
     * @param userInput Expected user input
     * @param commandClass Expected Command class/type returned
     * @param <T> Type of Command expected
     * @return Command Returns a Command for verification
     */
    @SuppressWarnings("unchecked")
    private <T extends Command> T getParsedCommand(String userInput, Class<T> commandClass) {
        // This is because user input is normally trimmed by TextUi
        String trimmedUserInput = userInput.trim();
        final Command result = mainParser.parseCommand(trimmedUserInput);
        assertTrue(commandClass.isInstance(result));
        return (T) result;
    }


    @Test
    public void parseDeleteCommand_validIndex_expectDeleteContactIndexMatch() {
        testIndex = 1;
        testUserInput = "rm " + testIndex;
        final DeleteContactCommand testResultCommand = getParsedCommand(testUserInput, DeleteContactCommand.class);
        assertEquals(testIndex, testResultCommand.getDeletedIndex());
    }

    @Test
    public void parseDeleteCommand_validIndexWithSpaces_expectDeleteContactIndexMatch() {
        testIndex = 1;
        testUserInput = "rm    " + testIndex;
        final DeleteContactCommand testResultCommand = getParsedCommand(testUserInput, DeleteContactCommand.class);
        assertEquals(testIndex, testResultCommand.getDeletedIndex());
    }

    @Test
    public void parseDeleteCommand_missingIndex_expectFailedCommandType() {
        testUserInput = "rm";
        FailedCommandType expectedFailedCommandType = FailedCommandType.MISSING_ARG;
        final FailedCommand actualFailedCommand = getParsedCommand(testUserInput, FailedCommand.class);
        assertEquals(expectedFailedCommandType, actualFailedCommand.getType());
    }

    @Test
    public void parseDeleteCommand_invalidNumber_expectFailedCommandType() {
        testUserInput = "rm abc";
        FailedCommandType expectedFailedCommandType = FailedCommandType.INVALID_INDEX;
        final FailedCommand actualFailedCommand = getParsedCommand(testUserInput, FailedCommand.class);
        assertEquals(expectedFailedCommandType, actualFailedCommand.getType());
    }

    @Test
    public void parseDeleteCommand_invalidIndexFormatting_expectFailedCommandType() {
        testUserInput = "rm 1 2";
        FailedCommandType expectedFailedCommandType = FailedCommandType.INVALID_INDEX;
        final FailedCommand actualFailedCommand = getParsedCommand(testUserInput, FailedCommand.class);
        assertEquals(expectedFailedCommandType, actualFailedCommand.getType());
    }

    @Test
    public void parseDeleteCommand_outOfRangeIndex_expectFailedCommandType() {
        ContactList contactList = new ContactList();
        testUserInput = "rm 3";
        // adding 1 input contact into contactList
        String addContactInput = "add -n wangyih -g ongzai";
        AddContactCommand addContactCommand = getParsedCommand(addContactInput, AddContactCommand.class);
        addContactCommand.setContactList(contactList);
        addContactCommand.execute();
        // trying to delete the contact with index 3 when there is only 1 contact (with index 0) in the contact list
        DeleteContactCommand deleteContactCommand = getParsedCommand(testUserInput, DeleteContactCommand.class);
        deleteContactCommand.setContactList(contactList);
        int deletedIndex = deleteContactCommand.getDeletedIndex();

        assertThrows(IndexOutOfBoundsException.class, () -> contactList.deleteContact(deletedIndex));
    }


    @Test
    public void parseAddCommand_validInputsWithIrregularSpaces_expectAddContactCommand() {
        testUserInput = "         add -n   andre -g  ng-andre  ";
        AddContactCommand actualCommand = getParsedCommand(testUserInput, AddContactCommand.class);
        AddContactCommand expectedCommand = new AddContactCommand("andre", "ng-andre");
        assertEquals(expectedCommand.getName(), actualCommand.getName());
        assertEquals(expectedCommand.getGithub(), actualCommand.getGithub());
    }

    @Test
    public void parseAddCommand_validInputsWithExtraCharacters_expectAddContactCommand() {
        testUserInput = "         add   1231267azldasd -n   marcus    -g  marcus-bory  ";
        AddContactCommand actualCommand = getParsedCommand(testUserInput, AddContactCommand.class);
        AddContactCommand expectedCommand = new AddContactCommand("marcus", "marcus-bory");
        assertEquals(expectedCommand.getName(), actualCommand.getName());
        assertEquals(expectedCommand.getGithub(), actualCommand.getGithub());
    }

    @Test
    public void parseAddCommand_validInputs_expectAddContactCommand() {
        testUserInput = "add -n andre -g ng-andre";
        final AddContactCommand actualCommand = getParsedCommand(testUserInput, AddContactCommand.class);
        final AddContactCommand expectedCommand = new AddContactCommand("andre", "ng-andre");
        assertEquals(expectedCommand.getName(), actualCommand.getName());
        assertEquals(expectedCommand.getGithub(), actualCommand.getGithub());
    }

    @Test
    public void parseViewCommand_validIndex_expectViewContactIndexMatch() {
        final int testIndex = 1;
        final String testUserInput = "view " + testIndex;
        final ViewCommand testResultCommand = getParsedCommand(testUserInput, ViewCommand.class);
        assertEquals(testIndex,testResultCommand.getIndex());
    }

    @Test
    public void parseViewCommand_invalidIndex_expectException() {
        testIndex = 99999;
        final ViewCommand testResultCommand = new ViewCommand(testIndex);
        assertThrows(NullPointerException.class, testResultCommand::execute);
    }

    @Test
    public void parseAddCommand_missingName_expectFailedCommand() {
        testUserInput = " add -g      github ";
        final FailedCommand actualCommand = getParsedCommand(testUserInput, FailedCommand.class);
        final FailedCommand expectedCommand = new FailedCommand(FailedCommandType.MISSING_NAME);
        assertEquals(expectedCommand.getType(), actualCommand.getType());
    }
}

