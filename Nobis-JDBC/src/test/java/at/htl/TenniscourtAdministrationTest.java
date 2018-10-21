package at.htl;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;

public class TenniscourtAdministrationTest {
    public static final String DRIVER_STRING = "org.apache.derby.jdbc.ClientDriver";
    public static final String CONNECTION_STRING = "jdbc:derby://localhost:1527/db";
    public static final String USER = "app";
    public static final String PASSWORD = "app";
    private static Connection conn;

    @BeforeClass
    public static void initJdbc(){
        try {
            Class.forName(DRIVER_STRING);
            conn = DriverManager.getConnection(CONNECTION_STRING, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Klasse konnte nicht gefunden werden!\n" + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Verbindung zur Datenbank nicht möglich!\n" + e.getMessage());
            System.exit(1);
        }
        try {
            Statement statement = conn.createStatement();
            String sql = "CREATE TABLE tennisclub ("
                + "club_id INT CONSTRAINT PK_club_id_tennisclub PRIMARY KEY,"
                + "club_name VARCHAR(255) NOT NULL,"
                + "club_courts_amount INT NOT NULL,"
                + "club_members_amount INT NOT NULL"
                + ")";
            statement.execute(sql);

            sql = "INSERT INTO tennisclub (club_id, club_name, club_courts_amount, club_members_amount) VALUES (1, 'TC St. Valentin', 7, 250)";
            statement.execute(sql);
            sql = "INSERT INTO tennisclub (club_id, club_name, club_courts_amount, club_members_amount) VALUES (2, 'UNION Luftenberg', 3, 80)";
            statement.execute(sql);

            System.out.println("Tabelle TENNISCLUB erfolgreich erstellt und befüllt");

            sql = "CREATE TABLE tennisteam ("
                    + "team_id INT CONSTRAINT PK_team_id_tennisteam PRIMARY KEY,"
                    + "team_members_amount INT NOT NULL,"
                    + "team_sex VARCHAR(255) NOT NULL,"
                    + "club_id INT NOT NULL CONSTRAINT FK_club_id_tennisteam REFERENCES tennisclub(club_id)"
                    + ")";
            statement.execute(sql);

            sql = "INSERT INTO tennisteam (team_id, team_members_amount, team_sex, club_id) VALUES (1, 40, 'male', 1)";
            statement.execute(sql);
            sql = "INSERT INTO tennisteam (team_id, team_members_amount, team_sex, club_id) VALUES (2, 15, 'female', 1)";
            statement.execute(sql);
            sql = "INSERT INTO tennisteam (team_id, team_members_amount, team_sex, club_id) VALUES (3, 25, 'male_45', 1)";
            statement.execute(sql);
            sql = "INSERT INTO tennisteam (team_id, team_members_amount, team_sex, club_id) VALUES (4, 10, 'male', 2)";
            statement.execute(sql);

            System.out.println("Tabelle TENNISTEAM erfolgreich erstellt und befüllt");

            sql = "CREATE TABLE tennisplayer ("
                    + "player_id INT CONSTRAINT PK_player_id_tennisplayer PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + "player_name VARCHAR(255) NOT NULL,"
                    + "player_year_born INT NOT NULL,"
                    + "player_itn DOUBLE NOT NULL,"
                    + "player_sex VARCHAR(255) NOT NULL,"
                    + "club_id INT NOT NULL CONSTRAINT FK_club_id_tennisplayer REFERENCES tennisclub(club_id)"
                    + ")";
            statement.execute(sql);

            //erstellen der spieler
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO tennisplayer (player_name, player_year_born, player_itn, player_sex, club_id) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, "Julian Nobis");
            preparedStatement.setInt(2, 2001);
            preparedStatement.setDouble(3, 4.9);
            preparedStatement.setString(4, "male");
            preparedStatement.setInt(5, 1);
            preparedStatement.execute();

            preparedStatement = conn.prepareStatement("INSERT INTO tennisplayer (player_name, player_year_born, player_itn, player_sex, club_id) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, "Philipp Bräuer");
            preparedStatement.setInt(2, 1991);
            preparedStatement.setDouble(3, 4.4);
            preparedStatement.setString(4, "male");
            preparedStatement.setInt(5, 1);
            preparedStatement.execute();

            preparedStatement = conn.prepareStatement("INSERT INTO tennisplayer (player_name, player_year_born, player_itn, player_sex, club_id) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, "Alexander jun. Bräuer");
            preparedStatement.setInt(2, 1989);
            preparedStatement.setDouble(3, 3.9);
            preparedStatement.setString(4, "male");
            preparedStatement.setInt(5, 1);
            preparedStatement.execute();

            preparedStatement = conn.prepareStatement("INSERT INTO tennisplayer (player_name, player_year_born, player_itn, player_sex, club_id) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, "Yannik Leitner");
            preparedStatement.setInt(2, 1992);
            preparedStatement.setDouble(3, 3.8);
            preparedStatement.setString(4, "male");
            preparedStatement.setInt(5, 1);
            preparedStatement.execute();

            preparedStatement = conn.prepareStatement("INSERT INTO tennisplayer (player_name, player_year_born, player_itn, player_sex, club_id) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, "Sofia Groza");
            preparedStatement.setInt(2, 2002);
            preparedStatement.setDouble(3, 8.6);
            preparedStatement.setString(4, "female");
            preparedStatement.setInt(5, 1);
            preparedStatement.execute();

            preparedStatement = conn.prepareStatement("INSERT INTO tennisplayer (player_name, player_year_born, player_itn, player_sex, club_id) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, "Sorana Groza");
            preparedStatement.setInt(2, 2004);
            preparedStatement.setDouble(3, 8.8);
            preparedStatement.setString(4, "female");
            preparedStatement.setInt(5, 1);
            preparedStatement.execute();

            preparedStatement = conn.prepareStatement("INSERT INTO tennisplayer (player_name, player_year_born, player_itn, player_sex, club_id) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, "Dan Mihai Groza");
            preparedStatement.setInt(2, 1973);
            preparedStatement.setDouble(3, 2.5);
            preparedStatement.setString(4, "male");
            preparedStatement.setInt(5, 1);
            preparedStatement.execute();

            preparedStatement = conn.prepareStatement("INSERT INTO tennisplayer (player_name, player_year_born, player_itn, player_sex, club_id) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, "Gerald Kaiser");
            preparedStatement.setInt(2, 1964);
            preparedStatement.setDouble(3, 2.2);
            preparedStatement.setString(4, "male");
            preparedStatement.setInt(5, 1);
            preparedStatement.execute();

            preparedStatement = conn.prepareStatement("INSERT INTO tennisplayer (player_name, player_year_born, player_itn, player_sex, club_id) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, "Daniel Holzleithner");
            preparedStatement.setInt(2, 1989);
            preparedStatement.setDouble(3, 3.6);
            preparedStatement.setString(4, "male");
            preparedStatement.setInt(5, 2);
            preparedStatement.execute();

            preparedStatement = conn.prepareStatement("INSERT INTO tennisplayer (player_name, player_year_born, player_itn, player_sex, club_id) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, "Alexander Gassner");
            preparedStatement.setInt(2, 1997);
            preparedStatement.setDouble(3, 4.4);
            preparedStatement.setString(4, "male");
            preparedStatement.setInt(5, 2);
            preparedStatement.execute();

            System.out.println("Tabelle TENNISPLAYER erfolgreich erstellt und befüllt");

        } catch (SQLException e) {
            System.err.println("Ein Fehler ist bei der Datenmodellierung aufgetreten!\n" + e.getMessage());
        }
    }

    @AfterClass
    public static void teardownJdbc(){
        try {
            conn.createStatement().execute("DROP TABLE tennisplayer");
            System.out.println("Tabelle TENNISPLAYER erfolgreich gelöscht");
        } catch (SQLException e) {
            System.err.println("Tabelle TENNISPLAYER konnte nicht gelöscht werden! -> " + e.getMessage());
        }
        try {
            conn.createStatement().execute("DROP TABLE tennisteam");
            System.out.println("Tabelle TENNISTEAM erfolgreich gelöscht");
        } catch (SQLException e) {
            System.err.println("Tabelle TENNISTEAM konnte nicht gelöscht werden! --> " + e.getMessage());
        }
        try {
            conn.createStatement().execute("DROP TABLE tennisclub");
            System.out.println("Tabelle TENNISCLUB erfolgreich gelöscht");
        } catch (SQLException e) {
            System.err.println("Tabelle TENNISCLUB konnte nicht gelöscht werden!\n" + e.getMessage());
        }

        // schließen der verbindung
        try {
            if (conn != null && !conn.isClosed()){
                conn.close();
                System.out.println("Auf Wiedersehen");
            }
        } catch (SQLException e) {
            System.err.println("Fehler beim Schließen der Verbindung!\n" + e.getMessage());
        }
    }

    @Test
    public void testFirstTennisclub(){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM tennisclub");
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            assertThat(resultSet.getInt("club_id"), is(1));
            assertThat(resultSet.getString("club_name"), is("TC St. Valentin"));
            assertThat(resultSet.getInt("club_courts_amount"), is(7));
            assertThat(resultSet.getInt("club_members_amount"), is(250));

        } catch (SQLException e) {
            System.err.println("Fehler in testFirstTennisclub! --> " + e.getMessage());
        }
    }
    @Test
    public void testSecondTennisclub(){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM tennisclub");
            ResultSet resultSet = preparedStatement.executeQuery();

            // 2 mal .next() ausführen um den 1. tennisclub zu "überspringen"
            resultSet.next();
            resultSet.next();

            assertThat(resultSet.getInt("club_id"), is(2));
            assertThat(resultSet.getString("club_name"), is("UNION Luftenberg"));
            assertThat(resultSet.getInt("club_courts_amount"), is(3));
            assertThat(resultSet.getInt("club_members_amount"), is(80));

        } catch (SQLException e) {
            System.err.println("Fehler in testSecondTennisclub! --> " + e.getMessage());
        }
    }

    @Test
    public void testTennisteamsFromFirstClub(){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM tennisteam where club_id = 1");
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            // 1. team testen
            assertThat(resultSet.getInt("team_id"), is(1));
            assertThat(resultSet.getInt("team_members_amount"), is(40));
            assertThat(resultSet.getString("team_sex"), is("male"));

            resultSet.next();
            // 2. team testen
            assertThat(resultSet.getInt("team_id"), is(2));
            assertThat(resultSet.getInt("team_members_amount"), is(15));
            assertThat(resultSet.getString("team_sex"), is("female"));

            resultSet.next();
            // 3. team testen
            assertThat(resultSet.getInt("team_id"), is(3));
            assertThat(resultSet.getInt("team_members_amount"), is(25));
            assertThat(resultSet.getString("team_sex"), startsWith("male_"));

        } catch (SQLException e) {
            System.err.println("Fehler in testTennisTeamsFromFirstClub! --> " + e.getMessage());
        }
    }

    @Test
    public void testTennisteamsFromSecondClub(){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM tennisteam where club_id = 2");
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            assertThat(resultSet.getInt("team_id"), is(4));
            assertThat(resultSet.getInt("team_members_amount"), is(10));
            assertThat(resultSet.getString("team_sex"), is("male"));

        } catch (SQLException e) {
            System.err.println("Fehler in testTennisTeamsFromSecondClub! --> " + e.getMessage());
        }
    }

    @Test
    public void testTennisplayersFromFirstClub(){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM tennisplayer where club_id = 1");
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            // 1. player testen
            assertThat(resultSet.getString("player_name"), is("Julian Nobis"));
            assertThat(resultSet.getInt("player_year_born"), is(2001));
            assertThat(resultSet.getDouble("player_itn"), is(4.9));
            assertThat(resultSet.getString("player_sex"), is("male"));

            resultSet.next();
            // 2. player testen
            assertThat(resultSet.getString("player_name"), is("Philipp Bräuer"));
            assertThat(resultSet.getInt("player_year_born"), is(1991));
            assertThat(resultSet.getDouble("player_itn"), is(4.4));
            assertThat(resultSet.getString("player_sex"), is("male"));

        } catch (SQLException e) {
            System.err.println("Fehler in testTennisplayersFromFirstClub! --> " + e.getMessage());
        }
    }

    @Test
    public void testTennisplayersFromSecondClub(){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM tennisplayer where club_id = 2");
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            // 1. player testen
            assertThat(resultSet.getString("player_name"), is("Daniel Holzleithner"));
            assertThat(resultSet.getInt("player_year_born"), is(1989));
            assertThat(resultSet.getDouble("player_itn"), is(3.6));
            assertThat(resultSet.getString("player_sex"), is("male"));

            resultSet.next();
            // 2. player testen
            assertThat(resultSet.getString("player_name"), is("Alexander Gassner"));
            assertThat(resultSet.getInt("player_year_born"), is(1997));
            assertThat(resultSet.getDouble("player_itn"), is(4.4));
            assertThat(resultSet.getString("player_sex"), is("male"));

        } catch (SQLException e) {
            System.err.println("Fehler in testTennisplayersFromSecondClub! --> " + e.getMessage());
        }
    }

    @Test
    public void testOldestTennisplayer(){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM tennisplayer where player_year_born <= (SELECT MIN(player_year_born) FROM tennisplayer)");
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            assertThat(resultSet.getString("player_name"), is("Gerald Kaiser"));

        } catch (SQLException e) {
            System.err.println("Fehler in testOldestTennisplayer! --> " + e.getMessage());
        }
    }

    @Test
    public void testBestTennisplayer(){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM tennisplayer where player_itn <= (SELECT MIN(player_itn) FROM tennisplayer)");
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            assertThat(resultSet.getString("player_name"), is("Gerald Kaiser"));

        } catch (SQLException e) {
            System.err.println("Fehler in testOldestTennisplayer! --> " + e.getMessage());
        }
    }

