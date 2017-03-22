package pl.hypeapp.episodie.entrypoints.rest.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import spock.lang.Specification

class NotFoundExceptionTest extends Specification {

    def "shouldThrow404"() {
        given:
        ResponseStatus responseStatus = NotFoundException.class.getAnnotation(ResponseStatus.class)
        expect:
        responseStatus.value() == HttpStatus.NOT_FOUND
    }
}
