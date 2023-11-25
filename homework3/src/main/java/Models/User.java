package Models;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;


@Setter
@Getter
@EqualsAndHashCode
@Builder
public class User {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirm_password;
}
