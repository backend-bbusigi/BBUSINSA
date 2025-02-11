package spring.bbusinsa.inquiry.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.bbusinsa.inquiry.domain.repository.InquiryRepository;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository inquiryRepository;

}
