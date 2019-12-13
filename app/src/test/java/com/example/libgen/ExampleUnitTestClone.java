package com.example.libgen;


import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTestClone{
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void ConvertDateCorret() throws ParseException {
        String Adate = "2019-12-10";
        String Bdate = "2019-12-09";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        SplashScreen splashScreen = new SplashScreen();
        assertEquals(Bdate, splashScreen.date(Adate, dateFormat));
    }

    @Test
    public void ParseMB(){
        FullBookDescription fullBookDescription = new FullBookDescription();
        String defaultMB = fullBookDescription.MBfile("6454311");
        assertEquals("6,16MB", defaultMB);
    }

    @Test
    public void ParseHtmlFormatToTXT()  {
        FullBookDescription fullBookDescription = new FullBookDescription();
        String correct = "http://93.174.95.29/covers/2448000/60406c45724cceeeee444fd861bc6f50-g.jpg";
        String cover = "\"2448000/60406c45724cceeeee444fd861bc6f50-g.jpg\"";
        assertEquals(fullBookDescription.MakeLinkCover(cover), correct);
    }

    @Test
    public void ParseYearOrPages(){
        FullBookDescription fullBookDescription = new FullBookDescription();

        String inYearOne = "\"2019\"";
        String correctYearOne = "2019";
        String inYearTwo = "\"0\"";
        String correctYearTwo = "\"No\"";
        String inPagesOne = "\"5913\"";
        String correctPagesOne = "5913";
        String inPagesTwo = "\"0\"";
        String correctPagesTwo = "\"No\"";

        assertEquals(fullBookDescription.MakeCorrectYearOrPages(inYearOne), correctYearOne);
        assertEquals(fullBookDescription.MakeCorrectYearOrPages(inYearTwo), correctYearTwo);
        assertEquals(fullBookDescription.MakeCorrectYearOrPages(inPagesOne), correctPagesOne);
        assertEquals(fullBookDescription.MakeCorrectYearOrPages(inPagesTwo), correctPagesTwo);
    }





}