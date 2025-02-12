package spring.bbusinsa.user.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.bbusinsa.user.domain.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
