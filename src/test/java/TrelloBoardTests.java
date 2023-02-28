import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.hamcrest.Matchers.notNullValue;

import com.epam.javacourse.api.trello.models.TrelloBoard;
import com.epam.javacourse.api.trello.request.specifications.TrelloBoardSpecification;
import com.epam.javacourse.api.trello.request.specifications.query.ListsParam;
import com.epam.javacourse.api.trello.request.specifications.query.TrelloBoardReadParams;
import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

public class TrelloBoardTests extends TestBase {
    private final int defaultBoardListsCount = 3;
    private final TrelloBoardSpecification boardSpecification = new TrelloBoardSpecification();
    private final String boardName = RandomStringUtils.random(40, true, true);
    private String boardId;

    @Test(priority = 0)
    public void createTest() {
        var newBoard = TrelloBoard.builder().name(boardName).build();

        var actual = RestAssured.given(boardSpecification.create(newBoard))
                                .when()
                                .post()
                                .then()
                                .spec(responseSpecification.okWithBody(is(notNullValue())))
                                .extract()
                                .body().as(TrelloBoard.class);

        assertThat(actual.getId(), is(notNullValue()));
        assertThat(actual.getName(), equalTo(boardName));

        boardId = actual.getId();
    }

    @Test(priority = 1)
    public void readTest() {

        var readParams = TrelloBoardReadParams.builder().lists(ListsParam.all).build();

        var actual = RestAssured.given(boardSpecification.read(boardId, readParams))
                                .when()
                                .get()
                                .then()
                                .spec(responseSpecification.okWithBody(is(notNullValue())))
                                .extract()
                                .body().as(TrelloBoard.class);

        assertThat(actual.getId(), equalTo(boardId));
        assertThat(actual.getName(), equalTo(boardName));
        assertThat(actual.getLists(), iterableWithSize(defaultBoardListsCount));
    }

    @Test(priority = 2)
    public void updateTest() {
        var description = RandomStringUtils.random(100, true, true);

        var board = TrelloBoard.builder().name(boardName).description(description).build();

        var actual = RestAssured.given(boardSpecification.update(boardId, board))
                                .when()
                                .put()
                                .then()
                                .spec(responseSpecification.okWithBody(is(notNullValue())))
                                .extract()
                                .body().as(TrelloBoard.class);

        assertThat(actual.getId(), equalTo(boardId));
        assertThat(actual.getName(), equalTo(boardName));
        assertThat(actual.getDescription(), equalTo(description));
    }

    @Test(priority = 3)
    public void deleteTest() {
        RestAssured.given(boardSpecification.delete(boardId))
                   .when().delete()
                   .then()
                   .spec(responseSpecification.ok());
    }

    @Test(priority = 4)
    public void readNonExistingTest() {
        RestAssured.given(boardSpecification.read(boardId))
                   .when()
                   .get()
                   .then()
                   .spec(responseSpecification.notFound());
    }

    @Test(priority = 5)
    public void deleteNonExistingTest() {
        RestAssured.given(boardSpecification.delete(boardId))
                   .when()
                   .delete()
                   .then()
                   .spec(responseSpecification.notFound());
    }
}
