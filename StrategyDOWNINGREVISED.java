/**
 * Class containing an type of Strategy.
 * @author	081028AW
 */
public class Strategy extends Object
{
  /**
   * Encoding for a strategy.
   */

	int opponentLastMove = 1;
	int myLastMove;
	double cProb;
	double dProb;
	String name;
	boolean swap = true;

	// 0 = defect, 1 = cooperate

	public Strategy()
	{
		name = "GARLESSAE";
		myLastMove = 1;
		opponentLastMove = 1;
		cProb = .5;
		dProb = .5;
		swap = true;
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
		return 0;
	}  /* nextMove */


	public void saveOpponentMove(int move)  { opponentLastMove = move; }
	public int getOpponentLastMove()  { return opponentLastMove; }
	public void saveMyMove(int move)  { myLastMove = move; }
	public int getMyLastMove()  { return myLastMove; }
	public String getName()  { return name; }
}  /* class Strategy */

