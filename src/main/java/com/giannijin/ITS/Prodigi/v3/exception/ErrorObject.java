package com.giannijin.ITS.Prodigi.v3.exception;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorObject {
    private Integer statusCode;
    private String message;
    private Date timestamp;

}
