package ds2.oss.core.api;

import ds2.oss.core.api.templating.RequestContext;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public interface ContentTemplateService {
    String formatContent(String templateName, RequestContext requestContext, Map<String, Object> placeholders) throws CoreException;

    void writeContent(String templateName, RequestContext requestContext, Map<String, Object> placeholders, Writer writer) throws CoreException, IOException;
}
