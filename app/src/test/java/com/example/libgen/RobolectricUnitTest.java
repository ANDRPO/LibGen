package com.example.libgen;

import android.net.Uri;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

@Config(sdk = 27, manifest = Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class RobolectricUnitTest {

    @Test
    public void ConvertHtmlFormatToTXT()  {

        FullBookDescription fullBookDescription = new FullBookDescription();
        String stringtxt = "Он словно вырезан из камня";
        String stringhtml = "<strong>Он словно вырезан из <b>камня</b></strong>";
        assertEquals(fullBookDescription.CorrectConvertHtmlToText(stringhtml), stringtxt);
    }

    @Test
    public void MakeLinkDownload(){
        FullBookDescription fullBookDescription = new FullBookDescription();
        Uri correct = Uri.parse("http://93.174.95.29/main/2448000/A3DCB4D229DE6FDE0DB5686DEE47145D/test_book.pdf");
        String md5case1= "\"A3DCB4D229DE6FDE0DB5686DEE47145D\"";
        String md5case2 = "A3DCB4D229DE6FDE0DB5686DEE47145D";
        String md5empty = "";

        String locatorcase1 = "\"test_book.pdf\"";
        String locatorcase2 = "test_book.pdf";
        String locatorempty = "";

        assertEquals(fullBookDescription.MakeLinkDownload(md5case1, locatorcase1), correct);
        assertEquals(fullBookDescription.MakeLinkDownload(md5case2, locatorcase2), correct);
    }


}
