package org.helianto.task.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.helianto.core.internal.SimpleCounter;
import org.helianto.task.domain.ProjectJournal;
import org.helianto.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Project journal repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ProjectJournalRepository extends JpaRepository<ProjectJournal, Serializable> {
	
	/**
	 * Find by natural key.
	 */
	ProjectJournal findByUserAndIssueDate(User user, Date issueDate);

	/**
	 * List project journals.
	 * 
	 * @param userId
	 */
	@Query("select projectJournal_ "
			+ "from ProjectJournal projectJournal_ "
			+ "where projectJournal_.user.id = ?1 "
			+ "and projectJournal_.projectJournalType = 'PRJ_CHECK_IN' "
			+ "order by projectJournal_.issueDate DESC ")
	List<ProjectJournal> findCheckinByUserAndProject(int userId);
	
	/**
	 * Last user journal project check-in.
	 * 
	 * @param userId
	 */
	@Query("select new "
			+ "org.helianto.core.internal.SimpleCounter("
			+ "  project_.id"
			+ ", count(project_.id)"
			+ ", max(projectJournal_.issueDate)"
			+ ") "
			+ "from Project project_ "
			+ "join project_.projectJournals projectJournal_ "
			+ "where projectJournal_.user.id = ?1 "
			+ "and project_.resolution = 'DOING' "
			+ "and projectJournal_.projectJournalType = 'PRJ_CHECK_IN' "
			+ "group by project_.id "
			+ "order by projectJournal_.issueDate DESC ")
	List<SimpleCounter> findByProjectCheckIn(int userId);
	
	/**
	 * Last user journal project check-out.
	 * 
	 * @param userId
	 */
	@Query("select new "
			+ "org.helianto.core.internal.SimpleCounter("
			+ "  project_.id"
			+ ", count(project_.id)"
			+ ", max(projectJournal_.issueDate)"
			+ ") "
			+ "from Project project_ "
			+ "join project_.projectJournals projectJournal_ "
			+ "where projectJournal_.user.id = ?1 "
			+ "and project_.resolution = 'DOING' "
			+ "and projectJournal_.projectJournalType = 'PRJ_CHECK_OUT' "
			+ "group by project_.id "
			+ "order by projectJournal_.issueDate DESC ")
	List<SimpleCounter> findByProjectCheckOut(int userId);

}
