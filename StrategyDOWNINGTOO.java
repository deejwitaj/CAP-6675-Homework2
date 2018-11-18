/**
 * Class containing an type of Strategy.
 * @author	081028AW
 */
public class StrategyDOWNINGTOO extends Strategy
{
  /**
   * Encoding for a strategy.
   */

	int mySecondLastMove = -1;
	double cProb; // Probability that opponent will cooperate if we last cooperated
	double dProb; // Probability that opponent will dcooperate if we last defected
	int oppCoopCount = 1; // Number of times opponent has cooperated after I have cooperated
	int oppDefCount = 1; // Number of times opponent has cooperated after I have defected
	int myCoopCount= 2; // Number of times I have cooperated
	int myDefCount = 2; // Number of times I have defected
	double probThreshold = .05; //If diff between probabilities is less than this, we will always defect
	double probSignificant = .70; // Point at which a probability seems significant
	int swap = 0;
	int swapReturn = 1;

	// 0 = defect, 1 = cooperate

	public StrategyDOWNINGTOO()
	{
		name = "DOWNINGTOO";
		myLastMove = 1;
		opponentLastMove = 1;
		cProb = .5;
		dProb = .5;
	}  /* Strategy */

	public int nextMove()
	{
		// Still set to initial value, so send our first move which is always a cooperate
		if(mySecondLastMove == -1)
		{
			UpdateMySecondLastMove();
			return 1;
		}

		UpdateMySecondLastMove();
		UpdateProbability();
		return CalculateBestMove();
		
	}  /* nextMove */

	public void UpdateProbability()
	{
		if (mySecondLastMove == 1)
		{
			myCoopCount++;

			/* If opponent cooperates after I cooperate, the odds it will do so has increased. Accordingly, the odds it will defect has decreased
			   If it defects after I cooperate, we assume that the odds it will cooperate should go down, which it will as the overall times I have
			   cooperated has gone up without also increasing the times the opponent has cooperated */
			if(opponentLastMove == 1)
				oppCoopCount++;
			
			cProb = oppCoopCount / myCoopCount;
		}

		if (mySecondLastMove == 0)
		{
			myDefCount++;

			if(opponentLastMove == 1)
				oppDefCount++;

			dProb = oppDefCount / myDefCount;
		}
	}

	public int CalculateBestMove()
	{
		// Value has not changed from initial value, so we are in first round and will cooperate to start
		if (opponentLastMove == -1)
			return 1;
		// Opponent seems to be random, default to defect
		if(Math.abs(cProb - dProb) < probThreshold)
			return 0;
		// Opponent seems to want to cooperate and punish defects, so we will also cooperate
		if((cProb > probSignificant) && (dProb < (1-probSignificant)))
			return 1;
		// Opponent seems to rarely cooperate when we have, but strangely cooperates when we've last defected. Pays to defect
		if((dProb > probSignificant) && (cProb < (1-probSignificant)))
			return 0;
		
		int i = swapReturn;
		swapReturn = swap;
		swap = i;
		return swapReturn;
	}

	public void UpdateMySecondLastMove() { mySecondLastMove = myLastMove; }
}  /* class Strategy */

