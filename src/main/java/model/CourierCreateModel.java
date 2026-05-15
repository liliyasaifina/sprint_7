package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourierCreateModel {
    private String login;
    private String password;
    private String firstName;
}
