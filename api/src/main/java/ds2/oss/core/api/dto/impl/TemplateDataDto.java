package ds2.oss.core.api.dto.impl;

import ds2.oss.core.api.templating.TemplateData;
import ds2.oss.core.api.templating.TemplateType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Reader;

@Getter
@Setter
@ToString(of = {"templateType", "description"})
public class TemplateDataDto implements TemplateData {
    private Reader contentStream;
    private TemplateType templateType;
    private String description;
}
