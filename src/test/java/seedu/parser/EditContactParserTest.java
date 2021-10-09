package seedu.parser;

import org.junit.jupiter.api.Test;

import seedu.exception.ForbiddenDetailException;
import seedu.exception.InvalidFlagException;
import seedu.exception.MissingArgException;
import seedu.exception.MissingDetailException;
import seedu.parser.EditContactParser;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EditContactParserTest {
    private EditContactParser editContactParser = new EditContactParser();

    @Test
    void parseContactDetails() throws MissingDetailException, MissingArgException, InvalidFlagException, ForbiddenDetailException {
        String[] expectedResult = {null, "github"};
        String testInput = "edit 1 -g github";
        String[] actualResult = editContactParser.parseContactDetails(testInput);
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    void getIndex() throws MissingArgException {
        int expectedResult = 3;
        String testInput = "edit 3 ";
        int actualResult = editContactParser.getIndex(testInput);
        assertEquals(expectedResult, actualResult);
    }
}
