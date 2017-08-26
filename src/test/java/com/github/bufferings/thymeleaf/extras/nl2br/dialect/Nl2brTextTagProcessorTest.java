package com.github.bufferings.thymeleaf.extras.nl2br.dialect;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class Nl2brTextTagProcessorTest {

  @Test
  public void nl2br() throws Exception {
    String actual;

    // \n
    actual = Nl2brTextTagProcessor.nl2br("line1\nline2\n");
    assertThat(actual, is("line1<br />\nline2<br />\n"));

    actual = Nl2brTextTagProcessor.nl2br("\n\nline1\n\nline2");
    assertThat(actual, is("<br />\n<br />\nline1<br />\n<br />\nline2"));

    // \r\n
    actual = Nl2brTextTagProcessor.nl2br("\r\nline1\r\nline2");
    assertThat(actual, is("<br />\r\nline1<br />\r\nline2"));

    actual = Nl2brTextTagProcessor.nl2br("line1\r\n\r\nline2\r\n\r\n");
    assertThat(actual, is("line1<br />\r\n<br />\r\nline2<br />\r\n<br />\r\n"));

    // mixed
    actual = Nl2brTextTagProcessor.nl2br("line1\r\n\nline2");
    assertThat(actual, is("line1<br />\r\n<br />\nline2"));

    actual = Nl2brTextTagProcessor.nl2br("line1\r\r\n\nline2");
    assertThat(actual, is("line1\r<br />\r\n<br />\nline2"));
  }

  @Test
  public void produceEscapedOutput() throws Exception {
    String actual;

    // br tag is added before \n and \r\n
    actual = Nl2brTextTagProcessor.produceEscapedOutput("line1\nline2\r\n");
    assertThat(actual, is("line1<br />\nline2<br />\r\n"));

    // HTML tag is escaped
    actual = Nl2brTextTagProcessor.produceEscapedOutput("<br>line1\nline2\r\n");
    assertThat(actual, is("&lt;br&gt;line1<br />\nline2<br />\r\n"));
  }

}