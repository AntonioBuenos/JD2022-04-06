package by.it.smirnov.calc.constants;

import java.io.File;

public interface Wordings {

    String END = "end";
    String EN = "en";
    String FR = "fr";
    String JP = "jp";
    String RU = "ru";
    String BE = "be";
    String USER_DIR = "user.dir";
    String FILE_REPO = "txtfiles" + File.separator + "Vars.txt";
    String LOG_TXT = "txtfiles" + File.separator + "log.txt";
    String REPORT_TXT = "txtfiles" + File.separator + "Report.txt";
    String TITLE_SHORT = "SHORT REPORT:";
    String TITLE_LONG = "LONG REPORT:";
    String REPORT_END = " - Calculator work end";
    String REPORT_START = " - Calculator work start";
    String BAD_STRING = "error.badstring";
    String BAD_SIZE = "error.badsize";
    String BAD_OPER = "error.badoper";
    String DIV_ZERO = "error.zero";
    String ERR = "error.error";
    String NOT_FOUND = "error.notfound";
    String VAR_UNKNOWN = "error.varunknown";
    String NO_FILE = "error.nofile";
    String ENDING = "message.ending";
    String START = "message.start";
    String CHANGE_LOCAL = "message.changelocale";

}