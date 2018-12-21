/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bremersee.google.maps.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.springframework.util.StringUtils;

/**
 * The top level country code converter.
 *
 * @author Christian Bremer
 */
abstract class TopLevelCountryCodeConverter {

  private static final Map<String, String> exceptions;

  static {
    final HashMap<String, String> map = new HashMap<>();
    map.put("gb", "uk");
    exceptions = Collections.unmodifiableMap(map);
  }

  private TopLevelCountryCodeConverter() {
  }

  /**
   * Converts a {@link Locale} into a top level country code.
   *
   * @param locale the locale
   * @return the top level country code
   */
  static String fromLocale(Locale locale) {
    if (locale == null || !StringUtils.hasText(locale.getCountry())) {
      return null;
    }
    final String countryCode = locale.getCountry().toLowerCase();
    return exceptions.getOrDefault(countryCode, countryCode);
  }

}
