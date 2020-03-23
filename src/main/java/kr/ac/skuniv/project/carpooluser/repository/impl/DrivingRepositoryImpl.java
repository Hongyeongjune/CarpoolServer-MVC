package kr.ac.skuniv.project.carpooluser.repository.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import kr.ac.skuniv.project.carpooluser.domain.dto.driving.DrivingGetDto;
import kr.ac.skuniv.project.carpooluser.domain.entity.*;
import kr.ac.skuniv.project.carpooluser.repository.custom.DrivingRepositoryCustom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class DrivingRepositoryImpl extends QuerydslRepositorySupport implements DrivingRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    private QDriving driving = QDriving.driving;

    public DrivingRepositoryImpl() {
        super(Driving.class);
    }

    private final int DEFAULT_LIMIT_SIZE = 9;

    /**
     * 출발지를 기준으로 요청 리스트를 조회
     * @param pageNum : 페이지 번호
     * @param departure : 출발지
     * @return 페이지 당 요청 리스트
     */
    @Override
    public Page<DrivingGetDto> getDrivingsByDeparture(int pageNum, String departure) {
        JPAQuery<DrivingGetDto> jpaQuery = new JPAQuery<>(entityManager);
        jpaQuery = setQuery(jpaQuery);
        jpaQuery.where(driving.departure.contains(departure))
                .orderBy(driving.dno.desc())
                .offset(--pageNum * DEFAULT_LIMIT_SIZE)
                .limit(DEFAULT_LIMIT_SIZE);
        List<DrivingGetDto> drivings = jpaQuery.fetch();
        return new PageImpl<>(drivings, PageRequest.of(pageNum, DEFAULT_LIMIT_SIZE,
                new Sort(Sort.Direction.DESC, "dno")), drivings.size());
    }

    /**
     * 도착지를 기준으로 요청 리스트를 조회
     * @param pageNum : 페이지 번호
     * @param destination : 도착지
     * @return 페이지 당 요청 리스트
     */
    @Override
    public Page<DrivingGetDto> getDrivingsByDestination(int pageNum, String destination) {
        JPAQuery<DrivingGetDto> jpaQuery = new JPAQuery<>(entityManager);
        jpaQuery = setQuery(jpaQuery);
        jpaQuery.where(driving.destination.contains(destination))
                .orderBy(driving.dno.desc())
                .offset(--pageNum * DEFAULT_LIMIT_SIZE)
                .limit(DEFAULT_LIMIT_SIZE);
        List<DrivingGetDto> drivings = jpaQuery.fetch();
        return new PageImpl<>(drivings, PageRequest.of(pageNum, DEFAULT_LIMIT_SIZE,
                new Sort(Sort.Direction.DESC, "dno")), drivings.size());
    }

    /**
     * 사용자가 신청한 요청 리스트만 조회
     * @param pageNum : 페이지 번호
     * @param userId : 사용자 아이디
     * @return 페이지 당 요청 리스트
     */
    @Override
    public Page<DrivingGetDto> getDrivingsByUserId(int pageNum, String userId) {
        JPAQuery<DrivingGetDto> jpaQuery = new JPAQuery<>(entityManager);
        jpaQuery = setQuery(jpaQuery);
        jpaQuery.where(driving.user.id.eq(userId))
                .orderBy(driving.dno.desc())
                .offset(--pageNum * DEFAULT_LIMIT_SIZE)
                .limit(DEFAULT_LIMIT_SIZE);

        List<DrivingGetDto> drivings = jpaQuery.fetch();
        return new PageImpl<>(drivings, PageRequest.of(pageNum, DEFAULT_LIMIT_SIZE,
                new Sort(Sort.Direction.DESC, "dno")), drivings.size());
    }

    /**
     * 모든 요청 리스트 조회
     * @param pageNum : 페이지 번호
     * @return 페이지 당 요청 리스트
     */
    @Override
    public Page<DrivingGetDto> getAllDrivings(int pageNum) {
        JPAQuery<DrivingGetDto> jpaQuery = new JPAQuery<>(entityManager);
        jpaQuery = setQuery(jpaQuery);
        jpaQuery.orderBy(driving.dno.desc())
                .offset(--pageNum * DEFAULT_LIMIT_SIZE)
                .limit(DEFAULT_LIMIT_SIZE);
        List<DrivingGetDto> drivings = jpaQuery.fetch();

        return new PageImpl<>(drivings, PageRequest.of(pageNum, DEFAULT_LIMIT_SIZE,
                new Sort(Sort.Direction.DESC, "dno")), drivings.size());
    }

    private JPAQuery<DrivingGetDto> setQuery(JPAQuery<DrivingGetDto> query) {
        return query.select(Projections.constructor(DrivingGetDto.class,
                driving.dno, driving.departure, driving.destination, driving.distance,
                driving.user.name, driving.isDriving, driving.date));
    }
}
