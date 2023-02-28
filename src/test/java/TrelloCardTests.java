import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import com.epam.javacourse.api.trello.models.TrelloCard;
import com.epam.javacourse.api.trello.request.specifications.TrelloCardSpecification;
import io.restassured.RestAssured;
import java.util.UUID;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.Test;

public class TrelloCardTests extends TrelloInBoardBaseTests {

    private final TrelloCardSpecification cardSpecification = new TrelloCardSpecification();

    private String cardId;

    @Test(priority = 0)
    public void createTest() {
        var cardName = RandomStringUtils.random(25);
        var listId = boardScope.getLists().get(RandomUtils.nextInt(0, boardScope.getLists().size() - 1)).getId();
        var newCard = TrelloCard.builder()
                                .name(cardName)
                                .parentBoardId(boardScope.getId())
                                .listId(listId)
                                .build();

        var actual = RestAssured.given(cardSpecification.create(newCard))
                                .when()
                                .post()
                                .then()
                                .spec(responseSpecification.okWithBody(is(notNullValue())))
                                .extract()
                                .body().as(TrelloCard.class);

        assertThat(actual.getId(), is(notNullValue()));
        assertThat(actual.getName(), equalTo(cardName));
        assertThat(actual.getListId(), equalTo(listId));

        cardId = actual.getId();
    }

    @Test(priority = 1)
    public void deleteTest() {
        RestAssured.given(cardSpecification.delete(cardId))
                   .when()
                   .delete()
                   .then()
                   .spec(responseSpecification.ok());
    }

    @Test
    public void deleteNonExistingTest() {
        var id = UUID.randomUUID().toString().replace("-", "").substring(0, 24);

        RestAssured.given(cardSpecification.delete(id))
                   .when()
                   .delete()
                   .then()
                   .spec(responseSpecification.notFound());
    }
}
