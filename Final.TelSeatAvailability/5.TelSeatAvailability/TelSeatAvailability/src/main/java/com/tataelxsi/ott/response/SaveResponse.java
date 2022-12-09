package com.tataelxsi.ott.response;

import com.tataelxsi.ott.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SaveResponse {

    private int status;
    private String message;
    private Object output;
}
