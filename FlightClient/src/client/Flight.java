package client;
import java.util.*;


public class Flight{
	
	private HashMap<Integer,LinkedList<Seat>> seatMap = new HashMap<Integer,LinkedList<Seat>>();
	private LinkedList<Seat> occupiedSeats = new LinkedList<Seat>();
	
	public Flight() {
		/**This is the flight server with Hashmap for seats and occupied seats linked list**/
	}


	public boolean makeReservation(int rowNumber, Seat aSeat){
		/**Reserve the flight. Before reserving, check if the seat is available **/
		synchronized(seatMap) {
			
			boolean reserved = false;

			if (checkAvailability(rowNumber, aSeat)) {
				
				int col = aSeat.getColumn();
				int producer = aSeat.getName();
				// if its avaiable, put the seat into the hashmap
				if (!seatMap.containsKey(rowNumber)) {
					// if flight does not contain this row, create this row and
					// add the seat to linked list
					seatMap.put(rowNumber, new LinkedList<Seat>());
					seatMap.get(rowNumber).add(aSeat);
				} else {
					// else directly add this to the row
					seatMap.get(rowNumber).add(aSeat);
				}
				String s = rowNumber + "," + col;
				System.out.println("The seat: "+ s + " is reserved by producer "
						+ aSeat.getName());
				
				Seat occupiedSeat = new Seat(rowNumber, col, producer);
				synchronized(occupiedSeats) {
					occupiedSeats.add(occupiedSeat);
				}
				
				reserved = true;
				
				
			} else {
				int producer = getProducerName(rowNumber, aSeat);
				System.out.println("FAILURE on producer "+ aSeat.getName() + ": The seat is already reserved by producer "
								+ producer);
				reserved = false;
			}

			return reserved;
		}

	}

	public boolean checkAvailability(int rowNumber, Seat aSeat){	
		
		synchronized(seatMap) {
		
		
		boolean available = true;
		LinkedList<Seat> sameRowSeat = new LinkedList<Seat>();
		//if it doesnt contain a row number, then there is no reservation 
		if(seatMap.containsKey(rowNumber)){
			//the row has some reservations 
			sameRowSeat = seatMap.get(rowNumber);
			
			//boolean flag = listIterator.hasNext();
			for (int i = 0; i < sameRowSeat.size(); i++) {
				
				if (sameRowSeat.get(i).getColumn() == aSeat.getColumn()) {
					available = false;
	            	break;
				}
				
			}
			
			return available;
		}
		else {
			return available;
		}
		}
	}

	public int getProducerName(int rowNumber, Seat aSeat){		
		LinkedList<Seat> listOfReservedSeatsInTheRow = new LinkedList<Seat>(); 
		synchronized(seatMap) {
			listOfReservedSeatsInTheRow = seatMap.get(rowNumber);
		}
		ListIterator<Seat> listIterator = listOfReservedSeatsInTheRow.listIterator();
		int producername = 0;
		while(listIterator.hasNext()){
			Seat seatInList = listIterator.next();
			if(aSeat.getColumn() == seatInList.getColumn())
				producername = seatInList.getName();
			}
		return producername;
	}

	public LinkedList<Seat> getOccupiedSeats(){
		
		return occupiedSeats;
		
	}
	
	
	
/*
	public static void main(String args[]){
		//seat(column, producername)
		Seat testSeat1 = new Seat(2, 1);
		Seat testSeat2 = new Seat(2, 2);
		Flight flight = new Flight();
		flight.makeReservation(1, testSeat1);
		flight.makeReservation(1, testSeat2);
		//getProducerName(1, testSeat);

	} 
*/

	
}