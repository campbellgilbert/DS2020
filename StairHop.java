//November 13
import java.util.Arrays;

public class StairHop {
	
	//The problem: A child is running up a staircase with n steps, and can hop either 1, 2, or 3 steps at a time. 
	//Implement a method to count how many unique ways the child can hop up the stairs.
	
	public static int stairhop_recur (int n) {
		//base case
		if (n <= 1) {
			return 1;
		} else if (n == 2) {
			return 2;
		} 
		//recursive call
		return stairhop_recur(n - 1) + stairhop_recur(n - 2) + stairhop_recur (n - 3);

	}
	
	public static int stairhop_dyn(int n) {
		int[] memo = new int[n+1];
		Arrays.fill(memo,  -1);
		return stairhop_dyn(n, memo);
	}
	
	public static int stairhop_dyn(int n, int[] memo) {
		// TODO: fill me in
		if (n <= 1) {
			return 1;
		} 
		if (n == 2) {
			return 2;
		}

		memo[0] = 1;
		memo[1] = 1;
		memo[2] = 2;
		
		for (int i = 3; i <= n; i++) {
			memo[i] = memo[i - 1] + memo[i - 2] + memo[i - 3];
		}
		
		return memo[n];
	}
	
	
	public static void main (String[] args) {
		
		int recur_1 = stairhop_recur(1);
		int recur_3 = stairhop_recur(3);
		int recur_10 = stairhop_recur(10);
		
		if (recur_1 != 1) {
			throw new AssertionError("incorrect recursive solution for 1 step");
		}
		if (recur_3 != 4) {
			throw new AssertionError("incorrect recursive solution for 3 steps");
		}
		if (recur_10 != 274) {
			throw new AssertionError("incorrect recursive solution for 10 steps");
		}
		
		for (int steps = 0; steps<=10; steps++){
			int recur = stairhop_recur(steps);
			int dyn = stairhop_dyn(steps);
			if (recur != dyn) {
				throw new AssertionError("recursive answer, " + recur + ", and dynamic answer, " + dyn + " do not match");
			}
			
		}
		
		System.out.println("Finished");
		
	}
}
