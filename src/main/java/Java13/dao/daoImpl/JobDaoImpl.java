package Java13.dao.daoImpl;

import Java13.config.JdbcConfig;
import Java13.dao.JobDao;
import Java13.model.Employee;
import Java13.model.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDaoImpl implements JobDao {
    private final Connection connection = JdbcConfig.getConnection();

    @Override
    public void createJobTable() {
        String sql = """
                 create table if not exists jobs (id serial primary key ,
                 position varchar ,
                 profession varchar ,
                 description varchar ,
                 experience int);
                """;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("success");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void addJob(Job job) {
        String sql = """
                insert into jobs(position,profession,description, experience)
                values(?,?,?,?)
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, job.getPosition());
            preparedStatement.setString(2, job.getProfession());
            preparedStatement.setString(3, job.getDescription());
            preparedStatement.setInt(4, job.getExperience());
            preparedStatement.executeUpdate();
            System.out.println("successfully added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    @Override
    public Job getJobById(Long jobId) {
        Job job = new Job();
        String sql = "select * from jobs where id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, jobId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }


            job.setId(resultSet.getLong("id"));
            job.setPosition(resultSet.getString("position"));
            job.setProfession(resultSet.getString("profession"));
            job.setDescription(resultSet.getString("description"));
            job.setExperience(resultSet.getInt("experience"));

            return job;
        } catch (SQLException e) {
            throw new RuntimeException("Error while retrieving job: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        List<Job> jobList = new ArrayList<>();

        String sql = null;
        if (ascOrDesc.equalsIgnoreCase("asc")) {
            sql = """ 
                       select * from jobs j order by j.experience desc;
                    """;

        } else if (ascOrDesc.equalsIgnoreCase("desc")) {
            sql = """
                    select * from jobs j order by j.experience desc;
                    """;
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new RuntimeException(" not id job ");
            }
            Job job = new Job();
            job.setId(resultSet.getLong("id"));
            job.setPosition(resultSet.getNString("position"));
            job.setProfession(resultSet.getString("profession"));
            job.setDescription(resultSet.getString("description"));
            job.setExperience(resultSet.getInt("experience"));
            jobList.add(job);


            return jobList;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }


    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        Job job = new Job();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "select * from jobs j inner join employees e  on e.job_id = ?")) {
            preparedStatement.setLong(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("position"));
                job.setProfession(resultSet.getString("profession"));
                job.setDescription(resultSet.getString("description"));
                job.setExperience(resultSet.getInt("experience"));}
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return job;
    }






    @Override
    public void deleteDescriptionColumn() {
        String sql = "alter table jobs drop column description ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            int deleteAuthor = preparedStatement.executeUpdate();
            if (deleteAuthor > 0) {
                System.out.println("successfully deleted");
            } else {
                System.out.println("column not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }



    }

