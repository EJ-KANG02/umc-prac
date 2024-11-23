package umc.spring.domain.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserFavFood is a Querydsl query type for UserFavFood
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserFavFood extends EntityPathBase<UserFavFood> {

    private static final long serialVersionUID = -490136958L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserFavFood userFavFood = new QUserFavFood("userFavFood");

    public final umc.spring.domain.common.QBaseEntity _super = new umc.spring.domain.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final umc.spring.domain.QFoodCategory foodCategory;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final umc.spring.domain.QUser user;

    public final NumberPath<Long> userFavFoodId = createNumber("userFavFoodId", Long.class);

    public QUserFavFood(String variable) {
        this(UserFavFood.class, forVariable(variable), INITS);
    }

    public QUserFavFood(Path<? extends UserFavFood> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserFavFood(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserFavFood(PathMetadata metadata, PathInits inits) {
        this(UserFavFood.class, metadata, inits);
    }

    public QUserFavFood(Class<? extends UserFavFood> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.foodCategory = inits.isInitialized("foodCategory") ? new umc.spring.domain.QFoodCategory(forProperty("foodCategory")) : null;
        this.user = inits.isInitialized("user") ? new umc.spring.domain.QUser(forProperty("user"), inits.get("user")) : null;
    }

}

