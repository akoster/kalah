package nl.nuggit.kalah;

public class Game {

    public static final int PITS = 6;
    public static final int STONES = 6;
    public static final int[] HOUSE = {PITS, 2 * PITS + 1};

    private String[] players = new String[2];
    private int currentPlayer;
    private boolean gameEnded;

    private int[] board = new int[2 * PITS + 2];

    public Game(String playerA, String playerB) {
        players[0] = playerA;
        players[1] = playerB;
        initBoard();
        currentPlayer = 0;
    }

    private void initBoard() {
        for (int i = 0; i < board.length; i++) {
            board[i] = STONES;
        }
        board[HOUSE[0]] = 0;
        board[HOUSE[1]] = 0;
    }

    public String[] getPlayers() {
        return players;
    }

    public String getCurrentPlayerName() {
        return players[currentPlayer];
    }

    public boolean isGameEnded() {
        return gameEnded;
    }

    public int[] getPoints() {
        return new int[]{board[HOUSE[0]], board[HOUSE[1]]};
    }

    public void setGameEnded(boolean gameEnded) {
        this.gameEnded = gameEnded;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public int getOtherPlayer() {
        return Math.abs(currentPlayer - 1);
    }

    public int endTurn() {
        return currentPlayer = Math.abs(currentPlayer - 1);
    }

    public int[] getBoard() {
        return board;
    }

    public int countStones(int pit) {
        return board[pit];
    }

    public int takeStones(int pit) {
        int stones = board[pit];
        board[pit] = 0;
        return stones;
    }

    public void putStones(int pit, int amount) {
        board[pit] += amount;
    }

    public int findOwner(int pit) {
        if (pit <= HOUSE[0]) {
            return 0;
        } else {
            return 1;
        }
    }

    public int findOppositePit(int pit) {
        if (pit < 0 || pit >= board.length || pit == HOUSE[0] || pit == HOUSE[1]) {
            throw new RuntimeException("Cannot find opposite of " + pit);
        }
        return 2 * Game.PITS - pit;
    }
}
