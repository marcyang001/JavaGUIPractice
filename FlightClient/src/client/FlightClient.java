package client;

import java.util.Random;

import javax.swing.JScrollPane;

public class FlightClient {
	
	
	public static void main(String[] args) {
		/**Invoke three threads here: producer 1, 2, 3 **/
		Flight flight = new Flight();
		
		ProducerOne p = new ProducerOne(flight);
		ProducerTwo q = new ProducerTwo(flight);
		producerThree f = new producerThree(flight);

		//create a flight for two threads
		
		Thread t = new Thread(p);
		t.start();
		
		Thread t1 = new Thread(q);
		t1.start();
		
	/*	
		Seat testSeat1 = new Seat(2, 1);
		Seat testSeat2 = new Seat(1, 2);
		flight.makeReservation(1, testSeat1);
		flight.makeReservation(1, testSeat2);
		
	*/
		
		Thread t2 = new Thread(f);
		t2.start();
		
		
		
		
		
	}
	
}


class ProducerOne implements Runnable{
	
	
	//automatically reserve seats
	/**
	 * randomly generate a producer ID 1 or 2 
	 * randomly generate a row number
	 * randomly generate a colomn number
	 * @param flight 
	 */
	
	Flight m_flight;
	
	public ProducerOne(Flight flight) {
		this.m_flight = flight;
		
	}
	
	public int randomProducer() {
		//returns randomly a producer ID
		Random r = new Random();
		int producer = 0;
		int Low = 1;
		int High = 3;
		producer = r.nextInt(High-Low) + Low;
		
		return producer;
	}
	
	public int rowNumber() {
		//returns randonly a row number 
		Random r = new Random();
		int row = 0;
		int Low = 1;
		int High = 51;
		row = r.nextInt(High-Low) + Low;
	
		return row;
	}
	
	public int columnNumber() {
		//returns randonly a column number 
		Random r = new Random();
		int column = 0;
		int Low = 1;
		int High = 5;
		column = r.nextInt(High-Low) + Low;
	
		return column;
	}
	
	public void reserveSeat(int rowNumber, Seat seat) {
		
		m_flight.makeReservation(rowNumber, seat);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int row;
		int producerId;
		int column;
		
		
		while (true) {
			
			row = rowNumber();
			producerId = randomProducer();
			column = columnNumber();
			
			//randomly generate a seat with a random ID
			Seat randSeat = new Seat(column, producerId);
			
			//reserve a flight
			reserveSeat(row, randSeat);
			
			
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		
	}
		
	
}


class ProducerTwo implements Runnable{

	
	//automatically reserve seats
	/**
	 * randomly generate a producer ID 1 or 2 
	 * randomly generate a row number
	 * randomly generate a colomn number
	 * @param flight 
	 */
	
	Flight mm_flight;
	
	public ProducerTwo(Flight flight) {
		
		/**This thread reserves the flights every 20 seconds **/
		this.mm_flight = flight;
		
	}
	
	public int randomProducer() {
		//returns randomly a producer ID
		Random r = new Random();
		int producer = 0;
		int Low = 1;
		int High = 3;
		producer = r.nextInt(High-Low) + Low;
		
		return producer;
	}
	
	public int rowNumber() {
		//returns randonly a row number 
		Random r = new Random();
		int row = 0;
		int Low = 1;
		int High = 51;
		row = r.nextInt(High-Low) + Low;
	
		return row;
	}
	
	public int columnNumber() {
		//returns randonly a column number 
		Random r = new Random();
		int column = 0;
		int Low = 1;
		int High = 5;
		column = r.nextInt(High-Low) + Low;
	
		return column;
	}
	
	public void reserveSeat(int rowNumber, Seat seat) {
		/**this thread reserves flight every 10 seconds **/
		mm_flight.makeReservation(rowNumber, seat);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int row;
		int producerId;
		int column;
		
		
		while (true) {
			
			row = rowNumber();
			producerId = randomProducer();
			column = columnNumber();
			
			//randomly generate a seat with a random ID
			Seat randSeat = new Seat(column, producerId);
			
			//reserve a flight
			reserveSeat(row, randSeat);
			
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
	
	
	
	
	
}


