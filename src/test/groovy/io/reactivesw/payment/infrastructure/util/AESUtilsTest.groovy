package io.reactivesw.payment.infrastructure.util

import spock.lang.Specification

import javax.crypto.BadPaddingException

/**
 * Test for AesUtils.
 */
class AESUtilsTest extends Specification {

    def secretKey = "DSihgbfnrXR3FLNj"
    def wrongKey = "qz2eaXX_2URoNz8A"
    def strToEncrypt = "iusodfnmcjhsgdfydufd"

    def "Test1: encrypt string and decrypt it"() {
        when:
        def encryptedString = AesUtils.encrypt(strToEncrypt, secretKey)

        then:
        encryptedString != null

        when:
        def decryptedString = AesUtils.decrypt(encryptedString, secretKey)

        then:
        decryptedString == strToEncrypt
    }

    def "Test2: encrypt string and decrypt it with wrong key"() {
        setup:
        def encryptedString = AesUtils.encrypt(strToEncrypt, secretKey)

        when:
        AesUtils.decrypt(encryptedString, wrongKey)

        then:
        thrown(BadPaddingException)
    }
}
