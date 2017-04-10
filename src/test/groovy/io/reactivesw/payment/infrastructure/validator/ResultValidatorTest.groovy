package io.reactivesw.payment.infrastructure.validator

import com.braintreegateway.Result
import com.braintreegateway.Transaction
import io.reactivesw.exception.ParametersException
import org.mockito.Mock
import spock.lang.Specification

/**
 * Created by Davis on 17/4/10.
 */
class ResultValidatorTest extends Specification{

    ResultValidator validator = new ResultValidator()

    def "Test1: validate ok"() {
        given:
        Transaction transaction = Mock()
        Result result = Mock()
        result.getTarget() >> transaction

        when:
        ResultValidator.validate(result)

        then:
        true
    }

    def "Test2: target is null and throw exception"() {
        given:
        Result result = Mock()
        result.getTarget() >> null

        when:
        ResultValidator.validate(result)

        then:
        thrown(ParametersException)
    }
}
