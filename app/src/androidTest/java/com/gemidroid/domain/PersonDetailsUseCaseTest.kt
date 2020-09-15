package com.gemidroid.domain

import com.gemidroid.data.model.PersonDetails
import org.junit.Assert
import org.junit.Test

class PersonDetailsUseCaseTest {
    private fun checkPersonDetails(userId: Int): PersonDetails? {
        val personDetails = PersonDetails(
            1, "Ahmed", "test test", 8.9, "15-9-2020", "/123abc.jpg", null,
            "Egypt", "Acting", null, null
        )

        return if (userId == 1) {
            personDetails
        } else null
    }

    @Test
    fun getPersonDetailsIfNull() {
        val personDetails = null
       Assert.assertEquals(personDetails, checkPersonDetails(1))
    }

    @Test
    fun getPersonDetailsIfNotNull() {
        val personDetails = PersonDetails(
            1, "Ahmed", "test test", 8.9, "15-9-2020", "/123abc.jpg", null,
            "Egypt", "Acting", null, null
        )
       Assert.assertNotEquals(personDetails, checkPersonDetails(2))
    }
}