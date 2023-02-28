import com.epam.javacourse.api.trello.response.specifications.ApiResponseSpecification;
import com.epam.javacourse.api.trello.response.specifications.TrelloApiResponseSpecification;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class TestBase {

    protected final ApiResponseSpecification responseSpecification = new TrelloApiResponseSpecification();

    @BeforeClass
    public void classSetup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);

        setupFixture();
    }

    protected void setupFixture() {}

    @AfterClass
    public void classTeardown() {
        teardownFixture();
    }

    protected void teardownFixture() {}
}
