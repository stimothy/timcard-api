package com.steventimothy.timcard.utils.mappers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <h1>The ExceptionMapper Class</h1>
 * <p>This class is responsible for mapping an exception to a ResponseEntity.</p>
 */
@Slf4j
@Component
public class ExceptionMapper {

//  /**
//   * Maps an exception to a response entity.
//   *
//   * @param method    The HTTP method that was called.
//   * @param path      The path to that endpoint.
//   * @param sessionId The sessionId given.
//   * @param ex        The exception that was thrown.
//   * @return returns the appropriate status code for each exception. If nothing maps, it will throw
//   * a respond with a 500.
//   */
//  public ResponseEntity mapExceptionToResponse(String method, String path, String sessionId, Exception ex) {
//    if (InvalidDataException.class.equals(ex.getClass()) || DatabaseDataException.class.equals(ex.getClass())) {
//      log.warn("[400] {}: {} - sessionId={} - {}", method, path, sessionId, ex.getMessage());
//      return ResponseEntity.badRequest().build();
//    }
//    else if (UnauthorizedException.class.equals(ex.getClass())) {
//      log.warn("[401] {}: {} - sessionId={} - {}", method, path, sessionId, ex.getMessage());
//      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//    }
//    else if (ForbiddenException.class.equals(ex.getClass())) {
//      log.warn("[403] {}: {} - sessionId={} - {}", method, path, sessionId, ex.getMessage());
//      return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//    }
//    else {
//      log.warn("[500] {}: {} - sessionId={} - {}", method, path, sessionId, ex.getMessage());
//      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//    }
//  }
}
