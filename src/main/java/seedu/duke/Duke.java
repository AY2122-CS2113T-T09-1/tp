package seedu.duke;

import seedu.command.Command;
import seedu.contact.Contact;
import seedu.contact.ContactList;
import seedu.contact.PersonalContact;
import seedu.exception.FileErrorException;
import seedu.parser.MainParser;
import seedu.storage.ContactsEncoder;
import seedu.storage.Storage;
import seedu.ui.ExceptionTextUi;
import seedu.ui.UserInputTextUi;

public class Duke {
    private String contactFilePath;
    private Storage storage;
    private MainParser parser;
    private ContactList contactList;
    private String personalContactFilePath;
    private PersonalContact personalContact;

    public Duke(String contactFilePath, String personalContactFilePath) {
        this.contactFilePath = contactFilePath;
        this.personalContactFilePath = personalContactFilePath;
        this.parser = new MainParser();
        this.storage = new Storage(contactFilePath, personalContactFilePath);

        try {
            this.contactList = storage.loadExistingContacts();
            this.personalContact = storage.loadExistingPersonalContact();
        } catch (FileErrorException e) {
            ExceptionTextUi.fileErrorMessage(this.contactFilePath);
        }
    }

    private void runConTech() {
        Command command;
        do {
            String userInput = UserInputTextUi.getUserInput();
            command = parser.parseCommand(userInput);
            runCommandProcedure(command);
        } while (!command.isExit());
    }

    private void runCommandProcedure(Command command) {
        try {
            command.setContactList(contactList);
            command.setPersonalContact(personalContact);
            command.execute();
            ContactsEncoder.saveContacts(contactFilePath, contactList);
            ContactsEncoder.savePersonalContact(personalContactFilePath, personalContact);
        } catch (FileErrorException e) {
            ExceptionTextUi.fileErrorMessage(contactFilePath);
        }
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Duke("data/contacts.txt", "data/me.txt").runConTech();
    }
}
