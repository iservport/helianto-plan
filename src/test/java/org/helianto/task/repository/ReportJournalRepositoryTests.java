package org.helianto.task.repository;

import java.io.Serializable;
import java.util.Date;

import org.helianto.core.domain.Identity;
import org.helianto.core.repository.IdentityRepository;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.task.domain.ReportJournal;
import org.helianto.user.domain.User;
import org.helianto.user.repository.UserRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author mauriciofernandesdecastro
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ReportJournalRepositoryTests extends
	AbstractJpaRepositoryIntegrationTest<ReportJournal, ReportJournalRepository> {

	@Autowired
	private ReportJournalRepository repository;
	
	@Autowired
	private IdentityRepository identityRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	protected ReportJournalRepository getRepository() {
		return repository;
	}
	
	private Identity identity;
	
	private User user;
	
	private Date now = new Date();
	
	@Override
	protected ReportJournal getNewTarget() {
		identity = identityRepository.saveAndFlush(new Identity("principal"));
		user = userRepository.saveAndFlush(new User(entity, identity));
		return new ReportJournal(user, now);
	}
	
	@Override
	protected Serializable getTargetId(ReportJournal target) {
		return target.getId();
	}
	
	@Override
	protected ReportJournal findByKey() {
		return getRepository().findByUserAndIssueDate(user, now);
	}

}
