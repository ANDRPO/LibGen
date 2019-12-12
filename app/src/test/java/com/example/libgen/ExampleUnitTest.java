package com.example.libgen;


import org.junit.Test;
import org.robolectric.Robolectric;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import static org.mockito.Mockito.*;
import android.text.Html;
import android.util.Log;


import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest extends FullBookDescription{
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void ConvertDateCorret() throws ParseException {
        String Adate = "2019-12-11";
        String Bdate = "2019-12-09";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        SplashScreen splashScreen = new SplashScreen();
        assertEquals(Bdate, splashScreen.date(Adate, dateFormat));
    }

    @Test
    public void ParseMB(){
        FullBookDescription fullBookDescription = new FullBookDescription();
        String defaultMB = fullBookDescription.MBfile("6454311");
        assertEquals("6,16", defaultMB);
    }

    @Test
    public void ParseHtmlFormatToTXT()  {

        FullBookDescription activity = mock(FullBookDescription.class);

        String stringtxt = "Он словно вырезан из камня, стоек и неподвижен в отличие от его противников.Дух и жизненная сила в нём достигла совершенства.Но вот беда — никто не смеет принять его вызов.";
        assertEquals(activity.CorrectConvertHtmlToText("<p>Он словно вырезан из <b>камня</b>, стоек и неподвижен в отличие от его противников.<i>Дух и жизненная сила</i> в нём достигла <b><i>совершенства</i></b>.Но вот беда — никто не смеет принять его вызов.</p>"), stringtxt);
    }




}