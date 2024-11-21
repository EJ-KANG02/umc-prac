package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.mapping.UserFavFood;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FoodCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, length = 30)
    private Long foodId;

    private String foodName;

    /*
    음식 카테고리는 보통 수정/삭제를 잘 하지 않으므로 양방향 매핑 x
    @OneToMany(mappedBy = "foodCategory", cascade = CascadeType.ALL)
    private List<UserFavFood> userFavFoodList = new ArrayList<>();
    */
}
