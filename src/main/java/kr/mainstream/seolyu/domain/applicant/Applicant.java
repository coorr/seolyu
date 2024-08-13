package kr.mainstream.seolyu.domain.applicant;

import jakarta.persistence.*;
import kr.mainstream.seolyu.common.model.BaseEntityCreateUpdateAggregate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "applicant")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Applicant extends BaseEntityCreateUpdateAggregate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "email", nullable = false, length = 70)
    private String email;

    public Applicant(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
