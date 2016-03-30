package client;

import java.awt.*;
import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

import java.awt.Canvas;
import java.io.*;
import java.util.LinkedList;

public class producerThree extends JFrame implements Runnable {

	JLabel label1;
	JTextField tf1;
	JLabel label2;
	JTextField tf2;
	JButton button;
	Flight mmm_flight;



	public int getRowText() {
		String a = tf1.getText();
		if (a.isEmpty()) {
			a = "-1";
		}
		return Integer.parseInt(a);
	}

	public int getColText() {
		String b = tf2.getText();
		if (b.isEmpty()) {
			b = "-1";
		}
		return Integer.parseInt(b);
	}

	public void reserveSeat() {
		
		int row = getRowText();
		int col = getColText();
		
		
		
		if ((row <=50 &&row>=1)&& (col>=1&& col<=4)){
			
			Seat seat = new Seat(col, 3);
			
			if (mmm_flight.checkAvailability(row, seat)) {
				System.out.println(row + " , " + col);
				Graphics g = this.getGraphics();
				g.setFont(new Font("Arial", Font.BOLD, 12));
				g.setColor(Color.RED);
				g.drawString("P3", col * 100 + 210, row * 30 + 115);
				
				mmm_flight.makeReservation(row, seat);
			}
			else {
				System.out.println("Seat is already reserved");
			}
			
		}
		
		else {
			System.out.println("No such seat!");
		
		}
	}
	
	public void display(){
		LinkedList<Seat> x = mmm_flight.getOccupiedSeats();
		for(int i =0; i<x.size(); i++){
			int row = x.get(i).getRow();
			int col = x.get(i).getColumn();
			String producerId = "P"+x.get(i).getName();
			Graphics g = this.getGraphics();
			g.setFont(new Font("Arial", Font.BOLD, 12));
			g.setColor(Color.RED);
			g.drawString(producerId, col * 100 + 210, row * 30 + 115);
		}
		
	}

	public producerThree(Flight x) {
		
		this.mmm_flight = x;


	}

	public void paint(Graphics g) {
		int i;
		int j;
		for (i = 1; i <= 50; i++) {
			for (j = 1; j <= 4; j++) {
				g.drawRect(j * 100 + 200, i * 30 + 100, 40, 20);
			}

		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
			
			/**This threads generates JFrame **/
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			setSize(1000, 500);
			setTitle("Producer 3");

			button = new JButton("submit");
			add(button, BorderLayout.PAGE_END);
			button.setActionCommand("reserve");
			button.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					if ("reserve".equals(e.getActionCommand())) {
						reserveSeat();
						display();
					}
				}

			});
			label1 = new JLabel("Enter row Number");
			add(label1, BorderLayout.LINE_START);
			label2 = new JLabel("Enter column Number");
			add(label2, BorderLayout.LINE_END);
			tf1 = new JTextField(15);
			add(tf1, BorderLayout.LINE_START);
			tf2 = new JTextField(15);
			add(tf2, BorderLayout.LINE_END);
			setVisible(true);

	}
	/*
	public static void main(String[] args) {

		
		Flight fl = new Flight();
		producerThree f = new producerThree(fl); // Create a window
		
		Thread t = new Thread(f);
		t.start();
		
		
	}
*/

	private Component initConnectors() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
