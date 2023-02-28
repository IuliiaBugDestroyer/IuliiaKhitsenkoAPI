import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import com.epam.javacourse.api.trello.models.TrelloList;
import com.epam.javacourse.api.trello.request.specifications.TrelloListSpecification;
import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

public class TrelloListTests extends TrelloInBoardBaseTests {

    private final TrelloListSpecification listSpecification = new TrelloListSpecification();
    private TrelloList createdList;

    @Test(priority = 0)
    public void createTest() {
        var listName = RandomStringUtils.random(20, true, true);
        var newList = TrelloList.builder().name(listName).boardId(boardScope.getId()).build();

        var actual = RestAssured.given(listSpecification.create(newList))
            .when()
            .post()
            .then()
            .spec(responseSpecification.okWithBody(is(notNullValue())))
            .extract()
            .body().as(TrelloList.class);

        assertThat(actual.getId(), is(notNullValue()));
        assertThat(actual.getName(), equalTo(listName));

        createdList = actual;
    }

    @Test(priority = 1)
    public void updateTest() {
        var list = TrelloList.builder().closed(!createdList.isClosed()).build();

        var actual = RestAssured.given(listSpecification.update(createdList.getId(), list))
            .when()
            .put()
            .then()
            .spec(responseSpecification.okWithBody(is(notNullValue())))
            .extract()
            .body().as(TrelloList.class);

        assertThat(actual.getId(), equalTo(createdList.getId()));
        assertThat(actual.isClosed(), not(equalTo(createdList.isClosed())));
        assertThat(actual.getBoardId(), equalTo(createdList.getBoardId()));
    }
}
