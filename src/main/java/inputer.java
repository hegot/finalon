import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class inputer {

    public static void main(String[] args) {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e2) {
            e2.printStackTrace();
        }

        robot.delay(3000);

        threeInARow(robot, "91612", "92158", "94671");
        threeInARow(robot, "47553", "48111", "49364");
        threeInARow(robot, "44059", "44047", "45307");
        threeInARow(robot, "", "", "");
        threeInARow(robot, "", "", "");
        threeInARow(robot, "", "", "");
        threeInARow(robot, "33019", "30762", "31703");
        threeInARow(robot, "", "", "");
        threeInARow(robot, "11040", "13285", "13604");
        threeInARow(robot, "", "", "");
        threeInARow(robot, "", "", "");
        threeInARow(robot, "", "", "");
        threeInARow(robot, "", "", "");
        threeInARow(robot, "772", "850", "911");
        threeInARow(robot, "", "", "");
        threeInARow(robot, "", "", "");
        threeInARow(robot, "", "", "");
        threeInARow(robot, "", "", "");
        threeInARow(robot, "", "", "");
        threeInARow(robot, "", "", "");
        threeInARow(robot, "10268", "12435", "12693");
        threeInARow(robot, "", "", "");
        threeInARow(robot, "3367", "3256", "3089");
        threeInARow(robot, "6901", "9179", "9604");
        threeInARow(robot, "6901", "9179", "9604");
        threeInARow(robot, "8003", "1264", "5575");
        threeInARow(robot, "14904", "10443", "15179");
    }

    private static void threeInARow(Robot robot, String string, String string2, String string3) {
        putPasteClipboard(string, robot);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        putPasteClipboard(string2, robot);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        putPasteClipboard(string3, robot);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
    }

    private static void putPasteClipboard(String text, Robot robot) {
        String myString = text;
        StringSelection stringSelection = new StringSelection(myString);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, null);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(40);
        type("V", robot);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    private static void type(String s, Robot robot) {
        byte[] bytes = s.getBytes();
        for (byte b : bytes) {
            int code = b;
            // keycode only handles [A-Z] (which is ASCII decimal [65-90])
            if (code > 96 && code < 246) code = code - 32;
            robot.delay(40);
            robot.keyPress(code);
            robot.keyRelease(code);
        }
    }
}