package org.helianto.order.repository;

import java.io.Serializable;

import javax.inject.Inject;

import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.order.domain.Part;
import org.helianto.order.domain.PartEquivalent;
import org.helianto.partner.domain.Partner;
import org.helianto.partner.domain.PrivateEntity;
import org.helianto.partner.repository.PartnerRepository;
import org.helianto.partner.repository.PrivateEntityRepository;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author mauriciofernandesdecastro
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PartEquivalentRepositoryTests extends
		AbstractJpaRepositoryIntegrationTest<PartEquivalent, PartEquivalentRepository> {

	@Inject
	private PartEquivalentRepository repository;

	@Inject
	private PartRepository partRepository;

	@Inject
	private PrivateEntityRepository privateEntityRepository;

	@Inject
	private PartnerRepository partnerRepository;

	@Override
	protected PartEquivalentRepository getRepository() {
		return repository;
	}

	Part part;
	Partner partner;
	
	@Override
	protected PartEquivalent getNewTarget() {
		part = partRepository.save(new Part(entity, "PART"));
		PrivateEntity privateEntity = privateEntityRepository.save(new PrivateEntity(entity, "PARTNER"));
		partner = partnerRepository.save(new Partner(privateEntity));
		return new PartEquivalent(part, partner);
	}

	@Override
	protected Serializable getTargetId(PartEquivalent target) {
		return target.getId();
	}

	@Override
	protected PartEquivalent findByKey() {
		return getRepository().findByPartAndPartner(part, partner);
	}

}
