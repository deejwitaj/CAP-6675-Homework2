/**
 * Class containing an type of Strategy.
 * @author	081028AW
 */
public class StrategyGARLESSAE extends Strategy
{
  /**
   * Encoding for a strategy.
   */

	double cProb;
	double dProb;
	boolean swap = true;

	// 0 = defect, 1 = cooperate

	public StrategyGARLESSAE()
	{
		name = "GARLESSAE";
		myLastMove = 1;
		opponentLastMove = 1;
		cProb = .5;
		dProb = .5;
	}  /* Strategy */

	public int nextMove()
	{
		if(getOpponentLastMove() == 1 && getMyLastMove() == 1){
			cProb += .01;
			dProb -= .01;
			return 1;
		}
		else if(Math.abs(cProb - dProb) < .05){
			cProb -= .01;
			dProb += .01;
			return 0;
		}
		else if(swap){
			swap = false;
			return 1;
		}
		else{
			swap = true;
			return 0;
		}
	}  /* nextMove */
}  /* class Strategy */

