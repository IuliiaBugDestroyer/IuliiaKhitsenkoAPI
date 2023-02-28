import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import com.epam.javacourse.api.trello.models.TrelloBoard;
import com.epam.javacourse.api.trello.request.specifications.TrelloBoardSpecification;
import com.epam.javacourse.api.trello.request.specifications.query.ListsParam;
import com.epam.javacourse.api.trello.request.specifications.query.TrelloBoardReadParams;
import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;

public abstract class TrelloInBoardBaseTests extends TestBase {

    private TrelloBoardSpecification boardSpecification = new TrelloBoardSpecification();

    protected TrelloBoard boardScope;

    @Override
    protected void setupFixture() {
        super.setupFixture();

        var board = TrelloBoard.builder().name(RandomStringUtils.random(45, true, true)).build();

        var createdBoard = RestAssured.given(boardSpecification.create(board))
                                      .when()
                                      .post()
                                      .then()
                                      .spec(responseSpecification.okWithBody(is(notNullValue())))
                                      .extract()
                                      .body().as(TrelloBoard.class);

        var readParams = TrelloBoardReadParams.builder().lists(ListsParam.all).build();

        boardScope = RestAssured.given(boardSpecification.read(createdBoard.getId(), readParams))
                                .when()
                                .get()
                                .then()
                                .spec(responseSpecification.okWithBody(is(notNullValue())))
                                .extract()
                                .body().as(TrelloBoard.class);
    }

    @Override
    protected void teardownFixture() {
        super.teardownFixture();

        RestAssured.given(boardSpecification.delete(boardScope.getId()))
            .when()
            .delete()
            .then()
            .spec(responseSpecification.ok());
    }
}
