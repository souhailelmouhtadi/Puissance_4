package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JPanel {

	/**
	 * Class qui s'occupe de la creation du menu
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public void paintComponent(Graphics g)
	{
		 String imgPath = "assets/menu.png";
		  try {
		      
			  Image img = ImageIO.read(new File(imgPath));
		  
		      g.drawImage(img, 0, 0, this);
		      
		  }catch (Exception e) {
			  
			  System.out.println(e.getMessage());
		}
	}
	
}
