package com.steventimothy.timcard.schemas.ids.sessions;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.steventimothy.timcard.schemas.ids.Id;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * <h1>The SessionId Class</h1>
 * <p>This class holds the id for a Session.</p>
 */
@JsonTypeName("sessionId")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = GeneralSessionId.class, name = "generalSessionId"),
    @JsonSubTypes.Type(value = UserSessionId.class, name = "userSessionId"),
    @JsonSubTypes.Type(value = AdminSessionId.class, name = "adminSessionId"),
})
@NoArgsConstructor
public abstract class SessionId implements Id {

  /**
   * The raw id of a session.
   */
  protected UUID rawId;


  /**
   * The constructor that sets the sessionId.
   * @param rawId The id of the session.
   */
  protected SessionId(UUID rawId) {
    this.rawId = rawId;
  }
}
