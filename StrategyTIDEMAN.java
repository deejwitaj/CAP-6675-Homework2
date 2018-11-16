public class StrategyTIDEMAN extends Strategy{
    // 0 = defect, 1 = cooperate

    int T = 7;
    int R = 5;
    int P = 3;
    int S = 1;

    int punishmentAmount;
    int opponentSecondLastMove;
    int punishmentSeverity;
    int opponentScore;
    int myScore;
    int punishmentHistory;
    int movesLeft = 200;

    public StrategyTIDEMAN()
    {
        name = "Tideman";
        punishmentAmount = 0; //Number of punishments to be dealt
        opponentLastMove = 1;
        opponentSecondLastMove = 1;
        myLastMove = 1;
        punishmentSeverity = 1; //How many defects we are currently adding to the punishment for each back to back opponent defect
        opponentScore = 0;
        myScore = 0;
        punishmentHistory = 0; //Number of punishments that have been dealt to opponent back to back
    }

    public int nextMove()
    {
        movesLeft--;
        if (movesLeft < 3)
            return 0;

        UpdateScore();

        JudgeOpponent();

        opponentSecondLastMove = opponentLastMove;

        if (punishmentAmount == 0)
            return opponentLastMove;

        if (punishmentAmount < 0)
        {
            punishmentAmount++;
            myLastMove = 1;
            return 1;
        }

        punishmentAmount--;
        punishmentHistory++;
        myLastMove = 0;
        return 0;
    }

    public void UpdateScore()
    {
        if (opponentLastMove == 0 && myLastMove == 0)
        {
            opponentScore += P;
            myScore += P;
        }
        else if (opponentLastMove == 0 && myLastMove == 1)
        {
            opponentScore += T;
            myScore += S;
        }
        else if (opponentLastMove == 1 && myLastMove == 0)
        {
            opponentScore += S;
            myScore += T;
        }
        else if (opponentLastMove == 1 && myLastMove == 1)
        {
            opponentScore += R;
            myScore += R;
        }
    }

    public void JudgeOpponent()
    {
        if((opponentLastMove == 0) && (opponentSecondLastMove == 0))
        {
            for(int i = 0; i < punishmentSeverity; i++)
                punishmentAmount++;
        }

        if(bIsBehind() || bIsRepentant() || bSufferedEnough() || (movesLeft ==10))
        {
            punishmentAmount = -2;
            punishmentHistory = 0;
        }
    }

    public boolean bIsBehind() { return ((myScore - opponentScore) >= 10); }
    public boolean bIsRepentant() { return ((opponentLastMove == 1) && (opponentSecondLastMove == 1)); }
    public boolean bSufferedEnough() { return (punishmentHistory > 20); }

}
