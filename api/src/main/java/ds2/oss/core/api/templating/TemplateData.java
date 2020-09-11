package ds2.oss.core.api.templating;

import java.io.Reader;

public interface TemplateData {
    Reader getContentStream();

    TemplateType getTemplateType();

    String getDescription();
}
