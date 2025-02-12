package spring.bbusinsa.inquiry.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity @Getter
@Table(name = "inquiries")
public class Inquiry {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_id", nullable = false)
    private Long inquiryId;

    @Column(name = "content", nullable = false, length = 500)
    private String content;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "market_id", nullable = false)
    private Long marketId;

}
