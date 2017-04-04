import com.google.common.collect.ImmutableMap;
import controllers.UserController;
import org.junit.*;

import play.db.Database;
import play.db.Databases;
import play.mvc.*;
import play.test.*;

import java.sql.*;

import static play.test.Helpers.*;
import static org.junit.Assert.*;

import static org.fluentlenium.core.filter.FilterConstructor.*;

public class IntegrationTest {

    public Database database;
    @Before
    public void createDatabase() {
        database = Databases.createFrom(
                "org.postgresql.Driver",
                "jdbc:postgresql://localhost/ahdb",
                ImmutableMap.of(
                        "username","ahuser",
                        "password", "p4ssw0rd"
                )
        );
    }
    @After
    public void shutdownDatabase() {
        database.shutdown();
    }

    @Test
    public void testDatabase() throws SQLException {
        Connection connection = this.database.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("SELECT * FROM tbl_user WHERE id=1");
        ResultSet resultSet = statement.getResultSet();

        resultSet.next();
        int id = statement.getResultSet().getInt("id");
        assertEquals(1, id);
    }

}
