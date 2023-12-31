package com.assign.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.assign.entites.StudentAndSubject;
import com.assign.jdbc.JDBC;

public class StudentAndSubjectDao {
	
	
	/**
	 * Get all students who have registered the subject by subjectID
	 * @param subjectID
	 * @return
	 */
	public ArrayList<StudentAndSubject> getSubjectStudents(int subjectID) {
		
		ArrayList<StudentAndSubject> stuArray = new ArrayList<StudentAndSubject>();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			statement = JDBC.getStatement();
			String sql = "select s.studentID, s.fullName, ss.score, s.latestVersion from Student s "
					+ "right join (select studentID, score from StudentAndSubject where subjectID = " + subjectID + ") as ss "
					+ "on ss.studentID = s.studentID;";
			//System.out.println(sql);
			rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				StudentAndSubject sas = new StudentAndSubject();
				sas.setSubjectID(subjectID);
				sas.setStudentID(rs.getInt("studentID"));
				sas.setFullName(rs.getString("fullName"));
				sas.setScore(rs.getString("score"));
				sas.setLatestVersion(rs.getInt("latestVersion"));
				stuArray.add(sas);			
			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {

					statement.close();

					rs = null;
				}

				if (connection != null) {
					connection.close();
					connection = null;
				}
				
				if (rs != null) {
					rs.close();
					rs = null;
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		return stuArray;
	}
	
	/**
	 * Update the subject score of the student by sutdentID and subjectID.
	 * @param studentID
	 * @param subjectID
	 */
	public void updateScore(int studentID, int subjectID, float score) {
		String sql = "update StudentAndSubject set score = ? where studentID = ? and subjectID = ?";
		PreparedStatement prepStatement = null;
		Connection connection = null;
		try {
			connection = JDBC.getConnection();
			prepStatement = connection.prepareStatement(sql);
			prepStatement.setFloat(1, score);
			prepStatement.setInt(2, studentID);
			prepStatement.setInt(3, subjectID);
			prepStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (prepStatement != null) {
					prepStatement.close();
				}

				if (connection != null) {
					connection.close();
					connection = null;
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}
}
