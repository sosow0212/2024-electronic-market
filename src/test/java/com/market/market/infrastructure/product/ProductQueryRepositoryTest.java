package com.market.market.infrastructure.product;

import com.market.helper.IntegrationHelper;
import com.market.market.domain.category.CategoryRepository;
import com.market.market.domain.product.Product;
import com.market.market.domain.product.ProductRepository;
import com.market.market.domain.product.dto.ProductPagingSimpleResponse;
import com.market.market.domain.product.dto.ProductSpecificResponse;
import com.market.market.domain.product.vo.Description;
import com.market.market.domain.product.vo.Price;
import com.market.market.domain.product.vo.ProductStatus;
import com.market.market.domain.product.vo.StatisticCount;
import com.market.member.domain.member.Member;
import com.market.member.domain.member.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static com.market.market.fixture.CategoryFixture.카테고리_생성;
import static com.market.market.fixture.ProductFixture.상품_생성;
import static com.market.member.fixture.member.MemberFixture.일반_유저_생성;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class ProductQueryRepositoryTest extends IntegrationHelper {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ProductQueryRepository productQueryRepository;

    @BeforeEach
    void setup() {
        categoryRepository.save(카테고리_생성());
    }

    @Test
    void no_offset_페이징_첫_조회() {
        // given
        for (long i = 1L; i <= 20L; i++) {
            productRepository.save(Product.builder()
                    .id(i)
                    .categoryId(1L)
                    .memberId(1L)
                    .description(new Description("title", "content"))
                    .statisticCount(StatisticCount.createDefault())
                    .price(new Price(10000))
                    .productStatus(ProductStatus.WAITING)
                    .build()
            );
        }

        // when
        List<ProductPagingSimpleResponse> result = productQueryRepository.findAllWithPagingByCategoryId(null, 1L, 10);

        // then
        assertSoftly(softly -> {
            softly.assertThat(result).hasSize(10);
            softly.assertThat(result.get(0).id()).isEqualTo(20L);
            softly.assertThat(result.get(9).id()).isEqualTo(11L);
        });
    }

    @Test
    void no_offset_페이징_두번째_조회() {
        // given
        for (long i = 1L; i <= 20L; i++) {
            productRepository.save(Product.builder()
                    .id(i)
                    .categoryId(1L)
                    .memberId(1L)
                    .description(new Description("title", "content"))
                    .statisticCount(StatisticCount.createDefault())
                    .price(new Price(10000))
                    .productStatus(ProductStatus.WAITING)
                    .build()
            );
        }

        // when
        List<ProductPagingSimpleResponse> result = productQueryRepository.findAllWithPagingByCategoryId(11L, 1L, 10);

        // then
        assertSoftly(softly -> {
            softly.assertThat(result).hasSize(10);
            softly.assertThat(result.get(0).id()).isEqualTo(10L);
            softly.assertThat(result.get(9).id()).isEqualTo(1L);
        });
    }

    @Test
    void 상품_상세_정보를_조회한다() {
        // given
        Member member = memberRepository.save(일반_유저_생성());
        Product product = productRepository.save(상품_생성());

        // when
        Optional<ProductSpecificResponse> result = productQueryRepository.findSpecificProductById(product.getId());

        // then
        assertSoftly(softly -> {
            softly.assertThat(result).isPresent();
            softly.assertThat(result.get().id()).isEqualTo(product.getId());
        });
     }
}
