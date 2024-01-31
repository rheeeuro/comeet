package com.a506.comeet.app.metadata.repository;

import com.a506.comeet.app.metadata.entity.Metadata;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.a506.comeet.app.metadata.entity.QMetadata.metadata;


@RequiredArgsConstructor
@Repository
public class CustomMetadataRepositoryImpl implements CustomMetadataRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MetadataDto> findByMemberIdForOneMonth(String memberId) {
        LocalDateTime minDate = LocalDateTime.now().minusMonths(1);
        return jpaQueryFactory.select(Projections.constructor(MetadataDto.class,
                        metadata.room.id,
                        metadata.enterTime,
                        metadata.leaveTime,
                        metadata.keywords))
                .from(metadata)
                .where(metadata.member.memberId.eq(memberId)
                        .and(metadata.leaveTime.gt(minDate)))
                .orderBy(metadata.leaveTime.desc())
                .fetch();
    }
}