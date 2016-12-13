package basic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUImanager {
	
	public void Font(JLabel label, int font){		
		java.awt.Font labelFont = label.getFont();
		String labelText = label.getText();
		int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);
		int componentWidth = label.getWidth();
		double widthRatio = (double)componentWidth / (double)stringWidth;
		int newFontSize = (int)(labelFont.getSize() * widthRatio);
		int componentHeight = label.getHeight();		
		int fontSizeToUse = Math.min(newFontSize, componentHeight);	
		label.setFont(new java.awt.Font(labelFont.getName(), font, fontSizeToUse));				
	}
	void setTextFit(JLabel label, String text) {
	    java.awt.Font originalFont = (java.awt.Font)label.getClientProperty("originalfont"); // Get the original Font from client properties
	    if (originalFont == null) { // First time we call it: add it
	        originalFont = label.getFont();
	        label.putClientProperty("originalfont", originalFont);
	        //test
	    }

	    int stringWidth = label.getFontMetrics(originalFont).stringWidth(text);
	    int componentWidth = label.getWidth();

	    if (stringWidth > componentWidth) { // Resize only if needed
	        // Find out how much the font can shrink in width.
	        double widthRatio = (double)componentWidth / (double)stringWidth;

	        int newFontSize = (int)Math.floor(originalFont.getSize() * widthRatio); // Keep the minimum size

	        // Set the label's font size to the newly determined size.
	        label.setFont(new java.awt.Font(originalFont.getName(), originalFont.getStyle(), newFontSize));
	    } else
	        label.setFont(originalFont); // Text fits, do not change font size

	    label.setText(text);
	}
	}
