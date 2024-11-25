package com.ojm.vacation_management.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVacation is a Querydsl query type for Vacation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVacation extends EntityPathBase<Vacation> {

    private static final long serialVersionUID = -735766923L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVacation vacation = new QVacation("vacation");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final com.ojm.vacation_management.vo.vacation.QVacationPeriod period;

    public final StringPath reason = createString("reason");

    public final EnumPath<com.ojm.vacation_management.vo.vacation.AppliedVacationStatus> status = createEnum("status", com.ojm.vacation_management.vo.vacation.AppliedVacationStatus.class);

    public final EnumPath<com.ojm.vacation_management.vo.vacation.VacationType> type = createEnum("type", com.ojm.vacation_management.vo.vacation.VacationType.class);

    public final NumberPath<Integer> userId = createNumber("userId", Integer.class);

    public QVacation(String variable) {
        this(Vacation.class, forVariable(variable), INITS);
    }

    public QVacation(Path<? extends Vacation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVacation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVacation(PathMetadata metadata, PathInits inits) {
        this(Vacation.class, metadata, inits);
    }

    public QVacation(Class<? extends Vacation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.period = inits.isInitialized("period") ? new com.ojm.vacation_management.vo.vacation.QVacationPeriod(forProperty("period")) : null;
    }

}

