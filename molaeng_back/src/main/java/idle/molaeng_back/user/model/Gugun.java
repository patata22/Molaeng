package idle.molaeng_back.user.model;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
public class Gugun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="gugun_id")
    private Long gugunId;

    @Column(name="gugun_name")
    private String gugunName;

    @OneToMany(mappedBy = "gugun", fetch = FetchType.LAZY)
    List<User> userList = new ArrayList<>();

    @OneToMany(mappedBy = "gugun", fetch = FetchType.LAZY)
    List<Outeat> outeatList = new ArrayList<>();



}
