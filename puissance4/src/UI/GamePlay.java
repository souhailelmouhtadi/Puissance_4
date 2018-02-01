package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import AI.AlphaBeta;
import AI.MinMax;
import puissance.Grid;
import puissance.Player;

public class GamePlay extends JFrame {

	/**
	 * Class qui va crée l'interface de gamePlay
	 */
	private static final long serialVersionUID = 1L;
	
	public final static int HEIGHT = 480;
	public final static int WDITH = 500;
	
	Stack<Integer> gamePlayHistory = new Stack<>();
	
	boolean switchTurn = true;
	boolean switchAlgo = true;
	
	String gameTitle = "AI Puissance 4";
	
	Grid grid = null;
	
	MinMax minmax = null;
	AlphaBeta alphabeta = null;
	
	JComboBox<String> comboMenu = null;
	
	public GamePlay()
	{
		this.setTitle(gameTitle);
		this.setSize(new Dimension(WDITH, HEIGHT));
		this.setResizable(false);
		
		grid = new Grid();
		
		minmax = new MinMax(grid, 5);
		alphabeta = new AlphaBeta(grid, 5);
		
		Menu menu = new Menu();
		
		PlayArea playarea = new PlayArea(grid);	
		
		comboMenu = UIHelper.createSimpleCombo(new String[]{ "MinMax", "AlphaBeta"});

		comboMenu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(comboMenu.getSelectedItem() == "MinMax")
				{
					switchAlgo = true;
				}else if(comboMenu.getSelectedItem() == "AlphaBeta"){
					
					switchAlgo = false;	
				}	
				
			}
		});
				
		JTextField jprofValue = new JTextField();
		
		jprofValue.setPreferredSize(new Dimension(30, 25));
		
		jprofValue.setBorder(
						BorderFactory.createCompoundBorder(jprofValue.getBorder(),
						BorderFactory.createEmptyBorder(5, 5, 5, 5))
						);
		
		jprofValue.setText("5");
		
		playarea.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
				if(switchTurn)
				{
					grid.addValue(Player.FIRST, playarea.getRealPosition(e.getX()));
				}else{
					
					grid.addValue(Player.AI, playarea.getRealPosition(e.getX()));

				}
				
				gamePlayHistory.push(playarea.getRealPosition(e.getX()));
				
				switchTurn = !switchTurn;
				
				playarea.repaint();
				
				
				whoWins();
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
				
		JButton playAI = UIHelper.createSimpleButton("PLAY AI");
		
		playAI.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			if( !switchTurn )
			{
				
				int prof = 5;
				
				try {
					prof = Integer.parseInt(jprofValue.getText());
					
				} catch (Exception e2) {
					
					System.out.println("Profondeur n'est pas valide");
				}
				
				if(switchAlgo)
				{
					minmax.setProf(prof);
					
					gamePlayHistory.push(minmax.AI());
					
				}else{
					
					alphabeta.setProf(prof);
					gamePlayHistory.push(alphabeta.AI());
				}
				
				repaint();
				
				switchTurn = !switchTurn;
				
				whoWins();
			}
			
			}
		});
		
		JButton backPlay = UIHelper.createSimpleButton("BACK");
		
		backPlay.setForeground(Color.WHITE);
		
		backPlay.setBackground(Color.RED);	
		
		backPlay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				grid.delValue(gamePlayHistory.pop());
				
				switchTurn = !switchTurn;
				
				playarea.repaint();

			}
		});
		
		JButton clearBtn = UIHelper.createSimpleButton("CLEAR");
		
		clearBtn.setForeground(Color.BLACK);
		clearBtn.setBackground(Color.GREEN);
		
		clearBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				grid.init();
				
				playarea.repaint();
				
			}
		});
		
		JButton saveBtn = UIHelper.createSimpleButton("Save");
		
		saveBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				grid.SaveGrid();
				
				System.out.println("hello");
				
			}
		});
		

		JLabel jprofondeur = new JLabel("Profondeur");
		
		jprofondeur.setForeground(Color.WHITE);
		
		menu.add(comboMenu);
		menu.add(jprofondeur);
		menu.add(jprofValue);
		menu.add(playAI);
		menu.add(backPlay);
		menu.add(clearBtn);
		menu.add(saveBtn);
				
		this.getContentPane().add(menu, BorderLayout.NORTH);
		
		this.getContentPane().add(playarea, BorderLayout.CENTER);
		
		
		this.setVisible(true);
	}
	
	public void whoWins()
	{
		
		if(grid.checkWin(Player.FIRST))
		{
        	JOptionPane.showMessageDialog(null, "YELLOW PLAYER WINS", "WINNER", JOptionPane.INFORMATION_MESSAGE);

		}else if(grid.checkWin(Player.AI))
		{
			JOptionPane.showMessageDialog(null, "RED PLAYER WINS", "WINNER", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	

	
}
