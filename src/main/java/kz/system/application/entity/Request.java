package kz.system.application.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    private int id;
   private String userName;
   private String courseName;
   private String commentary;
   private String phone;
   private boolean handled;
}
