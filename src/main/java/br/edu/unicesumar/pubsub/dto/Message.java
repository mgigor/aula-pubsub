package br.edu.unicesumar.pubsub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {
    
    public static final String myUser = "joao-sotoriva";
    
    private String message;
    
    private String user = Message.myUser;
    
    private String group;

}
