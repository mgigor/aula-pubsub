package br.edu.unicesumar.pubsub.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message implements Serializable {
    
    public static final String myUser = "joao-sotoriva";
    
    private String message;
    
    private String user = Message.myUser;
    
    private String grupo;

}
