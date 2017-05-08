package nl.nuggit.kalah;

public class Move {

    private final Game game;
    private int pit;

    public Move(Game game) {
        this.game = game;
    }

    public void from(int pit) {
        this.pit = pit;
        checkGameEnded();
        validateMove(pit);
        int stones = game.takeStones(pit);
        int currentPit = pit;
        while (stones > 0) {
            currentPit = findNextPit(currentPit);
            game.putStones(currentPit, 1);
            stones--;
        }
        handleLandingOnOwnEmptyPit(currentPit);
        if (currentPit != Game.HOUSE[game.getCurrentPlayer()]) {
            game.endTurn();
        }
    }

    private void checkGameEnded() {
        int stonesLeft = 0;
        int currentPlayer = game.getCurrentPlayer();
        for (int i = Game.HOUSE[currentPlayer] - Game.PITS; i < Game.HOUSE[currentPlayer]; i++) {
            stonesLeft += game.countStones(i);
        }
        if (stonesLeft == 0) {
            game.setGameEnded(true);
            int otherPlayer = game.getOtherPlayer();
            for (int i = Game.HOUSE[otherPlayer] - Game.PITS; i < Game.HOUSE[otherPlayer]; i++) {
                game.takeStones(i);
            }
        }
    }

    private void handleLandingOnOwnEmptyPit(int currentPit) {
        if (game.countStones(currentPit) == 1) {
            int pitOwner = game.findOwner(currentPit);
            int currentPlayer = game.getCurrentPlayer();
            if (currentPlayer == pitOwner && currentPit != Game.HOUSE[currentPlayer]) {
                int oppositePit = game.findOppositePit(currentPit);
                int stones = game.takeStones(oppositePit) + game.takeStones(currentPit);
                game.putStones(Game.HOUSE[currentPlayer], stones);
            }
        }
    }

    private int findNextPit(int pit) {
        int nextPit = pit + 1;
        if (nextPit == Game.HOUSE[game.getOtherPlayer()]) {
            nextPit = pit + 1;
        }
        if (nextPit >= (2 * Game.PITS + 2)) {
            nextPit = 0;
        }
        return nextPit;
    }

    private void validateMove(int pit) {
        if (pit == Game.HOUSE[0] || pit == Game.HOUSE[1]) {
            throw new RuntimeException("Cannot move from a house");
        }
        if (game.getCurrentPlayer() != game.findOwner(pit)) {
            throw new RuntimeException("Cannot move from opponents pit");
        }
        if (game.countStones(pit) == 0) {
            throw new RuntimeException("Cannot move from empty pit");
        }
    }


}
