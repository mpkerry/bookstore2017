package cscie56.demo

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Book)
class BookSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test validation of nullable objects" () {
        when:
            Book b1 = new Book()
        then:
            !b1.validate()
        when:
            b1 = new Book(title:'title',dateOfPublication: new Date(), isbn: "1234567890", author: new Author())
        then:
            b1.validate()
    }

    void "test isbn is unique"() {
        when:
            Book b1 = new Book(title:'title',dateOfPublication: new Date(), isbn: "1234567890", author: new Author())
            b1.save(flush:true)
            Book b2 = new Book(title:'title',dateOfPublication: new Date(), isbn: "1234567890", author: new Author())
            b2.save()
        then:
            b1.validate()
            !b2.validate()
    }
}