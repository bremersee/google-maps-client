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

package org.bremersee.google.maps.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URL;
import java.util.List;
import org.bremersee.google.maps.GoogleMapsProperties;
import org.bremersee.google.maps.model.GeocodingRequest;
import org.bremersee.google.maps.model.GeocodingResponse;
import org.bremersee.google.maps.model.GeocodingResult;
import org.springframework.util.MultiValueMap;

/**
 * @author Christian Bremer
 */
public class DefaultGeocodingClient extends AbstractDefaultClient
    implements TraditionalGeocodingClient {

  /**
   * Instantiates a new abstract client.
   *
   * @param properties the properties
   */
  public DefaultGeocodingClient(GoogleMapsProperties properties) {
    super(properties);
  }

  /**
   * Instantiates a new default geocoding client.
   *
   * @param properties the properties
   * @param objectMapper the object mapper
   */
  public DefaultGeocodingClient(
      GoogleMapsProperties properties,
      ObjectMapper objectMapper) {
    this(properties);
    setObjectMapper(objectMapper);
  }

  @Override
  public List<GeocodingResult> geocode(final GeocodingRequest request) {

    final String baseUri = getProperties().getGeocodeUri();
    final MultiValueMap<String, String> params = request.buildParameters(true);
    final URL url = buildUrl(baseUri, params);
    final GeocodingResponse response = call(
        url,
        HttpMethod.GET,
        null,
        GeocodingResponse.class);
    return parseGeocodingResponse(response);
  }

}
