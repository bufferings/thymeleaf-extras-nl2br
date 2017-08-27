package com.github.bufferings.thymeleaf.extras.nl2br.dialect;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.processor.AbstractStandardExpressionAttributeTagProcessor;
import org.thymeleaf.standard.processor.StandardTextTagProcessor;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.util.Validate;
import org.unbescape.html.HtmlEscape;

import java.util.regex.Pattern;

/**
 * Inserts HTML line breaks(&lt;br /&gt;) before all newlines in a string after escaping HTML.
 * <p>
 * Only HTML template mode is supported.
 *
 * @see StandardTextTagProcessor
 */
public final class Nl2brTextTagProcessor extends AbstractStandardExpressionAttributeTagProcessor {

  public static final int PRECEDENCE = 1300;

  public static final String ATTR_NAME = "text";

  private static final Pattern NEW_LINE_PATTERN = Pattern.compile("(\r\n|\n)");

  public Nl2brTextTagProcessor(final TemplateMode templateMode, final String dialectPrefix) {
    super(templateMode, dialectPrefix, ATTR_NAME, PRECEDENCE, true);
    Validate.notNull(templateMode, "Template mode cannot be null");
    Validate.isTrue(templateMode == TemplateMode.HTML,
        "Unsupported template mode " + templateMode + ". Only HTML mode is supported.");
  }

  @Override
  protected void doProcess(
      final ITemplateContext context,
      final IProcessableElementTag tag,
      final AttributeName attributeName, final String attributeValue,
      final Object expressionResult,
      final IElementTagStructureHandler structureHandler) {
    final String input = (expressionResult == null ? "" : expressionResult.toString());
    final CharSequence text = produceEscapedOutput(input);
    structureHandler.setBody(text, false);
  }

  // VisibleForTesting
  static String produceEscapedOutput(final String input) {
    return nl2br(HtmlEscape.escapeHtml4Xml(input));
  }

  // VisibleForTesting
  static String nl2br(final String input) {
    return NEW_LINE_PATTERN.matcher(input).replaceAll("<br />$1");
  }
}
