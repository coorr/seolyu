package kr.mainstream.seolyu.domain.member;

import jakarta.persistence.*;
import kr.mainstream.seolyu.common.model.BaseEntityCreateUpdateAggregate;
import kr.mainstream.seolyu.domain.member.exception.MemberNotAvailableWithdrawException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "member")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntityCreateUpdateAggregate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "email", nullable = false, length = 70)
    private String email;

    @Column(name = "password", nullable = false, length = 120)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private MemberRole role;

    @Enumerated(EnumType.STRING)
    @Column(name = "join_status", nullable = false)
    private MemberJoinStatus joinStatus;

    @Column(name = "occupation", length = 50)
    private String occupation;

    @Enumerated(EnumType.STRING)
    @Column(name = "position", length = 20)
    private JobPosition position;

    public void verifyWithdraw() {
        if (MemberJoinStatus.WITHDRAWAL.equals(this.joinStatus)) {
            throw new MemberNotAvailableWithdrawException();
        }
    }
}
