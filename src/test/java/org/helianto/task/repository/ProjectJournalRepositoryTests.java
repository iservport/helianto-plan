package org.helianto.task.repository;

import java.io.Serializable;
import java.util.Date;

import org.helianto.core.domain.Identity;
import org.helianto.core.repository.IdentityRepository;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.task.domain.ProjectJournal;
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
public class ProjectJournalRepositoryTests extends
	AbstractJpaRepositoryIntegrationTest<ProjectJournal, ProjectJournalRepository> {

	@Autowired
	private ProjectJournalRepository repository;
	
	@Autowired
	private IdentityRepository identityRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	protected ProjectJournalRepository getRepository() {
		return repository;
	}
	
	private Identity identity;
	
	private User user;
	
	private Date now = new Date();
	
	@Override
	protected ProjectJournal getNewTarget() {
		identity = identityRepository.saveAndFlush(new Identity("principal"));
		user = userRepository.saveAndFlush(new User(entity, identity));
		return new ProjectJournal(user, now);
	}
	
	@Override
	protected Serializable getTargetId(ProjectJournal target) {
		return target.getId();
	}
	
	@Override
	protected ProjectJournal findByKey() {
		return getRepository().findByUserAndIssueDate(user, now);
	}

}
