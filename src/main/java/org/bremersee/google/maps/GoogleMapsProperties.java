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

package org.bremersee.google.maps;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The nominatim properties.
 *
 * @author Christian Bremer
 */
@ToString
@EqualsAndHashCode
@SuppressWarnings("WeakerAccess")
public class GoogleMapsProperties {

  public static final String DEFAULT_SEARCH_URI = "https://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&key=";

  public static final String DEFAULT_REVERSE_URI = "https://nominatim.openstreetmap.org/reverse";

  public static final String DEFAULT_GEOCODE_URI = "https://maps.googleapis.com/maps/api/geocode/json";

  @Getter
  @Setter
  private String key;

  @Getter
  @Setter
  private String geocodeUri = DEFAULT_GEOCODE_URI;

  @Getter
  @Setter
  private String searchUri = DEFAULT_SEARCH_URI;

  @Getter
  @Setter
  private String reverseUri = DEFAULT_REVERSE_URI;

  @Getter
  @Setter
  private String userAgent = "curl/7.54.0";

}
