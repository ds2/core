package ds2.oss.core.templating;

import ds2.oss.core.api.templating.TemplateData;
import ds2.oss.core.api.templating.TemplateResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.cache.ICacheEntryValidity;
import org.thymeleaf.cache.NonCacheableCacheEntryValidity;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolution;
import org.thymeleaf.templateresource.ITemplateResource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.io.Reader;
import java.lang.invoke.MethodHandles;
import java.util.Map;

@ApplicationScoped
public class InternalTemplateResolver implements ITemplateResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Inject
    private TemplateResolver templateResolver;

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Integer getOrder() {
        return null;
    }

    @Override
    public TemplateResolution resolveTemplate(IEngineConfiguration iEngineConfiguration, String owner, String s1, Map<String, Object> map) {
        LOGGER.debug("Entering resolver");
        ICacheEntryValidity cacheValidity = new NonCacheableCacheEntryValidity();
        TemplateData templateData = templateResolver.getTemplateContent(s1);
        ITemplateResource templateResource = new ITemplateResource() {
            @Override
            public String getDescription() {
                return exists() ? templateData.getDescription() : null;
            }

            @Override
            public String getBaseName() {
                return s1;
            }

            @Override
            public boolean exists() {
                return templateData != null;
            }

            @Override
            public Reader reader() throws IOException {
                return templateData.getContentStream();
            }

            @Override
            public ITemplateResource relative(String s) {
                return null;
            }
        };
        TemplateMode templateMode = TemplateMode.RAW;
        if (templateData != null) {
            switch (templateData.getTemplateType()) {
                case HTML5:
                    templateMode = TemplateMode.HTML;
                    break;
                case XML:
                    templateMode = TemplateMode.XML;
                    break;
                case PLAINTEXT:
                    templateMode = TemplateMode.TEXT;
                    break;
                default:
                    throw new IllegalStateException("Unknown templateType which cannot be mapped to Thymeleaf: " + templateData.getTemplateType());
            }
        }
        TemplateResolution templateResolution = new TemplateResolution(templateResource, templateMode, cacheValidity);
        LOGGER.debug("Returning solution: {}", templateResolution);
        return templateResolution;
    }
}
