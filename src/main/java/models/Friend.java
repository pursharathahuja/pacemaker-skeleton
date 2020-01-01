package models;

import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.UUID;

import static com.google.common.base.MoreObjects.toStringHelper;

public class Friend implements Serializable {

  public String id;
  public String useremail;
  public String friendemail;

  public Friend() {
  }

  public Friend(String useremail, String friendemail) {
    this.id = UUID.randomUUID().toString();
    this.useremail = useremail;
    this.friendemail = friendemail;
  }

  public String getId() {
    return id;
  }

  public String getFriendemail() {
    return friendemail;
  }

  public String getUseremail() {
    return useremail;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj instanceof Friend) {
      final Friend other = (Friend) obj;
      return Objects.equal(friendemail, other.friendemail)
          && Objects.equal(useremail, other.useremail);
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return toStringHelper(this).addValue(id)
        .addValue(friendemail)
        .addValue(useremail)
        .toString();
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.id, this.friendemail, this.useremail);
  }
}
