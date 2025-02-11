package spring.bbusinsa.inquiry.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.bbusinsa.inquiry.domain.entity.Inquiry;

@Repository
public interface InquiryRepository extends CrudRepository<Inquiry, Long> {

}
