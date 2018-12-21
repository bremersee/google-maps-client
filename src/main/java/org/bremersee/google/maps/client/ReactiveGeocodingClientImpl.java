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

import org.bremersee.google.maps.GoogleMapsProperties;
import org.bremersee.google.maps.model.GeocodingRequest;
import org.bremersee.google.maps.model.GeocodingResponse;
import org.bremersee.google.maps.model.GeocodingResult;
import org.bremersee.web.ErrorDetectors;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import reactor.core.publisher.Flux;

/**
 * @author Christian Bremer
 */
public class ReactiveGeocodingClientImpl extends AbstractReactiveClient
    implements ReactiveGeocodingClient {

  /**
   * Instantiates a new abstract client.
   *
   * @param properties the properties
   */
  public ReactiveGeocodingClientImpl(GoogleMapsProperties properties) {
    super(properties,
        null,
        new ReactiveErrorDecoder());
  }

  /**
   * Instantiates a new abstract client.
   *
   * @param properties the properties
   * @param webClientBuilder the web client builder
   */
  public ReactiveGeocodingClientImpl(
      GoogleMapsProperties properties,
      Builder webClientBuilder) {
    super(properties,
        webClientBuilder,
        new ReactiveErrorDecoder());
  }

  @Override
  public Flux<GeocodingResult> geocode(GeocodingRequest request) {
    final String baseUri = getProperties().getGeocodeUri();
    final MultiValueMap<String, String> params = request.buildParameters(true);
    if (StringUtils.hasText(getProperties().getKey())) {
      params.set("key", getProperties().getKey());
    }
    return getWebClientBuilder()
        .baseUrl(baseUri)
        .build()
        .get()
        .uri(uriBuilder -> uriBuilder.queryParams(params).build())
        .header("User-Agent", getProperties().getUserAgent())
        .retrieve()
        .onStatus(ErrorDetectors.DEFAULT, getWebClientErrorDecoder())
        .bodyToMono(GeocodingResponse.class)
        .flatMapIterable(this::parseGeocodingResponse);
  }

}
