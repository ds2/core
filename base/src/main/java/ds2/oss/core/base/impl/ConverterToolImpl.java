/*
 * Copyright 2020 DS/2 <dstrauss@ds-2.de>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package ds2.oss.core.base.impl;

import java.util.Date;

import javax.enterprise.context.ApplicationScoped;

import ds2.oss.core.api.ConverterTool;
import ds2.oss.core.statics.Convert;

/**
 * Implemenation of the converter.
 *
 * @author dstrauss
 * @version 0.3
 */
@ApplicationScoped
public class ConverterToolImpl implements ConverterTool {
    
    @Override
    public long getDateMillis(final Date d) {
        if (d == null) {
            throw new IllegalArgumentException("You must give a date to convert!");
        }
        return d.getTime();
    }
    
    @Override
    public Date toDate(final long ms) {
        return new Date(ms);
    }
    
    @Override
    public int toInt(final Object o, final int defValue) {
        return Convert.toInt(o, defValue);
    }
}
