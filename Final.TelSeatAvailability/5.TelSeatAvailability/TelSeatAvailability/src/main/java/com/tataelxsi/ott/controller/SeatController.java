package com.tataelxsi.ott.controller;

import com.tataelxsi.ott.response.AvailableResponse;
import com.tataelxsi.ott.response.LoginResponse;
import com.tataelxsi.ott.response.SaveResponse;
import com.tataelxsi.ott.service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tataelxsi.ott.entity.Seat;
import com.tataelxsi.ott.response.Response;
import com.tataelxsi.ott.service.SeatService;

@CrossOrigin(origins = "*")
@RestController
public class SeatController {

	
	@Autowired
    private SeatService seatService;

    @Autowired
    JwtTokenService jwtTokenService;
      
	@PostMapping("/save")
    public SaveResponse save(@RequestParam(name = "token") String token,
                             @RequestBody Seat seat) throws Exception {
        try{
            if(jwtTokenService.VerifyJwtToken(token)){
                return seatService.save(seat);
            } else {
                return new SaveResponse(400, "Invalid token", null);
            }
        } catch (Exception e){
            return null;
        }
    }

	@PutMapping("/book/{emp_id}")
    public Response booking(@RequestParam(name = "token") String token,
                            @PathVariable int emp_id,
                            @RequestBody Seat seat) throws Exception {

       try{
           if(jwtTokenService.VerifyJwtToken(token)){
               return seatService.booking(emp_id,seat);
           } else {
               return new Response("400", "Invalid token");
           }
       } catch (Exception e){
           return null;
       }
   }
   
	@GetMapping("/seat-available")
	public AvailableResponse seatAvailability(@RequestParam(name = "token") String token,
                                              @RequestParam(name = "bookingDate") String checkingDate) throws Exception {
        try{
            if(jwtTokenService.VerifyJwtToken(token)){
                return seatService.seatAvailability(checkingDate);
            } else {
                return new AvailableResponse(400, "Invalid token",0,0,null,null);
            }
        } catch (Exception e){
            return null;
        }
	}
   
   
}