    @Test
    public void testSpecificPlayer(){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement("SELECT * FROM tennisplayer where player_name = 'Daniel Holzleithner'");
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            assertThat(resultSet.getDouble("player_itn"), is(3.6));
        } catch (SQLException e) {
            System.err.println("Fehler in testSpecificPlayer! --> " + e.getMessage());
        }
    }

    @Test
    public void testMetaDataTennisclub(){
        try {
            DatabaseMetaData databaseMetaData = conn.getMetaData();

            String catalog = null;
            String schemaPattern = null;
            String tableNamePattern = "TENNISCLUB"; // muss UPPERCASE sein
            String columnNamePattern = null;

            //testen der metadaten der einzelnen spalten in der tabelle tennisclub
            ResultSet resultSet = databaseMetaData.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern);

            // CLUB_ID testen
            resultSet.next();
            String columnName = resultSet.getString(4); // index 4 beinhaltet column name
            int columnType = resultSet.getInt(5); // index 5 beinhaltet column type
            // tutorials.jenkov.com/jdbc/databasemetadata.html
            assertThat(columnName, is("CLUB_ID")); // muss UPPERCASE sein
            assertThat(columnType, is(Types.INTEGER));

            // CLUB_NAME testen
            resultSet.next();
            columnName = resultSet.getString(4); // index 4 beinhaltet column name
            columnType = resultSet.getInt(5); // index 5 beinhaltet column type
            // tutorials.jenkov.com/jdbc/databasemetadata.html
            assertThat(columnName, is("CLUB_NAME")); // muss UPPERCASE sein
            assertThat(columnType, is(Types.VARCHAR));

            // CLUB_COURTS_AMOUNT testen
            resultSet.next();
            columnName = resultSet.getString(4); // index 4 beinhaltet column name
            columnType = resultSet.getInt(5); // index 5 beinhaltet column type
            // tutorials.jenkov.com/jdbc/databasemetadata.html
            assertThat(columnName, is("CLUB_COURTS_AMOUNT")); // muss UPPERCASE sein
            assertThat(columnType, is(Types.INTEGER));

            // CLUB_MEMBERS_AMOUNT testen
            resultSet.next();
            columnName = resultSet.getString(4); // index 4 beinhaltet column name
            columnType = resultSet.getInt(5); // index 5 beinhaltet column type
            // tutorials.jenkov.com/jdbc/databasemetadata.html
            assertThat(columnName, is("CLUB_MEMBERS_AMOUNT")); // muss UPPERCASE sein
            assertThat(columnType, is(Types.INTEGER));

            // Primary Key der Tabelle tennisclub erhalten
            catalog = null; // catalog sollte null sein (zur sicherheit auf nul setzen)
            String schema = null;
            tableNamePattern = "TENNISCLUB"; // darf sich auch nicht geändert haben (nur zur sicherheit)

            resultSet = databaseMetaData.getPrimaryKeys(catalog, schema, tableNamePattern);
            resultSet.next();
            columnName = resultSet.getString(4);
            assertThat(columnName, is("CLUB_ID"));

        } catch (SQLException e) {
            System.err.println("Fehler in testMetaDataTennisclub! --> " + e.getMessage());
        }
    }

    @Test
    public void testMetaDataTennisteam(){
        try {
            DatabaseMetaData databaseMetaData = conn.getMetaData();

            String catalog = null;
            String schemaPattern = null;
            String tableNamePattern = "TENNISTEAM"; // muss UPPERCASE sein
            String columnNamePattern = null;

            //testen der metadaten der einzelnen spalten in der tabelle tennisteam
            ResultSet resultSet = databaseMetaData.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern);

            // TEAM_ID testen
            resultSet.next();
            String columnName = resultSet.getString(4); // index 4 beinhaltet column name
            int columnType = resultSet.getInt(5); // index 5 beinhaltet column type
            // tutorials.jenkov.com/jdbc/databasemetadata.html
            assertThat(columnName, is("TEAM_ID")); // muss UPPERCASE sein
            assertThat(columnType, is(Types.INTEGER));

            // TEAM_MEMBERS_AMOUNT testen
            resultSet.next();
            columnName = resultSet.getString(4); // index 4 beinhaltet column name
            columnType = resultSet.getInt(5); // index 5 beinhaltet column type
            // tutorials.jenkov.com/jdbc/databasemetadata.html
            assertThat(columnName, is("TEAM_MEMBERS_AMOUNT")); // muss UPPERCASE sein
            assertThat(columnType, is(Types.INTEGER));

            // TEAM_SEX testen
            resultSet.next();
            columnName = resultSet.getString(4); // index 4 beinhaltet column name
            columnType = resultSet.getInt(5); // index 5 beinhaltet column type
            // tutorials.jenkov.com/jdbc/databasemetadata.html
            assertThat(columnName, is("TEAM_SEX")); // muss UPPERCASE sein
            assertThat(columnType, is(Types.VARCHAR));

            // TEAM_CLUB_ID testen
            resultSet.next();
            columnName = resultSet.getString(4); // index 4 beinhaltet column name
            columnType = resultSet.getInt(5); // index 5 beinhaltet column type
            // tutorials.jenkov.com/jdbc/databasemetadata.html
            assertThat(columnName, is("CLUB_ID")); // muss UPPERCASE sein
            assertThat(columnType, is(Types.INTEGER));

            // Primary Key der Tabelle tennisteam erhalten
            catalog = null; // catalog sollte null sein (zur sicherheit auf nul setzen)
            String schema = null;
            tableNamePattern = "TENNISTEAM"; // darf sich auch nicht geändert haben (nur zur sicherheit)

            resultSet = databaseMetaData.getPrimaryKeys(catalog, schema, tableNamePattern);
            resultSet.next();
            columnName = resultSet.getString(4);
            assertThat(columnName, is("TEAM_ID"));

        } catch (SQLException e) {
            System.err.println("Fehler in testMetaDataTennisclub! --> " + e.getMessage());
        }
    }

    @Test
    public void testMetaDataTennisplayer(){ // foreign keys kann man leider nicht testen
        try {
            DatabaseMetaData databaseMetaData = conn.getMetaData();

            String catalog = null;
            String schemaPattern = null;
            String tableNamePattern = "TENNISPLAYER"; // muss UPPERCASE sein
            String columnNamePattern = null;

            //testen der metadaten der einzelnen spalten in der tabelle tennisplayer
            ResultSet resultSet = databaseMetaData.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern);

            // PLAYER_ID testen
            resultSet.next();
            String columnName = resultSet.getString(4); // index 4 beinhaltet column name
            int columnType = resultSet.getInt(5); // index 5 beinhaltet column type
            // tutorials.jenkov.com/jdbc/databasemetadata.html
            assertThat(columnName, is("PLAYER_ID")); // muss UPPERCASE sein
            assertThat(columnType, is(Types.INTEGER));

            // PLAYER_NAME testen
            resultSet.next();
            columnName = resultSet.getString(4); // index 4 beinhaltet column name
            columnType = resultSet.getInt(5); // index 5 beinhaltet column type
            // tutorials.jenkov.com/jdbc/databasemetadata.html
            assertThat(columnName, is("PLAYER_NAME")); // muss UPPERCASE sein
            assertThat(columnType, is(Types.VARCHAR));

            // PLAYER_YEAR_BORN testen
            resultSet.next();
            columnName = resultSet.getString(4); // index 4 beinhaltet column name
            columnType = resultSet.getInt(5); // index 5 beinhaltet column type
            // tutorials.jenkov.com/jdbc/databasemetadata.html
            assertThat(columnName, is("PLAYER_YEAR_BORN"));
            assertThat(columnType, is(Types.INTEGER));

            // PLAYER_ITN testen
            resultSet.next();
            columnName = resultSet.getString(4); // index 4 beinhaltet column name
            columnType = resultSet.getInt(5); // index 5 beinhaltet column type
            // tutorials.jenkov.com/jdbc/databasemetadata.html
            assertThat(columnName, is("PLAYER_ITN"));
            assertThat(columnType, is(Types.DOUBLE));

            // PLAYER_SEX testen
            resultSet.next();
            columnName = resultSet.getString(4); // index 4 beinhaltet column name
            columnType = resultSet.getInt(5); // index 5 beinhaltet column type
            // tutorials.jenkov.com/jdbc/databasemetadata.html
            assertThat(columnName, is("PLAYER_SEX")); // muss UPPERCASE sein
            assertThat(columnType, is(Types.VARCHAR));

            // CLUB_ID testen
            resultSet.next();
            columnName = resultSet.getString(4); // index 4 beinhaltet column name
            columnType = resultSet.getInt(5); // index 5 beinhaltet column type
            // tutorials.jenkov.com/jdbc/databasemetadata.html
            assertThat(columnName, is("CLUB_ID")); // muss UPPERCASE sein
            assertThat(columnType, is(Types.INTEGER));

            // Primary Key der Tabelle tennisteam erhalten
            catalog = null; // catalog sollte null sein (zur sicherheit auf nul setzen)
            String schema = null;
            tableNamePattern = "TENNISTEAM"; // darf sich auch nicht geändert haben (nur zur sicherheit)

            resultSet = databaseMetaData.getPrimaryKeys(catalog, schema, tableNamePattern);
            resultSet.next();
            columnName = resultSet.getString(4);
            assertThat(columnName, is("TEAM_ID"));

        } catch (SQLException e) {
            System.err.println("Fehler in testMetaDataTennisplayer! --> " + e.getMessage());
        }
    }

}
