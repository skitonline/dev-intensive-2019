package net.thumbtack.school.database.jdbc;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcService {
    public static void insertTrainee(Trainee trainee) throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        String query = "insert into trainee values(?, ?, ?, ?)";
        PreparedStatement preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStmt.setNull(1, java.sql.Types.INTEGER);
        preparedStmt.setString (2, trainee.getFirstName());
        preparedStmt.setString   (3, trainee.getLastName());
        preparedStmt.setInt    (4, trainee.getRating());
        preparedStmt.executeUpdate();
        ResultSet generatedKeys = preparedStmt.getGeneratedKeys();
        if (generatedKeys.next()) {
            trainee.setId(generatedKeys.getInt(1));
        }
    }

    public  static void updateTrainee(Trainee trainee) throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        String query = "update trainee set id = ?, firstName = ?, lastName = ?, rating = ?";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt (1, trainee.getId());
        preparedStmt.setString (2, trainee.getFirstName());
        preparedStmt.setString   (3, trainee.getLastName());
        preparedStmt.setInt    (4, trainee.getRating());
        preparedStmt.executeUpdate();
    }

    public static Trainee getTraineeByIdUsingColNames(int traineeId) throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        String query = "select * from trainee where id = ?";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1, traineeId);
        ResultSet rs = preparedStmt.executeQuery();
        if (!rs.next())
            return null;
        return new Trainee(rs.getInt("id"),
                           rs.getString("firstName"),
                           rs.getString("lastName"),
                           rs.getInt("rating"));
    }

    public static Trainee getTraineeByIdUsingColNumbers(int traineeId) throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        String query = "select * from trainee where id = ?";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1, traineeId);
        ResultSet rs = preparedStmt.executeQuery();
        if (!rs.next())
            return null;
        return new Trainee(rs.getInt(1), rs.getString(2),
                rs.getString(3), rs.getInt(4));
    }

    public static List<Trainee> getTraineesUsingColNames() throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        String query = "select * from trainee";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        ResultSet rs = preparedStmt.executeQuery();
        List<Trainee> result = new ArrayList<>();
        while (rs.next()) {
            result.add(new Trainee(rs.getInt("id"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getInt("rating")));
        }
        return result;
    }

    public static List<Trainee> getTraineesUsingColNumbers() throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        String query = "select * from trainee";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        ResultSet rs = preparedStmt.executeQuery();
        List<Trainee> result = new ArrayList<>();
        while (rs.next()) {
            result.add(new Trainee(rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getInt(4)));
        }
        return result;
    }

    public static void deleteTrainee(Trainee trainee) throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        String query = "delete from trainee where id = ? and firstName = ? and lastName = ? and rating = ?";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt (1, trainee.getId());
        preparedStmt.setString (2, trainee.getFirstName());
        preparedStmt.setString   (3, trainee.getLastName());
        preparedStmt.setInt    (4, trainee.getRating());
        preparedStmt.executeUpdate();
    }

    public static void deleteTrainees() throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        String query = "delete from trainee";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.executeUpdate();
    }

    public static void insertSubject(Subject subject) throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        String query = "insert into subject_table values (?, ?)";
        PreparedStatement preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStmt.setNull(1, java.sql.Types.INTEGER);
        preparedStmt.setString (2, subject.getName());
        preparedStmt.executeUpdate();
        ResultSet generatedKeys = preparedStmt.getGeneratedKeys();
        if (generatedKeys.next()) {
            subject.setId(generatedKeys.getInt(1));
        }
    }

    public static Subject getSubjectByIdUsingColNames(int subjectId) throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        String query = "select * from subject_table where id = ?";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1, subjectId);
        ResultSet rs = preparedStmt.executeQuery();
        if (!rs.next())
            return null;
        return new Subject(rs.getInt("id"), rs.getString("name"));
    }

    public static Subject getSubjectByIdUsingColNumbers(int subjectId) throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        String query = "select * from subject_table where id = ?";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1, subjectId);
        ResultSet rs = preparedStmt.executeQuery();
        if (!rs.next())
            return null;
        return new Subject(rs.getInt(1), rs.getString(2));
    }

    public static void deleteSubjects() throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        String query = "delete from subject_table";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.executeUpdate();

    }

    public static void insertSchool(School school) throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        String query = "insert into school values (?, ?, ?)";
        PreparedStatement preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStmt.setNull(1, java.sql.Types.INTEGER);
        preparedStmt.setString (2, school.getName());
        preparedStmt.setInt (3, school.getYear());
        preparedStmt.executeUpdate();
        ResultSet generatedKeys = preparedStmt.getGeneratedKeys();
        if (generatedKeys.next()) {
            school.setId(generatedKeys.getInt(1));
        }
    }

    public static School getSchoolByIdUsingColNames(int schoolId) throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        String query = "select * from school where id = ?";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1, schoolId);
        ResultSet rs = preparedStmt.executeQuery();
        if (!rs.next())
            return null;
        return new School(rs.getInt("id"), rs.getString("name"), rs.getInt("year_field"));
    }

    public static School getSchoolByIdUsingColNumbers(int schoolId) throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        String query = "select * from school where id = ?";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1, schoolId);
        ResultSet rs = preparedStmt.executeQuery();
        if (!rs.next())
            return null;
        return new School(rs.getInt(1), rs.getString(2), rs.getInt(3));
    }

    public static void deleteSchools() throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        String query = "delete from school";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.executeUpdate();
    }

    public static void insertGroup(School school, Group group) throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        String query = "insert into group_table values (?, ?, ?, ?)";
        PreparedStatement preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStmt.setNull(1, java.sql.Types.INTEGER);
        preparedStmt.setString (2, group.getName());
        preparedStmt.setString (3, group.getRoom());
        preparedStmt.setInt (4, school.getId());
        preparedStmt.executeUpdate();
        ResultSet generatedKeys = preparedStmt.getGeneratedKeys();
        if (generatedKeys.next()) {
            group.setId(generatedKeys.getInt(1));
        }
    }

    public static School getSchoolByIdWithGroups(int id) throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        String query = "select * from school join group_table on school.id = group_table.school_id where school.id = ?";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt(1, id);
        ResultSet rs = preparedStmt.executeQuery();
        School result = null;
        while (rs.next()) {
            int id_school = rs.getInt(1);
            String name_school = rs.getString(2);
            int year_field = rs.getInt(3);
            int id_group = rs.getInt(4);
            String name_group = rs.getString(5);
            String room = rs.getString(6);
            if (result == null)
                result = new School(id_school, name_school, year_field);
            result.getGroups().add(new Group(id_group, name_group, room));
        }
        return result;
    }

    public static List<School> getSchoolsWithGroups() throws SQLException {
        Connection conn = JdbcUtils.getConnection();
        String query = "select * from school join group_table on school.id = group_table.school_id";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        ResultSet rs = preparedStmt.executeQuery();
        List<School> result = new ArrayList<>();
        while (rs.next()) {
            int id_school = rs.getInt(1);
            String name_school = rs.getString(2);
            int year_field = rs.getInt(3);
            int id_group = rs.getInt(4);
            String name_group = rs.getString(5);
            String room = rs.getString(6);
            int index_school = -1;
            for (int i = 0; i < result.size() && index_school == -1; i++)
                if (result.get(i).getId() == id_school)
                    index_school = i;
            if (index_school == -1)
                result.add(new School(id_school, name_school, year_field));
            result.get(result.size() - 1).getGroups().add(new Group(id_group, name_group, room));
        }
        return result;
    }
}
