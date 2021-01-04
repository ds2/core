package ds2.oss.core.templating.tests;

import ds2.oss.core.api.dto.impl.TemplateDataDto;
import ds2.oss.core.api.templating.TemplateData;
import ds2.oss.core.api.templating.TemplateResolver;
import ds2.oss.core.api.templating.TemplateType;
import ds2.oss.core.statics.IoMethods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.invoke.MethodHandles;

@ApplicationScoped
public class TestResolver implements TemplateResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public TemplateData getTemplateContent(String templateId) {
        LOGGER.debug("Trying to load template {}", templateId);
        TemplateDataDto rc = new TemplateDataDto();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(templateId + ".txt");
        String data = IoMethods.readResourceFromClasspath(templateId + ".txt");
        if (data != null) {
            rc.setContentStream(new StringReader(data));
        }
        rc.setTemplateType(TemplateType.PLAINTEXT);
        rc.setDescription("Text 1");
        LOGGER.debug("Done, returning: {}", rc);
        return rc;
    }
}
