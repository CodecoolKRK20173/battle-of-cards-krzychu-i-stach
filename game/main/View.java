package main;

import main.cards.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class View {

    private enum Position {
        TOP("/", "\\"),
        BOTTOM("\\", "/"),
        MIDDLE("|", "|");

        String left;
        String right;

        private Position(String left, String right) {
            this.left = left;
            this.right = right;
        }

        private String getLeftMark() {
            return left;
        }

        private String getRightMark() {
            return right;
        }
    }

    private enum Message {
        ASK_FOR_PARAMETER("Give parameter");

        String message;

        private Message(String message) {
            this.message = message;
        }

        private String getMessage() {
            return message;
        }
    }

    private String centerString(String string, Integer widthGoal) {
        StringBuilder centeredString = new StringBuilder();
        int stringLenght = string.length();
        int margin = widthGoal - stringLenght;
        int additionalMargin = 0;

        if (margin%2 != 0) {
            additionalMargin = 1;
        }
        margin /= 2;

        for (int i=0; i < margin; i++) {
            centeredString.append("\\s");
        }

        centeredString.append(string);

        for (int i=0; i < margin+additionalMargin; i++) {
            centeredString.append("\\s");
        }
        return centeredString.toString();
    }

    private void printLine(Position position, int cardWidth) {
        System.out.print(position.getLeftMark());
        for (int i=0; i<cardWidth; i++) {
            System.out.print("[-]");
            System.out.println(position.getRightMark());
        }
    }

    public void printCard(Card card) {
        int CARD_WIDTH = 20;
        Map<String, Integer> parametersMap = card.getCardParametersAndValues();
        Set<String> parameters = card.getCardParameters();

        printLine(Position.TOP, CARD_WIDTH);
        System.out.printf("|%s|", centerString(card.getName(), CARD_WIDTH));
        printLine(Position.MIDDLE, CARD_WIDTH);
        for (String parameter : parameters) {
            parameter = String.format("|%s - %d|", parameter, parametersMap.get(parameter));
            System.out.println(centerString(parameter, CARD_WIDTH));
            printLine(Position.MIDDLE, CARD_WIDTH);
        }
        printLine(Position.BOTTOM, CARD_WIDTH);
    }

    public void sayMessage(Message message) {
        System.out.println(message.getMessage());
    }

    public void printMainMenu() {
        String[] mainMenu = {"Play", "How to Play", "Exit"};
        String gameTitle = "BATTLE OF CARDS";

        System.out.printf("%s\n", gameTitle);
        for (int i=0; i<mainMenu.length; i++) {
            System.out.printf("\t%d) %s\n", i+1, mainMenu[i]);
        }
    }
}
