package com.lpereira.uts.unittestsample

import android.text.TextUtils
import com.lpereira.uts.unittestsample.utils.SomethingStatic
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers.any
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(PowerMockRunner::class)
@PrepareForTest(TextUtils::class)
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun addition_is() {
        PowerMockito.mockStatic(TextUtils::class.java)
        PowerMockito.`when`(TextUtils.equals(any(CharSequence::class.java), any(CharSequence::class.java)))
                .thenAnswer { invocation ->
                    val a = invocation.arguments[0] as CharSequence?
                    val a2 = invocation.arguments[1] as CharSequence?
                    return@thenAnswer (a?.equals(a2))
                }
        val something = SomethingStatic.getSomethingFromSomething("2")
        assertEquals(2, something)
    }
}
