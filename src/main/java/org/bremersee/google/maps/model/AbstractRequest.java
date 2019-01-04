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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

/**
 * The abstract request.
 *
 * @author Christian Bremer
 */
abstract class AbstractRequest {

  /**
   * Build the request parameter map.
   *
   * @param urlEncode if {@code true}, the parameter values will be encoded, otherwise not.
   * @return the request parameter map
   */
  public abstract MultiValueMap<String, String> buildParameters(final boolean urlEncode);

  /**
   * Encode query parameter.
   *
   * @param value  the parameter value
   * @param encode if {@code true} the parameter will be url encoded, otherwise not
   * @return the (encoded) parameter value
   */
  String encodeQueryParameter(final String value, final boolean encode) {
    if (!StringUtils.hasText(value)) {
      return "";
    }
    if (encode) {
      try {
        return URLEncoder.encode(value, StandardCharsets.UTF_8.name());

      } catch (UnsupportedEncodingException e) {
        // ignore
      }
    }
    return value;
  }

}
