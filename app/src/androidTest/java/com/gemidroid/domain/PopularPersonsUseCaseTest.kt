package com.gemidroid.domain

import com.gemidroid.data.model.PopularPerson
import com.gemidroid.data.model.Work
import org.junit.Assert
import org.junit.Test

class PopularPersonsUseCaseTest {

    @Test
    fun getPopoularPersons() {
        val mockedPersons = mutableListOf(
            PopularPerson(1, "Ahmed", "/123abc.jpg", listOf(Work("Sports"))),
            PopularPerson(2, "Ali", "/1234abcd.jpg", listOf(Work("Science"))),
            PopularPerson(3, "Mohamed", "/12345abcde.jpg", listOf(Work("Programming"))),
            PopularPerson(4, "Khalid", "/123456abcdef.jpg", listOf(Work("AI"))),
            PopularPerson(5, "Hanafi", "/1234567abcdefh.jpg", listOf(Work("ML"))))
        Assert.assertNotNull(mockedPersons)
    }
}
