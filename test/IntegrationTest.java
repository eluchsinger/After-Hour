import com.google.common.collect.ImmutableMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.db.Database;
import play.db.Databases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

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
