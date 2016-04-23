package org.helianto.order.repository;

import java.io.Serializable;

import org.helianto.core.test.AbstractJpaRepositoryIntegrationTest;
import org.helianto.order.domain.Contract;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Eldevan Nery Junior
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ContractRepositoryTests extends
		AbstractJpaRepositoryIntegrationTest<Contract, ContractRepository> {

	@Autowired
	private ContractRepository repository;

	@Override
	protected ContractRepository getRepository() {
		return repository;
	}

	@Override
	protected Contract getNewTarget() {

		return new Contract(entity, "code");
	}

	@Override
	protected Serializable getTargetId(Contract target) {
		return target.getId();
	}

	@Override
	protected Contract findByKey() {
		return getRepository().findByEntityAndDocCode(entity, "code");
	}

}
