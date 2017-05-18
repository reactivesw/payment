package io.reactivesw.payment.domain.service

import com.google.common.collect.Lists
import io.reactivesw.payment.application.model.BraintreeConfigModel
import io.reactivesw.payment.application.model.mapper.BraintreeConfigMapper
import io.reactivesw.payment.domain.model.BraintreeConfig
import io.reactivesw.payment.infrastructure.repository.BraintreeConfigRepository
import spock.lang.Specification

import java.security.PublicKey

/**
 * Test for BrainTreeconfigService.
 */
class BraintreeConfigServiceTest extends Specification {
    BraintreeConfigRepository configRepository = Mock()
    BraintreeConfigService service = new BraintreeConfigService(repository: configRepository)

    BraintreeConfigModel model = new BraintreeConfigModel("env", "mId", "publicKey", "privateKey")

    BraintreeConfig entity = BraintreeConfigMapper.toEntity(model)

    def "Test1: save config when there is not config id db"() {
        given:
        configRepository.findAll() >> Lists.newArrayList()
        configRepository.save(_) >> entity

        when:
        def result = service.save(model)

        then:
        result != null
        result == entity
    }

    def "Test2: save config when there is a config entity"() {
        given:
        configRepository.findAll() >> Lists.newArrayList(entity)
        configRepository.save(_) >> entity

        when:
        def result = service.save(model)

        then:
        result == entity
    }
}
