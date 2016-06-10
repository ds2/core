/*
 * Copyright 2012-2015 Dirk Strauss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ds2.oss.core.statics;

import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import com.sun.tools.javac.jvm.Pool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.management.ConnectorAddressLink;

import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.List;

/**
 * Created by deindesign on 21.12.15.
 */
public interface Methods {
    Logger LOG= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    static Number parseNumber(String s){
        Number rc=null;
        if(!isBlank(s)){
            try {
                Double d = Double.valueOf(s);
                if(d.longValue()==d){
                    rc=Long.valueOf(s);
                } else if(d.intValue()==d){
                    rc=Integer.valueOf(s);
                } else {
                    rc = d;
                }
            } catch(NumberFormatException e){
                //silently ignored
            }
        }
        return rc;
    }
    /**
     * Returns the first element from a given collection, or the default value.
     * @param c the collection
     * @param def the default value
     * @param <E> the type
     * @param <V> the value type
     * @return the first element of the collection, or null
     */
    static <E,V> E getNullOrElementFromCollection(Collection<E> c, E def){
        E rc=def;
        if(c!=null){
            rc=c.iterator().next();
        }
        return rc;
    }

    /**
     * Returns the first non-null element from the given sequence of items.
     * @param e the items
     * @param <E> the type
     * @return the first non-null element, or null
     */
    static <E> E getFirstNonNullElement(E...e){
        E rc=null;
        for(E eItem : e){
            if(eItem!=null){
                rc=eItem;
                break;
            }
        }
        return rc;
    }

    static <E extends Enum<E>> E findEnumValueByName(Class<E> enumClass, String name, E defValue){
        E rc=defValue;
        try {
            rc = Enum.valueOf(enumClass, name);
        } catch(IllegalArgumentException | NullPointerException e){
            //ok, maybe lowercase/uppercase problem
            for(E e1 : enumClass.getEnumConstants()){
                if(e1.name().equalsIgnoreCase(name)){
                    rc=e1;
                    break;
                }
            }
        }
        return rc;
    }

    static <E extends Enum<E>> E findEnumValueByName(Class<E> enumClass, String name){
        E rc=null;
        try {
            rc = Enum.valueOf(enumClass, name);
        } catch(IllegalArgumentException | NullPointerException e){
            //ok, maybe lowercase/uppercase problem
            for(E e1 : enumClass.getEnumConstants()){
                if(e1.name().equalsIgnoreCase(name)){
                    rc=e1;
                    break;
                }
            }
        }
        return rc;
    }

    /**
     * Checks if the given string contains some value.
     * @param s the string to check
     * @return TRUE if not blank, otherwise FALSE.
     * @deprecated Better use {@link #isBlank(String)} instead.
     */
    @Deprecated
    static boolean isNotBlank(String s){
        return s!=null&&s.trim().length()>0;
    }
    /**
     * Checks if the given string contains some value.
     * @param s the string to check
     * @return TRUE if not blank, otherwise FALSE.
     */
    static boolean isBlank(String s){
        return s==null||s.trim().length()<=0;
    }

    static JMXServiceURL getJmxServiceUrlByLocalPid(int pid){
        JMXServiceURL rc=null;
        List<VirtualMachineDescriptor> vms=VirtualMachine.list();
        for(VirtualMachineDescriptor vmd : vms){
            VirtualMachine vm=null;
            try {
                vm=VirtualMachine.attach(vmd);
                //VirtualMachine vm2=VirtualMachine.attach(""+pid);
            } catch (AttachNotSupportedException e) {
                LOG.debug("Cannot attach to this VM!", e);
            } catch (IOException e) {
                LOG.debug("IO error when trying to attach!", e);
            }
            if(vm==null){
                continue;
            }
            String connectorProp;
            try {
                LOG.debug("VMs properties: {}", vm.getSystemProperties());
                connectorProp = vm.getSystemProperties().getProperty("com.sun.management.jmxremote.localConnectorAddress");
                if(isBlank(connectorProp)){
                    connectorProp=ConnectorAddressLink.importFrom(Converts.toInt(vmd.id().trim(),0));
                }
                if(!isBlank(connectorProp)){
                    LOG.debug("Found url: {}", connectorProp);
                    rc=new JMXServiceURL(connectorProp);
                }
            } catch (IOException e) {
                LOG.debug("IO error when using the connection!");
            }
        }
        return rc;
    }
}
