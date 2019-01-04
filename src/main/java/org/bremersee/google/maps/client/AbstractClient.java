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
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.bremersee.exception.ServiceException;
import org.bremersee.google.maps.GoogleMapsProperties;
import org.bremersee.google.maps.exception.ErrorCodeConstants;
import org.bremersee.google.maps.model.GeocodingResponse;
import org.bremersee.google.maps.model.GeocodingResult;
import org.bremersee.google.maps.model.GeocodingStatus;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

/**
 * The abstract client.
 *
 * @author Christian Bremer
 */
@SuppressWarnings("WeakerAccess")
public class AbstractClient {

  @Getter(AccessLevel.PROTECTED)
  private GoogleMapsProperties properties;

  @Setter(AccessLevel.PROTECTED)
  private ObjectMapper objectMapper;

  /**
   * Instantiates a new abstract client.
   *
   * @param properties the properties
   */
  protected AbstractClient(GoogleMapsProperties properties) {
    this.properties = properties;
  }

  /**
   * Gets object mapper.
   *
   * @return the object mapper
   */
  protected ObjectMapper getObjectMapper() {
    if (objectMapper == null) {
      objectMapper = new ObjectMapper();
      objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
      objectMapper.enable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID);
      objectMapper.registerModules(
          new Jdk8Module(),
          new JavaTimeModule(),
          new ParameterNamesModule());
    }
    return objectMapper;
  }

  /**
   * Build the request url.
   *
   * @param baseUri the base uri
   * @param params  the params
   * @return the url
   */
  protected URL buildUrl(final String baseUri, final MultiValueMap<String, String> params) {
    if (StringUtils.hasText(getProperties().getKey())) {
      params.set("key", getProperties().getKey());
    }
    boolean baseUriContainsQuery = baseUri.contains("?");
    final StringBuilder urlBuilder = new StringBuilder();
    urlBuilder.append(baseUri);
    for (String key : params.keySet()) {
      if (baseUriContainsQuery) {
        urlBuilder.append('&');
      } else {
        urlBuilder.append('?');
        baseUriContainsQuery = true;
      }
      urlBuilder.append(key).append('=').append(params.getFirst(key));
    }
    try {
      return new URL(urlBuilder.toString());

    } catch (final MalformedURLException e) {
      throw new ServiceException(
          HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodeConstants.MALFORMED_URL, e);
    }
  }

  /**
   * Parse geocoding response.
   *
   * @param response the response
   * @return the geocoding result list
   * @throws ServiceException if the status is not {@link GeocodingStatus#OK}
   *                          or not {@link GeocodingStatus#ZERO_RESULTS}.
   */
  protected List<GeocodingResult> parseGeocodingResponse(final GeocodingResponse response) {
    final GeocodingStatus status = response.getStatus();
    switch (status) {
      case OK:
      case ZERO_RESULTS:
        return response.getResults();
      default:

        throw new ServiceException(
            500,
            response.getStatus().getMessage(),
            "GOOGLE_MAPS_CLIENT:" + response.getStatus().name());
    }
  }
}
