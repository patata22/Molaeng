package idle.molaeng_back.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Gugun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="gugun_id")
    private long gugunId;

    @Column(name="gugun_name")
    private String gugunName;

    @OneToMany(mappedBy = "gugun", fetch = FetchType.LAZY)
    List<User> userList = new ArrayList<>();

    @OneToMany(mappedBy = "gugun", fetch = FetchType.LAZY)
    List<Outeat> outeatList = new ArrayList<>();

    @Builder
    public Gugun(long gugunId, String gugunName, List<User> userList, List<Outeat> outeatList) {
        this.gugunId = gugunId;
        this.gugunName = gugunName;
        this.userList = userList;
        this.outeatList = outeatList;
    }
}
