package main;

import main.cards.*;
import main.players.Player;
import java.util.*;

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

    public enum Message {
        ASK_FOR_PARAMETER("GIVE PARAMETER"),
        PLAYER_CARD("YOUR CARD"),
        CPU_CARD("CPU CARD"),
        DRAW("DRAW");

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
            centeredString.append(" ");
        }

        centeredString.append(string);

        for (int i=0; i < margin+additionalMargin; i++) {
            centeredString.append(" ");
        }
        return centeredString.toString();
    }

    private void printLine(Position position, int cardWidth) {
        System.out.print(position.getLeftMark());
        for (int i=0; i<cardWidth; i++) {
            System.out.print("-");
        }
        System.out.println(position.getRightMark());
    }

    public void printCard(Card card) {
        int CARD_WIDTH = 25;
        String[] parameters = card.getCardParameters();
        int index = 1;
        printLine(Position.TOP, CARD_WIDTH);
        System.out.printf("|%s|\n", centerString(card.getName(), CARD_WIDTH));
        printLine(Position.MIDDLE, CARD_WIDTH);
        for (String parameter : parameters) {
            parameter = String.format("%s - %d", parameter, card.getParameter(index));
            System.out.println("|" + centerString(parameter, CARD_WIDTH) + "|");
            index++;
        }
        printLine(Position.BOTTOM, CARD_WIDTH);
    }

    public void printMessage(Message message) {
        System.out.println(message.getMessage());
    }

    public void printMainMenu() {
        String[] mainMenu = {"Play", "How to Play", "Exit"};
        String gameTitle = "BATTLE OF PKS";

        System.out.printf("%s\n", gameTitle);
        for (int i=0; i<mainMenu.length; i++) {
            System.out.printf("\t%d) %s\n", i+1, mainMenu[i]);
        }
    }

    public void printWinner(Player winner) {
        System.out.printf("%s won!\n", winner.getName());
        System.out.printf("%s won with %d cards in his hand\n", winner.getName(), winner.getHand().getAmountOfCardsInHand());
    }

    public void printGameRules() {
        System.out.println("All the cards are dealt among the players. There must be at least two players, and at least one card for each player. The starting player (normally the player sitting on the dealer's left) selects a category from his or her topmost card and reads out its value. Each other player then reads out the value of the same category from their cards. The best (usually the largest) value wins the \"trick\", and the winner takes all the cards of the trick and places them at the bottom of his or her pile. That player then looks at their new topmost card, and chooses the category for the next round. Ace introduced the Super Trump, a card that beats all other cards except “A” cards regardless of its data.In the event of a draw, the cards are placed in the centre and a new category is chosen from the next card by the same person as in the previous round. The winner of that round obtains all of the cards in the centre as well as the top card from each player.The game ends when one player has won all of the card off the other players.\n");

    }
}
