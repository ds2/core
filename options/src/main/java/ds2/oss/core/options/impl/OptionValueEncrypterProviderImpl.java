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
package ds2.oss.core.options.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import ds2.oss.core.api.options.ForValueType;
import ds2.oss.core.api.options.ValueType;
import ds2.oss.core.options.api.OptionValueEncrypter;
import ds2.oss.core.options.api.OptionValueEncrypterProvider;

/**
 * The enc provider.
 * 
 * @author dstrauss
 * @version 0.3
 *
 */
@ApplicationScoped
public class OptionValueEncrypterProviderImpl implements
		OptionValueEncrypterProvider {
	/**
	 * The string encrypter.
	 */
	@Inject
	@ForValueType(ValueType.STRING)
	private OptionValueEncrypter<String> stringEnc;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ds2.oss.core.options.api.OptionValueEncrypterProvider#getForValueType
	 * (ds2.oss.core.api.options .ValueType, java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <V> OptionValueEncrypter<V> getForValueType(ValueType t) {
		OptionValueEncrypter<V> rc = null;
		if (t != null) {
			switch (t) {
			case STRING:
				rc = (OptionValueEncrypter<V>) stringEnc;
				break;
			default:
				// nothing special to do
				break;
			}
		}
		return rc;
	}

}
