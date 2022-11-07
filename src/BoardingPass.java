
import java.util.ArrayList;
import javax.swing.*;

import java.awt.*;
import net.miginfocom.swing.MigLayout;

public class BoardingPass {
	private ArrayList<ArrayList<String>> bookings;
	public BoardingPass() {
		bookings = SQLConnect.getUserBookings();
		displayPasses();
	}
	
	public void displayPasses() {
		JFrame frame = new JFrame("Boarding Passes");
		frame.setSize(800,800);
		frame.setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		List list = new List();
		list.setSize(800,800);
		list.setBackground(new Color(245, 212, 137));
		list.setFont(new Font("Arial", Font.BOLD, 30));
		scrollPane.setViewportView(list);
		int i = 0;
		while (i < bookings.size()) {
			list.add("---------------------------------------------------------------------------");
			list.add("                             Boarding Pass #" + (i+1));
			list.add("---------------------------------------------------------------------------");
			list.add("Flight ID: " + bookings.get(i).get(0));
			list.add("From: " + bookings.get(i).get(1));
			list.add("To: " + bookings.get(i).get(2));
			list.add("Passenger Name: " + bookings.get(i).get(3) + " " + bookings.get(i).get(4));
			list.add("Person Type: " + bookings.get(i).get(5));
			list.add("Gate: " + bookings.get(i).get(6));
			list.add("Terminal: " + bookings.get(i).get(7));
			list.add("Boarding begins: " + bookings.get(i).get(8));
			list.add("Boarding ends: " + bookings.get(i).get(9));
			list.add("Boarding group: " + bookings.get(i).get(10));
			list.add("Seat: " + bookings.get(i).get(11));
			list.add("\n");
			i++;
		}
		panel.add(scrollPane);
		frame.setVisible(true);
		}
	}
