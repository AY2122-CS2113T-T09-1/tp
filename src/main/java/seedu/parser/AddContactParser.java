package seedu.parser;

import seedu.exception.ForbiddenDetailException;
import seedu.exception.InvalidFlagException;
import seedu.exception.MissingArgException;
import seedu.exception.MissingDetailException;

public class AddContactParser extends ContactParser {
    /**
     * Returns a String[] containing the user's input and the arguments specified for
     * each detail flag. Each detail is parsed individually using parseDetail.
     * <p>
     * Eg. userInput => add -n andre -g ng-andre -te ng_andre -tw andre -e andre@gmail.com -l ng-andre
     *     output => ["andre", "ng-andre", "ng_andre", "ng-andre", "ng_andre", "andre", "andre@gmail.com"]
     * </p>
     * Note: Each index is properly identified by enumeration in contact/DetailType
     * @param userInput User's complete untouched input
     * @return String[] Returns an array of details
     * @throws InvalidFlagException If the flag given is not recognised
     */
    public String[] parseContactDetails(String userInput) throws InvalidFlagException, MissingArgException,
            MissingDetailException, ForbiddenDetailException {
        String[] contactDetails = new String[NUMBER_OF_DETAILS];
        String[] destructuredInputs = userInput.split(DETAIL_SEPARATOR);
        if (destructuredInputs.length == 1) {
            throw new MissingArgException();
        }
        for (int i = CONTACT_PARAMS_START_INDEX; i < destructuredInputs.length; i++) {
            parseDetail(contactDetails, destructuredInputs[i]);
        }
        return contactDetails;
    }
}
