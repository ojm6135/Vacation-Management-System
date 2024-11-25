package com.ojm.vacation_management.vo.vacation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QVacationPeriod is a Querydsl query type for VacationPeriod
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QVacationPeriod extends BeanPath<VacationPeriod> {

    private static final long serialVersionUID = -99427380L;

    public static final QVacationPeriod vacationPeriod = new QVacationPeriod("vacationPeriod");

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public QVacationPeriod(String variable) {
        super(VacationPeriod.class, forVariable(variable));
    }

    public QVacationPeriod(Path<VacationPeriod> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVacationPeriod(PathMetadata metadata) {
        super(VacationPeriod.class, metadata);
    }

}

