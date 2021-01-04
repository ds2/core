package ds2.oss.core.templating;

import ds2.oss.core.api.ContentTemplateService;
import ds2.oss.core.api.CoreException;
import ds2.oss.core.api.templating.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templateresolver.ITemplateResolver;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.io.Writer;
import java.lang.invoke.MethodHandles;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@ApplicationScoped
public class ThymeleafTemplateServiceImpl implements ContentTemplateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private ITemplateEngine templateEngine;
    @Inject
    private ITemplateResolver iTemplateResolver;

    @PostConstruct
    public void onLoad() {
        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(iTemplateResolver);
        templateEngine = engine;
    }

    @Override
    public String formatContent(String templateName, RequestContext targetLocale, Map<String, Object> placeholders) {
        LOGGER.debug("Entering template check for template {} with placeHolders: {}", templateName, placeholders);
        placeholders.put("now", ZonedDateTime.now());
        IContext ctx = new IContext() {
            @Override
            public Locale getLocale() {
                return targetLocale.getLocale();
            }

            @Override
            public boolean containsVariable(String s) {
                return placeholders.containsKey(s);
            }

            @Override
            public Set<String> getVariableNames() {
                return placeholders.keySet();
            }

            @Override
            public Object getVariable(String s) {
                return placeholders.get(s);
            }
        };
        String rc = templateEngine.process(templateName, ctx);
        LOGGER.debug("template result is: {}", rc);
        return rc;
    }

    @Override
    public void writeContent(String templateName, RequestContext requestContext, Map<String, Object> placeholders, Writer writer) throws CoreException, IOException {
        String result = formatContent(templateName, requestContext, placeholders);
        writer.write(result);
    }
}
