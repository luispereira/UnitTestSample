package com.lpereira.uts.unittestsample;

import android.text.TextUtils;
import com.lpereira.uts.unittestsample.utils.SomethingStatic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

/**
 * @author lpereira on 17/12/2017.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(TextUtils.class)
public class TextUtilsCase {

    @Test
    public void addition_is() {
        //GIVEN
        PowerMockito.mockStatic(TextUtils.class);
        boolean equals = TextUtils.equals(any(CharSequence.class), any(CharSequence.class));
        PowerMockito.when(equals).thenAnswer(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                CharSequence charSequence = invocation.getArgument(0);
                CharSequence charSequence2 = invocation.getArgument(1);
                return (charSequence != null && charSequence.equals(charSequence2));
            }
        });
        //WHEN
        int something = SomethingStatic.getSomethingFromSomething("2");
        int something2 = SomethingStatic.getSomethingFromSomething("1");
        int something3 = SomethingStatic.getSomethingFromSomething(null);
        //THEN
        assertEquals(something, 2);
        assertEquals(something2, 0);
        assertEquals(something3, 0);
    }
}
