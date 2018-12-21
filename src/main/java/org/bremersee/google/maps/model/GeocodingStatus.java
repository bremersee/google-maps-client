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

import lombok.Getter;

/**
 * The geocoding status.
 *
 * @author Christian Bremer
 */
public enum GeocodingStatus {

  /**
   * Indicates that no errors occurred; the address was successfully parsed and at least one geocode
   * was returned.
   */
  OK("OK"),

  /**
   * Indicates that the geocode was successful but returned no results. This may occur if the
   * geocoder was passed a non-existent address.
   */
  ZERO_RESULTS("No results."),

  /**
   * Indicates any of the following:
   * <ul>
   * <li>The API key is missing or invalid.
   * <li>Billing has not been enabled on your account.
   * <li>A self-imposed usage cap has been exceeded.
   * <li>The provided method of payment is no longer valid (for example, a credit card has
   * expired).
   * </ul>
   */
  OVER_DAILY_LIMIT("The API key is missing, "
      + "billing has not been enabled on your account, "
      + "a self-imposed usage cap has been exceeded or "
      + "the provided method of payment is no longer valid."),

  /**
   * Indicates that you are over your quota.
   */
  OVER_QUERY_LIMIT("You are over your quota."),

  /**
   * Indicates that your request was denied.
   */
  REQUEST_DENIED("Your request was denied."),

  /**
   * Generally indicates that the query (address, components or latlng) is missing.
   */
  INVALID_REQUEST("The query (address, components or latlng) is missing."),

  /**
   * Indicates that the request could not be processed due to a server error. The request may
   * succeed if you try again.
   */
  UNKNOWN_ERROR("The request could not be processed due to a server error.");

  @Getter
  private String message;

  GeocodingStatus(String message) {
    this.message = message;
  }}
