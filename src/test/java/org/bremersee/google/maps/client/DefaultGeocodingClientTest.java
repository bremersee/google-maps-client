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

import java.util.Locale;
import org.bremersee.google.maps.model.GeocodingRequest;
import org.bremersee.google.maps.model.GeocodingResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.StringUtils;

/**
 * Test of the default geocoding client.
 *
 * @author Christian Bremer
 */
public class DefaultGeocodingClientTest extends Setup {

  private static DefaultGeocodingClient geocodingClient;

  /**
   * Init test.
   */
  @BeforeClass
  public static void init() {
    setup();
    geocodingClient = new DefaultGeocodingClient(properties, objectMapper);
  }

  /**
   * Test geocode.
   *
   * @throws Exception the exception
   */
  @Test
  public void testGeocode() throws Exception {
    if (!StringUtils.hasText(properties.getKey())) {
      return;
    }

    final GeocodingRequest req = GeocodingRequest
        .builder()
        .language(Locale.GERMANY)
        .query("Hauptstraße 56, Peine")
        .build();

    final GeocodingResponse res = geocodingClient.geocode(req);
    Assert.assertNotNull(res);

    System.out.println("### Geocoding response for query 'Hauptstraße 56, Peine':");
    System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(res));

    /*
    Assert.assertTrue(res.hasResults());
    Assert.assertTrue(
        res.getResults().stream().anyMatch(geocodeResult -> geocodeResult.getAddress() != null
            && "31228".equalsIgnoreCase(geocodeResult.getAddress().getPostalCode())
            && geocodeResult.getAddress().getStreetName() != null
            && geocodeResult.getAddress().getStreetName().contains("Hauptstraße")
            && "56".equalsIgnoreCase(geocodeResult.getAddress().getStreetNumber())));
            */
  }

}
