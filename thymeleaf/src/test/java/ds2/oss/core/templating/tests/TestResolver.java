package ds2.oss.core.templating.tests;

import ds2.oss.core.api.dto.impl.TemplateDataDto;
import ds2.oss.core.api.templating.TemplateData;
import ds2.oss.core.api.templating.TemplateResolver;
import ds2.oss.core.api.templating.TemplateType;

import javax.enterprise.context.ApplicationScoped;
import java.io.InputStreamReader;

@ApplicationScoped
public class TestResolver implements TemplateResolver {
    @Override
    public TemplateData getTemplateContent(String templateId) {
        TemplateDataDto rc=new TemplateDataDto();
        rc.setContentStream(new InputStreamReader(getClass().getResourceAsStream(templateId+".txt")));
        rc.setTemplateType(TemplateType.PLAINTEXT);
        rc.setDescription("Text 1");
        return rc;
    }
}
