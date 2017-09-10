package com.github.bufferings.thymeleaf.extras.nl2br.dialect;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.LinkedHashSet;
import java.util.Set;

public class Nl2brDialect extends AbstractProcessorDialect {

  public static final String NAME = "Nl2br Dialect";

  public static final String PREFIX = "nl2br";

  public static final int PROCESSOR_PRECEDENCE = 1000;

  public Nl2brDialect() {
    super(NAME, PREFIX, PROCESSOR_PRECEDENCE);
  }

  public Set<IProcessor> getProcessors(String dialectPrefix) {
    final Set<IProcessor> processors = new LinkedHashSet<IProcessor>();
    processors.add(new Nl2brTextTagProcessor(TemplateMode.HTML, dialectPrefix));
    return processors;
  }
}
