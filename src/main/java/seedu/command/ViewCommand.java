package seedu.command;

import seedu.contact.Contact;
import seedu.ui.TextUi;

public class ViewCommand extends Command {
    private final int index;

    public ViewCommand(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void execute() {
        try {
            Contact viewingContact = contactList.getContactAtIndex(index);
            TextUi.viewContactMessage(viewingContact, index);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            TextUi.numOutOfRangeMessage(contactList.getListSize() - 1);
        }
    }
}
