package seedu.command;

import seedu.parser.FailedCommandType;
import seedu.ui.TextUi;

public class FailedCommand extends Command {
    private FailedCommandType type;

    public FailedCommand(FailedCommandType type) {
        this.type = type;
    }

    public FailedCommandType getType() {
        return type;
    }

    public void execute() {
        switch (type) {
        case GENERAL:
            TextUi.invalidCommandMessage();
            break;
        case INVALID_FLAG:
            TextUi.invalidFlagMessage();
            break;
        case INVALID_INDEX:
            TextUi.invalidIndexMessage();
            break;
        case MISSING_ARG:
            TextUi.missingArgMessage();
            break;
        case MISSING_DETAIL:
            TextUi.missingDetailMessage();
            break;
        case INVALID_NUM:
            TextUi.invalidNumMessage();
            break;
        case INVALID_FORMAT:
            TextUi.invalidFormatMessage();
            break;
        case NUM_OUT_OF_BOUND:
            TextUi.numOutOfRangeMessage(contactList.getListSize() - 1);
            break;
        case MISSING_NAME:
            TextUi.missingNameMessage();
            break;
        case FORBIDDEN_DETAIL:
            TextUi.forbiddenDetailMessage();
            break;
        default:
            return;
        }
    }
}
