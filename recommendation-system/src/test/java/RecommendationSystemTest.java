import com.c0d1red.RecommendationFacade;
import com.c0d1red.domain.ResultingRecommendationObject;
import com.c0d1red.domain.SourceRecommendationObject;
import org.junit.Test;

import java.util.List;

public class RecommendationSystemTest {

    @Test
    public void test() {
        RecommendationFacade recommendationFacade = new RecommendationFacade();
        List<SourceRecommendationObject> allArticles = List.of(
                createSourceRecommendationObject(0, "ManU", "news", "England", "Ronaldo"),
                createSourceRecommendationObject(1, "ManU", "football", "Rooney", "England"),
                createSourceRecommendationObject(2, "ManC", "football", "Tevez", "England"),
                createSourceRecommendationObject(3, "Milan", "football", "Italia", "Ronaldo", "Mourinho"),
                createSourceRecommendationObject(4, "Inter", "football", "Lukaku", "news"),
                createSourceRecommendationObject(5, "some", "dummy", "tag")
        );
        List<SourceRecommendationObject> likedArticles = List.of(
                createSourceRecommendationObject(6, "ManU", "football", "news", "Soulskhaer"),
                createSourceRecommendationObject(7, "ManU", "football", "Rooney", "England")
        );

        List<ResultingRecommendationObject> recommendationObjects =
                recommendationFacade.createRecommendation(allArticles, likedArticles);
        System.out.println(recommendationObjects);
    }

    private SourceRecommendationObject createSourceRecommendationObject(int id, String... keyWords) {
        return new SourceRecommendationObject(id, List.of(keyWords));
    }
}
