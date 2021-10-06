package seedu.storage;

import seedu.contact.Contact;
import seedu.contact.ContactList;
import seedu.exception.FileErrorException;

import java.io.FileWriter;
import java.io.IOException;

import static seedu.storage.Storage.SEPARATOR;

public class ContactsEncoder {
    public static void saveContacts(String contactFilePath, ContactList contactList) throws FileErrorException {
        try {
            FileWriter fileWriter = new FileWriter(contactFilePath);
            for (int i = 0; i < contactList.getListSize(); i++) {
                Contact currentContact = contactList.getContactAtIndex(i);
                String encodedContact = encodeContact(currentContact);
                fileWriter.write(encodedContact);
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new FileErrorException();
        }
    }

    private static String encodeContact(Contact contact) {
        String stringifiedContact = contact.getName() + SEPARATOR + contact.getGithub() + "\n";
        return stringifiedContact;
    }
}
