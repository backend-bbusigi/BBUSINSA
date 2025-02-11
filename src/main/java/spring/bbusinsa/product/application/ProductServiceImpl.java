package spring.bbusinsa.product.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.bbusinsa.product.domain.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

}
