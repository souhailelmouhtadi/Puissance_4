package AI;

import java.util.ArrayList;

import puissance.Grid;
import puissance.Player;

public class AlphaBeta extends MinMax {

	public AlphaBeta(Grid grid,int prof) {
		
		super(grid,prof);
		
	}

	@Override
	public int AI() {
		
		int poMove = 0;
	    
		int alpha = 9999;
	    int beta = -9999;
	    
	    int tmp;
		
	    int profondeur = prof;
	    
	    ArrayList<Integer> positions = grid.getPossiblePositions();
	    
		for (Integer pos : positions) {
			
			grid.addValue(Player.AI, pos);
			
			tmp = min(grid,profondeur - 1,alpha,beta);
			
			if( tmp > alpha )
			{
				alpha = tmp;
				poMove = pos;
				
			}else{
				
				grid.delValue(pos);
				break;
			}
			
			grid.delValue(pos);
			
		}
			
		grid.addValue(Player.AI, poMove);
		
		return poMove;
	}

	
	public int min(Grid grid, int profondeur,int alpha,int beta)
	{
		if(grid.isFull() || eval() != 0 || profondeur <= 0)
		{
			if( profondeur <= 0 )
			{
				return grid.evalProgress(Player.AI) - grid.evalProgress(Player.FIRST);
			
			}else{
				
				return eval();
			}
			
		}
		
	    int tmp;
		
	    ArrayList<Integer> positions = grid.getPossiblePositions();
	    
		for (Integer pos : positions) {
			
			grid.addValue(Player.FIRST, pos);
			
			tmp = max(grid, profondeur - 1,alpha,beta);
			
			if( tmp < beta )
			{
				beta = tmp;
			
			}else{
				
				grid.delValue(pos);
				
				break;
			}
			
			grid.delValue(pos);
		}
		
		return beta;
	}
	
	public int max(Grid grid, int profondeur,int alpha,int beta)
	{
		if(grid.isFull() || eval() != 0 || profondeur <= 0)
		{
			if( profondeur <= 0 )
			{
				return grid.evalProgress(Player.AI) - grid.evalProgress(Player.FIRST);
			
			}else{
				
				return eval();
			}
			
		}
		
	    int tmp;
		
	    ArrayList<Integer> positions = grid.getPossiblePositions();
	    
		for (Integer pos : positions) {
			
			grid.addValue(Player.AI, pos);
			
			tmp = min(grid, profondeur - 1,alpha,beta);
			
			if( tmp > alpha )
			{
				alpha = tmp;
			}else{
				
				grid.delValue(pos);
				break;
			}
			
			grid.delValue(pos);
		}
		
		return alpha;
	}
	
	
	

}
