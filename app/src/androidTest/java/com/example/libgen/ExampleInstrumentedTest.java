package com.example.libgen;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;


import static org.junit.Assert.*;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void UsingIdRestrofit() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        StaticDate staticDate = new StaticDate();
        staticDate.GlobalPOST("2019-12-13", "2019-12-13", appContext);
        assertFalse(StaticDate.FIASKO);
    }

    @Test
    public void UsingFullInformationBooks() {
        try {
            for (int i = 2448347; i < 2448357; i++)
                StaticDate.listID.add(i);

            Async async = new Async();
            async.execute();
            Thread.sleep(5000);
            assertTrue(!StaticDate.listFull.isEmpty());
        } catch (InterruptedException e) {
            Log.e("ДЛЯ РОЗРОБОТЧИКА", "ТЫ НЕ СДАШЬ КУРСАЧ УЕБАН");
            e.printStackTrace();
        }

    }


}
