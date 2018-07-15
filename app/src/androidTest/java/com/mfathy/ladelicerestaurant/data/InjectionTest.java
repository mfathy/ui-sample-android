package com.mfathy.ladelicerestaurant.data;

import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Mohammed Fathy on 15/07/2018.
 * dev.mfathy@gmail.com
 */
public class InjectionTest {

    @Test
    public void provideDataRepository() throws Exception {
        DataRepository dataRepository = Injection.provideDataRepository(InstrumentationRegistry.getTargetContext());
        assertNotNull(dataRepository);
    }

}