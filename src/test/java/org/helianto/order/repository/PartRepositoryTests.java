package org.helianto.order.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.Serializable;

import javax.inject.Inject;

import org.helianto.core.domain.Identity;
import org.helianto.core.repository.IdentityRepository;
import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.order.domain.Part;
import org.helianto.user.domain.User;
import org.helianto.user.repository.UserRepository;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author mauriciofernandesdecastro
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PartRepositoryTests extends
		AbstractJpaRepositoryIntegrationTest<Part, PartRepository> {

	@Inject
	private PartRepository repository;

	@Inject
	private IdentityRepository identityRepository;

	@Inject
	private UserRepository userRepository;

	@Override
	protected PartRepository getRepository() {
		return repository;
	}

	@Override
	protected Part getNewTarget() {
		Identity identity = identityRepository.save(new Identity("principal"));
		User user = userRepository.save(new User(entity, identity));
		return new Part(user, "code");
	}

	@Override
	protected Serializable getTargetId(Part target) {
		return target.getId();
	}

	@Override
	protected Part findByKey() {
		Part target = getRepository().findByEntityAndDocCode(entity, "code");
		Part part = getRepository().findAdapter(target.getId());
		assertNotNull(part);
		assertEquals(target.getId(), part.getId());
		assertEquals(target.getId(), part.getId());
		assertEquals(target.getEntityId(), part.getEntityId());
//		assertEquals(target.getCategoryId(), part.getCategoryId());
		assertEquals(target.getOwnerId(), part.getOwnerId());
		assertEquals(target.getCurrencyId(), part.getCurrencyId());
		assertEquals(target.getIssueDate(), part.getIssueDate());
		assertEquals(target.getDocCode(), part.getDocCode());
		assertEquals(target.getDocName(), part.getDocName());
		assertEquals(target.getDocAbstract(), part.getDocAbstract());
		assertEquals(target.getActivityState(), part.getActivityState());
		assertEquals(target.isDocFlag(), part.isDocFlag());
		assertEquals(target.getDocValue(), part.getDocValue());
		assertEquals(target.getTokenPrefix(), part.getTokenPrefix());
		return target;
	}

}
