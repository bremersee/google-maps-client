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

import java.util.Locale;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

/**
 * The geocoding request.
 *
 * @author Christian Bremer
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@Builder
public class GeocodingRequest extends AbstractRequest {

  /**
   * The bounding box of the viewport within which to bias geocode results more prominently. This
   * parameter will only influence, not fully restrict, results from the geocoder.
   */
  private Bounds bounds;

  /**
   * The language in which to return results, see
   * <a href="https://developers.google.com/maps/faq#languagesupport">Supported languages</a>.
   */
  private Locale language;

  /**
   * The region code, specified as a ccTLD ("top-level domain") two-character value. This parameter
   * will only influence, not fully restrict, results from the geocoder.
   */
  private Locale region;

  /**
   * The street address that you want to geocode, in the format used by the national postal service
   * of the country concerned. Additional address elements such as business names and unit, suite or
   * floor numbers should be avoided.
   */
  private String query;

  private String postalCode;

  /**
   * A country name or a two letter ISO 3166-1 country code.
   */
  private String country;

  private String route;

  private String locality;

  private String administrativeArea;

  @Override
  public MultiValueMap<String, String> buildParameters(final boolean urlEncode) {
    final MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    if (bounds != null && bounds.getNortheast() != null && bounds.getSouthwest() != null) {
      map.set("bounds", bounds.getSouthwest() + "|" + bounds.getNortheast());
    }
    if (language != null && StringUtils.hasText(language.getLanguage())) {
      map.set("language", language.getLanguage());
    }
    if (TopLevelCountryCodeConverter.fromLocale(region) != null) {
      map.set("region", TopLevelCountryCodeConverter.fromLocale(region));
    }
    if (StringUtils.hasText(query)) {
      map.set("address", encodeQueryParameter(query, urlEncode));
    }
    if (StringUtils.hasText(postalCode)
        || StringUtils.hasText(country)
        || StringUtils.hasText(route)
        || StringUtils.hasText(locality)
        || StringUtils.hasText(administrativeArea)) {
      final StringBuilder sb = new StringBuilder();
      if (StringUtils.hasText(postalCode)) {
        sb.append("postal_code:").append(postalCode);
      }
      if (StringUtils.hasText(country)) {
        if (sb.length() > 0) {
          sb.append('|');
        }
        sb.append("country:").append(country);
      }
      if (StringUtils.hasText(route)) {
        if (sb.length() > 0) {
          sb.append('|');
        }
        sb.append("route:").append(route);
      }
      if (StringUtils.hasText(locality)) {
        if (sb.length() > 0) {
          sb.append('|');
        }
        sb.append("locality:").append(locality);
      }
      if (StringUtils.hasText(administrativeArea)) {
        if (sb.length() > 0) {
          sb.append('|');
        }
        sb.append("administrative_area:").append(administrativeArea);
      }
      map.set("components", encodeQueryParameter(sb.toString(), urlEncode));
    }
    return map;
  }

}
