package com.tataelxsi.ott.repository;

import com.tataelxsi.ott.entity.Seat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat,Long> {
    @Query("SELECT s From Seat s")
    List<Seat> getSeatDetails();

	@Query("From Seat where bayNumber=?1")
	List<Seat> findAllByBayNumber(int bayNumber);

	@Query("From Seat where bookingDate = ?1")
    List<Seat> findAllByBookingDate(String checkingDate);

	@Query("SELECT bayNumber FROM Seat WHERE bookingDate =?1")
	List<Integer> findBayNumberListByBookingDate(String date);

	@Query("SELECT DISTINCT bayNumber From Seat")
	List<Integer> findBayNumberList();

	@Query("SELECT COUNT(DISTINCT bayNumber) FROM Seat")
	int findDistinctBayNumber();
}
