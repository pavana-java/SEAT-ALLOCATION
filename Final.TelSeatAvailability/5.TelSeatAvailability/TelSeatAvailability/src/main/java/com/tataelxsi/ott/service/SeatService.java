package com.tataelxsi.ott.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import com.tataelxsi.ott.entity.User;
import com.tataelxsi.ott.repository.UserRepository;
import com.tataelxsi.ott.response.SaveResponse;
import org.hibernate.secure.spi.IntegrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.tataelxsi.ott.entity.Seat;
import com.tataelxsi.ott.repository.SeatRepository;
import com.tataelxsi.ott.response.AvailableResponse;
import com.tataelxsi.ott.response.Response;

@Service
public class SeatService {
	
	@Autowired
    private SeatRepository seatRepo;
    @Autowired
    private UserRepository userRepo;
      
    public SaveResponse save(Seat seat)
    {
        List<Seat> list = seatRepo.findAllByBayNumber(seat.getBayNumber());

        if(list.size() != 0){
            return new SaveResponse(400, "Bay Number already present", null);
        }
        return new SaveResponse(200, "New BayNumber Added Successfully", seatRepo.save(seat));

    }
    public Response booking(int emp_id, Seat seat){

        Seat bookSeat = new Seat();

        boolean bookedDate = checkDate(seat.getBookingDate());
        Optional<User> use = userRepo.findById(emp_id);
        if(use.isPresent()){

            List<Seat> list = seatRepo.findAllByBayNumber(seat.getBayNumber());
            if(list.size()!=0){
                if(bookedDate){

                    List<Integer> bookedList = seatRepo.findBayNumberListByBookingDate(seat.getBookingDate());

                    if(!bookedList.contains(seat.getBayNumber())){

                        List<Seat> oneuserSeats = userRepo.getUserByEmpId(emp_id).getSeat();
                        boolean out = false;
                        for(Seat se : oneuserSeats){
                            if(se.getBookingDate().equals(seat.getBookingDate())){
                                out = true;
                            }
                        }
                        if(!out){

                            bookSeat.setBookingDate(seat.getBookingDate());
                            bookSeat.setBayNumber(seat.getBayNumber());
                            bookSeat.setProject(seat.getProject());
                            bookSeat.setAvailability(false);

                            Optional<Object> output = use.map(user -> user.getSeat().add(bookSeat));

                            return new Response("200", seatRepo.save(bookSeat));

                        } else {
                            return new Response("400", "You already booked the seat");
                        }

                    } else {
                        return new Response("400", "Seat for that day is already booked");
                    }
                } else {
                    return new Response("400", "Date must be in yyyy-MM-dd format and within 10 days");
                }
            } else {
                return new Response("400", "Invalid BayNumber");
            }
        }
        return new Response("200", "user not found");
    }

    public boolean checkDate(String bookingDate){
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date bookDate = format.parse(bookingDate);

            LocalDate ldt = LocalDate.now();
            LocalDate endldt = ldt.plusDays(10);
            Date today = format.parse(ldt.toString());
            Date endDate = format.parse(endldt.toString());

            if (bookDate.before(today) || bookDate.after(endDate)){
                return false;
            }
            return true;

        }
        catch (Exception e){
            return false;
        }
    }

    @Scheduled(cron = "0 0 0 * * ?" ,zone = "Asia/Kolkata")
    public void clear(){
        List<Seat> list = seatRepo.findAll();
        for(Seat seat: list) {
            if (!checkDate(seat.getBookingDate()) && seat.getBookingDate()!=null){
                seatRepo.deleteById(seat.getSeatId());
            }
        }
    }

    public AvailableResponse seatAvailability(String checkingDate) {

        if(checkDate(checkingDate)){

//            List<Seat> listByDate = seatRepo.findAllByBookingDate(String.valueOf(checkingDate));

            List<Integer> listByDate = seatRepo.findBayNumberListByBookingDate(String.valueOf(checkingDate));

            int totalCount =seatRepo.findDistinctBayNumber();

            int available = totalCount - listByDate.size();

            List<Integer> aray = seatRepo.findBayNumberList();

            for(Integer bookedSeatList: listByDate){

                aray.remove(bookedSeatList);
            }

            return new AvailableResponse(200, "Successful", totalCount, available, aray, listByDate);
        }
        return new AvailableResponse(400, "Date must be in yyyy-MM-dd format and within 10 days", 0, 0, null,null);

    }
   
}