package models;

import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.UUID;

import static com.google.common.base.MoreObjects.toStringHelper;

public class Message implements Serializable {

  public String id;
  public String from;
  public String to;
  public String message;

  public Message() {
  }

  public Message(String from, String to, String message) {
    this.id = UUID.randomUUID().toString();
    this.from = from;
    this.to = to;
    this.message = message;
  }

  public String getId() {
    return id;
  }

  public String getFrom() {
    return from;
  }

  public String getTo() {
    return to;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj instanceof Message) {
      final Message other = (Message) obj;
      return Objects.equal(from, other.from)
          && Objects.equal(to, other.to)
          && Objects.equal(message, other.message);
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return toStringHelper(this).addValue(id)
        .addValue(from)
        .addValue(to)
        .addValue(message)
        .toString();
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.id, this.from, this.to, this.message);
  }
}
