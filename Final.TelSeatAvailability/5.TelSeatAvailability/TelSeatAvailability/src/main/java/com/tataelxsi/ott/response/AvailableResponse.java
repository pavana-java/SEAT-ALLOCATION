package com.tataelxsi.ott.response;

import java.util.List;

public class AvailableResponse {

    private int status;
    private String message;
    private int totalSeat;
    private int totalAvailableSeat;

	private List<Integer> seatsAvailable;
	private List<Integer> seatsBooked;


	public AvailableResponse() {
	}

	public AvailableResponse(int status, String message, int totalSeat, int totalAvailableSeat, List<Integer> seatsAvailable, List<Integer> seatsBooked) {
		this.status = status;
		this.message = message;
		this.totalSeat = totalSeat;
		this.totalAvailableSeat = totalAvailableSeat;
		this.seatsAvailable = seatsAvailable;
		this.seatsBooked = seatsBooked;
	}

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getTotalSeat() {
		return totalSeat;
	}
	public void setTotalSeat(int totalSeat) {
		totalSeat = totalSeat;
	}
	public int getTotalAvailableSeat() {
		return totalAvailableSeat;
	}
	public void setTotalAvailableSeat(int totalAvailableSeat) {
		totalAvailableSeat = totalAvailableSeat;
	}

	public List<Integer> getSeatsBooked() {
		return seatsBooked;
	}

	public void setSeatsBooked(List<Integer> seatsBooked) {
		this.seatsBooked = seatsBooked;
	}

	public List<Integer> getSeatsAvailable() {
		return seatsAvailable;
	}

	public void setSeatsAvailable(List<Integer> seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}
}
