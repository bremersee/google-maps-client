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

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bremersee.plain.model.UnknownAware;

/**
 * A result from a Geocoding API call.
 */
@JsonAutoDetect(
    fieldVisibility = Visibility.ANY,
    getterVisibility = Visibility.NONE,
    isGetterVisibility = Visibility.NONE,
    setterVisibility = Visibility.NONE)
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class GeocodingResult extends UnknownAware {

  /**
   * The separate address components in this result.
   */
  @JsonProperty("address_components")
  private List<AddressComponent> addressComponents;

  /**
   * The human-readable address of this location.
   *
   * <p>Often this address is equivalent to the "postal address," which sometimes differs from
   * country to country. (Note that some countries, such as the United Kingdom, do not allow
   * distribution of true postal addresses due to licensing restrictions.) This address is generally
   * composed of one or more address components. For example, the address "111 8th Avenue, New York,
   * NY" contains separate address components for "111" (the street number, "8th Avenue" (the
   * route), "New York" (the city) and "NY" (the US state). These address components contain
   * additional information.
   */
  @JsonProperty("formatted_address")
  private String formattedAddress;

  /**
   * All the localities contained in a postal code. This is only present when the result is a postal
   * code that contains multiple localities.
   */
  @JsonProperty("postcode_localities")
  private List<String> postcodeLocalities;

  /**
   * Location information for this result.
   */
  @JsonProperty("geometry")
  private Geometry geometry;

  /**
   * The types of the returned result. This array contains a set of zero or more tags identifying
   * the type of feature returned in the result. For example, a geocode of "Chicago" returns
   * "locality" which indicates that "Chicago" is a city, and also returns "political" which
   * indicates it is a political entity.
   */
  @JsonProperty("types")
  private List<AddressType> types;

  /**
   * Indicates that the geocoder did not return an exact match for the original request, though it
   * was able to match part of the requested address. You may wish to examine the original request
   * for misspellings and/or an incomplete address.
   *
   * <p>Partial matches most often occur for street addresses that do not exist within the locality
   * you pass in the request. Partial matches may also be returned when a request matches two or
   * more locations in the same locality. For example, "21 Henr St, Bristol, UK" will return a
   * partial match for both Henry Street and Henrietta Street. Note that if a request includes a
   * misspelled address component, the geocoding service may suggest an alternate address.
   * Suggestions triggered in this way will not be marked as a partial match.
   */
  @JsonProperty("partial_match")
  private boolean partialMatch;

  /**
   * A unique identifier for this place.
   */
  @JsonProperty("place_id")
  private String placeId;

  /**
   * The Plus Code identifier for this place.
   */
  @JsonProperty("plus_code")
  private PlusCode plusCode;

}
