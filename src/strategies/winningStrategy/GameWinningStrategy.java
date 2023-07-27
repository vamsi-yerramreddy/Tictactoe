package strategies.winningStrategy;

import models.Board;
import models.Cell;
import models.Player;

public interface GameWinningStrategy {
    boolean checkWinner(Board board , Player  lastMovePlayer, Cell moveCell );
    void updateMaps(Board board, Cell moveCell);
}
