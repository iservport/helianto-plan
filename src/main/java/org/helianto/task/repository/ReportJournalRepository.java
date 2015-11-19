package org.helianto.task.repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.helianto.core.internal.SimpleCounter;
import org.helianto.task.domain.ReportJournal;
import org.helianto.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Report journal repository.
 * 
 * @author mauriciofernandesdecastro
 */
public interface ReportJournalRepository extends JpaRepository<ReportJournal, Serializable> {
	
	/**
	 * Find by natural key.
	 */
	ReportJournal findByUserAndIssueDate(User user, Date issueDate);

	/**
	 * List report journals.
	 * 
	 * @param userId
	 */
	@Query("select reportJournal_ "
			+ "from ReportJournal reportJournal_ "
			+ "where reportJournal_.user.id = ?1 "
			+ "and reportJournal_.reportJournalType = 'PRJ_CHECK_IN' "
			+ "order by reportJournal_.issueDate DESC ")
	List<ReportJournal> findCheckinByUserAndReport(int userId);
	
	/**
	 * Last user journal report check-in.
	 * 
	 * @param userId
	 */
	@Query("select new "
			+ "org.helianto.core.internal.SimpleCounter("
			+ "  report_.id"
			+ ", count(report_.id)"
			+ ", max(reportJournal_.issueDate)"
			+ ") "
			+ "from Report report_ "
			+ "join report_.reportJournals reportJournal_ "
			+ "where reportJournal_.user.id = ?1 "
			+ "and report_.resolution = 'DOING' "
			+ "and reportJournal_.reportJournalType = 'PRJ_CHECK_IN' "
			+ "group by report_.id "
			+ "order by reportJournal_.issueDate DESC ")
	List<SimpleCounter> findByReportCheckIn(int userId);
	
	/**
	 * Last user journal report check-out.
	 * 
	 * @param userId
	 */
	@Query("select new "
			+ "org.helianto.core.internal.SimpleCounter("
			+ "  report_.id"
			+ ", count(report_.id)"
			+ ", max(reportJournal_.issueDate)"
			+ ") "
			+ "from Report report_ "
			+ "join report_.reportJournals reportJournal_ "
			+ "where reportJournal_.user.id = ?1 "
			+ "and report_.resolution = 'DOING' "
			+ "and reportJournal_.reportJournalType = 'PRJ_CHECK_OUT' "
			+ "group by report_.id "
			+ "order by reportJournal_.issueDate DESC ")
	List<SimpleCounter> findByReportCheckOut(int userId);
	
	/**
	 * Cherry-picking of journal records.
	 * 
	 * Only the last id of a group is taken, allowing the DB to remain immutable.
	 * 
	 * @param userId
	 * @param start
	 * @param end
	 */
	@Query("select reportJournal_ "
			+ "from ReportJournal reportJournal_ "
			+ "where reportJournal_.id in ("
			+ "  select max(r_.id) "
			+ "  from ReportJournal r_ "
			+ "  where r_.user.id = ?1 "
			+ "  group by r_.report.id, r_.reportJournalType, r_.day "
			+ ") "
			+ "and reportJournal_.issueDate between ?2 and ?3 "
			+ "order by reportJournal_.report.id, reportJournal_.reportJournalType ")
	List<ReportJournal> findGroupByDay(int userId, Date start, Date end);

}
