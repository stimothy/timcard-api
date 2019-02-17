package com.steventimothy.timcard.utils.mappers;

import com.steventimothy.timcard.schemas.exceptions.ForbiddenException;
import com.steventimothy.timcard.schemas.exceptions.InvalidDataException;
import com.steventimothy.timcard.schemas.exceptions.UnauthorizedException;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ExceptionMapperTest extends MappersBaseComponent {

//  /**
//   * Tests that a 400 is returned.
//   */
//  @Test
//  public void testInvalidDataException() {
//    ResponseEntity responseEntity = mapExceptionToResponse(new InvalidDataException("Invalid data."));
//    assertStatus(responseEntity, HttpStatus.BAD_REQUEST);
//  }
//
//  /**
//   * Tests that a 401 is returned.
//   */
//  @Test
//  public void testUnauthorizedException() {
//    ResponseEntity responseEntity = mapExceptionToResponse(new UnauthorizedException("Unauthorized."));
//    assertStatus(responseEntity, HttpStatus.UNAUTHORIZED);
//  }
//
//  /**
//   * Tests that a 403 is returned.
//   */
//  @Test
//  public void testForbiddenException() {
//    ResponseEntity responseEntity = mapExceptionToResponse(new ForbiddenException("Forbidden."));
//    assertStatus(responseEntity, HttpStatus.FORBIDDEN);
//  }
//
//  /**
//   * Tests that a 500 is returned.
//   */
//  @Test
//  public void testAnyOtherException() {
//    ResponseEntity responseEntity = mapExceptionToResponse(new Exception("My bad exception."));
//    assertStatus(responseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
//  }
}
