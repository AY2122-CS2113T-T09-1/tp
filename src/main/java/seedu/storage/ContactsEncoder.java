package seedu.storage;

import seedu.contact.Contact;
import seedu.contact.ContactList;
import seedu.contact.PersonalContact;
import seedu.exception.FileErrorException;

import java.io.FileWriter;
import java.io.IOException;

import static seedu.storage.Storage.SEPARATOR;

public class ContactsEncoder {
    public static void savePersonalContact(String contactFilePath, PersonalContact personalContact) throws FileErrorException {
        try {
            FileWriter fileWriter = new FileWriter(contactFilePath);
            String encodedContact = encodeContact(personalContact);
            fileWriter.write(encodedContact);
            fileWriter.close();
        } catch (IOException e) {
            throw new FileErrorException();
        }
    }

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
        String stringifiedContact = contact.getName() + SEPARATOR + contact.getGithub() + SEPARATOR
                + contact.getLinkedin() + SEPARATOR + contact.getTelegram() + SEPARATOR
                + contact.getTwitter() + SEPARATOR + contact.getEmail() + "\n";
        return stringifiedContact;
    }
}
