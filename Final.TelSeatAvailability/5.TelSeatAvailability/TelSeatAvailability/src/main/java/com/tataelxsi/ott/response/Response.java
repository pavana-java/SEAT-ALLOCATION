package com.tataelxsi.ott.response;

public class Response {
    String status;
    
    Object seat;

   public Response() {
        super();
        // TODO Auto-generated constructor stub
    }

   public Response(String status, Object seat) {
        super();
        this.status = status;
        this.seat = seat;
    }

   public String getStatus() {
        return status;
    }

   public void setStatus(String status) {
        this.status = status;
    }

   public Object getSeat() {
        return seat;
    }

   public void setSeat(Object seat) {
        this.seat = seat;
    }
}
    
    
