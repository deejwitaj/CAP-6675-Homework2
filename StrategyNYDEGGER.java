/**
  * Class containing NYDEGGER strategy
  * @author 181112DL
  */

import java.util.Arrays;

public class StrategyNYDEGGER extends Strategy
{
  // 0 = defect, 1 = cooperate

  int moveHistory = 3;
  int myDefects = 1;
  int oppDefects = 2;
  int myMoves[] = {-1, -1, -1};
  int oppMoves[] = {-1, -1, -1};
  int moveWeights[] = {16, 4, 1};
  int defectScore[] = {1, 6, 7, 17, 22, 23, 26, 29, 30,31, 33, 38, 39, 45, 49, 54, 55, 58, 61};

  public StrategyNYDEGGER()
  {
    name = "Nydegger";
    opponentLastMove = 1;
  }

  public int nextMove()
  {
    //System.out.printf("Opponent Last Move: %d\n", opponentLastMove);

    //First three moves
    if(myMoves[0] == -1)
    {
      UpdateMyMoves(1);
      return 1;
    }

    if(myMoves[1] == -1)
    {
      UpdateOpponentMoves(opponentLastMove);
      if(opponentLastMove == 1)
      {
        UpdateMyMoves(1);
        return 1;
      }
      UpdateMyMoves(0);
      return 0;
    }

    if(myMoves[2] == -1)
    {
      UpdateOpponentMoves(opponentLastMove);
      if((oppMoves[1] == 0) && (oppMoves[0] == 1) && (myMoves[1] == 1) && (myMoves[0] == 0))
      {
        UpdateMyMoves(0);
        return 0;
      }
      if (opponentLastMove == 1)
      {
        UpdateMyMoves(1);
        return 1;
      }
      UpdateMyMoves(0);
      return 0;
    }

    UpdateOpponentMoves(opponentLastMove);
    return DetermineMove();
  }

  public void UpdateMyMoves(int move)
  {
    myMoves[2] = myMoves[1];
    myMoves[1] = myMoves[0];
    myMoves[0] = move;
  }
  public void UpdateOpponentMoves(int move)
  {
    oppMoves[2] = oppMoves[1];
    oppMoves[1] = oppMoves[0];
    oppMoves[0] = move;
  }

  public int DetermineMove()
  {
    int defectionScore = 0;

    for (int i = 0; i < moveHistory; i++)
    {
      //System.out.printf("i = %d\n", i);
      if (myMoves[i] == 0)
      {
        defectionScore += (moveWeights[i]*myDefects);
        //System.out.printf("Mine: %d\n", moveWeights[i]);
      }
      if (oppMoves[i] == 0)
      {
        defectionScore += (moveWeights[i]*oppDefects);
        //System.out.printf("Opponent: %d\n", moveWeights[i]);
      }
    }

    //System.out.printf("Defection score is: %d\n", defectionScore);
    for (int i = 0; i < defectScore.length; i++)
    {
      if (defectionScore == defectScore[i])
      {
        UpdateMyMoves(0);
        return 0;
      }
    }

    UpdateMyMoves(1);
    return 1;
  }
}