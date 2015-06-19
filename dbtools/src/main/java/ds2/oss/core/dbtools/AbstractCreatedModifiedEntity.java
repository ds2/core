package ds2.oss.core.dbtools;

import ds2.oss.core.api.CreatedModifiedAware;
import ds2.oss.core.dbtools.modules.CreatedModifiedAwareModule;

import javax.persistence.Embedded;
import java.util.Date;

/**
 * Created by dstrauss on 18.06.15.
 */
public class AbstractCreatedModifiedEntity implements CreatedModifiedAware {
    @Embedded
    private CreatedModifiedAwareModule cma=new CreatedModifiedAwareModule();

    @Override
    public Date getCreated() {
        if(cma==null){
            cma=new CreatedModifiedAwareModule();
        }
        return cma.getCreated();
    }
    public void setCreated(Date date){
        if(cma==null){
            cma=new CreatedModifiedAwareModule();
        }
        cma.setCreated(date);
    }

    @Override
    public Date getModified() {
        if(cma==null){
            cma=new CreatedModifiedAwareModule();
        }
        return cma.getModified();
    }
    public void touchModified(){
        if(cma==null){
            cma=new CreatedModifiedAwareModule();
        }
        cma.touchModified();
    }
}
