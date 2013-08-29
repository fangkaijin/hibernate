package com.love.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import com.love.model.HibernateSessionFactory;
import com.love.model.Student;

public class TestHibernate {

	/**
	 * 
	 * @方法说明: 插入数据
	 * @参数:
	 * @return void
	 * @throws
	 */
	public static void insertData() {

		Session session = HibernateSessionFactory.getSession();

		Transaction tx = session.beginTransaction();

		Student sdt = new Student();

		sdt.setName("方小开");

		Short age = Short.parseShort("11");

		Short score = Short.parseShort("100");

		sdt.setScore(score);

		sdt.setAge(age);

		try {
			session.save(sdt);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		} finally {

			session.close();
		}
	}

	/**
	 * 
	 * @方法说明: 使用session.load方法查询数据
	 * @参数: @param id
	 * @参数: @return
	 * @return Student
	 * @throws
	 */
	public static Student queryDataByLoad(int id) {

		Session session = HibernateSessionFactory.getSession();

		Transaction tx = session.beginTransaction();

		List<Student> list = new ArrayList<Student>();
		try {
			
			//Student std = (Student) session.load(Student.class, id);
			
			Query query = session.createQuery("from Student as s where s.id = :id").setCacheable(true);
			
			query.setInteger("id", id);
			list = query.list();
			
			tx.commit();
			if(list != null && list.size() > 0){
				
				return list.get(0);
			}else {
				
				return null;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 
	 * @方法说明: 根据session.get方法查询数据
	 * @参数: @param id
	 * @参数: @return
	 * @return Student
	 * @throws
	 */
	public static Student queryDataByGet(int id) {

		Session session = HibernateSessionFactory.getSession();

		Transaction tx = session.beginTransaction();

		try {

			Student student = (Student) session.get(Student.class, id);
			tx.commit();
			return student;
		} catch (Exception e) {
			// TODO: handle exception

			tx.rollback();
			e.printStackTrace();

			return null;
		}
	}

	/**
	 * 
	 * @方法说明: 根据id修改
	 * @参数: @param id
	 * @return void
	 * @throws
	 */
	public static void updateData(int id) {

		Session session = HibernateSessionFactory.getSession();

		Transaction tx = session.beginTransaction();

		try {
			Student std = (Student) session.get(Student.class, id);

			if (std != null) {

				std.setName("季飞红");

				session.update(std);

				tx.commit();
			} else {

				throw new Exception("....");
			}
		} catch (Exception e) {
			// TODO: handle exception

			tx.rollback();
			e.printStackTrace();
		} finally {

			session.close();
		}
	}

	public static void deleteData(int id) {

		Session session = HibernateSessionFactory.getSession();

		Transaction tx = session.beginTransaction();

		try {

			Student std = (Student) session.get(Student.class, id);

			session.delete(std);

			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception

			tx.rollback();

			e.printStackTrace();
		} finally {

			session.close();
		}
	}

	/**
	 * @方法说明:
	 * @参数: @param args
	 * @return void
	 * @throws
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// insertData();

		// System.out.println(queryDataByGet(Integer.parseInt("5")));

		//deleteData(Integer.parseInt("2"));
		
		queryDataByLoad(Integer.parseInt("2"));
		
		
		
	}

}
