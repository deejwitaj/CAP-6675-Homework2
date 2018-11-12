/**
 * Class containing GROFFMAN strategy
 * @author 181112DL
 */

import java.util.Random;

public class StrategyGROFFMAN extends Strategy
{
    // 0 = defect, 1 = cooperate

    int myLastMove;

    public StrategyGROFFMAN()
    {
        name = "Groffman";
        opponentLastMove = 1;
        myLastMove = 1;
    }

    public int nextMove()
    {
        if (myLastMove == opponentLastMove)
        {
            myLastMove = 1;
            return 1;
        }

        Random rand = new Random();
        if((rand.nextInt(7) + 1) < 3)
            return 1;
        return 0;

    }
}